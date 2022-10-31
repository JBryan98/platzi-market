package com.platzi.market.persistance.mapper;

import com.platzi.market.domain.PurchaseItem;
import com.platzi.market.persistance.entities.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

//Como en el InheritInverseConfiguration estamos utilizando Producto, debemos referenciarlo en el @Mapper

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItemMapper {
    //Traduciendo de ComprasProducto a PurchaseItem
    @Mappings({
            @Mapping(source = "id.idProducto", target = "productId"),
            @Mapping(source = "cantidad", target = "quantity"),
            //Nota: Si el source y el target tienen el mismo nombre, no es necesario declararlo
            // @Mapping(source ="total", target = "total"),
            @Mapping(source = "estado", target = "active")
    })
    PurchaseItem toPurchaseItem(ComprasProducto producto);

    @InheritInverseConfiguration
    //Atributos no traducidos o ignorados, deben ser declaarados indicando que han sido ignorados
    @Mappings({
            @Mapping(target = "compra", ignore = true),
            //Nota: recordar que "producto" hace referencia a la entidad Producto, por eso lo referenciamos en el @Mapper
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "id.idCompra", ignore = true),
    })
    ComprasProducto toComprasProducto(PurchaseItem item);

}
