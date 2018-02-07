package com.example.microservice;

import com.example.domain.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final List<Product> products = new ArrayList<>();

    public ProductController() {
        products.add(new Product("PS4", "console de salon"));
        products.add(new Product("Crème de marron",
            "Une délicieuse confiture faite à la main dans la cuisine CDiscount"));
    }

    @GetMapping("")
    public
    @ResponseBody
    List<Product> getAllProducts() {
        return products;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getAllProducts(@PathVariable long id) {
        return products.stream()
            .filter(product -> product.id == id)
            .findFirst()
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity postProduct(Product product) {
        products.add(product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable long id) {
        Optional<Product> deletedProduct = products.stream()
            .filter(product -> product.id == (id))
            .findFirst();

        if (deletedProduct.isPresent()) {
            List<Product> filteredList = products.stream()
                .filter(product -> product.id != id)
                .collect(Collectors.toList());
            products.clear();
            products.addAll(filteredList);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
