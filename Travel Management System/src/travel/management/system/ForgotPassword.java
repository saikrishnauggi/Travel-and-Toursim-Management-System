package travel.management.system;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;
import java.awt.event.*;

public class ForgotPassword extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField usernameField, nameField, securityQuestionField, answerField, passwordField;
    private JButton searchButton, retrieveButton, backButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ForgotPassword().setVisible(true));
    }

    public ForgotPassword() {
        setTitle("Forgot Password - Travel Management System");
        setBounds(500, 200, 900, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Forgot Password");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setForeground(new Color(34, 139, 34));
        titleLabel.setBounds(320, 20, 300, 30);
        contentPane.add(titleLabel);

        addLabelAndField("Username", 80, usernameField = new JTextField(), true);
        addLabelAndField("Name", 120, nameField = new JTextField(), false);
        addLabelAndField("Security Question", 160, securityQuestionField = new JTextField(), false);
        addLabelAndField("Answer", 200, answerField = new JTextField(), true);
        addLabelAndField("Password", 240, passwordField = new JTextField(), false);

        // Buttons
        searchButton = createStyledButton("Search", 440, 80);
        retrieveButton = createStyledButton("Retrieve", 440, 200);
        backButton = createStyledButton("Back", 300, 300);

        searchButton.addActionListener(this);
        retrieveButton.addActionListener(this);
        backButton.addActionListener(this);

        contentPane.add(searchButton);
        contentPane.add(retrieveButton);
        contentPane.add(backButton);

        // Image
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("Travel/Management/System/icons/forgotpassword.jpg"));
        Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setBounds(650, 80, 200, 200);
        contentPane.add(imageLabel);

        // Panel
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 2), "Retrieve Password",
                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(34, 139, 34)));
        panel.setBackground(new Color(245, 245, 245));
        panel.setBounds(30, 60, 850, 250);
        contentPane.add(panel);
    }

    private void addLabelAndField(String labelText, int y, JTextField field, boolean editable) {
        JLabel label = new JLabel(labelText + ":");
        label.setFont(new Font("Tahoma", Font.BOLD, 13));
        label.setBounds(100, y, 150, 30);
        contentPane.add(label);

        field.setFont(new Font("Tahoma", Font.PLAIN, 13));
        field.setBounds(250, y + 5, 170, 25);
        field.setEditable(editable);
        contentPane.add(field);
    }

    private JButton createStyledButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setFont(new Font("Tahoma", Font.BOLD, 12));
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setBounds(x, y, 100, 30);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            Conn con = new Conn();

            if (ae.getSource() == searchButton) {
                String username = usernameField.getText().trim();

                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter your username!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String sql = "SELECT * FROM account WHERE username=?";
                PreparedStatement st = con.c.prepareStatement(sql);
                st.setString(1, username);
                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    nameField.setText(rs.getString("name"));
                    securityQuestionField.setText(rs.getString("question"));
                } else {
                    JOptionPane.showMessageDialog(this, "Username not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else if (ae.getSource() == retrieveButton) {
                String answer = answerField.getText().trim();

                if (answer.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter the answer to your security question!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String sql = "SELECT * FROM account WHERE answer=?";
                PreparedStatement st = con.c.prepareStatement(sql);
                st.setString(1, answer);
                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    passwordField.setText(rs.getString("password"));
                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect answer!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else if (ae.getSource() == backButton) {
                this.setVisible(false);
                new Login().setVisible(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while processing your request.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
