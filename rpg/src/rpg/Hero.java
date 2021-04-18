package rpg;

public class Hero extends Unit {
    public static final char SHAPE_HERO = 'H';
    public static final int HERO_START_ROW = (Board.ROW_SIZE/2);
    public static final int HERO_START_COL = (Board.COL_SIZE/2);

    public static final int HERO_DEFAULT_MAX_HP = 1000000;

    public static final int HERO_DEFAULT_ATK = 10;

    public static final int HERO_DEFAULT_GOLD = 0;

    public static final int HERO_DEFAULT_EXP = 0;
    
    public Hero() {
        super(SHAPE_HERO, Board.ROW_SIZE/2, Board.COL_SIZE/2, HERO_DEFAULT_MAX_HP, HERO_DEFAULT_MAX_HP, Board.DIR_NONE, HERO_DEFAULT_ATK, HERO_DEFAULT_GOLD, HERO_DEFAULT_EXP);
//---------------------------------------------------------------------------------------------------
// Slot
//---------------------------------------------------------------------------------------------------        
//        slot = new Inventory();
    }
    
//---------------------------------------------------------------------------------------------------
// Question
//---------------------------------------------------------------------------------------------------
//       public String printStat() {
//        Unit::printStat();
//        cout << endl;
//
//        cout << "Slot: ";
//        slot.print();
//        cout << endl;
//    }

    public void useItem(int index) {
//        slot.useItemAt(index, this);
    }

    public void move(int dir) {
        boolean moved = false;
        
        int prevRow = row;
        int prevCol = col;
        
        int nextRow = row;
        int nextCol = col;
        
        if (dir == Board.DIR_N) {
            nextRow--;
        }
        else if (dir == Board.DIR_E) {
            nextCol++;
        }
        else if (dir == Board.DIR_S) {
            nextRow++;
        }
        else if (dir == Board.DIR_W) {
            nextCol--;
        }
        
        if (nextRow >= 0 && nextRow < Board.ROW_SIZE &&
            nextCol >= 0 && nextCol < Board.COL_SIZE &&
            board.getUnit(nextRow, nextCol) == null &&
            board.getProp(nextRow, nextCol) == null) {
            board.setUnit(prevRow, prevCol, null);
            board.setUnit(nextRow, nextCol, this);
            moved = true;
        }
        
        if (moved) {
            for (int i = prevRow-1; i <= prevRow+1; i++) {
                for (int j = prevCol-1; j <= prevCol+1; j++) {
                    if (!(i == row && j == col) &&
                        !board.isOutOfBoundary(i, j) &&
                        board.getUnit(i, j) != null &&
                        board.getUnit(i, j).isMonster()) {
                        board.getUnit(i, j).unfreeze();
                    }
                }
            }

            for (int i = nextRow-1; i <= nextRow+1; i++) {
                for (int j = nextCol-1; j <= nextCol+1; j++) {
                    if (!(i == nextRow && j == nextCol) &&
                        !board.isOutOfBoundary(i, j) &&
                        board.getUnit(i, j) != null &&
                        board.getUnit(i, j).isMonster()) {
                        board.getUnit(i, j).freeze();
                    }
                }
            }
        }
//---------------------------------------------------------------------------------------------------
// Slot
//---------------------------------------------------------------------------------------------------
//        if (moved) {
//            if (board.getItem(nextRow, nextCol) != null) {
//                if (!slot.isFull()) {
//                    slot.add(board.getItem(nextRow, nextCol));
//                    board.setItem(nextRow, nextCol, null);
//                }
//            }
//        }
        
        if (!moved) {
            if (board.getUnit(nextRow, nextCol) != null) {
                board.getUnit(nextRow, nextCol).interact(this);
            }
        }
    }

    public boolean isHero() {
        return true;
    }

    public void interact(Unit unit) {
    }

}
