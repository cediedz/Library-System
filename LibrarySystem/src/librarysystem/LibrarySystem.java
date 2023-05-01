package librarysystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class LibrarySystem extends JFrame implements ActionListener {

    // GUI Components
    JLabel titleLabel = new JLabel("Library Management System");
    JLabel bookLabel = new JLabel("Book Title:");
    JTextField bookField = new JTextField(20);
    JLabel authorLabel = new JLabel("Author:");
    JTextField authorField = new JTextField(20);
    JLabel isbnLabel = new JLabel("ISBN:");
    JTextField isbnField = new JTextField(20);
    JButton addButton = new JButton("Add Book");
    JButton viewButton = new JButton("View Books");
    

    // Login Components
    JFrame loginFrame = new JFrame("Login");
    JLabel loginLabel = new JLabel("Enter username and password to login.");
    JLabel usernameLabel = new JLabel("Username:");
    JTextField usernameField = new JTextField(20);
    JLabel passwordLabel = new JLabel("Password:");
    JPasswordField passwordField = new JPasswordField(20);
    JButton loginButton = new JButton("Login");
    
    //Add Icons
    ImageIcon loginIcon = new ImageIcon("lib.png");

    // User information
    String username = "admin";
    String password = "password";

    public LibrarySystem() {
        super("Library Management System");

        // Set up the login GUI
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(350, 200);
        loginFrame.setResizable(false);
        
        loginLabel.setIcon(loginIcon);
        
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2));
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);
        

        loginButton.addActionListener(this);

        Container loginContentPane = loginFrame.getContentPane();
        loginContentPane.add(loginLabel, BorderLayout.NORTH);
        loginContentPane.add(loginPanel, BorderLayout.CENTER);

        loginFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // Login button clicked
        if (source == loginButton) {
            if (checkLogin()) {
                // Close the login window
                loginFrame.dispose();
                // Set up the main GUI
                initComponents();
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Incorrect username or password.");
            }
        }
        // Add button clicked
        else if (source == addButton) {
            addBook();
        }
        // View button clicked
        else if (source == viewButton) {
            viewBooks();
        }
    }

    private boolean checkLogin() {
        String inputUsername = usernameField.getText();
        String inputPassword = new String(passwordField.getPassword());

        if (inputUsername.equals(username) && inputPassword.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    private void initComponents() {
        // Set up the main GUI
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(bookLabel);
        panel.add(bookField);
        panel.add(authorLabel);
        panel.add(authorField);
        panel.add(isbnLabel);
        panel.add(isbnField);
        panel.add(addButton);
        panel.add(viewButton);

        addButton.addActionListener(this);
        viewButton.addActionListener(this);

        Container contentPane = getContentPane();
        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(titleLabel, BorderLayout.NORTH);

        setVisible(true);
    }

    private void addBook() {
        // Get the book information from the fields
        String bookTitle = bookField.getText();
        String bookAuthor = authorField.getText();
        String bookIsbn = isbnField.getText();

        // Check that all fields are filled in
        if (bookTitle.equals("") || bookAuthor.equals("") || bookIsbn.equals("")) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        // Add the book to the file
        try {
            FileWriter fileWriter = new FileWriter("gg.txt", true);
            fileWriter.write("TITLE :  " + bookTitle +  " , AUTHOR :  " + bookAuthor + " , ISBN :  " +bookIsbn);
            fileWriter.close();
            JOptionPane.showMessageDialog(this, "Book added successfully.");
            // Clear the fields
            bookField.setText("");
            authorField.setText("");
            isbnField.setText("");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error writing to file.");
        }
    }

    private void viewBooks() {
        // Read the books from the file and display them in a dialog box
        try {
            FileReader fileReader = new FileReader("HORRORbooks.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            String bookList = "";

            while ((line = bufferedReader.readLine()) != null) {
                bookList += line + "\n";
            }

            bufferedReader.close();
            JOptionPane.showMessageDialog(this, bookList);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading file.");
        }
    }

    public static void main(String[] args) {
        new LibrarySystem();
    }
}
