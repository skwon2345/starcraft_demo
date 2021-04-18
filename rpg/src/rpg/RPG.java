package rpg;

import java.util.Random;

// main class: contains main method
//      
public class RPG {
    public static final boolean BOSS_IQ_120_TEST_CASE = false;
    
    public static Random r = new Random();
    
    public static void main(String[] args) {
//        Tree t1 = new Tree();
//        System.out.println("t1: " + t1.toString()); // S.O.P()
        
        int rowSize, colSize, maxNumMons, maxNumBosses;
        
        // 이렇게 유저 인풋을 받아서 board를 customize할 수 있다.
        rowSize = Board.ROW_SIZE;
        colSize = Board.COL_SIZE;
        maxNumMons = Monster.MAX_NUM_MONS;
        maxNumBosses = Boss.MAX_NUM_BOSSES;
        
//        Board *board = new Board();
        Board board = new Board(rowSize, colSize, maxNumMons, maxNumBosses);

        Hero hero = board.getHero();
        
        int numTicks = 0;
        
        // Main Game Loop
        while (true) { // infinite
            System.out.println(board);

//            cout << "numTicks: " << numTicks << endl;
            System.out.print(hero.toStringStat());
            System.out.println();
            
            if (hero.isDead()) {
                System.out.println("Game Over!");
                break;
            }
            
            int heroDir = -1;
            
//            if (_kbhit()) {
            boolean _kbhit = false;
            if (_kbhit) {
                char command = '\0';
//    #ifdef WIN32
//                char command = _getch();
//    #else // MACOS
//                char command = getchar();
//    #endif
                if (command == Board.DIR_N_CHAR || command == Character.toUpperCase(Board.DIR_N_CHAR)) {
                    heroDir = Board.DIR_N;
                }
                else if (command == Board.DIR_E_CHAR || command == Character.toUpperCase(Board.DIR_E_CHAR)) {
                    heroDir = Board.DIR_E;
                }
                else if (command == Board.DIR_S_CHAR || command == Character.toUpperCase(Board.DIR_S_CHAR)) {
                    heroDir = Board.DIR_S;
                }
                else if (command == Board.DIR_W_CHAR || command == Character.toUpperCase(Board.DIR_W_CHAR)) {
                    heroDir = Board.DIR_W;
                }
                else {
                    heroDir = Board.DIR_NONE;
                }
                
                hero.move(heroDir);
                
                //------------------------------------------------------------------
                // USER_COMMAND_USE_ITEM handler
                //------------------------------------------------------------------
//                if (command == USER_COMMAND_USE_ITEM || command == toUpperCase(USER_COMMAND_USE_ITEM)) {
//                    hero.useItem();
//                }
                if (command >= '1' && command <= '5') {
                    hero.useItem(command-'1'); // '1'-'5'를 0-4라는 inventory의 index로 바꿔서 call한다.
                }
                
            }
            
            if (numTicks % Monster.MONSTER_DELAY == 0) {
                board.moveMons();
            }

            if (numTicks % Boss.BOSS_DELAY == 0) {
                board.moveBosses();
            }

            numTicks++;

            try { Thread.sleep(100); } catch(InterruptedException ex) { Thread.currentThread().interrupt(); }
        }
    }
}
