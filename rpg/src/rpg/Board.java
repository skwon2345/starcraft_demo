package rpg;

import java.util.Random;

public class Board {
    public static final int ROW_SIZE = 10;
    public static final int COL_SIZE = 40;

    public static final int NUM_DIRS = 4;

    public static final int DIR_NONE = -1;

    public static final int DIR_N = 0;
    public static final int DIR_E = 1;
    public static final int DIR_S = 2;
    public static final int DIR_W = 3;

    public static final char DIR_N_CHAR = 'w';
    public static final char DIR_E_CHAR = 'd';
    public static final char DIR_S_CHAR = 's';
    public static final char DIR_W_CHAR = 'a';

    public static final char SHAPE_EMPTY = '.';
    
    private Tile [][] board;
    private int rowSize;
    private int colSize;
    
    private Hero hero;
    
    private Monster [] mons;
    private int maxNumMons;
    private int numMons;

    private Boss [] bosses;
    private int maxNumBosses;
    private int numBosses;
    
    private String [] MAP_01 = {
        "........................................",
        "........................................",
        ".....Y.Y................................",
        ".....Y.Y................................",
        ".....Y&Y................................",
        "......Y.................................",
        "........................................",
        "........................................",
        "......H.................................",
        "........................................"
    };

    public Board() {
        init(ROW_SIZE, COL_SIZE, Monster.MAX_NUM_MONS, Boss.MAX_NUM_BOSSES);
    }
    
    public Board(int rowSize, int colSize, int maxNumMons, int maxNumBosses) {
        init(rowSize, colSize, maxNumMons, maxNumBosses);
    }
    
