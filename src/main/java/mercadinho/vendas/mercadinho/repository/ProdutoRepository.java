package mercadinho.vendas.mercadinho.repository;

import mercadinho.vendas.mercadinho.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query(value = "select sum(unidade) from produto;", nativeQuery = true)
    Integer buscarQuantidade();


    @Query(value = "select * from produto where preco = :valor ", nativeQuery = true)
    List<Produto> buscarPorValor(@Param("valor") Double valor);
    @Query(value = "select * from produto p where p.data_cadastro between :dataInicio and :dataFim", nativeQuery = true)
    List<Produto> buscarPorData(@Param("dataInicio") String dataInicio, @Param("dataFim") String dataFim);
}
