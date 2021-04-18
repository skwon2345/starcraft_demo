package starcraft_test;

import static org.junit.Assert.*;

import org.junit.*;

import starcraft.BoxCollider;
import starcraft.Unit;

public class BoxColliderTest {

    // Test Boundary: when the second box collider adjacent to ...
    // * <-- same pos
    @Test
    public void testAddBoundary_bc1_Pos0and0_Dim1and1_bc2_Pos0and0_Dim1and1() {
        BoxCollider boxColl1 = new BoxCollider(0, 0, 1, 1);
        BoxCollider boxColl2 = new BoxCollider(0, 0, 1, 1);
        
        boolean result = boxColl1.collisionDetection(boxColl2);
        boolean expected = true;
        assertTrue(result == expected);
//        assertTrue(true);
    }

    // Test Boundary: when the second box collider adjacent to ...
    //  012
    // 0*@@
    // 1*@@
    @Test
    public void testAddBoundary_bc1_Pos0and0_Dim1and1_bc2_Pos1and0_Dim1and1() {
        BoxCollider boxColl1 = new BoxCollider(0, 0, 1, 1);
        BoxCollider boxColl2 = new BoxCollider(1, 0, 1, 1);
        
        boolean result = boxColl1.collisionDetection(boxColl2);
        boolean expected = true;
        assertTrue(result == expected);
//        assertTrue(true);
    }

    // Test Boundary: when the second box collider adjacent to ...
    //  0123
    // 0**@@
    // 1**@@
    @Test
    public void testAddBoundary_bc1_Pos0and0_Dim1and1_bc2_Pos2and0_Dim1and1() {
        BoxCollider boxColl1 = new BoxCollider(0, 0, 1, 1);
        BoxCollider boxColl2 = new BoxCollider(2, 0, 1, 1);
        
        boolean result = boxColl1.collisionDetection(boxColl2);
        boolean expected = false;
        assertTrue(result == expected);
//        assertTrue(true);
    }
    
    // Test Boundary: when the second box collider adjacent to ...
    //  01234
    // 0***@@@
    // 1* *@ @
    // 2***@@@
    @Test
    public void testAddBoundary_bc1_Pos0and0_Dim2and2_bc2_Pos3and0_Dim2and2() {
        BoxCollider boxColl1 = new BoxCollider(0, 0, 2, 2);
        BoxCollider boxColl2 = new BoxCollider(3, 0, 2, 2);
        
        boolean result = boxColl1.collisionDetection(boxColl2);
        boolean expected = false;
        assertTrue(result == expected);
//        assertTrue(true);
    }
    
    // Test Boundary: when the second box collider adjacent to ...
    //  0123456
    // 0***@@@@
    // 1*  @  @
    // 2*  @  @
    // 3***@@@@
    @Test
    public void testAddBoundary_bc1_Pos0and0_Dim3and3_bc2_Pos3and0_Dim3and3() {
        BoxCollider boxColl1 = new BoxCollider(0, 0, 3, 3);
        BoxCollider boxColl2 = new BoxCollider(3, 0, 3, 3);
        
        boolean result = boxColl1.collisionDetection(boxColl2);
        boolean expected = true;
        assertTrue(result == expected);
    }
    
    // Test Boundary: when the first box collider adjacent to ...
    //  01
    // 0@@
    // 1@@
    @Test
    public void testAddBoundary_bc2_Pos0and0_Dim1and1_bc1_Pos0and0_Dim1and1() {
        BoxCollider boxColl1 = new BoxCollider(0, 0, 1, 1);
        BoxCollider boxColl2 = new BoxCollider(0, 0, 1, 1);
        
        boolean result = boxColl2.collisionDetection(boxColl1);
        boolean expected = true;
        assertTrue(result == expected);
    }
    
    // Test Boundary: when the first box collider adjacent to ...
    //  012
    // 0@**
    // 1@**
    @Test
    public void testAddBoundary_bc2_Pos1and0_Dim1and1_bc1_Pos0and0_Dim1and1() {
        BoxCollider boxColl1 = new BoxCollider(0, 0, 1, 1);
        BoxCollider boxColl2 = new BoxCollider(1, 0, 1, 1);
        
        boolean result = boxColl2.collisionDetection(boxColl1);
        boolean expected = true;
        assertTrue(result == expected);
    }
    
    // Test Boundary: when the first box collider adjacent to ...
    //  0123
    // 0@@**
    // 1@@**
    @Test
    public void testAddBoundary_bc2_Pos2and0_Dim1and1_bc1_Pos1and0_Dim1and1() {
        BoxCollider boxColl1 = new BoxCollider(0, 0, 1, 1);
        BoxCollider boxColl2 = new BoxCollider(2, 0, 1, 1);
        
        boolean result = boxColl2.collisionDetection(boxColl1);
        boolean expected = false;
        assertTrue(result == expected);
    }
    
    // Test Boundary: when the first box collider adjacent to ...
    //  01234
    // 0@@@***
    // 1@ @* *
    // 2@@@***
    @Test
    public void testAddBoundary_bc2_Pos3and0_Dim2and2_bc1_Pos0and0_Dim2and2() {
        BoxCollider boxColl1 = new BoxCollider(0, 0, 2, 2);
        BoxCollider boxColl2 = new BoxCollider(3, 0, 2, 2);
        
        boolean result = boxColl2.collisionDetection(boxColl1);
        boolean expected = false;
        assertTrue(result == expected);
    }
    
    // Test Boundary: when the first box collider adjacent to ...
    //  0123456
    // 0@@@****
    // 1@  *  *
    // 2@  *  *
    // 3@@@****
    @Test
    public void testAddBoundary_bc2_Pos3and0_Dim3and3_bc1_Pos0and0_Dim3and3() {
        BoxCollider boxColl1 = new BoxCollider(0, 0, 3, 3);
        BoxCollider boxColl2 = new BoxCollider(3, 0, 3, 3);
        
        boolean result = boxColl2.collisionDetection(boxColl1);
        boolean expected = true;
        assertTrue(result == expected);
    }
}
