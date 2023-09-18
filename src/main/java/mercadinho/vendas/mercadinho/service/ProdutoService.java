package mercadinho.vendas.mercadinho.service;

import mercadinho.vendas.mercadinho.dtos.ProdutoRequestDTO;
import mercadinho.vendas.mercadinho.dtos.ProdutoResponseDTO;
import mercadinho.vendas.mercadinho.mapper.ProdutoMapper;
import mercadinho.vendas.mercadinho.model.Produto;
import mercadinho.vendas.mercadinho.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;


@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository repository;

    @Autowired
    private ProdutoMapper mapper;

    public ProdutoResponseDTO salvarProduto(ProdutoRequestDTO produtoDTO) {
        Produto produto = mapper.toProduto(produtoDTO);
        produto.setDataCadastro(LocalDate.now());
        repository.save(produto);
        return mapper.toProdutoResponseDTO(produto);
    }

    public List<ProdutoResponseDTO> listarProdutos() throws RuntimeException {
        validarProdutoCadastrado(repository.findAll());
        return mapper.toListProdutoResponseDTO(repository.findAll());
    }

    public ProdutoResponseDTO pegarProdudo(Long id) {
        return mapper.toProdutoResponseDTO(repository.getReferenceById(id));
    }

    public String apagarProduto(Long id) throws RuntimeException {
        existePoduto(id);
        repository.deleteById(id);
        return "Deletado com Sucesso!";
    }

    private void existePoduto(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Produto não cadastrado");
        }
    }

    private void validarProdutoCadastrado(List<Produto> list) {
        if (list.isEmpty()) {
            throw new RuntimeException("Não existe Produto cadastrado");
        }
    }

    public Integer quantidadeProduto() {
        return repository.buscarQuantidade();
    }

    public List<ProdutoResponseDTO> getByPreco(Double preco) {
        List<Produto> response = repository.buscarPorValor(preco);
        return mapper.toListProdutoResponseDTO(response);
    }

    public List<ProdutoResponseDTO> buscarPorData(String dataInicio, String dataFim) {
        String dataf;
        dataf = validarDatasESempreRetornaDataFim(dataInicio, dataFim);


        return mapper.toListProdutoResponseDTO(repository.buscarPorData(dataInicio, dataf));
    }

    private String validarDatasESempreRetornaDataFim(String dataInicio, String dataFim) {
        LocalDate testarInicio;
        LocalDate testarFim;
        if (dataInicio.isBlank()) {
            throw new RuntimeException("Data inicio não pode vazia");
        } else if (dataFim.isEmpty()) {
            testarInicio = converterData(dataInicio);
            testarFim = LocalDate.now();
            dataFim = LocalDate.now().toString();

            if (testarInicio.isAfter(LocalDate.now())) {
                throw new RuntimeException("Data inicio não pode ser maior que a data atual");
            }

            return dataFim;
        } else {
            testarInicio = converterData(dataInicio);
            testarFim = converterData(dataFim);
        }

        if (testarFim.isBefore(testarInicio)) {
            throw new RuntimeException("Data fim Não pode ser menor que que Data Inicio");
        } else if (testarInicio.isAfter(LocalDate.now())) {
            throw new RuntimeException("Data inicio não pode ser maior que a data atual");
        }
        return dataFim;
    }

    private static LocalDate converterData(String data) {
        try {
            return LocalDate.parse(data);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Formato da data [" + data + "] invalido!");
        }
    }

}