    public void init(int rowSize, int colSize, int maxNumMons, int maxNumBosses) {
        //---------------------------------------------------------------------
        // init the board
        //---------------------------------------------------------------------
        this.rowSize = rowSize;
        this.colSize = colSize;
        
        board = new Tile [rowSize][colSize];
        
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                board[i][j] = new Tile();
            }
        }
        
    //------------------------------------------------------------------------------
    //------------------------------------------------------------------------------
    //------------------------------------------------------------------------------
    //#ifdef BOSS_IQ_120_TEST_CASE // MAP01로 map을 load하여 만드는 경우.
    //------------------------------------------------------------------------------
    //------------------------------------------------------------------------------
    //------------------------------------------------------------------------------
        if (RPG.BOSS_IQ_120_TEST_CASE) {
//            maxNumMons = 0;
//            maxNumBosses = 0;
//            
//            for (int i = 0; i < rowSize; i++) {
//                for (int j = 0; j < colSize; j++) {
//                    if (MAP_01[i][j] == SHAPE_MONSTER) {
//                        maxNumMons++;
//                    }
//                    else if (MAP_01[i][j] == SHAPE_BOSS) {
//                        maxNumBosses++;
//                    }
//                }
//            }
//            
//            this.maxNumMons = maxNumMons;
//            if (maxNumMons > 0) {
//                mons = new Monster *[maxNumMons];
//            }
//            else {
//                mons = null;
//            }
//            numMons = 0;
//            
//            this.maxNumBosses = maxNumBosses;
//            if (maxNumBosses > 0) {
//                bosses = new Boss *[maxNumBosses];
//            }
//            else {
//                bosses = null;
//            }
//            numBosses = 0;
//            
//            for (int i = 0; i < rowSize; i++) {
//                for (int j = 0; j < colSize; j++) {
//                    if (MAP_01[i][j] == SHAPE_HERO) {
//                        hero = new Hero();
//                        setUnit(i, j, hero);
//                        hero.setBoard(this);
//                    }
//                    else if (MAP_01[i][j] == SHAPE_MONSTER) {
//                        mons[numMons] = new Monster();
//                        setUnit(i, j, mons[numMons]);
//                        mons[numMons].setBoard(this);
//                        numMons++;
//                    }
//                    else if (MAP_01[i][j] == SHAPE_BOSS) {
//                        bosses[numBosses] = new Boss();
//                        setUnit(i, j, bosses[numBosses]);
//                        bosses[numBosses].setBoard(this);
//                        numBosses++;
//                    }
//                    else if (MAP_01[i][j] == SHAPE_TREE) {
//                        setProp(i, j, new Tree());
//                    }
//                    else if (MAP_01[i][j] == SHAPE_POTION) {
//                        setItem(i, j, new Potion());
//                    }
//                }
//            }
        }
    //------------------------------------------------------------------------------
    //------------------------------------------------------------------------------
    //------------------------------------------------------------------------------
    //#else // random으로 map을 만드는 경우.
    //------------------------------------------------------------------------------
    //------------------------------------------------------------------------------
    //------------------------------------------------------------------------------
        else {
            //---------------------------------------------------------------------
            // init the hero
            //---------------------------------------------------------------------
            hero = new Hero();
            setUnit(hero.getRow(), hero.getCol(), hero);
            hero.setBoard(this);
            
            //---------------------------------------------------------------------
            // init the monsters
            //---------------------------------------------------------------------
            this.maxNumMons = maxNumMons;
            mons = new Monster [maxNumMons];
            numMons = 0;
        
            for (int i = 0; i < maxNumMons; i++) {
                while (true) {
                    int randRow = RPG.r.nextInt(ROW_SIZE);
                    int randCol = RPG.r.nextInt(COL_SIZE);
                    
                    if (getUnit(randRow, randCol) == null &&
                        getProp(randRow, randCol) == null &&
                        getItem(randRow, randCol) == null) {
                        mons[i] = new Monster();
                        setUnit(randRow, randCol, mons[i]);
                        mons[i].setBoard(this);
                        break;
                    }
                }
                numMons++;
            }
            
            //---------------------------------------------------------------------
            // init the bosses
            //---------------------------------------------------------------------
            
            this.maxNumBosses = maxNumBosses;
            bosses = new Boss [maxNumBosses];
            numBosses = 0;
            
            for (int i = 0; i < maxNumBosses; i++) {
                while (true) {
                    int randRow = RPG.r.nextInt(ROW_SIZE);
                    int randCol = RPG.r.nextInt(COL_SIZE);
                    
                    if (getUnit(randRow, randCol) == null &&
                        getProp(randRow, randCol) == null &&
                        getItem(randRow, randCol) == null) {
                        bosses[i] = new Boss();
                        setUnit(randRow, randCol, bosses[i]);
                        bosses[i].setBoard(this);
                        break;
                    }
                }
                numBosses++;
            }
            
            //---------------------------------------------------------------------
            // init trees
            //---------------------------------------------------------------------
            //    board[0][0].setProp(new Tree()); // 직접 해당 위치의 Tile에게 명령을 내림.
            //    setProp(0, 0, new Tree()); // validation까지 해주는 내 펑션을 call하는 것이고..
            
            for (int i = 0; i < Tree.MAX_NUM_TREES; i++) {
                while (true) {
                    int randRow = RPG.r.nextInt(ROW_SIZE);
                    int randCol = RPG.r.nextInt(COL_SIZE);
                    
                    if (getUnit(randRow, randCol) == null &&
                        getProp(randRow, randCol) == null &&
                        getItem(randRow, randCol) == null) {
                        setProp(randRow, randCol, new Tree());
                        break;
                    }
                }
            }
            
            //---------------------------------------------------------------------
            // init potions
            //---------------------------------------------------------------------
            for(int i = 0; i < Potion.MAX_NUM_POTIONS; i++) {
                while(true) {
                    int randRow = RPG.r.nextInt(ROW_SIZE);
                    int randCol = RPG.r.nextInt(COL_SIZE);
                    
                    if (getUnit(randRow, randCol) == null &&
                        getProp(randRow, randCol) == null &&
                        getItem(randRow, randCol) == null) {
                        setItem(randRow, randCol, new Potion());
                        break;
                    }
                }
            }
        }
    //------------------------------------------------------------------------------
    //#endif
    //------------------------------------------------------------------------------
    }
    
    public String toString() {
        String s = "";
        s += "-----------------------------------------------" + "\n";
        
        s += "+";
        for (int j = 0; j < colSize; j++) {
            s += '-';
        }
        s += "+" + "\n";
        
        for (int i = 0; i < rowSize; i++) {
            s += "|";
            
            for (int j = 0; j < colSize; j++) {
                s += board[i][j].toString();
            }
            s += "|" + "\n";
        }
        
        s += "+";
        for (int j = 0; j < colSize; j++) {
            s += '-';
        }
        s += "+" + "\n";
        
        return s;
    }
    
    public int getRowSize() {
        return rowSize;
    }
    
    public int getColSize() {
        return colSize;
    }
    
    public boolean isOutOfBoundary(int row, int col) {
        return row < 0 || row >= rowSize || col < 0 || col >= colSize;
    }
    
    public Hero getHero() {
        return hero;
    }
    
    public boolean setUnit(int row, int col, Unit unit) {
        if (row < 0 || row >= rowSize || col < 0 || col >= colSize) { // validation: boundary check
            return false;
        }
        
        board[row][col].setUnit(unit);
        
        if (unit != null) {
            unit.setRow(row);
            unit.setCol(col);
        }
        
        return true;
    }
    
    public Unit getUnit(int row, int col) {
        if (row < 0 || row >= rowSize || col < 0 || col >= colSize) { // validation: boundary check
            return null;
        }
        
        return board[row][col].getUnit();
    }
    
    public void setProp(int row, int col, Prop prop) {
        if (row < 0 || row >= rowSize || col < 0 || col >= colSize) { // validation: boundary check
            return;
        }
        
        board[row][col].setProp(prop);
    }
    
    public Prop getProp(int row, int col) {
        if (row < 0 || row >= rowSize || col < 0 || col >= colSize) { // validation: boundary check
            return null;
        }
        
        return board[row][col].getProp();
    }
    
    public void setItem(int row, int col, Item item) {
        if (row < 0 || row >= rowSize || col < 0 || col >= colSize) { // validation: boundary check
            return;
        }
        
        board[row][col].setItem(item);
    }
    
    public Item getItem(int row, int col) {
        if (row < 0 || row >= rowSize || col < 0 || col >= colSize) { // validation: boundary check
            return null;
        }
        
        return board[row][col].getItem();
    }

    public void moveMons() {
        if (mons == null) {
            return;
        }

        for (int i = 0; i < maxNumMons; i++) {
            if (mons[i].isDead() && mons[i].getRow() != -1) {
                setUnit(mons[i].getRow(), mons[i].getCol(), null);
                mons[i].setRow(-1);
                mons[i].setCol(-1);
            }
            else {
                int randDir = RPG.r.nextInt(NUM_DIRS);
                mons[i].move(randDir);
            }
        }
    }

    public void moveBosses() {
        if (bosses == null) {
            return;
        }
        
        for (int i = 0; i < maxNumBosses; i++) {
            if (bosses[i].isDead() && bosses[i].getRow() != -1) {
                setUnit(bosses[i].getRow(), bosses[i].getCol(), null);
                bosses[i].setRow(-1);
                bosses[i].setCol(-1);
            }
            else {
                int randDir = RPG.r.nextInt(NUM_DIRS);
                bosses[i].move(randDir);
            }
        }
    }

    public boolean isBlocked(int row, int col) {
        if (isOutOfBoundary(row, col)) {
            return true;
        }
        else {
            return (getUnit(row, col) != null || getProp(row, col) != null);
        }
    }

    public boolean isBlocked(int row, int col, int dir) {
        int nextRow = row;
        int nextCol = col;
        
        if (dir == DIR_N) {
            nextRow--;
        }
        else if (dir == DIR_E) {
            nextCol++;
        }
        else if (dir == DIR_S) {
            nextRow++;
        }
        else if (dir == DIR_W) {
            nextCol--;
        }
        else {
            return false;
        }
        
        if (isOutOfBoundary(nextRow, nextCol)) {
            return true;
        }
        else {
            return (getUnit(nextRow, nextCol) != null || getProp(nextRow, nextCol) != null);
        }
    }
}
