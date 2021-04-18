package starcraft_test;

import static org.junit.Assert.*;

import org.junit.*;

import starcraft.CircleCollider;

public class CircleColliderTest {

    // Test Boundary: when the second circle collider adjacent to ...
    //  012
    // 0 *
    // 1* *
    // 2 *
    @Test
    public void testAddBoundary_cc1_Center1and1_Rad1_cc2_Center1and1_Rad1() {
        CircleCollider cirColl1 = new CircleCollider(1, 1, 1);
        CircleCollider cirColl2 = new CircleCollider(1, 1, 1);
        
        boolean result = cirColl1.collisionDetection(cirColl2);
        boolean expected = true;
        assertTrue(result == expected);
    }
    
    // Test Boundary: when the second circle collider adjacent to ...
    //  0123
    // 0 *@
    // 1*@*@
    // 2 *@
    @Test
    public void testAddBoundary_cc1_Center1and1_Rad1_cc2_Center2and1_Rad1() {
        CircleCollider cirColl1 = new CircleCollider(1, 1, 1);
        CircleCollider cirColl2 = new CircleCollider(2, 1, 1);
        
        boolean result = cirColl1.collisionDetection(cirColl2);
        boolean expected = true;
        assertTrue(result == expected);
    }
    
    // Test Boundary: when the second circle collider adjacent to ...
    //  01234
    // 0 * @
    // 1* @ @
    // 2 * @
    @Test
    public void testAddBoundary_cc1_Center1and1_Rad1_cc2_Center3and1_Rad1() {
        CircleCollider cirColl1 = new CircleCollider(1, 1, 1);
        CircleCollider cirColl2 = new CircleCollider(3, 1, 1);
        
        boolean result = cirColl1.collisionDetection(cirColl2);
        boolean expected = true;
        assertTrue(result == expected);
    }

    // Test Boundary: when the second circle collider adjacent to ...
    //  012345
    // 0 *  @
    // 1* *@ @
    // 2 *  @
    @Test
    public void testAddBoundary_cc1_Center1and1_Rad1_cc2_Center4and1_Rad1() {
        CircleCollider cirColl1 = new CircleCollider(1, 1, 1);
        CircleCollider cirColl2 = new CircleCollider(4, 1, 1);
        System.out.println("testAddBoundary_cc1_Center1and1_Rad1_cc2_Center4and1_Rad1");

        boolean result = cirColl1.collisionDetection(cirColl2);
        boolean expected = true;
        assertTrue(result == expected);
    }
    
    // Test Boundary: when the second circle collider adjacent to ...
    //  012345
    // 0  * @
    // 1 * @ @
    // 2* @ * @
    // 3 * @ @
    // 4  * @
    @Test
    public void testAddBoundary_cc1_Center2and2_Rad2_cc2_Center4and2_Rad2() {
        CircleCollider cirColl1 = new CircleCollider(2, 2, 2);
        CircleCollider cirColl2 = new CircleCollider(4, 2, 2);
        
        boolean result = cirColl1.collisionDetection(cirColl2);
        boolean expected = true;
        assertTrue(result == expected);
    }
    
    // Test Boundary: when the first circle collider adjacent to ...
    //  012
    // 0 @
    // 1@ @
    // 2 @
    @Test
    public void testAddBoundary_cc2_Center1and1_Rad1_cc1_Center1and1_Rad1() {
        CircleCollider cirColl1 = new CircleCollider(1, 1, 1);
        CircleCollider cirColl2 = new CircleCollider(1, 1, 1);
        
        boolean result = cirColl2.collisionDetection(cirColl1);
        boolean expected = true;
        assertTrue(result == expected);
    }
    
 // Test Boundary: when the first circle collider adjacent to ...
    //  0123
    // 0 *@
    // 1*@*@
    // 2 *@
    @Test
    public void testAddBoundary_cc2_Center2and1_Rad1_cc1_Center1and1_Rad1() {
        CircleCollider cirColl1 = new CircleCollider(1, 1, 1);
        CircleCollider cirColl2 = new CircleCollider(2, 1, 1);
        
        boolean result = cirColl2.collisionDetection(cirColl1);
        boolean expected = true;
        assertTrue(result == expected);
    }
    
 // Test Boundary: when the first circle collider adjacent to ...
    //  01234
    // 0 * @
    // 1* @ @
    // 2 * @
    @Test
    public void testAddBoundary_cc2_Center3and1_Rad1_cc1_Center1and1_Rad1() {
        CircleCollider cirColl1 = new CircleCollider(1, 1, 1);
        CircleCollider cirColl2 = new CircleCollider(3, 1, 1);
        
        boolean result = cirColl2.collisionDetection(cirColl1);
        boolean expected = true;
        assertTrue(result == expected);
    }
    
    // Test Boundary: when the first circle collider adjacent to ...
    //  012345
    // 0 *  @
    // 1* *@ @
    // 2 *  @
    @Test
    public void testAddBoundary_cc2_Center4and1_Rad1_cc1_Center1and1_Rad1() {
        CircleCollider cirColl1 = new CircleCollider(1, 1, 1);
        CircleCollider cirColl2 = new CircleCollider(4, 1, 1);
        
        boolean result = cirColl2.collisionDetection(cirColl1);
        boolean expected = true;
        assertTrue(result == expected);
    }
    
    // Test Boundary: when the first circle collider adjacent to ...
    //  012345
    // 0  * @
    // 1 * @ @
    // 2* @ * @
    // 3 * @ @
    // 4  * @
    @Test
    public void testAddBoundary_cc2_Center4and2_Rad2_cc1_Center2and2_Rad2() {
        CircleCollider cirColl1 = new CircleCollider(2, 2, 2);
        CircleCollider cirColl2 = new CircleCollider(4, 2, 2);
        
        boolean result = cirColl2.collisionDetection(cirColl1);
        boolean expected = true;
        assertTrue(result == expected);
    }
}
