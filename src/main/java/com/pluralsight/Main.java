package com.pluralsight;

import com.pluralsight.Book;

import java.util.Scanner;

public class Main {

    private static Book[] books;
    private static int id;
    private static Object Object;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Book[] books = createBookInventory();

        boolean running = true;

        while (running) {
            System.out.println("\n=== Neighborhood Library ===");
            System.out.println("1 - Show Available Books");
            System.out.println("2 - Show Checked Out Books");
            System.out.println("3 - Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showAvailableBooks(books, scanner);
                    break;
                case "2":
                    showCheckedOutBooks(books, scanner);
                    break;
                case "3":
                    running = false;
                    System.out.println("Exiting application...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    public static Book[] createBookInventory() {
        Book[] books = new Book[20];

