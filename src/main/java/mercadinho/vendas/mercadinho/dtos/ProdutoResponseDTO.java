package mercadinho.vendas.mercadinho.dtos;



public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private int unidade;
    private Double preco;


    public ProdutoResponseDTO() {
    }

    public ProdutoResponseDTO(Long id, String nome, int unidade, Double preco) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
