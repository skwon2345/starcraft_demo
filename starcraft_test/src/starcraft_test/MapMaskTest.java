package starcraft_test;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.junit.*;

import starcraft.BoxCollider;
import starcraft.Chunk;
import starcraft.CircleCollider;
import starcraft.Collider;
import starcraft.MapMask;
import starcraft.Pos;

public class MapMaskTest {
//
//    @Test
//    public void testBound1By1Empty_01() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_1_by_1_empty.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
////            map01W = map01.getWidth();
////            map01H = map01.getHeight();
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask01(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        
//        assertTrue(MapMask.chunkColl.size() == 0);
//    }
//
//    @Test
//    public void testBound1By1Filled_02() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_1_by_1_filled.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
////            map01W = map01.getWidth();
////            map01H = map01.getHeight();
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask02(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 1);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 0.0);
//    }
//    
//    @Test
//    public void testBound2By1Empty0Com0Filled1Com0_03() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_2_by_1_empty_0_com_0_filled_1_com_0.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 1);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 0.0);
//    }
//    
//    @Test
//    public void testBound2By1Filled0Com0Empty1Com0_04() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_2_by_1_filled_0_com_0_empty_1_com_0.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 1);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 0.0);
//    }
//    
//    @Test
//    public void testBound2By1Empty_05() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_2_by_1_empty.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        
//        assertTrue(MapMask.chunkColl.size() == 0);
//    }
//    
//    @Test
//    public void testBound2By1Filled_06() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_2_by_1_filled.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 2);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(1);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 0.0);
//    }
//    
//    @Test
//    public void testBound2By2Empty_07() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_2_by_2_empty.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        
//        assertTrue(MapMask.chunkColl.size() == 0);
//    }
//    
//    @Test
//    public void testBound2By2Empty0Com0Filled1Com0Filled0Com1Empty1Com1_08() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_2_by_2_empty_0_com_0_filled_1_com_0_filled_0_com_1_empty_1_com_1.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 2);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 1);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curChunk = MapMask.chunkColl.get(1);
//        assertTrue(curChunk.posColl.size() == 1);
//        
//        curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 1.0);
//    }
//    
//    @Test
//    public void testBound2By2Filled0Com0Empty1Com0Empty0Com1Filled1Com1_09() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_2_by_2_filled_0_com_0_empty_1_com_0_filled_0_com_1_empty_1_com_1.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 2);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(1);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 1.0);
//    }
//    
//    @Test
//    public void testBound2By2Empty0Com0Filled1Com0Empty0Com1Filled1Com1_10() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_2_by_2_empty_0_com_0_filled_1_com_0_empty_0_com_1_filled_1_com_1.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 2);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(1);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 1.0);
//    }
//    
//    @Test
//    public void testBound2By2Empty0Com0Filled1Com0Filled0Com1Filled1Com1_11() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_2_by_2_empty_0_com_0_filled_1_com_0_filled_0_com_1_filled_1_com_1.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 3);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(1);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 1.0);
//        
//        curPos = curChunk.posColl.get(2);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 1.0);
//    }
//    
//    @Test
//    public void testBound2By2Filled0Com0Empty1Com0Filled0Com1Filled1Com1_12() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_2_by_2_filled_0_com_0_empty_1_com_0_filled_0_com_1_filled_1_com_1.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 3);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(1);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 1.0);
//        
//        curPos = curChunk.posColl.get(2);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 1.0);
//    }
//    
//    @Test
//    public void testBound2By2Filled0Com0Filled1Com0Empty0Com1Filled1Com1_13() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_2_by_2_filled_0_com_0_filled_1_com_0_empty_0_com_1_filled_1_com_1.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 3);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(1);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(2);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 1.0);
//    }
//    
//    @Test
//    public void testBound2By2Filled0Com0Filled1Com0Filled0Com1Empty1Com1_14() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_2_by_2_filled_0_com_0_filled_1_com_0_filled_0_com_1_empty_1_com_1.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
////        assertTrue(curChunk.posColl.size() == 3);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(1);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(2);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 1.0);
////        for (int i = 0 ; i < curChunk.mapMaskHullTest.size(); i++) {
////            System.out.println("hull - x: " + curChunk.mapMaskHullTest.get(i).xOnMap + " y: " + curChunk.mapMaskHullTest.get(i).yOnMap);
////        }
//    }
    
//    @Test
//    public void testBound2By2Filled_15() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_2_by_2_filled.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 4);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(1);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(2);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 1.0);
//        
//        curPos = curChunk.posColl.get(3);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 1.0);
//    }
//    
//    @Test
//    public void testBound3By1Empty_16() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_1_empty.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 0);
//    }
//
//    @Test
//    public void testBound3By1Filled0Com0Empty1Com0Empty2Com0_17() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_1_filled_0_com_0_empty_1_com_0_empty_2_com_0.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 1);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 0.0);
//    }
//    
//    @Test
//    public void testBound3By1Empty0Com0Filled1Com0Empty2Com0_18() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_1_empty_0_com_0_filled_1_com_0_empty_2_com_0.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 1);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 0.0);
//    }
//    
//    @Test
//    public void testBound3By1Empty0Com0Empty1Com0Filled2Com0_19() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_1_empty_0_com_0_empty_1_com_0_filled_2_com_0.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 1);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 2.0);
//        assertTrue(curPos.yOnMap == 0.0);
//    }
//    
//    @Test
//    public void testBound3By1Filled0Com0Filled1Com0Empty2Com0_20() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_1_filled_0_com_0_filled_1_com_0_empty_2_com_0.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 2);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(1);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 0.0);
//    }
//    
//    @Test
//    public void testBound3By1Empty0Com0Filled1Com0Filled2Com0_21() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_1_empty_0_com_0_filled_1_com_0_filled_2_com_0.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 2);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(1);
//        assertTrue(curPos.xOnMap == 2.0);
//        assertTrue(curPos.yOnMap == 0.0);
//    }
//    
//    @Test
//    public void testBound3By1Filled_22() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_1_filled.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 3);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(1);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(2);
//        assertTrue(curPos.xOnMap == 2.0);
//        assertTrue(curPos.yOnMap == 0.0);
//    }
//    
//    @Test
//    public void testBound3By2Empty_23() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_2_empty.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 0);
//    }
//    
//    @Test
//    public void testBound3By2Filled0Com0_24() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_2_filled_0_com_0.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 1);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 0.0);
//    }
//    
//    @Test
//    public void testBound3By2Filled1Com0_25() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_2_filled_1_com_0.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 1);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 0.0);
//    }
//    
//    @Test
//    public void testBound3By2Filled2Com0_26() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_2_filled_2_com_0.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 1);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 2.0);
//        assertTrue(curPos.yOnMap == 0.0);
//    }
//    
//    @Test
//    public void testBound3By2Filled0Com1_27() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_2_filled_0_com_1.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 1);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 1.0);
//    }
//    
//    @Test
//    public void testBound3By2Filled1Com1_28() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_2_filled_1_com_1.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 1);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 1.0);
//    }
//    
//    @Test
//    public void testBound3By2Filled2Com1_29() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_2_filled_2_com_1.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 1);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 2.0);
//        assertTrue(curPos.yOnMap == 1.0);
//    }
//    
//    @Test
//    public void testBound3By2Filled0Com0Filled1Com0_30() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_2_filled_0_com_0_filled_1_com_0.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 2);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(1);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 0.0);
//    }
//    
//    @Test
//    public void testBound3By2Filled1Com0Filled2Com0_31() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_2_filled_1_com_0_filled_2_com_0.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 2);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(1);
//        assertTrue(curPos.xOnMap == 2.0);
//        assertTrue(curPos.yOnMap == 0.0);
//    }
//    
//    @Test
//    public void testBound3By2Filled0Com1Filled1Com1_32() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_2_filled_0_com_1_filled_1_com_1.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 2);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 1.0);
//        
//        curPos = curChunk.posColl.get(1);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 1.0);
//    }
//    
//    @Test
//    public void testBound3By2Filled1Com1Filled2Com1_33() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_2_filled_1_com_1_filled_2_com_1.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 2);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 1.0);
//        
//        curPos = curChunk.posColl.get(1);
//        assertTrue(curPos.xOnMap == 2.0);
//        assertTrue(curPos.yOnMap == 1.0);
//    }
//    
//    @Test
//    public void testBound3By2Filled0Com0Filled0Com1Filled1Com1Filled2Com1_34() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_2_filled_0_com_0_filled_1_com_0_filled_1_com_1_filled_2_com_1.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 4);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(1);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(2);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 1.0);
//        
//        curPos = curChunk.posColl.get(3);
//        assertTrue(curPos.xOnMap == 2.0);
//        assertTrue(curPos.yOnMap == 1.0);
////        MapMask.convexHull(posColl, mapMaskPos.size());
//
////        for (int i = 0 ; i < curChunk.mapMaskHullTest.size(); i++) {
////            System.out.println("hull - x: " + curChunk.mapMaskHullTest.get(i).xOnMap + " y: " + curChunk.mapMaskHullTest.get(i).yOnMap);
////        }
////        System.exit(1);
//    }
//    
//    @Test
//    public void testBound3By2Filled0Com0Filled2Com0Filled0Com1Filled2Com1_35() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_2_filled_0_com_0_filled_2_com_0_filled_0_com_1_filled_2_com_1.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 2);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 2);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(1);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 1.0);
//        
//        curChunk = MapMask.chunkColl.get(1);
//        assertTrue(curChunk.posColl.size() == 2);
//        
//        curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 2.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(1);
//        assertTrue(curPos.xOnMap == 2.0);
//        assertTrue(curPos.yOnMap == 1.0);
//    }
//    
//    @Test
//    public void testBound3By2Filled_36() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_2_filled.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 6);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(1);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(2);
//        assertTrue(curPos.xOnMap == 2.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(3);
//        assertTrue(curPos.xOnMap == 2.0);
//        assertTrue(curPos.yOnMap == 1.0);
//        
//        curPos = curChunk.posColl.get(4);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 1.0);
//        
//        curPos = curChunk.posColl.get(5);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 1.0);
//    }
//    
//    @Test
//    public void testBound3By3Empty_37() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_3_empty.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 0);
//    }
//    
//    @Test
//    public void testBound3By3Filled0Com0Filled1Com0Filled2Com1Filled2Com0Filled2Com2_38() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_3_filled_0_com_0_filled_1_com_0_filled_2_com_1_filled_2_com_0_filled_2_com_2.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 3);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size()==2);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(1);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curChunk = MapMask.chunkColl.get(1);
//        assertTrue(curChunk.posColl.size()==2);
//        
//        curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 2.0);
//        assertTrue(curPos.yOnMap == 1.0);
//        
//        curPos = curChunk.posColl.get(1);
//        assertTrue(curPos.xOnMap == 2.0);
//        assertTrue(curPos.yOnMap == 2.0);
//        
//        curChunk = MapMask.chunkColl.get(2);
//        assertTrue(curChunk.posColl.size()==1);
//        
//        curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 2.0);
//    }
//    
//    @Test
//    public void testBound3By3Filled0Com0Filled2Com0Filled1Com1Filled0Com2Filled2Com2_39() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_3_filled_0_com_0_filled_2_com_0_filled_1_com_1_filled_0_com_2_filled_2_com_2.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 5);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size()==1);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 0.0);
//                
//        curChunk = MapMask.chunkColl.get(1);
//        assertTrue(curChunk.posColl.size()==1);
//        
//        curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 2.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curChunk = MapMask.chunkColl.get(2);
//        assertTrue(curChunk.posColl.size()==1);
//        
//        curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 1.0);
//        
//        curChunk = MapMask.chunkColl.get(3);
//        assertTrue(curChunk.posColl.size()==1);
//        
//        curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 2.0);
//        
//        curChunk = MapMask.chunkColl.get(4);
//        assertTrue(curChunk.posColl.size()==1);
//        
//        curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 2.0);
//        assertTrue(curPos.yOnMap == 2.0);
//    }
//    
//    @Test
//    public void testBound3By3Filled1Com0Filled0Com1Filled2Com1Filled1Com2_40() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_3_filled_1_com_0_filled_0_com_1_filled_2_com_1_filled_1_com_2.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 4);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size()==1);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 0.0);
//                
//        curChunk = MapMask.chunkColl.get(1);
//        assertTrue(curChunk.posColl.size()==1);
//        
//        curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 1.0);
//        
//        curChunk = MapMask.chunkColl.get(2);
//        assertTrue(curChunk.posColl.size()==1);
//        
//        curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 2.0);
//        assertTrue(curPos.yOnMap == 1.0);
//        
//        curChunk = MapMask.chunkColl.get(3);
//        assertTrue(curChunk.posColl.size()==1);
//        
//        curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 2.0);
//    }
//    
//    @Test
//    public void testBound3By3Filled_41() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_3_filled.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size()==9);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(1);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(2);
//        assertTrue(curPos.xOnMap == 2.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(3);
//        assertTrue(curPos.xOnMap == 2.0);
//        assertTrue(curPos.yOnMap == 1.0);
//        
//        curPos = curChunk.posColl.get(4);
//        assertTrue(curPos.xOnMap == 2.0);
//        assertTrue(curPos.yOnMap == 2.0);
//        
//        curPos = curChunk.posColl.get(5);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 2.0);
//        
//        curPos = curChunk.posColl.get(6);
//        assertTrue(curPos.xOnMap == 1.0);
//        assertTrue(curPos.yOnMap == 1.0);
//        
//        curPos = curChunk.posColl.get(7);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 1.0);
//        
//        curPos = curChunk.posColl.get(8);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 2.0);
//    }
//    
//    @Test
//    public void testBound10By10Empty_42() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_10_by_10_empty.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 0);
//    }
//    
//    @Test
//    public void testBound10By10Filled_43() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_10_by_10_filled.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 100);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(99);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 1.0);
//    }
//    
//    @Test
//    public void testBound100By100Empty_44() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_100_by_100_empty.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 0);
//    }
//
//    //-------------------------------------------------------------------------------------
//    // Stack Overflow Occurs here when Recursive begins
//    //-------------------------------------------------------------------------------------
//    @Test
//    public void testBound100By100Filled_45() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_100_by_100_filled.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(curChunk.posColl.size() == 10000);
//        
//        Pos curPos = curChunk.posColl.get(0);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 0.0);
//        
//        curPos = curChunk.posColl.get(9999);
//        assertTrue(curPos.xOnMap == 0.0);
//        assertTrue(curPos.yOnMap == 1.0);
//    }
//    //-------------------------------------------------------------------------------------
//    // Stack Overflow Occurs here when Recursive ends
//    //-------------------------------------------------------------------------------------
//    @Test
//    public void testCollider2By2Filled0Com0Empty1Com0Filled0Com1Filled1Com1_12_46() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_2_by_2_filled_0_com_0_empty_1_com_0_filled_0_com_1_filled_1_com_1.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(colls.get(0) instanceof BoxCollider);
//    }
//    
//    @Test
//    public void testCollider3By3Filled1Com0Filled0Com1Filled1Com1Filled2Com1Filled1Com2_47() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_3_by_3_filled_1_com_0_filled_0_com_1_filled_1_com_1_filled_2_com_1_filled_1_com_2.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
//        assertTrue(colls.get(0) instanceof CircleCollider);
////        for (int i = 0 ; i < curChunk.mapMaskHullTest.size(); i++) {
////            System.out.println("hull - x: " + curChunk.mapMaskHullTest.get(i).xOnMap + " y: " + curChunk.mapMaskHullTest.get(i).yOnMap);
////        }
//    }
//    
//    @Test
//    public void testCollider10By10FilledCircle_48() {
//        BufferedImage testMask01 = null;
//        String imageFilename = "assets/map_mask_test/bound_10_by_10_filled_circle.png";
//        try {
//            testMask01 = ImageIO.read(new File(imageFilename));
//        } catch (IOException e) {
//            System.out.println("MapMaskTest::testMapMask03(): error - image file not found! : " + imageFilename);
//            System.exit(1);
//        }
//        
//        Vector<Collider> colls = MapMask.createBgColliders(testMask01);
////        fail("Not yet implemented");
//        assertTrue(MapMask.chunkColl.size() == 1);
//        
//        Chunk curChunk = MapMask.chunkColl.get(0);
////        System.out.println("maxX: " + (curChunk.maxW-curChunk.minW) + " maxY: " + (curChunk.maxH-curChunk.minH));
////        System.out.println("box area: " + curChunk.getAreaOfBox() + " circle area: " + curChunk.getAreaOfCircle());
//        assertTrue(colls.get(0) instanceof CircleCollider);
//    }
}
