package travel.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Payment extends JFrame {

    public Payment() {
        setLayout(null);
        setBounds(450, 200, 800, 600); // Center the frame on most screens
        setTitle("Payment Page");

        // Header Label
        JLabel headerLabel = new JLabel("Pay Using Paytm");
        headerLabel.setFont(new Font("Serif", Font.BOLD, 36));
        headerLabel.setForeground(new Color(50, 100, 200));
        headerLabel.setBounds(230, 20, 400, 50);
        add(headerLabel);

        // UPI Details Label
        JLabel upiLabel = new JLabel("Website UPI ID: 9398036854@axl");
        upiLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        upiLabel.setForeground(new Color(80, 120, 160));
        upiLabel.setBounds(230, 80, 400, 30);
        add(upiLabel);

        // Paytm Image
        ImageIcon paytmIcon = new ImageIcon(ClassLoader.getSystemResource("travel/management/system/icons/paytm.jpeg"));
        Image scaledImage = paytmIcon.getImage().getScaledInstance(800, 400, Image.SCALE_SMOOTH);
        JLabel paytmImage = new JLabel(new ImageIcon(scaledImage));
        paytmImage.setBounds(0, 150, 800, 400);
        add(paytmImage);

        // Pay Button
        JButton payButton = new JButton("Pay");
        payButton.setFont(new Font("Arial", Font.BOLD, 16));
        payButton.setBackground(new Color(60, 180, 75));
        payButton.setForeground(Color.WHITE);
        payButton.setFocusPainted(false);
        payButton.setToolTipText("Proceed to Pay via Paytm");
        payButton.setBounds(350, 120, 100, 40);
        payButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Paytm().setVisible(true);
            }
        });
        add(payButton);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(200, 70, 60));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setToolTipText("Go Back to the Previous Page");
        backButton.setBounds(470, 120, 100, 40);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        add(backButton);

        // Set background color
        getContentPane().setBackground(new Color(240, 250, 255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Payment().setVisible(true);
    }
}
