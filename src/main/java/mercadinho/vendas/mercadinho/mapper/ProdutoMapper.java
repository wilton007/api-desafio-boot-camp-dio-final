package mercadinho.vendas.mercadinho.mapper;


import mercadinho.vendas.mercadinho.dtos.ProdutoRequestDTO;
import mercadinho.vendas.mercadinho.dtos.ProdutoResponseDTO;
import mercadinho.vendas.mercadinho.model.Produto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    Produto toProduto(ProdutoRequestDTO produtoDTO);
    ProdutoResponseDTO toProdutoResponseDTO(Produto produto);

    List<ProdutoResponseDTO> toListProdutoResponseDTO(List<Produto> list);
}
