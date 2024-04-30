package application.model;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "listacompras")
public class ListaCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(nullable = false)
    private Calendar dataCompra;

    @ManyToOne
    @JoinColumn(name = "id_compra_possui_produto", nullable = false)
    private CompraPossuiProduto compraPossiProduto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Calendar dataCompra) {
        this.dataCompra = dataCompra;
    }

    public CompraPossuiProduto getCompraPossiProduto() {
        return compraPossiProduto;
    }

    public void setCompraPossiProduto(CompraPossuiProduto compraPossiProduto) {
        this.compraPossiProduto = compraPossiProduto;
    }
    
}
