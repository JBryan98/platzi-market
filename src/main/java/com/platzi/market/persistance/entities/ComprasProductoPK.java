package com.platzi.market.persistance.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/*Añadimos Embeddable porque luego esta clase la vamos a embedear dentro de la clase producto*/
@Embeddable
public class ComprasProductoPK implements Serializable {
    @Column(name = "id_compra")
    private Integer idCompra;

    @Column(name = "id_producto")
    private Integer idProducto;

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
}
