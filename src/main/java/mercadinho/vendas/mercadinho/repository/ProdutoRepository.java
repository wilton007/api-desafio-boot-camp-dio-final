package mercadinho.vendas.mercadinho.repository;

import mercadinho.vendas.mercadinho.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
