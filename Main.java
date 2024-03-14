import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Book[] bookList;
    private List<Student> userStudent;
    private Scanner scanner;

    public Main() {
        scanner = new Scanner(System.in);
        // Inisialisasi daftar buku
        bookList = new Book[]{
                new Book("388c-e681-9152", "Foxit eSign", "Accessibility", "Author1", 1),
                new Book("d95e-8c4-9523", "Nana Buku", "Category", "Author2", 2),
                new Book("Sejarah1", "Sejarah", "Sejarah", "Author3", 8),
                new Book("Sejarah2", "Sejarah", "Sejarah", "Author3", 8)
        };

        // Inisialisasi daftar user student
        userStudent = new ArrayList<>();
        userStudent.add(new Student("Alby El Fauza", "202310370311386", "Teknik", "Informatika"));
        userStudent.add(new Student("Rhea Ramiza", "200510370310521", "Teknik", "Informatika"));
        userStudent.add(new Student("Frista Fadillah", "282210378311283", "Teknik", "Informatika"));
    }

    public void menu() {
        int choice;
        do {
            System.out.println("Library System");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Student");
            System.out.println("3. Exit");
            System.out.println("Choose option: ");
            choice = readIntegerInput();

            switch (choice) {
                case 1:
                    menuAdmin();
                    break;
                case 2:
                    inputNim();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 3);
    }

    public int readIntegerInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.next(); // consume the invalid input
        }
        return scanner.nextInt();
    }

    public void inputNim() {
        System.out.println("Enter your NIM: ");
        scanner.nextLine(); // consume the newline character
        String nim = scanner.nextLine();
        checkNim(nim);
    }

    public void checkNim(String nim) {
        boolean found = false;
        for (Student student : userStudent) {
            if (student.getNim().equals(nim)) {
                found = true;
                student.menuStudent();
                break;
            }
        }
        if (!found) {
            System.out.println("Student with NIM " + nim + " not found.");
        }
    }

    public void menuAdmin() {
        int choice;
        do {
            System.out.println("Admin Menu");
            System.out.println("1. Add Student");
            System.out.println("2. Display Registered Students");
            System.out.println("3. Logout");
            System.out.println("Choose option: ");
            choice = readIntegerInput();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayStudent();
                    break;
                case 3:
                    System.out.println("Logging out from admin account...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 3);
    }

    public void addStudent() {
        System.out.print("Enter name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Enter NIM: ");
        String nim;
        do {
            nim = scanner.nextLine();
            if (nim.length() != 15) {
                System.out.println("Invalid NIM format. NIM should have 15 characters.");
                System.out.print("Enter NIM again: ");
            }
        } while (nim.length() != 15);

        System.out.print("Enter faculty: ");
        String faculty = scanner.nextLine();
        System.out.print("Enter study program: ");
        String studyProgram = scanner.nextLine();

        userStudent.add(new Student(name, nim, faculty, studyProgram));
    }

    public void displayStudent() {
        System.out.println("Registered Students:");
        for (Student student : userStudent) {
            System.out.println(student.getName() + " (" + student.getNim() + ")");
        }
    }

    public static void main(String[] args) {
        Main library = new Main();
        library.menu();
    }
}

class Book {
    private String id;
    private String title;
    private String author;
    private String category;
    private int stock;

    public Book(String id, String title, String author, String category, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.stock = stock;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

class Student {
    private String name;
    private String nim;
    private String faculty;
    private String studyProgram;
    private List<Book> borrowedBooks;
    private Scanner scanner;

    public Student(String name, String nim, String faculty, String studyProgram) {
        this.name = name;
        this.nim = nim;
        this.faculty = faculty;
        this.studyProgram = studyProgram;
        this.borrowedBooks = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void menuStudent() {
        int choice;
        do {
            System.out.println("Student Menu");
            System.out.println("1. Display Books");
            System.out.println("2. Borrowed Books");
            System.out.println("3. Borrow a Book");
            System.out.println("4. Logout");
            System.out.println("Choose option: ");
            choice = readIntegerInput();

            switch (choice) {
                case 1:
                    displayBooks(Main.bookList);
                    break;
                case 2:
                    displayBorrowedBooks();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    System.out.println("Logging out from student account...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 4);
    }

    public int readIntegerInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.next(); // consume the invalid input
        }
        return scanner.nextInt();
    }

    public void displayBooks(Book[] books) {
        System.out.println("Available Books:");
        for (Book book : books) {
            System.out.println(book.getTitle() + " (" + book.getAuthor() + ") - " + book.getStock() + " copies available");
        }
    }

    private void displayBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("You haven't borrowed any books.");
        } else {
            System.out.println("Borrowed Books:");
            for (Book book : borrowedBooks) {
                System.out.println(book.getTitle() + " (" + book.getAuthor() + ")");
            }
        }
    }

    private void borrowBook() {
        System.out.println("Enter the ID of the book you want to borrow: ");
        scanner.nextLine(); // consume the newline character
        String bookId = scanner.nextLine();
        Book bookToBorrow = null;
        for (Book book : Main.bookList) {
            if (book.getId().equals(bookId)) {
                bookToBorrow = book;
                break;
            }
        }
        if (bookToBorrow != null) {
            if (bookToBorrow.getStock() > 0) {
                borrowedBooks.add(bookToBorrow);
                bookToBorrow.setStock(bookToBorrow.getStock() - 1);
                System.out.println("Book borrowed successfully.");
            } else {
                System.out.println("Sorry, the book is currently out of stock.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(String studyProgram) {
        this.studyProgram = studyProgram;
    }
}