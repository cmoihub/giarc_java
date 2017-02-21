/**
 * One-player version of Tic Tac Toe, against terrible AI 
 * @author babak
 * @version 0.0
 */
import java.util.*;
public class TicTacToe extends BoardGame {
    public static int SIZE = 3;
    public TicTacToe(int size, MoveStrategy strategy) {
        super(size, strategy);
    }
    
    public TicTacToe(int size) {
        super(size);
    }
    
    public TicTacToe() {
        this(SIZE);
    }
    
    public TicTacToe(TicTacToe t) {
        super(t);
    }
    
    protected boolean canPlay(Move m) {
        int row = m.getRow(); 
        int col = m.getCol();
        if ((row < 0) || (row >= SIZE) || (col < 0) || (col >= SIZE)) return false;
        if (grid[row][col] != '_') return false;
        return true;
    }
    
    public GameStatus play(Move m) {
        if (!canPlay(m)) return GameStatus.ILLEGAL; 
        
        int row = m.getRow(); 
        int col = m.getCol();
        
        grid[row][col] = turn;
        if (checkRow(row) || checkCol(col) || checkFirstDiag() || checkSecondDiag()) {
            if (turn == 'x') 
            	return GameStatus.X_WON; 
            else 
            	return GameStatus.O_WON;
        }
        return GameStatus.ONGOING;
    }
        
    public void loop() {
        //int status = ONGOING;
        Scanner sc = new Scanner(System.in);
        Move move;
        
        System.out.println("Enter 'x' if you want to start");
        if (!(sc.nextLine().equals("x"))) {
            System.out.println("OK, I'll start then");
            gameStatus = machinePlay();
            toggleTurn();
        }
        
        while (gameStatus == GameStatus.ONGOING) {
           print();
           do {
               System.out.println("Enter your move: <row, col> or quit <q>");
               move = getMove(sc.nextLine());
               if (move == null) {
                   System.out.println("Bye bye!");
                   return;
               }
            }
           while (!(canPlay(move)));
           gameStatus = play(move);
           
           if (gameStatus == GameStatus.ONGOING) {
               print();
               System.out.println("Computer's turn: ");
               toggleTurn();
               gameStatus = machinePlay();
           }           
           toggleTurn();
        }
        
        determineWinner();
        displayStatus(gameStatus);
        System.out.println("Bye bye!");
           
    }
    
    private boolean checkRow(int i) {
        for (int j = 0; j < SIZE; j++) {
            if (grid[i][j] != turn) return false;
        }
        return true;
    }
    
    private boolean checkCol(int i) {
        for (int j = 0; j < SIZE; j++) {
            if (grid[j][i] != turn) return false;
        }
        return true;
    }
    
    private boolean checkFirstDiag() {
        for (int i = 0; i < SIZE; i++) {
            if (grid[i][i] != turn) return false;
        }
        return true;
    }
    
    private boolean checkSecondDiag() {
        for (int i = 0; i < SIZE; i++) {
            if (grid[i][SIZE - i - 1] != turn) return false;
        }
        return true;
    }
    
    protected void determineWinner() {
        if (gameStatus == GameStatus.NO_MOVE) gameStatus = GameStatus.TIE;
        //otherwise status is already clear, no need to do anything
    }
    
    public int evaluateMove(Move m) {
        TicTacToe next = new TicTacToe(this);
        GameStatus s = next.play(m);
        if ((turn == 'x') && (s == GameStatus.X_WON)) return 1;
        if ((turn == 'o') && (s == GameStatus.O_WON)) return 1;
        if ((turn == 'x') && (s == GameStatus.O_WON)) return -1;
        if ((turn == 'o') && (s == GameStatus.X_WON)) return -1;
        return 0;
    }
    
    protected int getMinScore() {return -1;}
    
    public static void main(String[] args) {
        (new TicTacToe(SIZE, new RandomMove())).loop();
    }
}