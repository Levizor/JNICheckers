package main;

import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel {
    public GameOverPanel(String winner) {
        setLayout(new BorderLayout());
        setBackground(Palette.DARK_TILE);

        StyledLabel winnerLabel = new StyledLabel(winner + " won!");
        add(winnerLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Palette.DARK_TILE);
        StyledButton restart = new StyledButton("Restart");
        StyledButton exit = new StyledButton("Exit");
        buttonPanel.add(restart);
        buttonPanel.add(exit);
        add(buttonPanel, BorderLayout.SOUTH);

        restart.addActionListener(e -> {
            Main.frame.remove(this);
            Main.restart();
        });
        exit.addActionListener(e -> System.exit(0));
    }

    private class StyledButton extends JButton {
        public StyledButton(String s) {
            super(s);
            setPreferredSize(new Dimension(300, 200));
            setFont(new Font("Arial", Font.BOLD, 60));
            setBackground(Palette.LIGHT_TILE);
            setForeground(Palette.HIGHLIGHTED);
            setFocusPainted(false);
            setBorderPainted(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            super.paintComponent(g2d);
        }
    }

    private class StyledLabel extends JLabel {
        public StyledLabel(String s) {
            super(s, SwingConstants.CENTER);
            setForeground(Palette.KING);
            setFont(new Font("Arial", Font.BOLD, 100));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            super.paintComponent(g2d);
        }
    }
}
