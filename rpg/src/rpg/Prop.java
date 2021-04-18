package rpg;

public class Prop {
    protected char shape;
    
    public Prop() {
        shape = 'P';
    }

    public Prop(char shape) {
        this.shape = shape;
    }

    public String toString() {
        String s = "";
        s += shape;
        return s;
    }
}
