package bookManagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // variables
        Scanner sc = new Scanner(System.in);
        String prompt = """
                -----------------------------------
                1. list all books
                2. add a new book
                3. edit book
                4. delete a book
                5. search books by name
                6. sort books descending by price
                0. save & exit
                -----------------------------------""";
        String input = "Your option: ";
        int command;
        boolean onLoop = true;
        int id;
        String name;
        double price;
        ArrayList<Book> lst;
        Book temp;

        BookManager bookManager = new BookManager();
        while (onLoop){
            System.out.println(prompt);
            System.out.print(input);
            command = sc.nextInt();
            sc.nextLine();
            switch (command) {
                case 0 -> {
                    try {
                        bookManager.saveToFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Saving to file...\n" +
                            "Bye!");
                    onLoop = false;
                }
                case 1 -> bookManager.printBooks(bookManager.getBooks());
                case 2 -> {
                    System.out.print("Enter book id: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter book name: ");
                    name = sc.nextLine();
                    System.out.print("Enter book price: ");
                    price = sc.nextDouble();
                    sc.nextLine();
                    if (bookManager.add(new Book(id, name, price)))
                        System.out.println("Added successfully.");
                    else
                        System.out.println("Duplicated ID");
                }
                case 3 -> {
                    System.out.print("Enter book id: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter book name: ");
                    name = sc.nextLine();
                    System.out.print("Enter book price: ");
                    price = sc.nextDouble();
                    sc.nextLine();
                    temp = bookManager.getBookById(id);
                    if (temp == null)
                        System.out.println("Invalid ID!");
                    else {
                        temp.setName(name);
                        temp.setPrice(price);
                        System.out.println("Edit successfully.");
                    }
                }
                case 4 -> {
                    System.out.print("Enter book id: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    Book t = bookManager.getBookById(id);
                    if (t == null)
                        System.out.println("Invalid ID!");
                    else {
                        bookManager.remove(t);
                        System.out.println("Deleted successfully!");
                    }
                }
                case 5 -> {
                    System.out.print("Enter keyword: ");
                    name = sc.nextLine();
                    lst = bookManager.searchByName(name);
                    if (lst.size() == 0)
                        System.out.println("(empty)");
                    else {
                        bookManager.printBooks(lst);
                    }
                }
                case 6 -> bookManager.sortDescByPrice();
                default -> System.out.println("Invalid option!");
            }
        }
        sc.close();
    }
}
