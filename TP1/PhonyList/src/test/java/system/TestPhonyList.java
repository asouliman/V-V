package system;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Thomas & Amona on 29/09/14.
 */
public class TestPhonyList {

    /*
     * Helper method to create lists.
     */
    private PhonyList<Integer> list(Integer... content) {
        PhonyList<Integer> list = new PhonyList<>();
        for (Integer i : content)
            list.add(i);
        return list;
    }

    /*
     * Helper method to create a big list [1,2,3,...,10000]
     */
    private PhonyList<Integer> thousandElementsList() {
        PhonyList<Integer> thousandElementsList = new PhonyList<>();
        for (int i = 1; i <= 10000; i++) {
            thousandElementsList.add(i);
        }
        return thousandElementsList;
    }

    /**
     * Tests the get method of a list.
     *
     * @see PhonyList#get(int)
     * @type Functional
     * @input list=[0,1], o=1
     * @oracle It must return 1.
     * @passed Yes
     */
    @Test
    public void get_Test() {
        // Creating a call context
        PhonyList<Integer> list = list(0, 1);

        int value = 0;

        // Calling the tested operation
        value = list.get(1);

        // Oracle
        assertEquals(value, 1);
    }

    /**
     * Tests the isEmpty method on a non empty list.
     *
     * @see PhonyList#isEmpty
     * @type Functional
     * @input list=[0]
     * @oracle It must return False.
     * @passed Yes
     */
    @Test
    public void isEmpty_False() {
        // Creating a call context
        PhonyList<Integer> list = list(0);

        // Calling the tested operation
        boolean empty = list.isEmpty();

        // Oracle
        assertFalse(empty);
    }

    /**
     * Tests the isEmpty method on an empty list.
     *
     * @see PhonyList#isEmpty
     * @type Functional
     * @input list=[]
     * @oracle It must return True.
     * @passed Yes
     */
    @Test
    public void isEmpty_True() {
        // Creating a call context
        PhonyList<Integer> list = list();

        // Calling the tested operation
        boolean empty = list.isEmpty();

        // Oracle
        assertTrue(empty);
    }

    /**
     * Tests the contains method of a list.
     *
     * @see PhonyList#contains(Object)
     * @type Functional
     * @input list=[0,1], o=1
     * @oracle It must return True.
     * @passed Yes
     */
    @Test
    public void contains_True() {
        // Creating a call context
        PhonyList<Integer> list = list(0, 1);

        // Calling the tested operation
        boolean contains = list.contains(1);

        // Oracle
        assertTrue(contains);
    }

    /**
     * Tests the contains method of a list.
     *
     * @see PhonyList#contains(Object)
     * @type Functional
     * @input list=[0,1], o=2
     * @oracle It must return False.
     * @passed Yes
     */
    @Test
    public void contains_False() {
        // Creating a call context
        PhonyList<Integer> list = list(0, 1);

        // Calling the tested operation
        boolean contains = list.contains(2);

        // Oracle
        assertFalse(contains);
    }

    /**
     * Tests the set method of a list.
     *
     * @see PhonyList#set(int, Object)
     * @type Functional
     * @input list=[0,1], o=2
     * @oracle It must return 1.
     * @passed Yes
     */
    @Test
    public void set_TestOldValue() {
        // Creating a call context
        PhonyList<Integer> list = list(0, 1);

        // Calling the tested operation
        int oldValue = list.set(1, 2);

        // Oracle
        assertEquals(1, oldValue);
        // assertEquals(2, (int)list.get(1));
    }

    /**
     * Tests the set method of a list.
     *
     * @see PhonyList#set(int, Object)
     * @type Functional
     * @input list=[0,1], o=2
     * @oracle It must return 2.
     * @passed No
     * @correction
     * <pre>
     *     l.245
     *     - elementData[++index] = element;
     *     + elementData[index] = element;
     * </pre>
     */
    @Test
    public void set_TestNewValue() {
        // Creating a call context
        PhonyList<Integer> list = list(0, 1);
        int index = 1;

        // Calling the tested operation
        int oldValue = list.set(index, 2);

        // Oracle
        assertEquals(2, (int)list.get(index));
    }

    /**
     * Tests the removeAll method of a list.
     *
     * @see PhonyList#removeAll(Collection)
     * @type Functional
     * @input []
     * @oracle The value returned with contains must correspond if the object is in the list or not.
     * @passed Yes
     */
    @Test
    public void removeAll_Test() {
        // Creating a call context
        PhonyList<Integer> actual = list();

        boolean value;

        actual.add(0);
        actual.add(1);
        actual.add(2);

        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(1);
        collection.add(2);

        // Calling the tested operation
        value = actual.removeAll(collection);

        // Oracle
        assertTrue(value);
    }

    /**
     * Tests the remove method of a list.
     *
     * @see PhonyList#remove(Object)
     * @type Functional
     * @input []
     * @oracle The value returned with remove must correspond if the object was in the list or not.
     * @passed Yes
     */
    @Test
    public void remove_NotLastElement()
    {
        PhonyList<Integer> actual = list();
        actual.add(1);
        actual.add(2);
        actual.add(3);

        boolean res = actual.remove((Integer)1);

        assertTrue(res);
    }

