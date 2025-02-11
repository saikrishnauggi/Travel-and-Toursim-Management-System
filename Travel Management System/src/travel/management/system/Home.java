package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Home extends JFrame {
    private String username;

    public static void main(String[] args) {
        // Launch the home page with a sample username (replace with actual username in production)
        new Home("TestUser").setVisible(true);
    }

    public Home(String username) {
        super("Travel and Tourism Management System");
        this.username = username;

        // Set up window properties
        setExtendedState(JFrame.MAXIMIZED_BOTH);  // Make the frame full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // Add the background image
        addBackgroundImage();

        // Create and add sidebar (left panel)
        JPanel leftPanel = createLeftPanel();

        // Wrap the leftPanel in a JScrollPane to make it scrollable
        JScrollPane scrollPane = new JScrollPane(leftPanel);
        scrollPane.setPreferredSize(new Dimension(300, getHeight()));  // Set preferred width and height
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Always show vertical scrollbar

        // Increase the scroll speed using MouseWheelListener
        scrollPane.getVerticalScrollBar().addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                // Change the number of steps for scrolling (adjust the value for faster scrolling)
                int scrollAmount = e.getUnitsToScroll();
                if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
                    int scrollSpeed = 15; // Increase this value for faster scrolling
                    scrollAmount *= scrollSpeed;
                }
                JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
                verticalScrollBar.setValue(verticalScrollBar.getValue() + scrollAmount);
            }
        });

        add(scrollPane, BorderLayout.WEST);

        // Make window sleek and user-friendly
        setUndecorated(false);  // Ensure the window isn't undetectable, and has a border/frame
    }

    // Adds a background image to the center of the window
    private void addBackgroundImage() {
        try {
            ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("travel/management/system/icons/home.jpg"));
            Image img = icon.getImage().getScaledInstance(1950, 1000, Image.SCALE_DEFAULT);
            JLabel backgroundLabel = new JLabel(new ImageIcon(img));
            backgroundLabel.setBounds(0, 0, 1950, 1000);
            add(backgroundLabel, BorderLayout.CENTER);
        } catch (Exception e) {
            System.out.println("Warning: Background image not found or failed to load.");
        }
    }

    // Creates the left sidebar for navigation buttons
    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(new Color(36, 37, 42));  // Darker background for a modern look

        // Title label for the sidebar
        JLabel titleLabel = new JLabel("Travel & Tourism");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(30));  // Add space above the title
        leftPanel.add(titleLabel);

        // Add navigation buttons
        addMenuButton(leftPanel, "CUSTOMER", e -> openWindow(new AddCustomer(username)));
        addMenuButton(leftPanel, "UPDATE CUSTOMER", e -> {
            openWindow(new UpdateCustomer(username));
        });
       
        

        addMenuButton(leftPanel, "PACKAGES", e -> openWindow(new CheckPackage()));
        addMenuButton(leftPanel, "BOOK PACKAGE", e -> openWindow(new BookPackage(username)));
        addMenuButton(leftPanel, "VIEW PACKAGE", e -> openWindow(new ViewPackage(username)));

        addMenuButton(leftPanel, "HOTELS", e -> openWindow(new BookHotel(username)));
        
        addMenuButton(leftPanel, "VIEW BOOKED HOTEL", e -> openWindow(new ViewBookedHotel(username)));

        addMenuButton(leftPanel, "DESTINATION", e -> openWindow(new Destination()));

        addMenuButton(leftPanel, "PAYMENT", e -> openWindow(new Payment()));

        addMenuButton(leftPanel, "UTILITY", e -> openUtility("notepad.exe"));
        addMenuButton(leftPanel, "CALCULATOR", e -> openUtility("calc.exe"));

        addMenuButton(leftPanel, "ABOUT", e -> openWindow(new About()));  // The About button

        // Add some spacing at the bottom
        leftPanel.add(Box.createVerticalGlue());

        return leftPanel;
    }

    // Helper method to add a navigation button with hover effects
    private void addMenuButton(JPanel panel, String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Roboto", Font.PLAIN, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(48, 49, 54));  // Dark gray for buttons
        button.setFocusPainted(false);  // Remove focus outline
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(250, 50));

        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(70, 70, 70));  // Lighter gray on hover
            }
            public void mouseExited(MouseEvent evt) {
                button.setBackground(new Color(48, 49, 54));  // Reset to original color
            }
        });

        // Add smooth animation effect for button press
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setContentAreaFilled(false);  // Make background transparent when clicked
        button.addActionListener(action);

        panel.add(Box.createVerticalStrut(10));  // Space between buttons
        panel.add(button);
    }

    // Open a new window (frame) based on the action performed
    private void openWindow(JFrame frame) {
        try {
            // Set the close operation to dispose the child window, keeping the main window open
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to open window: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Open utility like Notepad or Calculator
    private void openUtility(String command) {
        try {
            Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to open utility: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
