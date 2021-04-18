package rpg;

public class Monster extends Unit {
    
    public static final char SHAPE_MONSTER = 'm';
    public static final int MAX_NUM_MONS = 3;
    public static final int MONSTER_DELAY = 10;

    public static final int MONSTER_DEFAULT_MAX_HP = 30;

    public static final int MONSTER_DEFAULT_ATK = 10;

    public static final int MONSTER_DEFAULT_GOLD = 10;

    public static final int MONSTER_DEFAULT_EXP = 10;
    
    
    public Monster() { 
        super(SHAPE_MONSTER, 0, 0, MONSTER_DEFAULT_MAX_HP, MONSTER_DEFAULT_MAX_HP, Board.DIR_NONE, MONSTER_DEFAULT_ATK, MONSTER_DEFAULT_GOLD, MONSTER_DEFAULT_EXP);
    }

    public Monster(char shape, int row, int col, int hp, int maxHp, int dir, int atk, int gold, int exp) {
        super(shape, row, col, hp, maxHp, dir, atk, gold, exp);
    }

    public void move(int dir) {
        if (isDead() || isFrozen()) {
            return;
        }
        
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
    }

    public boolean isMonster() {
        return true;
    }

    // battle
    public void interact(Unit unit) {
//---------------------------------------------------------------------------------------------------
// Error Message Problem
//---------------------------------------------------------------------------------------------------
        if (unit == null) {
//            cout << "Monster::interact(): error: unit == null" << endl;
            System.out.println("Monster::interact(): error: unit == null");
            System.exit(1);
        }
        
        if (isDead()) {
            return;
        }
        
        decHp(unit.getAtk()); // hero hits mon

        if (!isDead()) {
            unit.decHp(atk);
        }
        else {
            unit.incGold(gold);
            unit.incExp(exp);
            decGold(gold);
            decGold(exp);
        }
    }
}
