package starcraft;

public class Pos {
    public double x;
    public double y;
    public double xOnMap;
    public double yOnMap;
    public boolean inChunk;
//    public Pos parentPos;
    public boolean n;
    public boolean e;
    public boolean s;
    public boolean w;
    
    public boolean vertex;
//    public double prevMapX;
//    public double prevMapY;
    
    public Pos() {
        x = -1.0;
        y = -1.0;
        xOnMap = -1.0;
        yOnMap = -1.0;
        inChunk = false;
//        parentPos = null;
        n = false;
        e = false;
        s = false;
        w = false;
        
        vertex = false;
    }
    
    public Pos(double xOnMap, double yOnMap) {
        this.xOnMap = xOnMap;
        this.yOnMap = yOnMap;
        this.x = this.xOnMap + Starcraft.START_MAP01_X;
        this.y = this.yOnMap + Starcraft.START_MAP01_Y;
        inChunk = false;
//        parentPos = null;
        n = false;
        e = false;
        s = false;
        w = false;
        
        vertex = false;
    }
    
    public boolean isEqual (double xOnMap, double yOnMap) {
        return (this.xOnMap == xOnMap) && (this.yOnMap == yOnMap);
    }
}
