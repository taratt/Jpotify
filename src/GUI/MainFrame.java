package GUI;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    private JFrame mainFrame;
    private JPanel panel, panel5, panel2, panel3, panel4;
    private PlayList playListPanel;
    private static final int HEIGHT = 920, WIDTH = 1500;
    private GridBagConstraints gbc;
    public MainFrame() {
        //set JFrame's default properties
        this.mainFrame = new JFrame("Spotify");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setSize(WIDTH, HEIGHT);

        gbc = new GridBagConstraints();
        //set Main Panel default properties
        panel = new JPanel();
//        panel.setLayout(new BorderLayout(5, 5));
        panel.setLayout(new GridBagLayout());
        panel.setOpaque(true);
        panel.setBackground(Color.BLACK);

        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();

        playListPanel = new PlayList();

        panel2.setOpaque(true);
        panel2.setBackground(Color.RED);

        panel3.setOpaque(true);
        panel3.setBackground(Color.YELLOW);
        panel3.setPreferredSize(new Dimension(0,30));

        panel4.setOpaque(true);
        panel4.setBackground(Color.BLUE);
        panel4.setPreferredSize(new Dimension(100,80));

        panel5.setOpaque(true);
        panel5.setBackground(Color.PINK);
        panel5.setPreferredSize(new Dimension(0,120));

        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx=0;
        gbc.weighty=1;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(playListPanel,gbc);

        gbc.weightx=2;
        gbc.gridx=1;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        panel.add(panel3,gbc);
        gbc.weightx=0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx=2;
        panel.add(panel4,gbc);
//
        gbc.gridx=1;
        gbc.gridy=1;
        panel.add(panel2,gbc);

        gbc.weighty=0;
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth=3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(panel5,gbc);
//        panel.add(playListPanel, BorderLayout.WEST);
//        panel.add(panel2, BorderLayout.CENTER);
//        panel.add(panel3, BorderLayout.EAST);
//        panel.add(panel4, BorderLayout.NORTH);
//        panel.add(panel5, BorderLayout.SOUTH);

        mainFrame.add(panel);

    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }


}
