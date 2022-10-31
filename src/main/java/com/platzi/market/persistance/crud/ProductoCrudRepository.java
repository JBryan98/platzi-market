package com.platzi.market.persistance.crud;

import com.platzi.market.persistance.entities.Producto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer>{
    //Tambien podemos utilizar consultas de manera nativa mediante el tag @Query
    //@Query(value = "SELECT * FROM productos WHERE id_Categoria = ?", nativeQuery = true)
    //Query JPA
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    //Importante: Los parametros deben llamarse igualmente a como estan definidas en sus clases correspondientes
    //Nota: Para los parametros podemos usar datos primitivos o envoltorios, no hay problema
    //En esta query estamos buscando los productos cuyo stock sea menor al stock que recibe por parametro
    //y tambien podemos añadirle un filtro mas por su estado. (Para ello utilizamos el conector AND en el query)
    //Optional: Esta clase puede o no contener un valor, es decir puede reciber null.
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);


    //Mis querys
    //Actualizar stock
    @Modifying //Utilizamos el tag @Modifying porque alteraremos el estado de la base de datosm mas no realizaremos un select a la data
    @Query (value = "UPDATE productos SET cantidad_stock = cantidad_stock - :cantidad WHERE id_producto = :id_producto", nativeQuery = true)
    int updateStock(@Param("id_producto") int id_producto, @Param("cantidad") int cantidad);
}
