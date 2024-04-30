package application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private String marca;

    @ManyToOne
    @JoinColumn(name = "id_compra_possui_produto", nullable = false)
    private CompraPossuiProduto compraPossiProduto;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public CompraPossuiProduto getCompraPossiProduto() {
        return compraPossiProduto;
    }
    public void setCompraPossiProduto(CompraPossuiProduto compraPossiProduto) {
        this.compraPossiProduto = compraPossiProduto;
    }
    
}
