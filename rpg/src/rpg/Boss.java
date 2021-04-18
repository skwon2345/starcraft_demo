package rpg;

public class Boss extends Monster {
    class Pos {
        public int row;
        public int col;
        
        public Pos() {
            row = -1;
            col = -1;
        }

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    
    public static final char SHAPE_BOSS = '&';
    public static final int MAX_NUM_BOSSES = 1;
    public static final int BOSS_DELAY = 5;

    public static final int BOSS_DEFAULT_MAX_HP = 300;

    public static final int BOSS_DEFAULT_ATK = 30;

    //public static final BOSS_DEFAULT_RANGE 5
    public static final int BOSS_DEFAULT_RANGE = 20;

    public static final int BOSS_DEFAULT_GOLD = 20;

    public static final int BOSS_DEFAULT_EXP = 20;

    // IQ 120: Wall Follopwing Algorithm용.
    public static final int MAX_LEN_BOSS_PATH = 1000;
    
    public static final boolean BOSS_IQ_80 = false;
    public static final boolean BOSS_IQ_100 = false;
    public static final boolean BOSS_IQ_120 = false;
    
    protected int range;
    
    // IQ 120용: Wall Following Algorithm용
    protected boolean pathFound;
    protected int [][]path; // row와 col 좌표를 기록하기 위해 row size가 2개 필요하다.
    protected int pathLen;
    protected int curIndexPath;
    
    protected int findOpenDir(int dir) {
        if (BOSS_IQ_80) {
            return dir;
        }
        
        else { // BOSS_IQ_100 
            int []DIRS_VAL = new int[4];// [-1, 1, 1, -1];
            DIRS_VAL[0] = -1;
            DIRS_VAL[1] = 1;
            DIRS_VAL[2] = 1;
            DIRS_VAL[3] = -1;
            
            if (dir == Board.DIR_W || dir == Board.DIR_E) { // start direction is horz
                if (!board.isBlocked(row, col + DIRS_VAL[dir])) {
                    return dir;
                }
                else {
                    int vertDir = Board.DIR_NONE;
                    if ((vertDir = findOpenDirVert()) != Board.DIR_NONE) {
                        return vertDir;
                    }
                    else {
                        if (!board.isBlocked(row, col + DIRS_VAL[(dir+2)%4])) { // 처음 주어진 방향의 반대 방향: (dir+2)%4
                            return (dir+2)%4;
                        }
                        else {
                            return Board.DIR_NONE;
                        }
                    }
                }
            }
            else { // start direction is vert
                if (!board.isBlocked(row + DIRS_VAL[dir], col)) {
                    return dir;
                }
                else {
                    int horzDir = Board.DIR_NONE;
                    if ((horzDir = findOpenDirHorz()) != Board.DIR_NONE) {
                        return horzDir;
                    }
                    else {
                        if (!board.isBlocked(row + DIRS_VAL[(dir+2)%4], col)) { // 처음 주어진 방향의 반대 방향: (dir+2)%4
                            return (dir+2)%4;
                        }
                        else {
                            return Board.DIR_NONE;
                        }
                    }
                }
            }
        }
//    #endif
    }
    
    protected int findOpenDirVert() {
        int startDir = (RPG.r.nextInt(2) == 0)?-1:1;

        for (int i = 0; i < 2; i++) {
            if (!board.isBlocked(row + startDir, col)) {
                return (startDir == -1)?Board.DIR_N:Board.DIR_S;
            }
            startDir *= -1;
        }
        
        return Board.DIR_NONE;
    }
    
    protected int findOpenDirHorz() {
        int []DIR_ORDER = new int[2]; //{1, -1};
        DIR_ORDER[0] = 1;
        DIR_ORDER[1] = -1;
        
        int DIR_HORZ[] = new int [2]; //{DIR_E, DIR_W};
        DIR_HORZ[0] = Board.DIR_E;
        DIR_HORZ[1] = Board.DIR_W;
        
        int startIndex = RPG.r.nextInt(2);

        for (int i = 0; i < 2; i++)
            if (!board.isBlocked(row + DIR_ORDER[(i+startIndex) % 2], col))
                return DIR_HORZ[(i+startIndex) % 2];
        
        return Board.DIR_NONE;
    }
    
