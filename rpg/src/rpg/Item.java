package rpg;

public abstract class Item {
    protected char shape;
    
    protected String name;
    protected int price;
    
    protected boolean disposable;
    protected boolean removable;
    
    public Item() {
        shape = 'I';
    }

    public Item(char shape, String name, int price, boolean disposable, boolean removable) {
        this.shape = shape;
        this.name = name;
        this.price = price;
        this.disposable = disposable;
        this.removable = removable;
    }

    public String toString() {
        return "" + shape;
    }

    public boolean isDisposable() {
        return disposable;
    }

    public boolean isRemovable() {
        return removable;
    }
    
    public abstract boolean use(Unit unit); // pure virtual function
}
