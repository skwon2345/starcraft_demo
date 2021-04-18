package starcraft_test;

import static org.junit.Assert.*;

import org.junit.*;

public class StarcraftTest {

    @Test
    public void testAddBoundary1() {
//        fail("Not yet implemented");
        assertTrue(starcraft.Starcraft.add(0, 0) == 0);
    }

    @Test
    public void testAddBoundary2() {
        assertTrue(starcraft.Starcraft.add(0, 1) == 1);
    }
    
    @Test
    public void testAddBoundary3() {
        int result = starcraft.Starcraft.add(1, 0);
        int expected = 1;
        assertTrue(result == expected);
    }
}
