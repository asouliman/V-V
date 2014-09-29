package system;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by thomas on 29/09/14.
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
     * @input []
     * @oracle The value returned with get must correspond to the value added.
     * @passed Yes
     */
    @Test
    public void get_Test() {
        // Creating a call context
        PhonyList<Integer> actual = list();

        int value = 0;

        actual.add(0);
        actual.add(1);

        // Calling the tested operation
        value = actual.get(1);

        // Oracle
        assertEquals(value, 1);
    }

    /**
     * Tests the isEmpty method of a list.
     *
     * @see PhonyList#isEmpty
     * @type Functional
     * @input []
     * @oracle The value returned with get must correspond if the list is empty or not.
     * @passed Yes
     */
    @Test
    public void isEmpty_Test() {
        // Creating a call context
        PhonyList<Integer> actual = list();
        boolean empty;

        actual.add(0);

        // Calling the tested operation
        empty = actual.isEmpty();

        // Oracle
        assertEquals(empty, false);

        actual.clear();
        empty = actual.isEmpty();

        assertEquals(empty, true);
    }

    /**
     * Tests the contains method of a list.
     *
     * @see PhonyList#contains(Object)
     * @type Functional
     * @input []
     * @oracle The value returned with contains must correspond if the object is in the list or not.
     * @passed Yes
     */
    @Test
    public void contains_Test() {
        // Creating a call context
        PhonyList<Integer> actual = list();

        boolean value;

        actual.add(0);
        actual.add(1);

        // Calling the tested operation
        value = actual.contains(1);

        // Oracle
        assertEquals(value, true);

        actual.clear();
        value = actual.contains(1);

        assertEquals(value, false);
    }

    /**
     * Tests the contains method of a list.
     *
     * @see PhonyList#contains(Object)
     * @type Functional
     * @input []
     * @oracle The value returned with contains must correspond if the object is in the list or not.
     * @passed Yes
     */
    @Test
    public void set_Test() {
        // Creating a call context
        PhonyList<Integer> actual = list();

        int oldValue = 0;

        actual.add(0);
        actual.add(1);

        // Calling the tested operation
        oldValue = actual.set(1, 2);

        // Oracle
        assertEquals(1, oldValue);
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
        assertEquals(true, value);
    }
}
