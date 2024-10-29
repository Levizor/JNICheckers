package main;

import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel {
   public GameOverPanel(String winner) {
       setLayout(new BorderLayout());
       setBackground(Palette.DARK_TILE);

       JLabel winnerLabel = new JLabel(winner + " won!", SwingConstants.CENTER);
       winnerLabel.setForeground(Palette.KING);
       winnerLabel.setFont(new Font("Arial", Font.BOLD, 32));
       add(winnerLabel, BorderLayout.NORTH);

       JPanel buttonPanel = new JPanel(new FlowLayout());
       StyledButton restart = new StyledButton("Restart");
       StyledButton exit = new StyledButton("Exit");
       buttonPanel.add(restart);
       buttonPanel.add(exit);
       add(buttonPanel, BorderLayout.CENTER);

       restart.addActionListener(e -> {
           Main.frame.remove(this);
           Main.restart();
       });
       exit.addActionListener(e->System.exit(0));
   }

   private class StyledButton extends  JButton{
       public StyledButton(String s) {
           super(s);
           setPreferredSize(new Dimension(200, 100));
           setFont(new Font("Arial", Font.BOLD, 20));
           setBackground(Palette.LIGHT_TILE);
           setForeground(Palette.HIGHLIGHTED);
           setFocusPainted(false);
           setBorderPainted(false);
       }
   }
}
