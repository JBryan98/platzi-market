package com.platzi.market.web.controller;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//Request Mapping recibe como parametro en que Path haremos la solicitud
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    //ResponeEntity da mejor claridad a nuestra API haciendo uso de los codigos HTTP, los endpoints dan una respuesta mas adecuada gracias al control que ofrece ResponseEntity
    //Listar Productos
    @GetMapping("/all")
    @ApiOperation("Get all supermarket products")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Product>> getAll(){
        //ResponseEntity recibe 2 parámetros (Cuerpo de la respuesta, Http.Status)
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    //Obtener un producto por su Id
    @GetMapping("/{productId}")
    @ApiOperation("Search a product by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Product not found"),
    })
    public ResponseEntity<Product> getProduct(@PathVariable int productId){
        return productService.getProduct(productId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));//Cuando no exista un producto, responderá NotFound
    }

    //Listar Productos por el Id de su Categoria
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getByCategory(@ApiParam(value = "The id of the product", required = true, example = "7") @PathVariable int categoryId){
        return productService.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity deleteProduct(@PathVariable int productId){
        return productService.deleteProduct(productId) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
        /*if(productService.deleteProduct(productId)){
            return new ResponseEntity(HttpStatus.OK);
        }
        else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }*/
    }
}
