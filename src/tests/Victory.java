package tests;

import main.Main;
import main.StateType;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Victory {
    @BeforeEach
    public void restart(){
        Main.restartGame();
    }

    @Test
    public void VictoryWhenNoPiecesOfOneTeamLeft(){
        Main.makeMove(new int[]{5, 6}, new int[]{4, 7});
        Main.makeMove(new int[]{2, 3}, new int[]{3, 4});
        Main.makeMove(new int[]{5, 4}, new int[]{4, 5});
        Main.makeMove(new int[]{3, 4}, new int[]{5, 6});
        Main.makeMove(new int[]{6, 7}, new int[]{4, 5});
        Main.makeMove(new int[]{2, 7}, new int[]{3, 7});
        Main.makeMove(new int[]{2, 7}, new int[]{3, 6});
        Main.makeMove(new int[]{4, 5}, new int[]{2, 7});
        Main.makeMove(new int[]{2, 5}, new int[]{3, 6});
        Main.makeMove(new int[]{4, 7}, new int[]{2, 5});
        Main.makeMove(new int[]{1, 4}, new int[]{3, 6});
        Main.makeMove(new int[]{2, 7}, new int[]{4, 5});
        Main.makeMove(new int[]{1, 6}, new int[]{2, 5});
        Main.makeMove(new int[]{5, 2}, new int[]{4, 3});
        Main.makeMove(new int[]{2, 5}, new int[]{3, 6});
        Main.makeMove(new int[]{4, 5}, new int[]{2, 7});
        Main.makeMove(new int[]{0, 5}, new int[]{1, 6});
        Main.makeMove(new int[]{2, 7}, new int[]{0, 5});
        Main.makeMove(new int[]{0, 7}, new int[]{1, 6});
        Main.makeMove(new int[]{0, 5}, new int[]{2, 7});
        Main.makeMove(new int[]{0, 3}, new int[]{1, 4});
        Main.makeMove(new int[]{2, 7}, new int[]{0, 5});
        Main.makeMove(new int[]{2, 1}, new int[]{3, 2});
        Main.makeMove(new int[]{0, 5}, new int[]{2, 3});
        Main.makeMove(new int[]{2, 3}, new int[]{4, 1});
        Main.makeMove(new int[]{1, 2}, new int[]{2, 3});
        Main.makeMove(new int[]{4, 1}, new int[]{0, 5});
        Main.makeMove(new int[]{0, 1}, new int[]{1, 2});
        Main.makeMove(new int[]{4, 3}, new int[]{3, 2});
        Main.makeMove(new int[]{1, 2}, new int[]{2, 3});
        Main.makeMove(new int[]{3, 2}, new int[]{1, 4});
        Main.makeMove(new int[]{1, 0}, new int[]{2, 1});
        Main.makeMove(new int[]{1, 4}, new int[]{0, 3});
        Main.makeMove(new int[]{2, 1}, new int[]{3, 2});
        assertEquals(StateType.Whitewon.ordinal(), Main.makeMove(new int[]{0, 5}, new int[]{4, 1}));
    }

    @Test
    public void TieAfter40MovesWithoutCapture(){
        Main.makeMove(new int[]{5, 6}, new int[]{4, 7});
        Main.makeMove(new int[]{2, 5}, new int[]{3, 6});
        Main.makeMove(new int[]{4, 7}, new int[]{2, 5});
        Main.makeMove(new int[]{1, 4}, new int[]{3, 6});
        Main.makeMove(new int[]{6, 7}, new int[]{5, 6});
        Main.makeMove(new int[]{3, 6}, new int[]{4, 5});
        Main.makeMove(new int[]{5, 6}, new int[]{3, 4});
        Main.makeMove(new int[]{2, 3}, new int[]{4, 5});
        Main.makeMove(new int[]{5, 4}, new int[]{3, 6});
        Main.makeMove(new int[]{2, 7}, new int[]{4, 5});
        Main.makeMove(new int[]{6, 5}, new int[]{5, 4});
        Main.makeMove(new int[]{1, 6}, new int[]{2, 5});
        Main.makeMove(new int[]{5, 4}, new int[]{3, 6});
        Main.makeMove(new int[]{3, 6}, new int[]{1, 4});
        Main.makeMove(new int[]{0, 3}, new int[]{2, 5});
        Main.makeMove(new int[]{5, 2}, new int[]{4, 3});
        Main.makeMove(new int[]{2, 5}, new int[]{3, 4});
        Main.makeMove(new int[]{4, 3}, new int[]{2, 5});
        Main.makeMove(new int[]{0, 7}, new int[]{1, 6});
        Main.makeMove(new int[]{2, 5}, new int[]{0, 7});
        Main.makeMove(new int[]{0, 5}, new int[]{1, 6});
        Main.makeMove(new int[]{0, 7}, new int[]{2, 5});
        Main.makeMove(new int[]{1, 2}, new int[]{2, 3});
        Main.makeMove(new int[]{5, 0}, new int[]{4, 1});
        Main.makeMove(new int[]{0, 1}, new int[]{1, 2});
        Main.makeMove(new int[]{4, 1}, new int[]{3, 2});
        Main.makeMove(new int[]{2, 3}, new int[]{4, 1});
        Main.makeMove(new int[]{7, 4}, new int[]{6, 5});
        Main.makeMove(new int[]{1, 2}, new int[]{2, 3});
        Main.makeMove(new int[]{6, 3}, new int[]{5, 4});
        Main.makeMove(new int[]{2, 1}, new int[]{3, 2});
        Main.makeMove(new int[]{6, 1}, new int[]{5, 0});
        Main.makeMove(new int[]{4, 1}, new int[]{5, 2});
        Main.makeMove(new int[]{2, 5}, new int[]{6, 1});
        Main.makeMove(new int[]{1, 0}, new int[]{2, 1});
        Main.makeMove(new int[]{6, 1}, new int[]{1, 6});
        Main.makeMove(new int[]{2, 1}, new int[]{3, 0});
        Main.makeMove(new int[]{1, 6}, new int[]{2, 7});
        Main.makeMove(new int[]{3, 0}, new int[]{4, 1});
        Main.makeMove(new int[]{7, 2}, new int[]{6, 3});
        Main.makeMove(new int[]{2, 3}, new int[]{3, 4});
        Main.makeMove(new int[]{5, 4}, new int[]{4, 3});
        Main.makeMove(new int[]{3, 2}, new int[]{5, 4});
        Main.makeMove(new int[]{5, 4}, new int[]{7, 2});
        Main.makeMove(new int[]{5, 0}, new int[]{3, 2});
        Main.makeMove(new int[]{3, 4}, new int[]{2, 7});
        Main.makeMove(new int[]{3, 4}, new int[]{2, 7});
        Main.makeMove(new int[]{3, 4}, new int[]{2, 7});
        Main.makeMove(new int[]{3, 4}, new int[]{4, 5});
        Main.makeMove(new int[]{2, 7}, new int[]{6, 3});
        Main.makeMove(new int[]{7, 2}, new int[]{3, 6});
        Main.makeMove(new int[]{3, 2}, new int[]{2, 3});
        Main.makeMove(new int[]{3, 6}, new int[]{2, 7});
        Main.makeMove(new int[]{6, 5}, new int[]{5, 4});
        Main.makeMove(new int[]{2, 7}, new int[]{7, 2});
        Main.makeMove(new int[]{2, 3}, new int[]{1, 2});
        Main.makeMove(new int[]{7, 2}, new int[]{6, 3});
        Main.makeMove(new int[]{1, 2}, new int[]{0, 3});
        Main.makeMove(new int[]{6, 3}, new int[]{5, 2});
        Main.makeMove(new int[]{0, 3}, new int[]{1, 4});
        Main.makeMove(new int[]{5, 2}, new int[]{6, 3});
        Main.makeMove(new int[]{1, 4}, new int[]{0, 3});
        Main.makeMove(new int[]{6, 3}, new int[]{7, 2});
        Main.makeMove(new int[]{0, 3}, new int[]{1, 2});
        Main.makeMove(new int[]{7, 2}, new int[]{6, 3});
        Main.makeMove(new int[]{1, 2}, new int[]{0, 3});
        Main.makeMove(new int[]{6, 3}, new int[]{7, 2});
        Main.makeMove(new int[]{0, 3}, new int[]{1, 2});
        Main.makeMove(new int[]{7, 2}, new int[]{6, 3});
        Main.makeMove(new int[]{1, 2}, new int[]{2, 3});
        Main.makeMove(new int[]{6, 3}, new int[]{7, 2});
        Main.makeMove(new int[]{2, 3}, new int[]{3, 2});
        Main.makeMove(new int[]{7, 2}, new int[]{6, 3});
        Main.makeMove(new int[]{3, 2}, new int[]{2, 3});
        Main.makeMove(new int[]{6, 3}, new int[]{7, 2});
        Main.makeMove(new int[]{2, 3}, new int[]{1, 2});
        Main.makeMove(new int[]{7, 2}, new int[]{6, 3});
        Main.makeMove(new int[]{1, 2}, new int[]{0, 3});
        Main.makeMove(new int[]{6, 3}, new int[]{7, 2});
        Main.makeMove(new int[]{0, 3}, new int[]{1, 2});
        Main.makeMove(new int[]{7, 2}, new int[]{6, 3});
        Main.makeMove(new int[]{1, 2}, new int[]{0, 3});
        Main.makeMove(new int[]{6, 3}, new int[]{7, 2});
        Main.makeMove(new int[]{0, 3}, new int[]{1, 2});
        Main.makeMove(new int[]{7, 2}, new int[]{6, 3});
        Main.makeMove(new int[]{1, 2}, new int[]{0, 3});
        Main.makeMove(new int[]{6, 3}, new int[]{7, 2});
        Main.makeMove(new int[]{0, 3}, new int[]{1, 2});
        Main.makeMove(new int[]{7, 2}, new int[]{6, 3});
        Main.makeMove(new int[]{1, 2}, new int[]{0, 3});
        Main.makeMove(new int[]{6, 3}, new int[]{7, 2});
        Main.makeMove(new int[]{0, 3}, new int[]{1, 2});
        Main.makeMove(new int[]{7, 2}, new int[]{6, 3});
        Main.makeMove(new int[]{1, 2}, new int[]{0, 3});
        Main.makeMove(new int[]{6, 3}, new int[]{7, 2});
        assertEquals(StateType.Tie.ordinal(), Main.makeMove(new int[]{0, 3}, new int[]{1, 2}));

    }
}
