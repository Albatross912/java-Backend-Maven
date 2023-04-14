package com.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductSearch {
    private final List<String> csvFiles;
    public final List<Product> products;

    public ProductSearch(List<String> csvFiles) {
        this.csvFiles = csvFiles;
        this.products = new ArrayList<>();
    }

    public void loadProducts() throws IOException, CsvException {
        for (String csvFile : csvFiles) {
            CSVReader reader = new CSVReader(new FileReader(csvFile));
            List<String[]> lines = reader.readAll();
            int temp = 0;
            for (String[] line : lines) {
                if(temp == 0)
                {
                    temp++;
                    continue;
                }
                String name = line[1];
                String color = line[2];
                String gender = line[3];
                String size = line[4];
                int price = Integer.parseInt(line[5]);
                double rating = Double.parseDouble(line[6]);
                Product p = new Product(name, color, gender, size, price, rating);
                ProductList.productList.add(p);
            }
        }
    }

    public List<Product> searchProducts(String colorQ, String sizeQ, String genderQ) {
        List<Product> results = new ArrayList<>();
        for (Product product : ProductList.productList) {
            if (product.getColor().toLowerCase().contains(colorQ.toLowerCase()) &&
                product.getSize().toLowerCase().contains(sizeQ.toLowerCase()) &&
                product.getGender().toLowerCase().contains(genderQ.toLowerCase())) {
                results.add(product);
            }
        }
        return results;
    }
    public void readProducts()
    {
        System.out.println("Number of products: " + products.size());
        for(Product p: ProductList.productList)
            System.out.print(p.getName());
        System.out.println();
    }
}
