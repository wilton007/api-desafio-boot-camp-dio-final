package mercadinho.vendas.mercadinho.service;


import mercadinho.vendas.mercadinho.dtos.ProdutoRequestDTO;
import mercadinho.vendas.mercadinho.dtos.ProdutoResponseDTO;
import mercadinho.vendas.mercadinho.mapper.ProdutoMapper;
import mercadinho.vendas.mercadinho.model.Produto;
import mercadinho.vendas.mercadinho.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;



@Service
public class ProdutoService {


    @Autowired
    ProdutoRepository repository;


    private static final Logger log = LoggerFactory.getLogger(ProdutoService.class);
    @Autowired
    private ProdutoMapper mapper;

    public ProdutoResponseDTO salvarProduto(ProdutoRequestDTO produtoDTO) {
        Produto produto = mapper.toProduto(produtoDTO);
        produto.setDataCadastro(LocalDate.now());
        repository.save(produto);
        log.info("produto {} salvo", produtoDTO.getNome());

        return mapper.toProdutoResponseDTO(produto);
    }

    public List<ProdutoResponseDTO> listarProdutos() throws RuntimeException {
        validarProdutoCadastrado(repository.findAll());
        log.info("obtido lista de produtos");
        return mapper.toListProdutoResponseDTO(repository.findAll());
    }
    public ProdutoResponseDTO pegarProdudo(Long id) {
        log.info("obtido produto por id [{}]", id);

        return mapper.toProdutoResponseDTO(repository.getReferenceById(id));
    }

    public String apagarProduto(Long id) throws RuntimeException {
        existePoduto(id);
        repository.deleteById(id);
        log.info("produto apagado por id [{}]",id);
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


    public List<ProdutoResponseDTO> buscarPorData(String dataInicio, String dataFim) {
        String dataf;
        dataf = validarDatasESempreRetornaDataFim(dataInicio, dataFim);
        log.info("obtido lista de produtos por data");

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

    public ProdutoResponseDTO editarProduto(Long id, ProdutoRequestDTO produtoRequestDTO) {
        return mapper.toProdutoResponseDTO(editarCamposProduto(repository.getReferenceById(id), produtoRequestDTO));
    }


    private Produto editarCamposProduto(Produto produto, ProdutoRequestDTO produtoRequestDTO) {
        String unidade = String.valueOf(produtoRequestDTO.getUnidade());

        if (!(produtoRequestDTO.getNome() == null)) {
            produto.setNome(produtoRequestDTO.getNome());
        }
        if (produtoRequestDTO.getPreco() != null) {
            produto.setPreco(produtoRequestDTO.getPreco());
        }
        if (unidade.isEmpty()) {
            produto.setUnidade(produtoRequestDTO.getUnidade());
        }

        repository.save(produto);
        log.info("produto [{}] editado", produto.getNome());
        return produto;
    }

}

