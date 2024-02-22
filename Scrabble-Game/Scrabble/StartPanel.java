import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel{
    private JComboBox<Integer> numPlayersDropdown;
    private JButton startButton;

    public StartPanel(ActionListener startButtonListener){
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add Start Button
        startButton = new JButton("Start");
        startButton.addActionListener(startButtonListener);
        add(startButton, gbc);

        //Add label dropdown
        gbc.gridy++;
        add(new JLabel("Select Number of Players:"), gbc);
        gbc.gridy++;
        numPlayersDropdown = new JComboBox<>(new Integer[]{1,2,3,4});
        add(numPlayersDropdown, gbc);
        

        Dimension buttonSize = new Dimension(100, 40);
        startButton.setPreferredSize(buttonSize);
    }

    public int getSelectedNumberOfPlayers(){
        return (int) numPlayersDropdown.getSelectedItem();
    }


}
