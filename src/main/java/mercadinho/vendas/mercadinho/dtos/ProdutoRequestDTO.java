package mercadinho.vendas.mercadinho.dtos;


import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

public class ProdutoRequestDTO {


    @NotBlank
    private String nome;

    @NotNull
    private int unidade;

    @NotNull
    private Double preco;

    public ProdutoRequestDTO() {
    }

    public ProdutoRequestDTO(String nome, int unidade, Double preco) {
        this.nome = nome;
        this.unidade = unidade;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getUnidade() {
        return unidade;
    }

    public void setUnidade(int unidade) {
        this.unidade = unidade;
    }
}
