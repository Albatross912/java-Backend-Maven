package com.example;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {

        final String CSV_LOCATION = "src/main/java/com/resource";
        final int CHECK_INTERVAL = 5000; // in milliseconds

        List<String> csvFiles = List.of("src/main/java/com/resource/Nike.csv", "src/main/java/com/resource/Puma.csv");
        ProductSearch productSearch = new ProductSearch(csvFiles);
        productSearch.loadProducts();

        CsvFileWatcher csvFileWatcher = new CsvFileWatcher(CSV_LOCATION, CHECK_INTERVAL);
        csvFileWatcher.start();

        while(true)
        {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a T-shirt color to search: ");
            String colorQ = scanner.nextLine();
            System.out.print("Enter a T-shirt size to search: ");
            String sizeQ = scanner.nextLine();
            System.out.print("Enter a T-shirt gender to search: ");
            String genderQ= scanner.nextLine();
            System.out.print("Enter the output preference (by price), (by rating), (by both price and rating): ");
            String outputPref = scanner.nextLine();
            List<Product> results = productSearch.searchProducts(colorQ, sizeQ, genderQ);
            if(results.isEmpty())
                System.out.println("No matching T-shirts found.");
            else
            {
                String newString = outputPref.replaceAll("\\s+", "").toLowerCase();
                switch (newString) {
                    case "byprice":
                        results.sort(Comparator.comparingDouble(Product::getPrice));
                        break;
                    case "byrating":
                        results.sort(Comparator.comparingDouble(Product::getRating));
                        break;
                    case "bybothpriceandrating":
                        Comparator<Product> priceComparator = Comparator.comparing(Product::getPrice);
                        Comparator<Product> ratingComparator = Comparator.comparing(Product::getRating).reversed();
                        results.sort(priceComparator.thenComparing(ratingComparator));
                        break;
                }
                for(Product p:results)
                    System.out.println("-> " + p.getName());
                System.out.println();
            }
            System.out.println("To stop the program press 1 ...... else press enter");
            String input = scanner.nextLine();
            if(input.equals("1")) System.exit(1);
        }
    }
}
