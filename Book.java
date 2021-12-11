package bookManagement;

public class Book {
    protected int id;
    protected String name;
    protected double price;

    public Book (int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price){
        this.price = price;
    }

    @Override // from Object class
    public String toString(){
        return String.format("%5d %-45s %10.2f", this.id, this.name, this.price);
    }

//    public static void main(String[] args) {
//        Book a = new Book(123, "Doraemon", 12.99);
//        System.out.println(a);
//    }
}
