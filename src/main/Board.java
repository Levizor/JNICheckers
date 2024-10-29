package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Board extends JPanel {
    Tile[][] tiles;
    StateType state;

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

    public void playSound(StateType state){
        String filePath = switch (state) {
            case WrongMove -> "wrong.wav";
            case Moved -> "moved.wav";
            case Blackwon -> "win.wav";
            case Whitewon -> "win.wav";
            case Tie -> "win.vav";
        };
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath))) {
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.start();
                    clip.drain();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    public void update(){
        for(int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                Tile button = tiles[i][j];
                button.setChecker(Main.getTile(button.coordinates));
            }
        }

        if(state==null) return;
        playSound(state);

        String winner = "";
        switch(state){
            case WrongMove ->{return;}
            case Moved -> {return;}
            case Blackwon -> winner = "Black";
            case Whitewon -> winner = "White";
            case Tie-> winner = "Friendship";
        }
        state = null;


        Main.frame.add(new GameOverPanel(winner));
        Main.frame.remove(Main.board);
        Main.frame.revalidate();
        Main.frame.repaint();
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
                return;
            }
            if(from!=null){
                if(!oneTeamPieceSelected(from.coordinates, tile.coordinates)){
                    tile.setSelected(false);
                    from.setSelected(false);
                    state = StateType.values()[Main.makeMove(from.coordinates, tile.coordinates)];
                    update();
                    from = null;
                    return;
                }
                from.setSelected(false);
            }
            int[][] actions = Main.getPossibleMoves(tile.coordinates);
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
