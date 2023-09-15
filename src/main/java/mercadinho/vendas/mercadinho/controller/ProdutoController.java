package mercadinho.vendas.mercadinho.controller;

import jakarta.validation.Valid;
import mercadinho.vendas.mercadinho.dtos.BaseResponseDTO;
import mercadinho.vendas.mercadinho.dtos.ProdutoRequestDTO;
import mercadinho.vendas.mercadinho.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/produto")
public class ProdutoController {


    @Autowired
    private ProdutoService service;


    @PostMapping
    public ResponseEntity<BaseResponseDTO> salvar(@Valid @RequestBody ProdutoRequestDTO produtoRequestDTO) {
        return BaseController.ok(service.salvarProduto(produtoRequestDTO));
    }

    @GetMapping("/todos")
    public ResponseEntity<BaseResponseDTO> listarProdutos() {
        return BaseController.ok(service.listarProdutos());
    }

    @GetMapping("/id")
    public ResponseEntity<BaseResponseDTO> pegarProduto(@RequestParam Long id) {
        return BaseController.ok(service.pegarProdudo(id));
    }

    @DeleteMapping
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<BaseResponseDTO> deletarUmProduto(@RequestParam Long id) {
        try {
            return BaseController.ok(service.apagarProduto(id));
        } catch (RuntimeException e) {
            return BaseController.error(e.getMessage());
        }


    }
}




