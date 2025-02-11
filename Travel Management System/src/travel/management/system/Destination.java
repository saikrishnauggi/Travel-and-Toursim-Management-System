package travel.management.system;

import javax.swing.*;
import java.awt.*;

public class Destination extends JFrame implements Runnable {
    private JLabel[] slides; // Array to store slide labels
    private Thread th;

    public static void main(String[] args) {
        new Destination().setVisible(true);
    }

    public Destination() {
        setTitle("Travel Destinations");
        setBounds(300, 100, 900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(220, 240, 250));
        setLayout(null);

        JLabel title = new JLabel("Explore To Choose Destinations");
        title.setFont(new Font("Serif", Font.BOLD, 28));
        title.setForeground(new Color(60, 80, 120));
        title.setBounds(200, 20, 500, 40);
        add(title);

        // Initializing the slide array
        slides = new JLabel[10];
        String[] imagePaths = {
            "travel/management/system/icons/dest1.jpeg",
            "travel/management/system/icons/dest2.png",
            "travel/management/system/icons/dest3.jpg",
            "travel/management/system/icons/dest4.jpg",
            "travel/management/system/icons/dest5.jpg",
            "travel/management/system/icons/dest6.jpg",
            "travel/management/system/icons/dest7.jpeg",
            "travel/management/system/icons/dest8.jpg",
            "travel/management/system/icons/dest9.jpg",
            "travel/management/system/icons/dest10.jpg"
        };

        // Loading images into the slide array
        for (int i = 0; i < slides.length; i++) {
            ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(imagePaths[i]));
            Image scaledImage = icon.getImage().getScaledInstance(900, 700, Image.SCALE_SMOOTH);
            slides[i] = new JLabel(new ImageIcon(scaledImage));
            slides[i].setBounds(0, 70, 900, 600);
            slides[i].setVisible(false);
            add(slides[i]);
        }

        // Start the animation
        th = new Thread(this);
        th.start();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < slides.length; i++) {
                slides[i].setVisible(true);
                Thread.sleep(2800); // Show each image for 2.8 seconds
                slides[i].setVisible(false);
            }
            setVisible(false); // Close the window after showing all slides
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
