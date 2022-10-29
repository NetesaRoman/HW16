package service;

import api.CategoryNotFoundException;
import models.Product;
import models.ProductList;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        List<Product> productList = Arrays.asList(
                new Product("Cheese", 345.2d, true),
                new Product("Headphones", 4375.50d, false),
                new Product("Cards", 60d, true),
                new Product("Book", 102.25d, true),
                new Product("Book", 41d, true),
                new Product("Book", 2.25d, true),
                new Product("Book", 20.25d, true),
                new Product("Book", 402.25d, true),
                new Product("Book", 1202.25d, false),
                new Product("Chocolate", 75d, false));


        System.out.println("-------------------------1--------------------------------------");
        printExpensiveBooks(productList);


        System.out.println("-------------------------2--------------------------------------");
        printSaleBooks(productList);


        System.out.println("-------------------------3--------------------------------------");
        String category = "Book";

        try {
            printMinCostBook(productList, category);
        } catch (CategoryNotFoundException e) {
            e.printStackTrace();
        }


        System.out.println("-------------------------4--------------------------------------");
        printLastThree(productList);


        System.out.println("-------------------------5--------------------------------------");
        try {
            printSumOfCheapBooksThisYear(productList);
        } catch (CategoryNotFoundException e) {
            e.printStackTrace();
        }


        System.out.println("-------------------------6--------------------------------------");
        groupAndPrintByCategories(productList);
    }


    public static void printExpensiveBooks(List<Product> productList) {
        productList.stream()
                .filter(p -> p.getPrice() >= 250)
                .forEach(Product::printInfo);
    }

    public static void printSaleBooks(List<Product> productList) {
        productList.stream()
                .filter(p -> p.isSale() && p.getType().equals("Book"))
                .forEach(Product::printInfo);
    }

    public static void printMinCostBook(List<Product> productList, String category) throws CategoryNotFoundException {

        //handling category exception
        //throw event (if no category - throw exception)
        if (!productList.stream().anyMatch(p -> p.getType().equals(category))) {
            throw new CategoryNotFoundException(category);
        }

        //find minimal
        productList.stream()
                .filter(p -> p.getType().equals(category))
                .min(Comparator.comparing(Product::getPrice))
                .ifPresent(Product::printInfo);

    }

    public static void printLastThree(List<Product> productList) {
        productList.stream().skip(productList.size() - 3L).forEach(Product::printInfo);
    }

    public static void printSumOfCheapBooksThisYear(List<Product> productList) throws CategoryNotFoundException {
        final double[] sum = {0};

        //handling category exception
        //throw event (if no category - throw exception)
        if (!productList.stream().anyMatch(p -> p.getType().equals("Book"))) {
            throw new CategoryNotFoundException("Book");
        }

        //find sum
        productList.stream()
                .filter(p -> p.getPrice() <= 75d && p.getDate().getYear() == 2022 && p.getType().equals("Book"))
                .forEach(p -> sum[0] += p.getPrice());
        System.out.println(sum[0]);
    }

    public static void groupAndPrintByCategories(List<Product> productList) {
        Map<String, ProductList> productMap = new HashMap<>();

        productList.stream().forEach(p -> {
            if (productMap.containsKey(p.getType())) {
                productMap.get(p.getType()).add(p);
            } else {
                productMap.put(p.getType(), new ProductList());
                productMap.get(p.getType()).add(p);
            }
        });

        productMap.forEach((key, value) -> value.printInfo());
    }
}
