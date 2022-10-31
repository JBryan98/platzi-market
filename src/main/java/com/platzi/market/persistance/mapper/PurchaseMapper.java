package com.platzi.market.persistance.mapper;

import com.platzi.market.domain.Purchase;
import com.platzi.market.persistance.entities.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

//Referenciamos PurchaseItemMapper porque a traves de la compra referenciaremos todos sus productos
@Component
@Mapper(componentModel = "spring", uses={PurchaseItemMapper.class})
public interface PurchaseMapper {
    //Traduciendo de Compra a Purchasse
    @Mappings({
            @Mapping(source = "idCompra", target = "purchaseId"),
            @Mapping(source = "idCliente", target = "clientId"),
            @Mapping(source = "fecha", target = "date"),
            @Mapping(source = "medioPago", target = "paymentMethod"),
            @Mapping(source = "comentario", target = "comment"),
            @Mapping(source = "estado", target = "state"),
            //Este mapping es el que utiliza PurchaseItemMapper para luego convertir los productos uno a uno
            @Mapping(source = "productos", target="items")
    })
    Purchase toPurchase(Compra compra);
    //Tambien recibiremos una lista de Purchases, asi que tambien necesitamos declarar este metodo
    List<Purchase> toPurchases(List<Compra> compras);

    @InheritInverseConfiguration
    //Esta vez al ser solo un atributo el que utilizamos, obviamos el tag @Mappings
    //Obviamos el atributo cliente
    @Mapping(target = "cliente", ignore = true)
    Compra toCompra(Purchase purchase);
}
