import com.pluralsight.Book;

import java.util.Scanner;

public class Main {

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

        books[0] = new Book(1, "111111111", "Harry Potter and the Sorcerer's Stone");
        books[1] = new Book(2, "222222222", "Spider-Man");
        books[2] = new Book(3, "333333333", "The Art Of War");
        books[3] = new Book(4, "444444444", "The Labyrinth");
        books[4] = new Book(5, "555555555", "1984");
        books[5] = new Book(6, "666666666", "Wonder");
        books[6] = new Book(7, "777777777", "Iron Man");
        books[7] = new Book(8, "888888888", "Captain America");
        books[8] = new Book(9, "999999999", "The Lord Of The Rings");
        books[9] = new Book(10, "101010101", "Star Wars");
        books[10] = new Book(11, "111111110", "The Odyssey");
        books[11] = new Book(12, "121212121", "Moby-Dick");
        books[12] = new Book(13, "131313131", "Charlotte's Web");
        books[13] = new Book(14, "141414141", "Lion King");
        books[14] = new Book(15, "151515151", "Alice And The Wonderland");
        books[15] = new Book(16, "161616161", "Steph Curry");
        books[16] = new Book(17, "171717171", "The Lightning Thief");
        books[17] = new Book(18, "181818181", "Lebron James");
        books[18] = new Book(19, "191919191", "Dune");
        books[19] = new Book(20, "202020202", "A Game of Thrones");

        return books;
    }

    public static void showAvailableBooks(Book[] books, Scanner scanner) {
        System.out.println("\n=== Available Books ===");

        boolean hasAvailableBooks = false;
        for (Book book : books) {
            if (!book.isCheckedOut()) {
                hasAvailableBooks = true;
                System.out.println("ID: " + book.getId()
                        + " | ISBN: " + book.getIsbn()
                        + " | Title: " + book.getTitle());
            }
        }

        if (!hasAvailableBooks) {
            System.out.println("No available books right now.");
        }

        System.out.print("\nEnter a book ID to check out or X to return home: ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("X")) {
            return;
        }

        try {
            int bookId = Integer.parseInt(input);
            Book selectedBook = findBookById(books, bookId);

            if (selectedBook == null) {
                System.out.println("Book ID not found.");
            } else if (selectedBook.isCheckedOut()) {
                System.out.println("That book is already checked out.");
            } else {
                System.out.print("Enter your name: ");
                String name = scanner.nextLine();
                selectedBook.checkOut(name);
                System.out.println("Book checked out successfully.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid book ID or X.");
        }
    }

    public static void showCheckedOutBooks(Book[] books, Scanner scanner) {
        System.out.println("\n=== Checked Out Books ===");

        boolean hasCheckedOutBooks = false;
        for (Book book : books) {
            if (book.isCheckedOut()) {
                hasCheckedOutBooks = true;
                System.out.println("ID: " + book.getId()
                        + " | ISBN: " + book.getIsbn()
                        + " | Title: " + book.getTitle()
                        + " | Checked Out To: " + book.getCheckedOutTo());
            }
        }

        if (!hasCheckedOutBooks) {
            System.out.println("No books are currently checked out.");
        }

        System.out.println("\nC - Check in a book");
        System.out.println("X - Return home");
        System.out.print("Choose an option: ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("X")) {
            return;
        } else if (input.equalsIgnoreCase("C")) {
            checkInBook(books, scanner);
        } else {
            System.out.println("Invalid option.");
        }
    }

    public static void checkInBook(Book[] books, Scanner scanner) {
        System.out.print("Enter the ID of the book to check in: ");
        String input = scanner.nextLine();

        try {
            int bookId = Integer.parseInt(input);
            Book selectedBook = findBookById(books, bookId);

            if (selectedBook == null) {
                System.out.println("Book ID not found.");
            } else if (!selectedBook.isCheckedOut()) {
                System.out.println("That book is not currently checked out.");
            } else {
                selectedBook.checkIn();
                System.out.println("Book checked in successfully.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid book ID.");
        }
    }

    public static Book findBookById(Book[] books, int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}
