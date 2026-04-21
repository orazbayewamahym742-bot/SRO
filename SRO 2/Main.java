import java.util.*;

// Базалық класс (Мұрагерлік үшін)
class Person {
    protected String name;

    public Person(String name) {
        this.name = name;
    }

    public void getInfo() {
        System.out.println("Name: " + name);
    }
}

// Автор класы
class Author extends Person {
    private List<Book> books = new ArrayList<>();

    public Author(String name) {
        super(name);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public void getInfo() {
        System.out.println("Author: " + name);
    }
}

// Кітап класы
class Book {
    private String title;
    private Author author;
    private boolean isAvailable;

    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("Кітап берілді: " + title);
        } else {
            System.out.println("Кітап қолжетімді емес!");
        }
    }

    public void returnBook() {
        isAvailable = true;
        System.out.println("Кітап қайтарылды: " + title);
    }

    public void getInfo() {
        System.out.println("Кітап: " + title + ", Автор: " + author.name);
    }
}

// Пайдаланушы класы
class User extends Person {
    private List<Book> borrowedBooks = new ArrayList<>();

    public User(String name) {
        super(name);
    }

    public void borrow(Book book) {
        if (book.isAvailable()) {
            book.borrowBook();
            borrowedBooks.add(book);
        } else {
            System.out.println("Бұл кітап бос емес!");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            book.returnBook();
            borrowedBooks.remove(book);
        }
    }

    @Override
    public void getInfo() {
        System.out.println("User: " + name);
    }
}

// Кітапхана класы
class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void showBooks() {
        for (Book b : books) {
            b.getInfo();
        }
    }

    public Book searchBook(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                return b;
            }
        }
        return null;
    }
}

// Негізгі класс
public class Main {
    public static void main(String[] args) {

        // Авторлар
        Author author1 = new Author("Абай");
        Author author2 = new Author("Мұхтар Әуезов");

        // Кітаптар
        Book book1 = new Book("Қара сөздер", author1);
        Book book2 = new Book("Абай жолы", author2);

        author1.addBook(book1);
        author2.addBook(book2);

        // Кітапхана
        Library library = new Library();
        library.addBook(book1);
        library.addBook(book2);

        // Пайдаланушы
        User user = new User("Али");

        // Кітаптарды көрсету
        System.out.println("Кітаптар тізімі:");
        library.showBooks();

        // Кітап алу
        System.out.println("\nКітап алу:");
        user.borrow(book1);

        // Қайтадан алу әрекеті
        user.borrow(book1);

        // Кітап қайтару
        System.out.println("\nКітап қайтару:");
        user.returnBook(book1);

        // Қайта алу
        user.borrow(book1);
    }
}