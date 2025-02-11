package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BookHotel extends JFrame {
    private JPanel contentPane;
    private JTextField t1, t2;
    private Choice c1, c2, c3;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BookHotel frame = new BookHotel("JohnDoe");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public BookHotel(String username) {
        setTitle("Book Hotel");
        setBounds(320, 150, 1100, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Header Section
        JLabel lblHeader = new JLabel("BOOK HOTEL");
        lblHeader.setFont(new Font("Serif", Font.BOLD, 30));
        lblHeader.setForeground(new Color(72, 61, 139));
        lblHeader.setBounds(420, 10, 300, 40);
        contentPane.add(lblHeader);

        // Image Section
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("travel/management/system/icons/book.jpg"));
        Image scaledImage = icon.getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH);
        JLabel lblImage = new JLabel(new ImageIcon(scaledImage));
        lblImage.setBounds(580, 120, 500, 300);
        contentPane.add(lblImage);

        // User Panel
        JPanel userPanel = new JPanel();
        userPanel.setBackground(new Color(240, 248, 255));
        userPanel.setBounds(50, 80, 480, 500);
        userPanel.setLayout(null);
        userPanel.setBorder(BorderFactory.createTitledBorder("Customer Details"));
        contentPane.add(userPanel);

        // Username
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblUsername.setBounds(30, 30, 120, 25);
        userPanel.add(lblUsername);

        JLabel lblUserValue = new JLabel(username);
        lblUserValue.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblUserValue.setBounds(160, 30, 250, 25);
        userPanel.add(lblUserValue);

        // Select Hotel
        JLabel lblHotel = new JLabel("Select Hotel:");
        lblHotel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblHotel.setBounds(30, 70, 120, 25);
        userPanel.add(lblHotel);

        c1 = new Choice();
        c1.add("Hotel Paradise");
        c1.add("Beach Resort");
        c1.add("Mountain View");
        c1.add("City Inn");
        c1.add("Luxury Suites");
        c1.setBounds(160, 70, 200, 25);
        userPanel.add(c1);

        // Total Persons
        JLabel lblPersons = new JLabel("Total Persons:");
        lblPersons.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblPersons.setBounds(30, 110, 120, 25);
        userPanel.add(lblPersons);

        t1 = new JTextField("0");
        t1.setFont(new Font("SansSerif", Font.PLAIN, 14));
        t1.setBounds(160, 110, 200, 25);
        userPanel.add(t1);

        // Number of Days
        JLabel lblDays = new JLabel("Number of Days:");
        lblDays.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblDays.setBounds(30, 150, 140, 25);
        userPanel.add(lblDays);

        t2 = new JTextField("0");
        t2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        t2.setBounds(160, 150, 200, 25);
        userPanel.add(t2);

        // AC/Non-AC
        JLabel lblAC = new JLabel("AC / Non-AC:");
        lblAC.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblAC.setBounds(30, 190, 120, 25);
        userPanel.add(lblAC);

        c2 = new Choice();
        c2.add("AC");
        c2.add("Non-AC");
        c2.setBounds(160, 190, 200, 25);
        userPanel.add(c2);

        // Food Included
        JLabel lblFood = new JLabel("Food Included:");
        lblFood.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblFood.setBounds(30, 230, 120, 25);
        userPanel.add(lblFood);

        c3 = new Choice();
        c3.add("Yes");
        c3.add("No");
        c3.setBounds(160, 230, 200, 25);
        userPanel.add(c3);

        // Total Price
        JLabel lblTotal = new JLabel("Total Price:");
        lblTotal.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblTotal.setBounds(30, 270, 120, 25);
        userPanel.add(lblTotal);

        JLabel lblTotalValue = new JLabel("Rs. 0");
        lblTotalValue.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTotalValue.setForeground(Color.RED);
        lblTotalValue.setBounds(160, 270, 200, 25);
        userPanel.add(lblTotalValue);

        // Buttons
        JButton btnCheckPrice = new JButton("Check Price");
        btnCheckPrice.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnCheckPrice.setBackground(new Color(72, 209, 204));
        btnCheckPrice.setBounds(30, 320, 140, 30);
        userPanel.add(btnCheckPrice);

        btnCheckPrice.addActionListener(e -> {
            try {
                int persons = Integer.parseInt(t1.getText());
                int days = Integer.parseInt(t2.getText());
                int price = (persons * days) * 2000; // Example calculation
                lblTotalValue.setText("Rs. " + price);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid integers for Total Persons and Days!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnBook = new JButton("Book");
        btnBook.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnBook.setBackground(new Color(124, 252, 0));
        btnBook.setBounds(200, 320, 140, 30);
        userPanel.add(btnBook);

        btnBook.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Hotel booked successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnBack.setBackground(Color.LIGHT_GRAY);
        btnBack.setBounds(370, 320, 80, 30);
        userPanel.add(btnBack);

        btnBack.addActionListener(e -> setVisible(false));
    }
}