    /**
     * Tests the remove method of a list.
     *
     * @see PhonyList#remove(Object)
     * @type Functional
     * @input []
     * @oracle The value returned with remove must correspond if the object was in the list or not.
     * @passed Yes
     */
    @Test
    public void removeFalse_Test()
    {
        PhonyList<Integer> actual = list();
        actual.add(1);
        actual.add(2);
        actual.add(3);

        boolean res = actual.remove((Integer)4);

        assertFalse(res);
    }

    /**
     * Tests the remove method of a list.
     *
     * @see PhonyList#remove(Object)
     * @type Functional
     * @input []
     * @oracle The value returned with remove must correspond if the object was in the list or not.
     * @passed Yes
     */
    @Test
    public void remove_nullFound()
    {
        PhonyList<Integer> actual = list();
        actual.add(1);
        actual.add(2);
        actual.add(null);

        boolean res = actual.remove(null);

        assertTrue(res);
    }

    @Test
    public void remove_nullNotFound()
    {
        PhonyList<Integer> actual = list();
        actual.add(1);
        actual.add(2);
        actual.add(3);

        boolean res = actual.remove(null);

        assertFalse(res);
    }

    @Test
    public void remove_LastElement()
    {
        PhonyList<Integer> actual = list(0,1,2);

        boolean res = actual.remove((Integer)2);

        assertTrue(res);
    }

    /**
     * Tests the addAll method of a list.
     *
     * @see PhonyList#addAll(int, Collection)
     * @type Functional
     * @input []
     * @oracle The value returned with addAll
     * @passed Yes
     */
    @Test
    public void addAll_Test()
    {
        PhonyList<Integer> actual = list();
        actual.add(0);
        actual.add(3);

        Collection<Integer> toInsert = new ArrayList<Integer>();
        toInsert.add((Integer)1);
        toInsert.add((Integer)2);

        actual.addAll(1, toInsert);

        assertEquals((Integer) 1, actual.get(1));
    }

    @Test
    public void addAll_ZeroMoved()
    {
        PhonyList<Integer> actual = list();
        actual.add(0);
        actual.add(3);

        Collection<Integer> toInsert = new ArrayList<Integer>();
        toInsert.add((Integer)1);
        toInsert.add((Integer)2);

        actual.addAll(2, toInsert);

        assertEquals((Integer) 1, actual.get(2));
    }

    @Test
    public void addAll_ReturnTrue()
    {
        PhonyList<Integer> actual = list();
        actual.add(0);
        actual.add(3);

        Collection<Integer> toInsert = new ArrayList<Integer>();
        toInsert.add((Integer)1);
        toInsert.add((Integer)2);

        boolean res = actual.addAll(1, toInsert);

        assertTrue(res);
    }

    @Test
    public void addAll_ReturnFalse()
    {
        PhonyList<Integer> actual = list();
        actual.add(0);
        actual.add(3);

        Collection<Integer> toInsert = new ArrayList<Integer>();

        boolean res = actual.addAll(1, toInsert);

        assertFalse(res);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void rangeCheck_Test()
    {
        PhonyList<Integer> actual = list();
        actual.add(0);

        Integer res = actual.get(2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void rangeCheckForAdd_NegativeIndex()
    {
        PhonyList<Integer> actual = list();
        actual.add(0);

        Collection<Integer> toInsert = new ArrayList<Integer>();
        toInsert.add((Integer)1);

        actual.addAll(-1, toInsert);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void rangeCheckForAdd_OverSizeIndex()
    {
        PhonyList<Integer> actual = list();
        actual.add(0);

        Collection<Integer> toInsert = new ArrayList<Integer>();
        toInsert.add((Integer)1);

        actual.addAll(2, toInsert);
    }

    @Test
    public void removeRange_Test()
    {
        PhonyList<Integer> actual = list();
        actual.add(0);
        actual.add(1);
        actual.add(2);

        actual.removeRange(0, 1);

        assertEquals((Integer)1, actual.get(0));
    }

    @Test(expected = NullPointerException.class)
    public void removeAllNull_Test() {
        // Creating a call context
        PhonyList<Integer> actual = list();

        boolean value;

        actual.add(0);
        actual.add(1);
        actual.add(2);

        // Calling the tested operation
        value = actual.removeAll(null);
    }

    @Test
    public void removeAllNonExisting_Test() {
        // Creating a call context
        PhonyList<Integer> actual = list();

        boolean value;

        actual.add(0);
        actual.add(1);
        actual.add(2);

        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(3);
        collection.add(4);
        collection.add(5);

        // Calling the tested operation
        value = actual.removeAll(collection);

        assertFalse(value);
    }

    @Test
    public void indexOf_nullNotFound()
    {
        // Creating a call context
        PhonyList<Integer> actual = list(1,2);

        int res = actual.indexOf(null);

        assertEquals(-1, res);
    }

}
