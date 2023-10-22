import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.Stack;
import javax.sound.sampled.*;
import javax.swing.border.MatteBorder;
import java.util.HashMap;

public class CupCakeSimulator extends JFrame {

    private ToppingsStack toppingsStack = new ToppingsStack();
    private JLabel toppingLabel;
    private JComboBox<String> flavorDropdown;
    private JComboBox<String> toppingsDropdown;
    private boolean isMuted = false;
    private Clip clip;

    public CupCakeSimulator() {
        setTitle("Cupcake Maker Simulator");
        setSize(1920, 411);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JPanel controlPanel = new JPanel();
        controlPanel.setBorder(new MatteBorder(5, 0, 0, 0, (Color) new Color(194, 62, 80)));
        controlPanel.setBackground(new Color(245, 195, 195));
        controlPanel.setBounds(0, 177, 535, 195);
        getContentPane().add(controlPanel);

        JButton addButton = new JButton("Add Topping");
        addButton.setBackground(new Color(245, 195, 195));
        addButton.setBounds(20, 60, 150, 30);
        JButton removeButton = new JButton("Remove Topping");
        removeButton.setBackground(new Color(245, 195, 195));
        removeButton.setBounds(20, 101, 150, 30);
        JButton doneButton = new JButton("Done");
        doneButton.setBackground(new Color(245, 195, 195));
        doneButton.setBounds(20, 142, 150, 30);
        JButton muteButton = new JButton("Mute/Unmute");
        muteButton.setBounds(416, 11, 97, 23);
        getContentPane().add(muteButton);
        
        // Dropdown for selecting flavors
        String[] flavors = {"Vanilla", "Chocolate", "Strawberry"};
        flavorDropdown = new JComboBox<>(flavors);
        flavorDropdown.setBounds(347, 60, 150, 30);

        // Dropdown for selecting toppings
        String[] toppings = {"Strawberry", "Blueberry", "Chocolate chips","Sprinkels"};
        toppingsDropdown = new JComboBox<>(toppings);
        toppingsDropdown.setBounds(187, 60, 150, 30);

        controlPanel.setLayout(null);
        controlPanel.add(addButton);
        controlPanel.add(removeButton);
        controlPanel.add(doneButton);
        controlPanel.add(flavorDropdown);
        controlPanel.add(toppingsDropdown);

        toppingLabel = new JLabel("Toppings: ");
        toppingLabel.setBounds(20, 11, 300, 14);
        controlPanel.add(toppingLabel);

        muteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleMute();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTopping = (String) toppingsDropdown.getSelectedItem();
                toppingsStack.addTopping(selectedTopping);
                updateToppingLabel();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toppingsStack.removeTopping();
                updateToppingLabel();
            }
        });

        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFlavor = (String) flavorDropdown.getSelectedItem();
                toppingsStack.displayCupcake(selectedFlavor);
            }
        });
    }

    private void updateToppingLabel() {
        toppingLabel.setText("Toppings: " + toppingsStack.toString());
    }

    private void playBackgroundMusic() {
        try {
            File audioFile = new File("C:\\Users\\Emman\\eclipse-workspace\\PROJECTDSA\\src\\bgMusics\\bgMusicCupcake.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void toggleMute() {
        if (isMuted) {
            clip.start();
        } else {
            clip.stop();
        }
        isMuted = !isMuted;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CupCakeSimulator simulator = new CupCakeSimulator();
                simulator.setVisible(true);
                simulator.playBackgroundMusic();
            }
        });
    }
}

class ToppingsStack {
    private Stack<String> toppingsStack = new Stack<>();

    public void addTopping(String topping) {
        toppingsStack.push(topping);
    }

    public void removeTopping() {
        if (!toppingsStack.isEmpty()) {
            toppingsStack.pop();
        } else {
            JOptionPane.showMessageDialog(null, "No toppings to remove.", "Empty Stack", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void displayCupcake(String flavor) {
        JFrame cupcakeFrame = new JFrame("Cupcake");
        cupcakeFrame.setSize(300, 300);
        cupcakeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Customize the cupcake appearance here by displaying an image
        ImageIcon cupcakeIcon = getCupcakeIcon(flavor);
        JLabel cupcakeLabel = new JLabel(cupcakeIcon);
        cupcakeFrame.add(cupcakeLabel);

        cupcakeFrame.setVisible(true);
    }

    private ImageIcon getCupcakeIcon(String flavor) {
        // Sort the toppings to handle different order of selection
        String[] sortedToppings = toppingsStack.toArray(new String[0]);
        Arrays.sort(sortedToppings);

        // Combine the sorted toppings into a single string
        String toppingsString = String.join("", sortedToppings);

        // Map toppings combinations to image paths
        HashMap<String, String> imageMap = new HashMap<>();
        imageMap.put("BlueberryStrawberry", "C:\\Users\\Emman\\eclipse-workspace\\PROJECTDSA\\src\\pics\\CUPCAKE.jpg");
        imageMap.put("StrawberryBlueberryCream", "C:\\Users\\Emman\\eclipse-workspace\\PROJECTDSA\\src\\pics\\Blueberry_strawberyToppings.jpg");
        // Add more mappings for different flavors and toppings

        // Determine the image to display based on the toppings combination and flavor
        String imagePath = imageMap.get(toppingsString + flavor);

        if (imagePath != null) {
            return new ImageIcon(imagePath);
        } else {
            return new ImageIcon("default_image.jpg"); // Replace with a default image path
        }
    }

    @Override
    public String toString() {
        return toppingsStack.toString();
    }
}
