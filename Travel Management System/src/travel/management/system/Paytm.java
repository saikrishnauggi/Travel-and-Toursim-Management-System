package travel.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Paytm extends JFrame {

    Paytm() {
        setTitle("Pay via Paytm");
        setLayout(new BorderLayout());
        setSize(800, 600);
        setLocation(450, 200);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(new Color(60, 120, 200));
        headerPanel.setPreferredSize(new Dimension(800, 50));

        JLabel headerLabel = new JLabel("Pay Your Bill with Paytm", JLabel.CENTER);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 20));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel, BorderLayout.CENTER);

        add(headerPanel, BorderLayout.NORTH);

        // JEditorPane for displaying the webpage
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);

        try {
            editorPane.setPage("https://paytm.com/electricity-bill-payment");
        } catch (Exception e) {
            editorPane.setContentType("text/html");
            editorPane.setText("<html><h1 style='color:red;'>Could not load the page.</h1></html>");
        }

        JScrollPane scrollPane = new JScrollPane(editorPane);
        add(scrollPane, BorderLayout.CENTER);

        // Footer Panel with Back Button
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setBackground(new Color(240, 240, 240));

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.setBackground(new Color(200, 70, 60));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        footerPanel.add(backButton);

        add(footerPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Paytm().setVisible(true);
    }
}
