package travel.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CheckPackage extends JFrame {

    public static void main(String[] args) {
        new CheckPackage().setVisible(true);
    }

    CheckPackage() {
        setTitle("Tourism Packages");
        setBounds(550, 220, 900, 600);  
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        
        // Create tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        
        String[] package1 = new String[]{
            "package1.jpg", "<html><b style='color:#cd5c5c;'>BRONZE PACKAGE</b></html>", 
            "<html><i>6 Days and 7 Nights</i></html>", 
            "<html><b style='color:#00bfff;'>Airport Assistance</b></html>", 
            "<html>Half Day <b style='color:#ff6347;'>City Tour</b></html>", 
            "<html>Welcome <b>Drinks</b> on Arrival</html>", 
            "<html><b style='color:#ff6347;'>Daily Buffet</b></html>", 
            "<html>Full Day <b>3 Island Cruise</b></html>", 
            "<html>English <b>Speaking Guide</b></html>", 
            "<html><font color='#ff4500'><b>BOOK NOW</b></font></html>", 
            "<html><font color='#f0e68c'><b>Summer Special!</b></font></html>", 
            "<html><b><font color='#32cd32'>Rs 12000 only</font></b></html>"
        };
        tabbedPane.addTab("Package 1", null, createPackage(package1));

        String[] package2 = new String[]{
            "package2.jpg", "<html><b style='color:#c0c0c0;'>SILVER PACKAGE</b></html>", 
            "<html><i>4 Days and 3 Nights</i></html>", 
            "<html><b style='color:#ff4500;'>Toll Free & Tickets Included</b></html>", 
            "<html><b>Meet & Greet</b> at Airport</html>", 
            "<html>Welcome <b>Drinks</b> on Arrival</html>", 
            "<html><b style='color:#f08080;'>Night Safari</b></html>", 
            "<html>Full Day <b>3 Island Cruise</b></html>", 
            "<html><b>Cruise with Dinner</b></html>", 
            "<html><font color='#ff4500'><b>BOOK NOW</b></font></html>", 
            "<html><font color='#1e90ff'><b>Winter Special!</b></font></html>", 
            "<html><b><font color='#32cd32'>Rs 25000 only</font></b></html>"
        };
        tabbedPane.addTab("Package 2", null, createPackage(package2));

        String[] package3 = new String[]{
            "package3.jpg", "<html><b style='color:#ffcc00;'>GOLD PACKAGE</b></html>", 
            "<html><i>6 Days and 5 Nights</i></html>", 
            "<html><b style='color:#32cd32;'>Return Airfare</b></html>", 
            "<html>Free <b>Clubbing, Horse Riding</b> & more</html>", 
            "<html>Welcome <b>Drinks</b> on Arrival</html>", 
            "<html><b style='color:#ff6347;'>Daily Buffet</b></html>", 
            "<html>Stay in <b>5-Star Hotel</b></html>", 
            "<html><b>BBQ Dinner</b></html>", 
            "<html><font color='#ff4500'><b>BOOK NOW</b></font></html>", 
            "<html><font color='#228b22'><b>Winter Special!</b></font></html>", 
            "<html><b><font color='#32cd32'>Rs 32000 only</font></b></html>"
        };
        tabbedPane.addTab("Package 3", null, createPackage(package3));
        
        add(tabbedPane, BorderLayout.CENTER);
    }

    public JPanel createPackage(String[] pack) {
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(240, 248, 255));  // Light blue background

        // Package Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("travel/management/system/icons/" + pack[0]));
        Image i3 = i1.getImage().getScaledInstance(550, 300, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(400, 0, 450, 420);
        p1.add(l1);

        // Package Name (Title)
        JLabel lblName = new JLabel(pack[1]);
        lblName.setFont(new Font("Arial", Font.BOLD, 32));  // Bold font for better visibility
        lblName.setBounds(50, 20, 350, 40);
        p1.add(lblName);

        // Package Details (Each feature)
        String[] details = { pack[2], pack[3], pack[4], pack[5], pack[6], pack[7], pack[8] };
        int yPosition = 80;
        for (String detail : details) {
            JLabel lblDetail = new JLabel(detail);
            lblDetail.setForeground(new Color(50, 50, 50));  // Dark gray for detail text
            lblDetail.setFont(new Font("Arial", Font.PLAIN, 16));
            lblDetail.setBounds(35, yPosition, 300, 20);
            p1.add(lblDetail);
            yPosition += 30;
        }

        // Special Offers
        JLabel lblSpecial = new JLabel(pack[9]);
        lblSpecial.setForeground(new Color(255, 69, 0));  // Orange-red for special offers
        lblSpecial.setFont(new Font("Arial", Font.BOLD, 18));
        lblSpecial.setBounds(35, yPosition, 300, 25);
        p1.add(lblSpecial);

        // Price and Booking
        JLabel lblPrice = new JLabel(pack[11]);
        lblPrice.setForeground(new Color(0, 204, 0));  // Green color for price
        lblPrice.setFont(new Font("Arial", Font.BOLD, 30));
        lblPrice.setBounds(600, 460, 400, 40);
        p1.add(lblPrice);

        // Booking Button (Improved)
        JButton btnBook = new JButton(pack[10]);
        btnBook.setBackground(new Color(0, 128, 0));  // Dark green background for the button
        btnBook.setFont(new Font("Arial", Font.BOLD, 20));
        btnBook.setForeground(Color.WHITE);
        btnBook.setBounds(35, yPosition + 40, 200, 60);
        btnBook.setFocusPainted(false);
        btnBook.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));  // Blue border
        btnBook.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Hover Effect for Button
        btnBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBook.setBackground(new Color(0, 255, 0));  // Light green when hovered
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBook.setBackground(new Color(0, 128, 0));  // Original dark green
            }
        });
        
        btnBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Booking Now...");
            }
        });
        p1.add(btnBook);

        return p1;
    }
}
