package bookManagement;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BookManager {
    private final ArrayList<Book> books = new ArrayList<>();
    private final File file = new File("src/bookManagement/books.txt");

    public BookManager(){
        loadFromFile();
    }

    public ArrayList<Book> getBooks() {
        if (!books.isEmpty())
            return this.books;
        return null;
    }

    private void loadFromFile(){
        System.out.println("Loading...");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                line = line.trim();
                if (line.length() != 0) {
                    String[] tmp = line.split("\\s+");
                    int id = Integer.parseInt(tmp[0]);
                    String name = String.join(" ", Arrays.copyOfRange(tmp, 1, tmp.length - 1));
                    double price = Double.parseDouble(tmp[tmp.length - 1]);
                    Book new_book = new Book(id, name, price);
                    books.add(new_book);
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * print books (one/line) in required format
     */
    public void printBooks(ArrayList<Book> books){
        if (books == null){
            System.out.println("(empty)");
            return;
        }
        if (books.isEmpty()) {
            System.out.println("(empty)");
            return;
        }
        System.out.printf("%-5s %-45s %-10s\n", "ID", "Name", "Price");
        for(var i : books)
            System.out.println(i);
    }

    public boolean add(Book book) {
        for(Book i : books)
            if (i.id == book.id)
                return false;
        books.add(book);
        return true;
    }

    public Book getBookById(int id) {
        for(Book i : books)
            if (i.id == id)
                return i;

        return null;
    }

    /**
     * remove book from this.books
     */
    public void remove(Book book) {
        books.remove(book);
    }

    /**
     * update this.books to be sorted by price from high -> low
     */
    public void sortDescByPrice() {
        // TODO: your code here
        books.sort((o1, o2) -> Double.compare(o2.price, o1.price));
    }

    /**
     * return all books having name contains keyword (case in-sensitive)
     */
    public ArrayList<Book> searchByName(String keyword) {
        ArrayList<Book> matches = new ArrayList<>();
        String kw = keyword.toLowerCase();
        for(Book i : books)
            if ((i.name.toLowerCase()).contains(kw))
                matches.add(i);

        return matches;
    }

    /**
     * write this.books to file books.txt in required format
     */
    public void saveToFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for(Book i : books)
            bw.write(i + "\n");
        bw.close();
    }
}
