package com.platzi.market.domain.repository;

import com.platzi.market.domain.Product;

import java.util.List;
import java.util.Optional;


/*Patron data Mapper
*Consiste en traducir o convertir 2 objetos que pueden hacer una misma labor
*Clase Producto      ->    Clase Product
*nombre              ->    name
*idProducto          ->    productId
*cantidadStock       ->    stock
*precioVenta         ->    price
*estado              ->    active
*codigoBarras
*Obviamos codigoBarras porque en este caso no queremos exponer el código de Barras en el API
*Esto garantiza que no se exponga toda la Base de Datos a la API, lo que garantiza que agentes externos no se den cuenta
*de que forma esta construida nuestra base de datos. (Permite obviar campos innecesarios en la API)
*Si se realizan cambios en la base de datos, se agregan un nuevo traductor para la nueva tabla
*/


public interface ProductRepository {
    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getScaseProducts(int quantity);
    Optional<Product> getProduct(int productId);
    Product saveProduct(Product product);
    void deleteProduct(int productId);
}
/*Nota: Las clases Category y Product haran referencia a nuestros objetos de dominio*
*Los objetos de dominio nos ayudan en lo siguiente:
*No exponer la base de datos en el API
*Desacoplay nuestra API a una base de datos puntual
*No tener campos innecesarios en el API(No es necesario colocar todos los campos de la tabla en el API -> En este ejemplo obviamos el codigo de barras de Producto)
*Nos ayuda evitar mezclar idiomas en el dominio(Las entidades inicialmente estaban en español, los objetos de dominio estan en ingles)
 */
