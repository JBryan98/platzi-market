package com.platzi.market.domain.repository;

import com.platzi.market.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    //Obtener todas las compras
    List<Purchase> getAll();

    //Obtener las compras de un cliente
    //Como puede que consultemos un cliente que no tenga compras, utilizamos Optional
    Optional<List<Purchase>> getByClient(String clientId);


    //Guardar compra
    Purchase savePurchase(Purchase purchase);

}
