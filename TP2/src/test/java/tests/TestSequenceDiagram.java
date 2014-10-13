package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.runners.MockitoJUnitRunner;
import simpleGame.core.Game;
import simpleGame.core.Board;
import simpleGame.core.Pawn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by Thomas & Amona on 06/10/14.
 */
@RunWith(MockitoJUnitRunner.class)
public class TestSequenceDiagram {

    @Test
    public void testGetBoard() {

        Board mockBoard = mock(Board.class);
        when(mockBoard.getXSize()).thenReturn(4);
        when(mockBoard.getYSize()).thenReturn(4);

        Game g = new Game();
        g.setBoard(mockBoard);

        assertEquals(g.getBoard(), mockBoard);
    }

    @Test
    public void testIsGameOver() {
        Board b = mock(Board.class);

        when(b.maxGold()).thenReturn(2);

        Game g = new Game();
        g.setBoard(b);

        // Call the tested operation
        g.isGameOver();

        InOrder mockInOrder = inOrder(b);

        mockInOrder.verify(b).numberOfPawns();
        mockInOrder.verify(b).maxGold();
    }

    @Test
    public void testMaxGold() {
        Pawn p1 = mock(Pawn.class);
        Pawn p2 = mock(Pawn.class);

        when(p1.getX()).thenReturn(1);
        when(p1.getY()).thenReturn(1);
        when(p1.getLetter()).thenReturn('A');
        when(p1.getGold()).thenReturn(1);

        when(p2.getX()).thenReturn(2);
        when(p2.getY()).thenReturn(2);
        when(p2.getLetter()).thenReturn('B');
        when(p2.getGold()).thenReturn(3);

        Board b = new Board(2, 4, 4, 3, 3);

        b.addPawn(p1);
        b.addPawn(p2);

        // Call the tested operation
        b.maxGold();

        InOrder mockInOrder = inOrder(p1, p2);

        mockInOrder.verify(p1).getGold();
        mockInOrder.verify(p2).getGold();
    }
}
