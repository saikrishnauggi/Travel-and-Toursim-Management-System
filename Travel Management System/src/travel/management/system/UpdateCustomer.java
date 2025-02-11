package travel.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateCustomer extends JFrame {
    private JPanel contentPane;
    private JTextField t1, t2, t3, t4, t5, t6, t7, t8, t9;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UpdateCustomer frame = new UpdateCustomer("sampleUser");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public UpdateCustomer(String username) {
        setTitle("Update Customer Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 100, 900, 600);

        // Gradient Background for the main content panel
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(240, 248, 255);
                Color color2 = new Color(135, 206, 235);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Title
        JLabel lblTitle = new JLabel("Update Customer Details");
        lblTitle.setFont(new Font("Sans Serif", Font.BOLD, 30));
        lblTitle.setForeground(new Color(0, 51, 102));
        lblTitle.setBounds(50, 20, 500, 50);
        contentPane.add(lblTitle);

        // Labels and TextFields
        addLabelAndField("Username (Read-Only):", 50, 100, username, true);
        t1 = addField(username, 300, 100, true);

        addLabelAndField("ID Type:", 50, 150, null, false);
        t2 = addField("", 300, 150, false);

        addLabelAndField("Number:", 50, 200, null, false);
        t3 = addField("", 300, 200, false);

        addLabelAndField("Name:", 50, 250, null, false);
        t4 = addField("", 300, 250, false);

        addLabelAndField("Gender:", 50, 300, null, false);
        t5 = addField("", 300, 300, false);

        addLabelAndField("Country:", 50, 350, null, false);
        t6 = addField("", 300, 350, false);

        addLabelAndField("Address:", 50, 400, null, false);
        t7 = addField("", 300, 400, false);

        addLabelAndField("Phone:", 50, 450, null, false);
        t8 = addField("", 300, 450, false);

        addLabelAndField("Email:", 50, 500, null, false);
        t9 = addField("", 300, 500, false);

        // Load existing customer details
        loadCustomerDetails(username);

        // Create a panel for buttons with the same background as left panel
        JPanel buttonPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(240, 248, 255);
                Color color2 = new Color(135, 206, 235);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBounds(650, 100, 200, 450); // Set the position and size for button panel

        // Buttons
        JButton btnUpdate = createButton("Update");
        btnUpdate.addActionListener(e -> updateCustomerDetails(username));
        buttonPanel.add(Box.createVerticalStrut(30)); // Add space between buttons
        buttonPanel.add(btnUpdate);

        JButton btnBack = createButton("Back");
        btnBack.addActionListener(e -> setVisible(false));
        buttonPanel.add(Box.createVerticalStrut(30)); // Add space between buttons
        buttonPanel.add(btnBack);

        contentPane.add(buttonPanel); // Add the button panel to the content pane
    }

    private void addLabelAndField(String labelText, int x, int y, String prefillText, boolean readOnly) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Tahoma", Font.BOLD, 14));
        label.setBounds(x, y, 200, 30);
        contentPane.add(label);

        JTextField field = new JTextField(prefillText != null ? prefillText : "");
        field.setBounds(x + 250, y, 300, 30);
        field.setFont(new Font("Tahoma", Font.PLAIN, 14));
        field.setEditable(!readOnly);
        field.setBorder(BorderFactory.createLineBorder(new Color(173, 216, 230), 1));
        contentPane.add(field);
    }

    private JTextField addField(String text, int x, int y, boolean readOnly) {
        JTextField field = new JTextField(text);
        field.setBounds(x, y, 300, 30);
        field.setFont(new Font("Tahoma", Font.PLAIN, 14));
        field.setEditable(!readOnly);
        field.setBorder(BorderFactory.createLineBorder(new Color(173, 216, 230), 1));
        return field;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 40));
        button.setFont(new Font("Tahoma", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 128, 128));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void loadCustomerDetails(String username) {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM customer WHERE username = '" + username + "'");
            if (rs.next()) {
                t2.setText(rs.getString(2));
                t3.setText(rs.getString(3));
                t4.setText(rs.getString(4));
                t5.setText(rs.getString(5));
                t6.setText(rs.getString(6));
                t7.setText(rs.getString(7));
                t8.setText(rs.getString(8));
                t9.setText(rs.getString(9));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading customer details: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateCustomerDetails(String username) {
        try {
            Conn c = new Conn();  // Assuming Conn is your custom connection class
            Connection conn = c.getConnection();

            // Prepare the update query with placeholders for parameters
            String query = "UPDATE customer SET id_type = ?, number = ?, name = ?, gender = ?, country = ?, address = ?, phone = ?, email = ? WHERE username = ?";
            PreparedStatement pst = conn.prepareStatement(query);

            pst.setString(1, t2.getText());
            pst.setString(2, t3.getText());
            pst.setString(3, t4.getText());
            pst.setString(4, t5.getText());
            pst.setString(5, t6.getText());
            pst.setString(6, t7.getText());
            pst.setString(7, t8.getText());
            pst.setString(8, t9.getText());
            pst.setString(9, username);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Customer details updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            setVisible(false);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error updating customer details: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
