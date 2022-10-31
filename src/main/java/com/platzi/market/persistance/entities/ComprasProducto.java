package com.platzi.market.persistance.entities;

import javax.persistence.*;

@Entity
@Table(name = "compras_productos")
public class ComprasProducto {
    //EmbeddedId para denotar llaves primarias compuestas
    @EmbeddedId
    private ComprasProductoPK id;

    private Integer cantidad;

    private Double total;

    private Boolean estado;

    @ManyToOne
    //Cuando queremos guardar en cascada debemos poner la anotación @MapsId
    //porque esta anotación es la que proporciona la asignación para una clave primaria cuando se usa @EmbeddedId.
    @MapsId("idCompra")
    //!insertable !updatable -> Todas las relaciones ManyToOne deben llevar estos atributos
    @JoinColumn(name = "id_compra", insertable = false, updatable = false)
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Producto producto;

    public ComprasProductoPK getId() {
        return id;
    }

    public void setId(ComprasProductoPK id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
