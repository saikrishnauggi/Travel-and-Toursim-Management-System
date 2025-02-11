package travel.management.system;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;

public class Signup extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField usernameField, nameField, answerField;
    private JPasswordField passwordField;
    private JButton createButton, backButton, togglePasswordButton;
    private JComboBox<String> securityQuestionBox;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Signup().setVisible(true));
    }

    public Signup() {
        setTitle("Signup - Travel Management System");
        setBounds(450, 200, 800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Create Account");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setForeground(new Color(34, 139, 34));
        titleLabel.setBounds(280, 20, 300, 30);
        contentPane.add(titleLabel);

        // Labels and Fields
        addLabelAndField("Username", 80, usernameField = new JTextField());
        addLabelAndField("Name", 130, nameField = new JTextField());
        addLabelAndField("Password", 180, passwordField = new JPasswordField(), true);
        addLabelAndComboBox("Security Question", 230, securityQuestionBox = new JComboBox<>(
                new String[]{"Your NickName?", "Your Lucky Number?", "Your child SuperHero?", "Your childhood Name?"}
        ));
        addLabelAndField("Answer", 280, answerField = new JTextField());

        // Buttons
        createButton = createStyledButton("Create", 180, 340, Color.BLACK, Color.WHITE);
        backButton = createStyledButton("Back", 400, 340, Color.BLACK, Color.WHITE);

        // Add actions
        createButton.addActionListener(this);
        backButton.addActionListener(this);

        // Add to panel
        contentPane.add(createButton);
        contentPane.add(backButton);

        // Image
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("Travel/Management/System/icons/signup.png"));
        Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setBounds(550, 100, 200, 200);
        contentPane.add(imageLabel);

        // Panel border
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 0), 2), "Signup",
                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(34, 139, 34)));
        panel.setBounds(30, 50, 720, 350);
        panel.setBackground(new Color(245, 245, 245));
        contentPane.add(panel);
    }

    private void addLabelAndField(String labelText, int y, JTextField field) {
        addLabelAndField(labelText, y, field, false);
    }

    private void addLabelAndField(String labelText, int y, JTextField field, boolean isPasswordField) {
        JLabel label = new JLabel(labelText + ":");
        label.setFont(new Font("Tahoma", Font.BOLD, 14));
        label.setBounds(50, y, 150, 30);
        contentPane.add(label);

        field.setBounds(200, y + 5, 250, 25);
        contentPane.add(field);

        if (isPasswordField) {
            togglePasswordButton = new JButton("Show");
            togglePasswordButton.setBounds(460, y + 5, 70, 25);
            togglePasswordButton.addActionListener(e -> togglePasswordVisibility());
            contentPane.add(togglePasswordButton);
        }
    }

    private void addLabelAndComboBox(String labelText, int y, JComboBox<String> comboBox) {
        JLabel label = new JLabel(labelText + ":");
        label.setFont(new Font("Tahoma", Font.BOLD, 14));
        label.setBounds(50, y, 150, 30);
        contentPane.add(label);

        comboBox.setBounds(200, y + 5, 250, 25);
        contentPane.add(comboBox);
    }

    private JButton createStyledButton(String text, int x, int y, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Tahoma", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setBounds(x, y, 120, 40);
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(new Color(0, 0, 0), 1));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void togglePasswordVisibility() {
        if (passwordField.getEchoChar() == '\u2022') {
            passwordField.setEchoChar((char) 0);
            togglePasswordButton.setText("Hide");
        } else {
            passwordField.setEchoChar('\u2022');
            togglePasswordButton.setText("Show");
        }
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            Conn con = new Conn();

            if (ae.getSource() == createButton) {
                if (usernameField.getText().isEmpty() || nameField.getText().isEmpty() ||
                        new String(passwordField.getPassword()).isEmpty() || answerField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String sql = "insert into account(username, name, password, question, answer) values(?, ?, ?, ?, ?)";
                PreparedStatement st = con.c.prepareStatement(sql);

                st.setString(1, usernameField.getText());
                st.setString(2, nameField.getText());
                st.setString(3, new String(passwordField.getPassword()));
                st.setString(4, (String) securityQuestionBox.getSelectedItem());
                st.setString(5, answerField.getText());

                int i = st.executeUpdate();
                if (i > 0) {
                    JOptionPane.showMessageDialog(this, "Account Created Successfully!");
                }

                usernameField.setText("");
                nameField.setText("");
                passwordField.setText("");
                answerField.setText("");
            }

            if (ae.getSource() == backButton) {
                this.setVisible(false);
                new Login().setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
