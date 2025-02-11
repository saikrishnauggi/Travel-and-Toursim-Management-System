package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Loading extends JFrame implements Runnable {

    private JPanel contentPane;
    private JLabel lblLoadingText;
    private String username;
    private int s;
    private Thread th;
    private Timer timer;
    private JProgressBar progressBar;  // Progress bar for smoother loading animation

    public static void main(String[] args) {
        new Loading("").setVisible(true);
    }

    public void setUploading() {
        setVisible(false);
        th.start();
    }

    public void run() {
        try {
            for (int i = 1; i <= 100; i++) {  // Start progress from 1% to 100%
                s = i;
                progressBar.setValue(s);  // Update progress bar
                repaint();  // Repaint to show progress
                Thread.sleep(30);  // Adjust speed for smooth animation
            }
            // When progress reaches 100%, transition to the next page
            setVisible(false);
            new Home(username).setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Loading(String username) {
        this.username = username;
        th = new Thread((Runnable) this);

        // Set the JFrame properties
        setBounds(600, 300, 600, 400);
        contentPane = new JPanel() {
            // Apply a smooth gradient background for a modern touch
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(50, 115, 220);  // Light Blue
                Color color2 = new Color(30, 90, 150);   // Dark Blue
                GradientPaint gradient = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Title label with modern font style
        JLabel lbllibraryManagement = new JLabel("Travel and Tourism Application");
        lbllibraryManagement.setForeground(Color.WHITE);
        lbllibraryManagement.setFont(new Font("Arial", Font.BOLD, 32));
        lbllibraryManagement.setBounds(50, 50, 500, 40);
        contentPane.add(lbllibraryManagement);

        // Animated loading text with a typing effect
        lblLoadingText = new JLabel("Loading");
        lblLoadingText.setForeground(Color.WHITE);
        lblLoadingText.setFont(new Font("Verdana", Font.BOLD, 22));
        lblLoadingText.setBounds(230, 290, 200, 30);  // Positioned below the circle
        contentPane.add(lblLoadingText);

        // Progress Bar (new element to create a smooth loading experience)
        progressBar = new JProgressBar();
        progressBar.setFont(new Font("Arial", Font.BOLD, 14));
        progressBar.setStringPainted(true);  // Show percentage text
        progressBar.setForeground(new Color(255, 215, 0));  // Gold color
        progressBar.setBackground(new Color(50, 115, 220));  // Dark Blue background
        progressBar.setBounds(100, 200, 400, 30);  // Position and size of the progress bar
        contentPane.add(progressBar);

        // Timer for animated text changes
        timer = new Timer(500, new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (count == 0) {
                    lblLoadingText.setText("Loading.");
                    count = 1;
                } else if (count == 1) {
                    lblLoadingText.setText("Loading..");
                    count = 2;
                } else {
                    lblLoadingText.setText("Loading...");
                    count = 0;
                }
            }
        });
        timer.start();

        // Make the window undecorated and start the animation
        setUndecorated(true);
        setUploading();
    }
}
