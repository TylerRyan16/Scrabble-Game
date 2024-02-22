import javax.swing.*;
import java.awt.*;

public class CenteredPanel extends JPanel {

    public CenteredPanel() {
        // Set the layout manager to null to allow manual positioning
        setLayout(null);

        // Add components to the centered panel
        JLabel label = new JLabel("Centered Panel");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);

        // Set the bounds of the label to center it within the panel
        Dimension size = label.getPreferredSize();
        int x = (getWidth() - size.width) / 2;
        int y = (getHeight() - size.height) / 2;
        label.setBounds(x, y, size.width, size.height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Reposition the label when the panel is repainted
        Component[] components = getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                Dimension size = label.getPreferredSize();
                int x = (getWidth() - size.width) / 2;
                int y = (getHeight() - size.height) / 2;
                label.setBounds(x, y, size.width, size.height);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Centered Panel Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            CenteredPanel panel = new CenteredPanel();
            frame.add(panel);
            frame.setSize(400, 300);
            frame.setVisible(true);
        });
    }
}