package com.platzi.market.persistance;

import com.platzi.market.domain.Purchase;
import com.platzi.market.domain.repository.PurchaseRepository;
import com.platzi.market.persistance.crud.CompraCrudRepository;
import com.platzi.market.persistance.entities.Compra;
import com.platzi.market.persistance.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepository compraCrudRepository;

    //Inyectamos el mapper, porque este repositorio debe hablar en terminos del dominio
    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        //Nota: Es necesario castear el argumento que estamos mandando(comprasCrudRepository.findAll())
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase savePurchase(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return mapper.toPurchase(compraCrudRepository.save(compra));
    }

}
