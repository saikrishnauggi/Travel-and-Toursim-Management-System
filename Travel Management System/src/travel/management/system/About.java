package travel.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class About extends JFrame implements ActionListener {

    JButton exitButton;
    JLabel titleLabel;
    JTextArea aboutText;

    public About() {
        setTitle("About Project");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Changed from EXIT to DISPOSE
        setBounds(600, 220, 600, 500);
        setResizable(false);

        // Main Container Panel
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());
        containerPanel.setBackground(new Color(245, 245, 245));
        add(containerPanel);

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(60, 120, 200));
        titlePanel.setPreferredSize(new Dimension(600, 80));
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        containerPanel.add(titlePanel, BorderLayout.NORTH);

        titleLabel = new JLabel("About Project");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

        // Text Area Panel
        JPanel textPanel = new JPanel();
        textPanel.setBackground(new Color(245, 245, 245));
        textPanel.setLayout(new BorderLayout());
        containerPanel.add(textPanel, BorderLayout.CENTER);

        aboutText = new JTextArea();
        aboutText.setText(
                "The objective of the Travel and Tourism Management System project is to develop a system "
                        + "that automates the processes and activities of travel management. The purpose is to design a "
                        + "system that allows users to perform operations related to traveling efficiently.\n\n"
                        + "This application provides access to information about travel destinations, tour tracking, and "
                        + "travel agency details with ease.\n\n"
                        + "Advantages:\n"
                        + "- Accurate information\n"
                        + "- Simplifies manual work\n"
                        + "- Minimizes documentation\n"
                        + "- Up-to-date information\n"
                        + "- User-friendly interface with alerts\n"
                        + "- Travelers' details and booking confirmations\n"
        );
        aboutText.setFont(new Font("Arial", Font.PLAIN, 16));
        aboutText.setEditable(false);
        aboutText.setWrapStyleWord(true);
        aboutText.setLineWrap(true);
        aboutText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Add custom scroll behavior to speed up the scroll
        JScrollPane scrollPane = new JScrollPane(aboutText);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);  // Set a faster scroll speed
        textPanel.add(scrollPane, BorderLayout.CENTER);

        // Footer Panel
        JPanel footerPanel = new JPanel();
        footerPanel.setPreferredSize(new Dimension(600, 60));
        footerPanel.setBackground(new Color(245, 245, 245));
        footerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        containerPanel.add(footerPanel, BorderLayout.SOUTH);

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.setBackground(new Color(200, 70, 60));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        exitButton.addActionListener(this);
        footerPanel.add(exitButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose(); // This closes only the About window
    }

    public static void main(String[] args) {
        new About().setVisible(true);
    }
}
