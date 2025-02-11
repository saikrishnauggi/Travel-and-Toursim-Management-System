package travel.management.system;

import java.awt.*;
import javax.swing.*;

public class Splash {
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            SplashFrame splashFrame = new SplashFrame();
            splashFrame.setVisible(true);
            splashFrame.showSplashScreen();
        });
    }
}

class SplashFrame extends JFrame {
    private static final int INITIAL_WIDTH = 1030;
    private static final int INITIAL_HEIGHT = 750;
    private static final int FINAL_WIDTH = 600;
    private static final int FINAL_HEIGHT = 400;
    private static final int SLEEP_INTERVAL = 10;
    private static final int SPLASH_DURATION = 7000;

    SplashFrame(){
        setUndecorated(true);
        setLayout(new BorderLayout());
        
        // Set the splash image
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("Travel/Management/System/icons/splash.jpg"));
        Image img = icon.getImage().getScaledInstance(INITIAL_WIDTH, INITIAL_HEIGHT, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(img);
        
        JLabel label = new JLabel(scaledIcon);
        add(label, BorderLayout.CENTER);
        
        // Set initial size and position
        setSize(INITIAL_WIDTH, INITIAL_HEIGHT);
        setLocationRelativeTo(null); // Center the window on the screen
    }

    public void showSplashScreen(){
        Thread animationThread = new Thread(() -> {
            int x = 1;
            for (int i = 2; i <= FINAL_WIDTH; i += 10, x += 7) {
                setLocation(900 - ((i + x) / 2), 500 - (i / 2));
                setSize(i + x, i);
                try {
                    Thread.sleep(SLEEP_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Wait for the splash duration before transitioning to the login screen
            try {
                Thread.sleep(SPLASH_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.setVisible(false);
            showLoginScreen();
        });
        animationThread.start();
    }

    private void showLoginScreen() {
        // Transition to the Login screen (assuming Login class exists)
        SwingUtilities.invokeLater(() -> {
            Login loginFrame = new Login();
            loginFrame.setVisible(true);
        });
    }
}
