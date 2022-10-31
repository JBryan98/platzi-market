package com.platzi.market.persistance.mapper;

import com.platzi.market.domain.Product;
import com.platzi.market.persistance.entities.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

//Enviamos CategoryMapper como parametro mediante uses
@Component
@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {

    //Mapeando de Producto a Product
    @Mappings({
            @Mapping(source = "idProducto", target = "productId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "precioVenta", target = "price"),
            @Mapping(source = "cantidadStock", target = "stock"),
            @Mapping(source = "estado", target = "active"),
            //Dado que category tiene su propio mapper, debemos mandarlo como parametro en el Mapping
            @Mapping(source ="categoria", target = "category")
    })
    Product toProduct(Producto producto);
    //Podemos crear otro metodo para poder traducir multples Productos en Products
    List<Product> toProducts(List<Producto> productos);

    //Traducción inversa, de Product a Producto

    @InheritInverseConfiguration
    //Como estamos obviando codigoBarras, debemos indicarle al Mapper que se ignorara este atributo
    @Mapping(target = "codigoBarras", ignore = true)
    Producto toProducto(Product product);

}
