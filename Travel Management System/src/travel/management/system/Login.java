package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    private JPanel panel;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton b1, b2, b3;

    public Login() {
        // Frame settings
        setTitle("Login - Travel Management System");
        setBounds(550, 250, 700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set a clean background color and layout
        setBackground(new Color(255, 255, 204));
        panel = new JPanel();
        panel.setBackground(new Color(245, 245, 245));
        setContentPane(panel);
        panel.setLayout(null);

        // Title label with a sleek font
        JLabel titleLabel = new JLabel("Travel Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(46, 139, 87));
        titleLabel.setBounds(160, 30, 380, 30);
        panel.add(titleLabel);

        // Username label and text field
        JLabel l1 = new JLabel("Username : ");
        l1.setFont(new Font("Arial", Font.PLAIN, 14));
        l1.setBounds(120, 100, 95, 24);
        panel.add(l1);

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBounds(210, 100, 250, 30);
        textField.setBorder(BorderFactory.createLineBorder(new Color(46, 139, 87), 2));
        panel.add(textField);

        // Password label and field
        JLabel l2 = new JLabel("Password : ");
        l2.setFont(new Font("Arial", Font.PLAIN, 14));
        l2.setBounds(120, 140, 95, 24);
        panel.add(l2);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBounds(210, 140, 250, 30);
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(46, 139, 87), 2));
        panel.add(passwordField);

        // Login button with hover effects
        b1 = new JButton("Login");
        b1.addActionListener(this);
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(46, 139, 87));
        b1.setFont(new Font("Arial", Font.BOLD, 16));
        b1.setBounds(210, 200, 250, 40);
        b1.setFocusPainted(false);
        b1.setBorder(BorderFactory.createLineBorder(new Color(46, 139, 87), 2));
        b1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b1.setBackground(new Color(34, 139, 34));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b1.setBackground(new Color(46, 139, 87));
            }
        });
        panel.add(b1);

        // SignUp button with hover effects
        b2 = new JButton("SignUp");
        b2.addActionListener(this);
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(70, 130, 180));
        b2.setFont(new Font("Arial", Font.BOLD, 16));
        b2.setBounds(210, 260, 115, 40);
        b2.setFocusPainted(false);
        b2.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));
        b2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b2.setBackground(new Color(0, 0, 128));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b2.setBackground(new Color(70, 130, 180));
            }
        });
        panel.add(b2);

        // Forgot Password button with hover effects
        b3 = new JButton("Forgot Password?");
        b3.addActionListener(this);
        b3.setForeground(new Color(255, 99, 71));
        b3.setBackground(new Color(255, 245, 230));
        b3.setFont(new Font("Arial", Font.PLAIN, 12));
        b3.setBounds(335, 260, 125, 40);
        b3.setFocusPainted(false);
        b3.setBorder(BorderFactory.createLineBorder(new Color(255, 99, 71), 2));
        b3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b3.setBackground(new Color(255, 160, 122));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b3.setBackground(new Color(255, 245, 230));
            }
        });
        panel.add(b3);

        // Stylish login icon on the top-right
        ImageIcon c1 = new ImageIcon(ClassLoader.getSystemResource("Travel/Management/System/icons/login.png"));
        Image i1 = c1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i1);

        JLabel l6 = new JLabel(i2);
        l6.setBounds(520, 70, 150, 150);
        panel.add(l6);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            String username = textField.getText();
            String password = new String(passwordField.getPassword());
            try {
                Conn con = new Conn();
                String sql = "SELECT * FROM account WHERE username=? AND password=?";
                PreparedStatement st = con.c.prepareStatement(sql);
                st.setString(1, username);
                st.setString(2, password);

                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    this.setVisible(false);
                    new Loading(username).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login or Password!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (ae.getSource() == b2) {
            setVisible(false);
            Signup su = new Signup();
            su.setVisible(true);
        }
        if (ae.getSource() == b3) {
            setVisible(false);
            ForgotPassword forgot = new ForgotPassword();
            forgot.setVisible(true);
        }
    }

    public static void main(String[] args) {
        // Start the Login screen
        new Login().setVisible(true);
    }
}