    //-----------------------------------------------------------------------------------------------
    // pass by reference problem
    //-----------------------------------------------------------------------------------------------
    protected void calcGoal(Pos pos, int dir) {
        if(dir == Board.DIR_N) {
            while(pos.row >= 0) {
                if(!board.isBlocked(--pos.row, pos.col, dir)) {
                    pos.row--;
                    break;
                }
            }
        }
        else if(dir == Board.DIR_E) {
            while(pos.col < Board.COL_SIZE) {
                if(!board.isBlocked(pos.row, ++pos.col, dir)) {
                    pos.col++;
                    break;
                }
            }
        }
        else if(dir == Board.DIR_S) {
            while(pos.row < Board.ROW_SIZE) {
                if(!board.isBlocked(++pos.row, pos.col, dir)) {
                    pos.row++;
                    break;
                }
            }
        }
        else if(dir == Board.DIR_W){
            while(pos.col >= 0) {
                if(!board.isBlocked(pos.row, --pos.col, dir)) {
                    pos.col--;
                    break;
                }
            }
        }
    }
    
//    protected void calcPath(int &index, Pos pos, int &dir) {
//        if(board.isBlocked(row, col, dir)) {
//            if(board.isBlocked(row, col, --dir)) {
//                //-----------------------------------------------------------------------------------
//                // error message problem
//                //-----------------------------------------------------------------------------------
//                if(board.isBlocked(row, col, --dir)) {
//                    System.out.println("Boss::move(int dir) error : boss is blocked in every directions");
////                    cout << "Boss::move(int dir) error : boss is blocked in every directions";
//                    exit(1);
//                }
//            }
//        }
//        if(dir == Board.DIR_N) {
//            path[0][index] = --row;
//            path[1][index] = col;
//            index++;
//        }
//        else if(dir == Board.DIR_E) {
//            path[0][index] = row;
//            path[1][index] = ++col;
//            index++;
//        }
//        else if(dir == Board.DIR_S) {
//            path[0][index] = ++row;
//            path[1][index] = col;
//            index++;
//        }
//        else if(dir == Board.DIR_W) {
//            path[0][index] = row;
//            path[1][index] = --col;
//            index++;
//        }
//    }    
    
    public Boss() {
        super(SHAPE_BOSS, 0, 0, BOSS_DEFAULT_MAX_HP, BOSS_DEFAULT_MAX_HP, Board.DIR_NONE, BOSS_DEFAULT_ATK, BOSS_DEFAULT_GOLD, BOSS_DEFAULT_EXP);
        range = BOSS_DEFAULT_RANGE;
        
        pathFound = false;
        pathLen = 0;
        curIndexPath = -1;
    }

    public boolean isBoss() {
        return true;
    }
    
