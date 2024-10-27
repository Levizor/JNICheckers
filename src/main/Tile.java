package main;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Tile extends JToggleButton {
    private CheckerType checker;
    boolean highlited = false;
    int[] coordinates;



    public Tile(int x, int y) {
        super();
        coordinates = new int[]{x, y};
    }

    public void setChecker(CheckerType checker) {
        this.checker = checker;
    }

    public void setChecker(int checker){
        this.checker = CheckerType.values()[checker];
    }

    public void setHighlited(boolean highlited) {
        this.highlited = highlited;
    }

    public CheckerType getChecker() {
        return checker;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color color;
        if (highlited) {
            color = Palette.HIGHLIGHTED;
        }else {
            color = (coordinates[0]+coordinates[1])%2 == 0 ? Palette.LIGHT_TILE : Palette.DARK_TILE;
        }
        g2d.setColor(color);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        if(isSelected()) {
            g2d.setColor(Palette.SELECTED);
            g2d.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 8, 8);
        }

        drawChecker(g2d);
    }

    private void drawChecker(Graphics2D g2d) {
        if(checker==CheckerType.Blank) return;
        int d = Math.min(getWidth(), getHeight());
        d=(int) (0.8*d);
        int x = (getWidth()-d)/2;
        int y = (getHeight()-d)/2;

        if(checker.ordinal()%2==0) {
            g2d.setColor(Palette.WHITE);
        }else{
            g2d.setColor(Palette.BLACK);
        }

        g2d.fillOval(x, y, d, d);

        if(checker==CheckerType.Blackking || checker==CheckerType.Whiteking) {
            g2d.setColor(Palette.KING);
            g2d.drawString("K", x + d / 3, y + (2 * d) / 3);
        }
    }
    @Override
    public String toString() {
        return Arrays.toString(coordinates);
    }
}
