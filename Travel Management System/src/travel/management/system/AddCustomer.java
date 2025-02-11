package travel.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AddCustomer extends JFrame {
    private JTextField t1, t2, t3, t5, t6, t7, t8;
    private JComboBox<String> comboBox;
    private JRadioButton r1, r2;
    private ButtonGroup genderGroup;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AddCustomer frame = new AddCustomer("sampleUser");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AddCustomer(String username) {
        setTitle("Add Customer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 100, 1000, 700);

        // Content Pane with gradient background
        JPanel contentPane = new JPanel() {
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

        // Title Label
        JLabel title = new JLabel("Add New Customer");
        title.setFont(new Font("Sans Serif", Font.BOLD, 34));
        title.setForeground(new Color(0, 51, 102));
        title.setBounds(50, 30, 400, 50);
        contentPane.add(title);

        // Form Panel without white background
        JPanel formPanel = new JPanel();
        formPanel.setBounds(50, 100, 500, 500);
        formPanel.setLayout(null);
        formPanel.setOpaque(false); // Use the gradient background
        contentPane.add(formPanel);

        // Form fields
        JLabel lblUsername = createLabel("Username:", 30, 30);
        formPanel.add(lblUsername);

        // Prefilled username field
        t7 = createTextField(200, 30);
        t7.setText(username); // Prefill with the provided username
        t7.setEditable(false); // Make it read-only if needed
        formPanel.add(t7);

        JLabel lblId = createLabel("ID Type:", 30, 80);
        formPanel.add(lblId);
        comboBox = new JComboBox<>(new String[]{"Passport", "Aadhar Card", "Voter ID", "Driving License"});
        comboBox.setBounds(200, 80, 250, 30);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        formPanel.add(comboBox);

        JLabel lblNumber = createLabel("Number:", 30, 130);
        formPanel.add(lblNumber);
        t1 = createTextField(200, 130);
        formPanel.add(t1);

        JLabel lblName = createLabel("Name:", 30, 180);
        formPanel.add(lblName);
        t2 = createTextField(200, 180);
        formPanel.add(t2);

        JLabel lblGender = createLabel("Gender:", 30, 230);
        formPanel.add(lblGender);
        r1 = new JRadioButton("Male");
        r1.setBounds(200, 230, 80, 30);
        r1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        r1.setBackground(new Color(0, 0, 0, 0)); // Transparent background
        formPanel.add(r1);

        r2 = new JRadioButton("Female");
        r2.setBounds(290, 230, 100, 30);
        r2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        r2.setBackground(new Color(0, 0, 0, 0)); // Transparent background
        formPanel.add(r2);

        genderGroup = new ButtonGroup();
        genderGroup.add(r1);
        genderGroup.add(r2);

        JLabel lblCountry = createLabel("Country:", 30, 280);
        formPanel.add(lblCountry);
        t3 = createTextField(200, 280);
        formPanel.add(t3);

        JLabel lblAddress = createLabel("Address:", 30, 330);
        formPanel.add(lblAddress);
        t5 = createTextField(200, 330);
        formPanel.add(t5);

        JLabel lblPhone = createLabel("Phone:", 30, 380);
        formPanel.add(lblPhone);
        t6 = createTextField(200, 380);
        formPanel.add(t6);

        JLabel lblEmail = createLabel("Email:", 30, 430);
        formPanel.add(lblEmail);
        t8 = createTextField(200, 430);
        formPanel.add(t8);

        // Buttons at the bottom-right
        JButton addButton = createButton("Add Customer", 700, 520);
        addButton.setBackground(new Color(0, 128, 128));
        addButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Customer Added Successfully"));
        contentPane.add(addButton);

        JButton backButton = createButton("Back", 700, 580);
        backButton.setBackground(new Color(178, 34, 34));
        backButton.addActionListener(e -> setVisible(false));
        contentPane.add(backButton);

        // Cropped Image on the right side
        JLabel imageLabel = new JLabel(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("travel/management/system/icons/newcustomer.jpg"))
                .getImage().getScaledInstance(350, 500, Image.SCALE_SMOOTH)));
        imageLabel.setBounds(600, 100, 350, 400);
        contentPane.add(imageLabel);
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 150, 30);
        label.setFont(new Font("Tahoma", Font.BOLD, 16));
        label.setForeground(new Color(0, 51, 102));
        return label;
    }

    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 250, 30);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(new Color(173, 216, 230), 1));
        return textField;
    }

    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 200, 40);
        button.setFont(new Font("Tahoma", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }
}
