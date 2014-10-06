package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import simpleGame.core.Board;
import simpleGame.core.Pawn;

import java.util.ArrayList;

/**
 * Created by Thomas & Amona on 06/10/14.
 */
@RunWith(MockitoJUnitRunner.class)
public class TestExample {

    /**
     * Helper class
     * @param numberOfPawn
     * @param sizeX
     * @param sizeY
     * @param xBonus
     * @param yBonus
     * @return Board
     */
    Board create(int numberOfPawn, int sizeX, int sizeY, int xBonus, int yBonus) {
        return new Board(numberOfPawn, sizeX, sizeY, xBonus, yBonus);
    }

    @Test
    public void testXSize() {
        Board b = create(2, 2, 2, 2, 2);

        int x = b.getXSize();
        assertEquals(x, 2);
    }

    @Test
    public void testYSize() {
        Board b = create(2, 2, 2, 2, 2);

        int y = b.getYSize();
        assertEquals(y, 2);
    }

    @Test
    public void testNumberOfPawns() {
        Board b = create(1, 2, 2, 2, 2);

        int numberOfPawns = b.numberOfPawns();
        assertEquals(numberOfPawns, 1);
    }

    @Test
    public void testRemoveAllPawns() {
        Board b = create(2, 2, 2, 2, 2);

        b.removeAllPawns();
        int numberOfPawns = b.numberOfPawns();
        assertEquals(numberOfPawns, 0);
    }

    @Test
    public void testIsBonusSquare() {
        Board b = create(2, 2, 2, 2, 2);

        assertTrue(b.isBonusSquare(2, 2));
        assertFalse(b.isBonusSquare(1, 1));
        assertFalse(b.isBonusSquare(2, 1));
        assertFalse(b.isBonusSquare(1, 2));
    }

    @Test
    public void testMaxGold() {
        Board b = create(2, 2, 2, 2, 2);

        assertEquals(b.maxGold(), 0);
    }

    @Test
    public void testSquareContentSprite() {
        Board b = create(1, 2, 2, 1, 1);

        assertEquals(b.squareContentSprite(1, 1), '#');
        assertEquals(b.squareContentSprite(2, 2), '.');
    }
}
