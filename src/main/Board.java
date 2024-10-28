package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Board extends JPanel {
    Tile[][] tiles;

    public Board(){
        super(new GridLayout(8, 8));
        setBackground(Palette.DARK_TILE);
        setBorder(BorderFactory.createLineBorder(Palette.NORD2));
        tiles = new Tile[8][8];
        ActionListener tl = new TileListener();
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                Tile button = new Tile(i, j);
                tiles[i][j] = button;
                button.setChecker(Main.getTile(button.coordinates));
                button.addActionListener(tl);
                this.add(button);
            }
        }
    }

    public void update(){
        for(int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                Tile button = tiles[i][j];
                button.setChecker(Main.getTile(button.coordinates));
            }
        }
    }

    public void highlight(int[][] coords){
        Arrays.stream(tiles)
                .flatMap(Arrays::stream)
                .filter(t -> Arrays.stream(coords)
                    .anyMatch(c-> Arrays.equals(c, t.coordinates)))
                .forEach(tile -> {tile.setHighlited(true);});
        repaint();
    }

    public void dehighlightAll(){
        Arrays.stream(tiles).flatMap(Arrays::stream).forEach(t -> {t.setHighlited(false);});
        repaint();
    }

    private class TileListener implements ActionListener {
        static Tile from;
        static int[][] posActs;
        @Override
        public void actionPerformed(ActionEvent e) {
            dehighlightAll();
            Tile tile= (Tile) e.getSource();
            if(from==tile){
                from.setSelected(false);
                dehighlightAll();
                from=null;
                posActs=null;
                return;
            }
            if(from!=null){
                if(!oneTeamPieceSelected(from.coordinates, tile.coordinates)){
                    tile.setSelected(false);
                    from.setSelected(false);
                    if (Arrays.stream(posActs).anyMatch(c1 -> Arrays.equals(c1, tile.coordinates))) {
                        int i = Main.makeMove(from.coordinates, tile.coordinates);
                        update();
                    }
                    from = null;
                    posActs = null;
                    return;
                }
                from.setSelected(false);
            }
            int[][] actions = Main.getPossibleMoves(tile.coordinates);
            System.out.println(Arrays.deepToString(actions));
            if(actions.length==0){
                tile.setSelected(false);
                return;
            }
            from = tile;
            posActs=actions;
            highlight(posActs);
        }

        boolean oneTeamPieceSelected(int[] from, int[] to){
            int t = Main.getTile(to);
            int f = Main.getTile(from);
            return t!=0 && f%2==t%2;
        }
    }

}
