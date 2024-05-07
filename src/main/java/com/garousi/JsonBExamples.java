package com.garousi;

import com.garousi.jsonb.Product;
import com.garousi.jsonb.ProductInfo;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonBExamples {
    public static void main(String[] args) {

        try {
//            readJsonProduct();
            readJsonAllProducts();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readJsonProduct() throws IOException {
//        https://dummyjson.com/products
        Jsonb jsonb = JsonbBuilder.create();

        Product product;
        try(InputStream in = new FileInputStream("src/main/resources/product.json")) {
            product = jsonb.fromJson(in, Product.class);
        }
        System.out.println(product);
        product.getImages().forEach(System.out::println);
    }

    private static void readJsonAllProducts() throws IOException {
        Jsonb jsonb = JsonbBuilder.create();

        ProductInfo productInfo;
        try(InputStream in = new FileInputStream("src/main/resources/product-list.json")) {
            productInfo = jsonb.fromJson(in, ProductInfo.class);
        }

        System.out.println("Total: " + productInfo.getTotal());
        System.out.println("limit: " + productInfo.getLimit());
        productInfo.getProducts()
                .forEach(p -> System.out.println("images: " + p.getImages()));

    }
}
