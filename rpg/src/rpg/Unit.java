package rpg;

public abstract class Unit {
    protected char shape;
    
    protected int row;
    protected int col;
    
    protected int dir;
    
    protected int hp;
    protected int maxHp;
    
//    int mp;
//    int maxMp;
    
    protected int atk;
//    int def;

    protected int gold;
    protected int exp;

//    Item *pocket;
    
    protected Board board;
    
    protected boolean died;
    protected boolean frozen;
    
    public Unit() {
        shape = 'U';
    }

    public Unit(char shape, int row, int col, int hp, int maxHp, int dir, int atk, int gold, int exp) {
        this.shape = shape;
        
        this.row = row;
        this.col = col;
        
        this.dir = dir;

        this.hp = hp;
        this.maxHp = maxHp;
        
        this.atk = atk;
        
        this.gold = gold;
        this.exp = exp;

//        pocket = null;
        
        board = null;
        
        died = false;
        frozen = false;
    }

    public String toString() {
        return "" + shape;
    }

    public String toStringStat() {
        String s = "";
        s += "[" + shape + "]: HP(" + hp + "/" + maxHp + ") ATK(" + atk + ")";
//        cout << "[" << shape << "]: HP(" << hp << "/" << maxHp << ") ATK(" << atk << ")";
        
//        cout << " Pocket: ";
//        if (pocket == null) {
//            cout << " ";
//        }
//        else {
//            pocket->print();
//        }
        s += " Gold: " + gold;
        s +=  " Exp: " + exp;
//        cout << " Gold: " << gold;
//        cout << " Exp: " << exp;
        
        return s;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public boolean incHp(int hpInc) {
        if (hp == maxHp) {
            return false;
        }
        
        if (hp + hpInc >= maxHp) {
            hp = maxHp;
        }
        else {
            hp += hpInc;
        }
        
        return true;
    }

    public void decHp(int hpDec) {
        if (hp - hpDec <= 0) {
            hp = 0;
            died = true;
        }
        else {
            hp -= hpDec;
        }
    }

    // 이 rpg에서는 오직 weapon과 armor로만 atk와 def를 더하고 빼므로, 0 아래로 떨어질 일은 없다. 또한 max는 inf이다.

    public int getAtk() {
        return atk;
    }

    public void incAtk(int atkInc) {
        atk += atkInc;
    }

    public void decAtk(int atkDec) {
        atk -= atkDec;
    }

    public void incGold(int gold) {
        this.gold += gold;
    }

    public void decGold(int gold) {
        this.gold -= gold;
    }

    public void incExp(int exp) {
        this.exp += exp;
    }

    public void decExp(int exp) {
        this.exp -= exp;
    }

    public boolean isDead() {
        return died;
    }

    public boolean isHero() {
        return false;
    }

    public boolean isMonster() {
        return false;
    }

    public boolean isBoss() {
        return false;
    }

    public void freeze() {
        frozen = true;
    }

    public void unfreeze() {
        frozen = false;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void useItem(int index) { // fake body
    }
    
    public abstract void move(int dir); // pure virtual function
    public abstract void interact(Unit unit); // pure virtual function
    
}
