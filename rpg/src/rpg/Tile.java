package rpg;

public class Tile {
    public static final char SHAPE_EMPTY = '.';

    private Unit unit;
    private Item item;
    private Prop prop;
    
    private boolean blockedSpot;
    
    public Tile() {
        unit = null;
        item = null;
        prop = null;
        
        blockedSpot = false;
    }

    public String toString() {
        String s = "";
        
        if (unit != null) {
            s += unit.toString();
        }
        else if (item != null) {
            s += item.toString();
        }
        else if (prop != null) {
            s += prop.toString();
        }
        else {
            s += SHAPE_EMPTY;
        }
        
        return s;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Prop getProp() {
        return prop;
    }

    public void setProp(Prop prop) {
        this.prop = prop;
    }
}
