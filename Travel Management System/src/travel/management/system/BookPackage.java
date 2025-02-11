package travel.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

public class BookPackage extends JFrame {
    private JPanel contentPane;
    JTextField t1;
    JComboBox<String> c1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BookPackage frame = new BookPackage("username");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public BookPackage(String username) {
        // Frame setup
        setBounds(350, 200, 900, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(240, 248, 255); // Start color
                Color color2 = new Color(135, 206, 235); // End color for gradient
                g2d.setPaint(new GradientPaint(0, 0, color1, 0, getHeight(), color2));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Title Label
        JLabel lblTitle = new JLabel("Book Your Package");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 36));
        lblTitle.setForeground(new Color(0, 51, 102));
        lblTitle.setBounds(280, 20, 400, 50);
        contentPane.add(lblTitle);

        // Username Section
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblUsername.setForeground(new Color(0, 51, 102));
        lblUsername.setBounds(50, 100, 200, 25);
        contentPane.add(lblUsername);

        JLabel lblDisplayUsername = new JLabel(username);
        lblDisplayUsername.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblDisplayUsername.setForeground(new Color(0, 51, 102));
        lblDisplayUsername.setBounds(200, 100, 200, 25);
        contentPane.add(lblDisplayUsername);

        // Package Section
        JLabel lblPackage = new JLabel("Select Package:");
        lblPackage.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblPackage.setForeground(new Color(0, 51, 102));
        lblPackage.setBounds(50, 150, 200, 25);
        contentPane.add(lblPackage);

        String[] packages = {"Gold Package", "Silver Package", "Bronze Package"};
        c1 = new JComboBox<>(packages);
        c1.setBounds(200, 150, 200, 25);
        c1.setFont(new Font("SansSerif", Font.PLAIN, 14));
        c1.setBackground(Color.WHITE);
        c1.setForeground(new Color(51, 51, 51));
        c1.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
        contentPane.add(c1);

        // Total Persons Section
        JLabel lblPersons = new JLabel("Total Persons:");
        lblPersons.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblPersons.setForeground(new Color(0, 51, 102));
        lblPersons.setBounds(50, 200, 200, 25);
        contentPane.add(lblPersons);

        t1 = new JTextField("0");
        t1.setFont(new Font("SansSerif", Font.PLAIN, 14));
        t1.setBounds(200, 200, 200, 25);
        t1.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
        t1.setForeground(new Color(0, 51, 102));
        contentPane.add(t1);

        // Total Price Section
        JLabel lblPrice = new JLabel("Total Price:");
        lblPrice.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblPrice.setForeground(new Color(0, 51, 102));
        lblPrice.setBounds(50, 250, 200, 25);
        contentPane.add(lblPrice);

        JLabel lblDisplayPrice = new JLabel("Rs 0");
        lblDisplayPrice.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblDisplayPrice.setForeground(new Color(0, 51, 102));
        lblDisplayPrice.setBounds(200, 250, 200, 25);
        contentPane.add(lblDisplayPrice);

        // Check Price Button
        JButton btnCheckPrice = new JButton("Check Price");
        btnCheckPrice.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnCheckPrice.setBounds(50, 300, 150, 30);
        btnCheckPrice.setBackground(new Color(51, 204, 255));
        btnCheckPrice.setForeground(Color.BLACK);
        btnCheckPrice.setFocusPainted(false);
        btnCheckPrice.addActionListener(e -> {
            try {
                String selectedPackage = (String) c1.getSelectedItem();
                int cost = 0;
                switch (selectedPackage) {
                    case "Bronze Package":
                        cost = 12000;
                        break;
                    case "Silver Package":
                        cost = 25000;
                        break;
                    case "Gold Package":
                        cost = 32000;
                        break;
                }
                cost *= Integer.parseInt(t1.getText());
                lblDisplayPrice.setText("Rs " + cost);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for total persons.");
            }
        });
        contentPane.add(btnCheckPrice);

        // Book Now Button
        JButton btnBook = new JButton("Book Now");
        btnBook.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnBook.setBounds(250, 300, 150, 30);
        btnBook.setBackground(new Color(0, 204, 102));
        btnBook.setForeground(Color.WHITE);
        btnBook.setFocusPainted(false);
        btnBook.addActionListener(e -> {
            try {
                if (t1.getText().isEmpty() || Integer.parseInt(t1.getText()) <= 0) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number of persons.");
                    return;
                }

                if (lblDisplayPrice.getText().equals("Rs 0")) {
                    JOptionPane.showMessageDialog(null, "Please calculate the total price before booking.");
                    return;
                }

                Conn c = new Conn();
                String query = "INSERT INTO bookPackage (username, package, persons, totalPrice) VALUES (?, ?, ?, ?)";
                PreparedStatement pstmt = c.getConnection().prepareStatement(query);

                String selectedPackage = (String) c1.getSelectedItem();
                int totalPersons = Integer.parseInt(t1.getText());
                String totalPrice = lblDisplayPrice.getText().replace("Rs ", "").trim();

                pstmt.setString(1, username);
                pstmt.setString(2, selectedPackage);
                pstmt.setInt(3, totalPersons);
                pstmt.setString(4, totalPrice);

                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Package Booked Successfully!");
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Booking failed. Please try again.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error occurred while booking the package. Please try again later.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for total persons.");
            }
        });
        contentPane.add(btnBook);

        // Back Button
        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnBack.setBounds(150, 350, 150, 30);
        btnBack.setBackground(new Color(255, 102, 102));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.addActionListener(e -> setVisible(false));
        contentPane.add(btnBack);
    }
}