    public void move(int dir) {
        // IQ 120/IQ 150등에서 찾은 path가 있다면 그것을 다 따라가기 전까지는 계속 따라간다.
        if (pathFound) {
            int nextRow = path[0][curIndexPath];
            int nextCol = path[1][curIndexPath];
            
            if (!board.isBlocked(nextRow, nextCol)) {
                board.setUnit(row, col, null);
                board.setUnit(nextRow, nextCol, this); // 이 코드에서 boss의 row, col도 바뀌므로 주의하자.
                curIndexPath++;
                if (curIndexPath == pathLen) {
                    pathFound = false;
                    pathLen = 0;
                    curIndexPath = -1;
                }
            }
            // 만약 막혔다면 한번 또는 계속 기다린다. monster등에 의해 path가 잠시 막힐 수 있다.
            
            return;
        }
        
        boolean heroFound = false;
        Hero hero = null;
        int heroRow = -1;
        int heroCol = -1;
        
        for (int i = row-range; i <= row+range && !heroFound; i++) {
            for (int j = col-range; j <= col+range && !heroFound; j++) {
                if (!(i == row && j == col) &&
                    !board.isOutOfBoundary(i, j) &&
                    board.getUnit(i, j) != null &&
                    board.getUnit(i, j).isHero()) {
                    heroFound = true;
                    hero = (Hero)board.getUnit(i, j);
                    heroRow = i;
                    heroCol = j;
                }
            }
        }
        
        if (heroFound) {
//            cout << "Boss::move(): heroFound!!" << endl;
    //------------------------------------------------------------------------------
    //------------------------------------------------------------------------------
    //------------------------------------------------------------------------------
    //#ifdef BOSS_IQ_80 || BOSS_IQ_100
    //------------------------------------------------------------------------------
    //------------------------------------------------------------------------------
    //------------------------------------------------------------------------------
            if (BOSS_IQ_80 || BOSS_IQ_100) {
                dir = Board.DIR_NONE;
    
                // horz
                if (heroRow == row) {
                    if (heroCol < col) { // left?
    //                    cout << "Boss::move(): if (heroRow == row) {" << endl;
                        if (heroCol+1 == col) { // adjacent?
                            hero.decHp(atk);
                            dir = Board.DIR_NONE;
                        }
                        else { // move toward hero
                            dir = findOpenDir(Board.DIR_W);
                        }
                    }
                    else { // right?
                        if (heroCol-1 == col) { // adjacent?
                            hero.decHp(atk);
                            dir = Board.DIR_NONE;
                        }
                        else { // move toward hero
                            dir = findOpenDir(Board.DIR_E);
                        }
                    }
                }
                else if (heroCol == col) { // vert
                    if (heroRow < row) { // up?
                        if (heroRow+1 == row) { // adjacent?
                            hero.decHp(atk);
                            dir = Board.DIR_NONE;
                        }
                        else { // move toward hero
                            dir = findOpenDir(Board.DIR_N);
                        }
                    }
                    else { // down?
                        if (heroRow-1 == row) { // adjacent?
                            hero.decHp(atk);
                            dir = Board.DIR_NONE;
                        }
                        else { // move toward hero
                            dir = findOpenDir(Board.DIR_S);
                        }
                    }
                }
                else {
                    if (heroRow < row && heroCol < col) { // NW
                        //---------------------------------------------------------------------------
                        // abs problem
                        //---------------------------------------------------------------------------
                        int diffRow = Math.abs(heroRow - row);
                        int diffCol = Math.abs(heroCol - col);
                        
                        if (diffRow > diffCol) { // to north
                            dir = findOpenDir(Board.DIR_N);
                        }
                        else {
                            dir = findOpenDir(Board.DIR_W);
                        }
                    }
                    else if (heroRow < row && heroCol > col) { // NE
                        int diffRow = Math.abs(heroRow - row);
                        int diffCol = Math.abs(heroCol - col);
                        
                        if(diffRow > diffCol) {
                            dir = findOpenDir(Board.DIR_N);
                        }
                        else {
                            dir = findOpenDir(Board.DIR_E);
                        }
                    }
                    else if (heroRow > row && heroCol > col) { // SE
                        int diffRow = Math.abs(heroRow - row);
                        int diffCol = Math.abs(heroCol - col);
                        
                        if(diffRow > diffCol) {
                            dir = findOpenDir(Board.DIR_S);
                        }
                        else {
                            dir = findOpenDir(Board.DIR_E);
                        }
                    }
                    else { //if (heroRow > row && heroCol < col) { // SW
                        int diffRow = Math.abs(heroRow - row);
                        int diffCol = Math.abs(heroCol - col);
                        
                        if(diffRow > diffCol) {
                            dir = findOpenDir(Board.DIR_S);
                        }
                        else {
                            dir = findOpenDir(Board.DIR_W);
                        }
                    }
                }
            
            
            
                //----------------------------------------------------------------------
                //----------------------------------------------------------------------
                //----------------------------------------------------------------------
                //#ifdef BOSS_IQ_120
                //----------------------------------------------------------------------
                //----------------------------------------------------------------------
                //----------------------------------------------------------------------
                
                if (BOSS_IQ_120) {
                    // IQ 120을 trigger하는 if문.
                    // Hero와 Boss가 딱 붙어 있을 경우.. 때리고, dir은 DIR_NONE이므로, IQ 120은 필요없다.
                    // 하지만, dir이 DIR_NONE이 아니라면, 주어진 방향으로 hero에게 접근을 할려고 하는 것이다.
                    // 이때, 만약 그 방향의 tile을 check하여 막혔다면, IQ 120인 Wall Following Algorithm을 돌리자.
                    if (dir != Board.DIR_NONE && board.isBlocked(row, col, dir)) {
                        // 일단 모든 path관련 member varaible들을 reset하자.
                        
                        pathFound = false;
                        pathLen = 0;
                        curIndexPath = -1;
        
                        // Wall Following 앨거리듬:
                        // 준비과정:
                        //     - 현재 진행하려는 방향이 막혔으므로, 그 막힌 지점부터 계속 전진하여 막히지 않은 곳이 나오면 goalRow, goalCol로 지정한다.
                        // 실제 앨거리듬:
                        // 1. 현재 방향으로 전진하려 해본다.
                        //      1-a. 막혔다면, 왼쪽으로 돈다. 그리고 다시 1로 간다.
                        //      1-b. 안 막혔다면, 전진한 후,(현재 지점을(curRow와 curCol을) path[0][curIndex]와 path[1][curIndex]에 저장한다.)
                        //           1-b-1. 현재 지점이 goalRow, goalCol이면 멈추고, path를 따라갈 수 있도록 member variable을 잘 셋해준다.
                        //           1-b-1. 오른쪽으로 돌고 1로 간다.
                        int curIndex = 0;
                        int curDir = dir;
                        int curRow = row;
                        int curCol = col;
//                        int goalRow = row;
//                        int goalCol = col;
                        
                        int DIR[][] = new int[2][4];
    //                    {
    //                        {-1, 0, 1, 0},
    //                        { 0, 1, 0, -1}
    //                    };
                        DIR[0][0] = -1;
                        DIR[0][1] = 0;
                        DIR[0][2] = 1;
                        DIR[0][3] = 0;
                        DIR[1][0] = 0;
                        DIR[1][1] = 1;
                        DIR[1][2] = 0;
                        DIR[1][3] = -1;
                        
                        Pos goalPos = new Pos(row, col);
                        calcGoal(goalPos, curDir);
        
                        while (true) {
                            //--------------------------------------------------------------
                            // 1. 현재 방향으로 전진하려 해본다.
                            //--------------------------------------------------------------
                            if (board.isBlocked(curRow, curCol, curDir)) {
                                //      1-a. 막혔다면, 왼쪽으로 돈다. 그리고 다시 1로 간다.
                                curDir = (curDir - 1 + Board.NUM_DIRS) % Board.NUM_DIRS;
                            }
                            else {
                                //      1-b. 안 막혔다면, 전진한 후,(현재 지점을(curRow와 curCol을) path[0][curIndex]와 path[1][curIndex]에 저장한다.)
                                curRow += DIR[0][curDir];
                                curCol += DIR[1][curDir];
                                
                                path[0][curIndex] = curRow;
                                path[1][curIndex] = curCol;
                                curIndex++;
                                
                                //           1-b-1. 현재 지점이 goalRow, goalCol이면 멈추고, path를 따라갈 수 있도록 member variable을 잘 셋해준다.
                                if (curRow == goalPos.row && curCol == goalPos.col) {
                                    curIndexPath = 0;
                                    pathLen = curIndex;
                                    pathFound = true;
                                    break;
                                }
                                //           1-b-1. 오른쪽으로 돌고 1로 간다.
                                else {
                                    curDir = (curDir + 1) % Board.NUM_DIRS;
                                }
                            }
                        }
                    }
                //----------------------------------------------------------------------
    //            #endif // end of BOSS_IQ_120
                //----------------------------------------------------------------------
                
               
                }
    //------------------------------------------------------------------------------
//    #endif // end of BOSS_IQ_80 or BOSS_IQ_100
    //------------------------------------------------------------------------------
            }
        }
        
        super.move(dir);
    }
        
    // battle
    public void interact(Unit unit) {
        super.interact(unit);
    }
}
