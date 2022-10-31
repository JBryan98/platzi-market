package com.platzi.market.domain.service;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    //Listar
    public List<Product> getAll(){
        return productRepository.getAll();
    }

    //Producto por Id

    public Optional<Product> getProduct(int productId){
        return productRepository.getProduct(productId);
    }

    //Producto por Categoria
    public Optional<List<Product>> getByCategory(int categoryId){
        return productRepository.getByCategory(categoryId);
    }

    //Guardar producto
    public Product saveProduct(Product product){
        return productRepository.saveProduct(product);
    }

    //Eliminar Producto
    public boolean deleteProduct(int productId){
    //Si solo queremos que elimine : productoRepository.deleteProduct(productId)
    //Recibir true | false  como confirmacion si elimino o no
        return getProduct(productId).map(product -> {
            productRepository.deleteProduct(productId);
            return true;
        }).orElse(false);
    }

}
