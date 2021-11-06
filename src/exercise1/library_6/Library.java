package exercise1.library_6;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * ,
 */
public class Library  {
    private String addresses;

    private static String openData;
    private StringBuffer[] bookName;
    private ArrayList<Book> book=new ArrayList<Book>();
    //private Book[] book;
    public Library() {

    }

    public Library(String addresses) {
        this.addresses = addresses;
    }

    public void addBook(Book book){
        if(this.book.size()>=1&&this.book.contains(book)){
            System.out.println("the book has existed!");
        }
        else{
            this.book.add(book);
            return;
        }
        this.book.add(book);

    }

    public static void printOpeningHours(){
        System.out.println("Libraries are open daily from 9am to 5pm.");
    }

    public void printAddress(){
        System.out.println(addresses);
    }

    public void borrowBook(String bookName){
        int index=-1;
        for(int i=0;i<this.book.size();i++){
            if(this.book.get(i).getTitle()==bookName){
                index=i;
                break;
            }
        }
        if(index!=-1){
            if(this.book.get(index).isBorrowed()){
                System.out.println("Sorry, this book is already borrowed.");

            }
            else{
                Date date = new Date();
                String formatDate= DateFormat.getDateInstance().format(date);
                this.book.get(index).borrowed(formatDate);
                System.out.println("You successfully borrowed The Lord of the Rings");
            }
        }
        else{
            System.out.println("Sorry, this book is not in our catalog.");
        }
    }

    public void printAvailableBooks(){
        if(this.book.size()!=0){
            for(Book b:book){
                if(!b.isBorrowed()){
                    System.out.println(b.getTitle());
                }
            }
        }
        else{
            System.out.println("This library has not book!");
        }

    }

    public void returnBook(String bookName){
        int index=-1;
        for(int i=0;i<this.book.size();i++){
            if(this.book.get(i).getTitle()==bookName){
                index=i;
                break;
            }
        }
        if(index!=-1){
            if(!this.book.get(index).isBorrowed()){
                System.out.println("the book has been returned!");
            }
            else{
                Date date = new Date();
                String formatDate= DateFormat.getDateInstance().format(date);
                this.book.get(index).returned(formatDate);
                System.out.println(bookName+" has successfully been returned!");
            }
        }
        else{
            System.out.println("the book not existed!");
        }
    }

    // Add the missing implementation to this class
    public static void main(String[] args){
        // Create two libraries
        Library firstLibrary = new Library("10 Main St.");
        Library secondLibrary = new Library("228 Liberty St.");

        //Add four books to the first library
        firstLibrary.addBook(new Book("The Da Vinci Code","2011-12-24"));
        firstLibrary.addBook(new Book("Le Petit Prince","2013-10-24"));
        firstLibrary.addBook(new Book("A Tale of Two Cities","2013-10-24"));
        firstLibrary.addBook(new Book("The Lord of the Rings","2011-12-24"));

        // Print opening hours and the addresses
        System.out.println("Library hours:");
        printOpeningHours();
        System.out.println();
        System.out.println("Library addresses:");
        firstLibrary.printAddress();
        secondLibrary.printAddress();
        System.out.println();

        // Try to borrow The Lords of the Rings from both libraries
        System.out.println("Borrowing The Lord of the Rings:");
        firstLibrary.borrowBook("The Lord of the Rings");
        firstLibrary.borrowBook("The Lord of the Rings");
        secondLibrary.borrowBook("The Lord of the Rings");
        System.out.println();

        // Print the titles of all available books from both libraries
        System.out.println("Books available in the first library:");
        firstLibrary.printAvailableBooks();
        System.out.println();
        System.out.println("Books available in the second library:");
        secondLibrary.printAvailableBooks();
        System.out.println();

        //Return The Lords of the Rings to the first library
        System.out.println("Returning The Lord of the Rings:");
        firstLibrary.returnBook("The Lord of the Rings");
        System.out.println();
        // Print the titles of available from the first library
        System.out.println("Books available in the first library:");
        firstLibrary.printAvailableBooks();
    }
}
