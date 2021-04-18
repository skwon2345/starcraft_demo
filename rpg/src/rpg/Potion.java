package rpg;

public class Potion extends Item {
    public static final char SHAPE_POTION = 'b';
    public static final int MAX_NUM_POTIONS = 20;
    public static final int POTION_DEFAULT_HP = 10;

    public static final String POTION_NAME = "Potion";
    public static final int POTION_PRICE = 5;
    public static final boolean POTION_DISPOSABLE = true;
    public static final boolean POTION_REMOVABLE = false;
    
    protected int hp;
    
    public Potion() {
        super(SHAPE_POTION, POTION_NAME, POTION_PRICE, POTION_DISPOSABLE, POTION_REMOVABLE);
        hp = POTION_DEFAULT_HP;
    }

    public Potion(int hp) {
        super(SHAPE_POTION, POTION_NAME, POTION_PRICE, POTION_DISPOSABLE, POTION_REMOVABLE);
        this.hp = hp;
    }
    
    public boolean use(Unit unit) {
        return unit.incHp(hp);
    }
}
