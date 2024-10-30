package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class Board extends JPanel {
    Tile[][] tiles;
    StateType state;

    ArrayList<int[]> highlighted = new ArrayList<>();

    int focusedRow = 3;
    int focusedCol = 3;

    static Tile from;

    public Board() {
        super(new GridLayout(8, 8));
        setBackground(Palette.DARK_TILE);
        setBorder(BorderFactory.createLineBorder(Palette.NORD2));
        tiles = new Tile[8][8];
        ActionListener tl = new TileListener();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile button = new Tile(i, j);
                tiles[i][j] = button;
                button.setChecker(Main.getTile(button.coordinates));
                button.addActionListener(tl);
                button.addMouseListener((MouseAdapter) tl);
                this.add(button);
            }
        }

        setFocusTraversalKeysEnabled(false);
        addKeyListener(new BoardKeyListener());
        setFocusable(true);
        requestFocusInWindow();

        updateFocus();
    }

    public void playSound(StateType state) {
        String filePath = switch (state) {
            case WrongMove -> "wrong.wav";
            case Moved -> "moved.wav";
            case Blackwon -> "win.wav";
            case Whitewon -> "win.wav";
            case Tie -> "win.wav";
        };
        Thread thread = new Thread(
                new Runnable() {
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
                }
        );
        thread.start();
    }

    public void update() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile button = tiles[i][j];
                button.setChecker(Main.getTile(button.coordinates));
            }
        }

        if (state == null) return;
        playSound(state);

        String winner = switch (state) {
            case Blackwon -> "Black";
            case Whitewon -> "White";
            case Tie -> "Friendship";
            default -> "";
        };
        state = null;

        if (!winner.isEmpty()) {
            Main.frame.add(new GameOverPanel(winner));
            Main.frame.remove(Main.board);
            Main.frame.revalidate();
            Main.frame.repaint();
        }
    }

    public void highlight() {
        Arrays.stream(tiles)
                .flatMap(Arrays::stream)
                .filter(t -> highlighted.stream().anyMatch(c -> Arrays.equals(c, t.coordinates)))
                .forEach(tile ->
                        tile.setHighlited(true)
                );
        repaint();
    }

    public void dehighlightAll() {
        Arrays.stream(tiles).flatMap(Arrays::stream).forEach(t -> t.setHighlited(false));
        repaint();
        highlighted.clear();
    }


    private void updateFocus() {
        tiles[focusedRow][focusedCol].setFocused(true);
        repaint();
    }


    public void selectTile(Tile tile) {
        dehighlightAll();
        if (from == null) {
            highlighted = new ArrayList<>(Arrays.asList(Main.getPossibleMoves(tile.coordinates)));
            if (highlighted.size() == 0) {
                return;
            }
            tile.setSelected(true);
            highlighted.add(tile.coordinates);
            from = tile;
            highlight();
            return;
        }
        if (from == tile) {
            from.setSelected(false);
            from = null;
            return;
        }
        tile.setSelected(true);
        if (!oneTeamPieceSelected(from.coordinates, tile.coordinates)) {
            tile.setSelected(false);
            state = StateType.values()[Main.makeMove(from.coordinates, tile.coordinates)];
            update();
        }
        from.setSelected(false);
        from = null;
    }

    boolean oneTeamPieceSelected(int[] from, int[] to) {
        int t = Main.getTile(to);
        int f = Main.getTile(from);
        return t != 0 && f % 2 == t % 2;
    }

    private class TileListener extends MouseAdapter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            requestFocusInWindow();
            selectTile((Tile) e.getSource());
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            int[] cords = ((Tile) e.getSource()).coordinates;
            tiles[focusedRow][focusedCol].setFocused(false);
            focusedRow = cords[0];
            focusedCol = cords[1];
            updateFocus();
        }

    }

    private class BoardKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int direction = switch (e.getKeyCode()) {
                case KeyEvent.VK_UP -> 1;
                case KeyEvent.VK_DOWN -> 2;
                case KeyEvent.VK_LEFT -> 3;
                case KeyEvent.VK_RIGHT -> 4;
                case KeyEvent.VK_ENTER -> 0;
                case KeyEvent.VK_ESCAPE -> 5;
                default -> -1;
            };
            if (direction == 0) {
                selectTile(tiles[focusedRow][focusedCol]);
                return;
            }
            if(direction == 5){
                tiles[focusedRow][focusedCol].setFocused(false);
                int[] coords = highlighted.getLast();
                selectTile(tiles[coords[0]][coords[1]]);
                focusedRow = coords[0];
                focusedCol = coords[1];
                updateFocus();
                return;
            }

            tiles[focusedRow][focusedCol].setFocused(false);
            if (!highlighted.isEmpty()) {
                Optional<int[]> coords;
                switch (direction) {
                    case 1 -> {
                        coords = highlighted.stream().filter(t -> t[0] < focusedRow).findFirst();
                    }
                    case 2 -> {
                        coords = highlighted.stream().filter(t -> t[0] > focusedRow).findFirst();
                    }
                    case 3 -> {
                        coords = highlighted.stream().filter(t -> t[1] < focusedCol).findFirst();
                    }
                    case 4 -> {
                        coords = highlighted.stream().filter(t -> t[1] > focusedCol).findFirst();
                    }
                    default -> {
                        return;
                    }
                }

                if (coords.isEmpty()) return;
                focusedRow = coords.get()[0];
                focusedCol = coords.get()[1];
            } else {
                switch (direction) {
                    case 1 -> {
                        focusedRow = focusedRow - 1 >= 0 ? focusedRow - 1 : 7;
                    }
                    case 2 -> {
                        focusedRow = focusedRow + 1 < 7 ? focusedRow + 1 : 0;
                    }
                    case 3 -> {
                        focusedCol = focusedCol - 1 >= 0 ? focusedCol - 1 : 7;
                    }
                    case 4 -> {
                        focusedCol = focusedCol + 1 < 7 ? focusedCol + 1 : 0;
                    }
                }
            }
            updateFocus();
        }
    }
}
