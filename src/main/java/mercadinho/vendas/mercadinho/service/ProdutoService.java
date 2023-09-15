package mercadinho.vendas.mercadinho.service;

import mercadinho.vendas.mercadinho.controller.BaseController;
import mercadinho.vendas.mercadinho.dtos.ProdutoRequestDTO;
import mercadinho.vendas.mercadinho.dtos.ProdutoResponseDTO;
import mercadinho.vendas.mercadinho.mapper.ProdutoMapper;
import mercadinho.vendas.mercadinho.model.Produto;
import mercadinho.vendas.mercadinho.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<ProdutoResponseDTO> listarProdutos() {
        return mapper.toListProdutoResponseDTO(repository.findAll());
    }

    public ProdutoResponseDTO pegarProdudo(Long id) {
        return mapper.toProdutoResponseDTO(repository.getReferenceById(id));
    }

    public String apagarProduto(Long id)  throws RuntimeException {
            existePoduto(id);
            repository.deleteById(id);
            return "Deletado com Sucesso!";
    }

    private void existePoduto(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Produto não cadastrado");
        }
    }

}

