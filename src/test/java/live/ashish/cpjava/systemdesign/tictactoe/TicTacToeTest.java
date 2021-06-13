package live.ashish.cpjava.systemdesign.tictactoe;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TicTacToeTest {

    TicTacToe game;

    @Before
    public void setUp() throws Exception {
        game=new TicTacToe();
    }

    @Test
    public void isPlayerTurn() {
        game.turn(1,1);
        assertFalse(game.isPlayerTurn());        //player 2
        game.turn(2,2);
        assertTrue(game.isPlayerTurn());         // player 1
        // invalid turn
        game.turn(2,2);
        assertTrue(game.isPlayerTurn());         // player 1
    }

    @Test
    public void getTurnCount() {
        game.turn(1,1);
        game.turn(2,1);
        game.turn(1,2);
        assertEquals(3,game.getTurnCount());
    }

    @Test
    public void isCompletedDraw() {
        game.turn(1,1);
        game.turn(2,2);
        game.turn(3,3);
        assertFalse(game.isCompleted());

        game.turn(2,3);
        game.turn(2,1);
        game.turn(3,1);
        game.turn(3,2);
        game.turn(1,2);
        game.turn(1,3);

        assertTrue(game.isCompleted());
        assertEquals(game.getGameState(), GameState.DRAW);
    }

    @Test
    public void isCompletedWin() {
        game.turn(1,1);
        game.turn(2,2);
        game.turn(2,1);
        game.turn(3,2);
        assertEquals(game.getGameState(), GameState.NOT_COMPLETED);
        game.turn(3,1);
        assertEquals(game.getGameState(), GameState.WIN);
    }

}
