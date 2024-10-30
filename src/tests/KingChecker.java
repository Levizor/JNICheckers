package tests;

import main.CheckerType;
import main.Main;
import main.StateType;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KingChecker {
    @BeforeEach
    public void restart(){
        Main.restartGame();
    }

    @Test
    public void ManCheckerGettingToTheLastRankBecomesKing(){
        Main.makeMove(new int[]{5, 6}, new int[]{4, 5});
        Main.makeMove(new int[]{2, 7}, new int[]{3, 6});
        Main.makeMove(new int[]{4, 5}, new int[]{2, 7});
        Main.makeMove(new int[]{2, 5}, new int[]{3, 4});
        Main.makeMove(new int[]{6, 7}, new int[]{5, 6});
        Main.makeMove(new int[]{1, 6}, new int[]{2, 5});
        Main.makeMove(new int[]{5, 6}, new int[]{4, 7});
        Main.makeMove(new int[]{0, 5}, new int[]{1, 6});
        assertEquals(CheckerType.Whiteman.ordinal(), Main.getTile(new int[]{2, 7}));
        Main.makeMove(new int[]{2, 7}, new int[]{0, 5});
        assertEquals(CheckerType.Whiteking.ordinal(), Main.getTile(new int[]{0, 5}));
    }

    @Test
    public void KingCheckerCanMoveAllDirections(){
        Main.makeMove(new int[]{5, 4}, new int[]{4, 5});
        Main.makeMove(new int[]{2, 7}, new int[]{3, 6});
        Main.makeMove(new int[]{4, 5}, new int[]{2, 7});
        Main.makeMove(new int[]{2, 5}, new int[]{3, 6});
        Main.makeMove(new int[]{2, 7}, new int[]{4, 5});
        Main.makeMove(new int[]{2, 3}, new int[]{3, 2});
        Main.makeMove(new int[]{4, 5}, new int[]{3, 6});
        Main.makeMove(new int[]{2, 1}, new int[]{3, 0});
        Main.makeMove(new int[]{5, 6}, new int[]{4, 7});
        Main.makeMove(new int[]{1, 4}, new int[]{2, 5});
        Main.makeMove(new int[]{3, 6}, new int[]{1, 4});
        Main.makeMove(new int[]{0, 5}, new int[]{2, 3});
        Main.makeMove(new int[]{4, 7}, new int[]{3, 6});
        Main.makeMove(new int[]{0, 3}, new int[]{1, 4});
        Main.makeMove(new int[]{3, 6}, new int[]{2, 7});
        Main.makeMove(new int[]{1, 4}, new int[]{2, 5});
        Main.makeMove(new int[]{2, 7}, new int[]{0, 5});
        Main.makeMove(new int[]{3, 2}, new int[]{4, 3});
        Main.makeMove(new int[]{0, 5}, new int[]{3, 2});
        Main.makeMove(new int[]{3, 2}, new int[]{5, 4});
        Main.makeMove(new int[]{2, 5}, new int[]{3, 4});
        Main.makeMove(new int[]{5, 4}, new int[]{3, 6});
        Main.makeMove(new int[]{1, 2}, new int[]{2, 1});

        int[] coords = {3, 6};
        int[][] directions = new int[][]{{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        int[][] posacts = Main.getPossibleMoves(coords);

        for(var dir: directions){
            int[] c = {coords[0]+dir[0], coords[1]+dir[1]};
            assertTrue(Arrays.stream(posacts).anyMatch(a -> Arrays.equals(a, c)));
        }
    }

    @Test
    public void KingCanMoveArbitraryAmountOfTiles(){
        Main.makeMove(new int[]{5, 6}, new int[]{4, 7});
        Main.makeMove(new int[]{2, 5}, new int[]{3, 6});
        Main.makeMove(new int[]{4, 7}, new int[]{2, 4});
        Main.makeMove(new int[]{4, 7}, new int[]{2, 5});
        Main.makeMove(new int[]{1, 4}, new int[]{3, 6});
        Main.makeMove(new int[]{5, 4}, new int[]{4, 5});
        Main.makeMove(new int[]{3, 6}, new int[]{5, 4});
        Main.makeMove(new int[]{6, 3}, new int[]{4, 5});
        Main.makeMove(new int[]{2, 7}, new int[]{3, 6});
        Main.makeMove(new int[]{4, 5}, new int[]{2, 7});
        Main.makeMove(new int[]{0, 5}, new int[]{1, 4});
        Main.makeMove(new int[]{2, 7}, new int[]{0, 5});
        Main.makeMove(new int[]{2, 3}, new int[]{3, 4});
        Main.makeMove(new int[]{0, 5}, new int[]{2, 3});
        Main.makeMove(new int[]{2, 3}, new int[]{4, 5});
        Main.makeMove(new int[]{1, 2}, new int[]{2, 3});
        Main.makeMove(new int[]{4, 5}, new int[]{1, 2});
        Main.makeMove(new int[]{1, 2}, new int[]{3, 0});
        Main.makeMove(new int[]{0, 3}, new int[]{1, 2});
        Main.makeMove(new int[]{3, 0}, new int[]{0, 3});
        Main.makeMove(new int[]{0, 1}, new int[]{1, 2});
        Main.makeMove(new int[]{0, 3}, new int[]{3, 0});
        Main.makeMove(new int[]{1, 0}, new int[]{2, 1});
        Main.makeMove(new int[]{3, 0}, new int[]{0, 3});
        Main.makeMove(new int[]{0, 7}, new int[]{1, 6});
        Main.makeMove(new int[]{6, 7}, new int[]{5, 6});
        Main.makeMove(new int[]{1, 6}, new int[]{2, 7});
        assertEquals(StateType.Moved.ordinal(), Main.makeMove(new int[]{0, 3}, new int[]{4, 7}));
    }


}
