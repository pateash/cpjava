package live.ashish.cpjava.systemdesign.tictactoe;


import java.util.Scanner;

enum GameState{
    NOT_COMPLETED,
    WIN,
    DRAW;

}
class Player{
    private String name;
}

public class TicTacToe {
    private final char[][]state = new char[3][];
    private boolean playerTurn=true; //true, player1 (O) and false for player2(X)
    private GameState gameState=GameState.NOT_COMPLETED; // for not completed.
    private int turnCount=0;

    public char[][] getState() {
        return state;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public GameState getGameState() {
        return gameState;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public TicTacToe() {
        for(int i = 0;i<3;i++){
            state[i]=new char[]{'.', '.', '.'};
        }
    }

    public void print(){
        System.out.println(state.length);
        System.out.println(state[0].length);
        for(int i=0;i<state.length;i++){
            for (int j=0;j<state[i].length;j++){
                System.out.print(state[i][j]+" ");
            }
            System.out.println();
        }
    }

    public void turn(int i, int j){
        i--;j--;
        if(state[i][j] !='.') {
            System.out.println("invalid input, try again ");
            return;
        }
        if(playerTurn){ // we will put O
            state[i][j]='O';
        }
        else { // put X
            state[i][j]='X';
        }

        turnCount++;
        playerTurn = !playerTurn;
        checkState(i,j);
    }

    private void checkState(int row, int col) {
        // check if due to this i,j we are having the game completed or not.

        // case  - due to this move we got win
        // check for row and column
        boolean win = true;
        for( int k=1; k<3 ;k++){
            if(state[k][col] =='.' || state[k][col] != state[k-1][col]) {
                win = false;
                break;
            }
        }
        if(win) {
            gameState=GameState.WIN;
            return;
        }

        win=true;
        for( int k=1; k<3 ;k++){
            if(state[row][k] =='.' || state[row][k] != state[row][k-1]) {
                win = false;
                break;
            }
        }
        if(win) {
            gameState=GameState.WIN;
            return;
        }

        // if lies on any of the diagonal
        if(row == col || (row+col) == 2){ // have to check for diagonals also
            // found in both diagonal
            int prev=state[1][1];

            // main  diagonal
            win=true;
            for( int k=1; k<3 ;k++){
                if(state[k][k]=='.'  || state[k][k] != prev) {
                    win = false;
                    break;
                }
            }
            if(win) {
                gameState=GameState.WIN;
                return;
            }


            // secondary diagonal
            win=true;
            for( int k=0; k<3 ;k++){
                if(state[k][2-k]=='.' ||state[k][2-k] == prev) {
                    win = false;
                    break;
                }
            }
            if(win) {
                gameState=GameState.WIN;
                return;
            }

        }


        // case  - the game is completed ( all has been filled - DRAW as there is no win
        if(turnCount==9)
            gameState=GameState.DRAW;
    }


    public boolean isCompleted() {
        return gameState == GameState.WIN ||
                gameState == GameState.DRAW;
    }

    public String getPlayer(){
        return playerTurn ? "Player 1" : "Player 2";
    }
}


class Main{
    public static void main(String[] args) {
        TicTacToe game=new TicTacToe();
        System.out.println("play...");
        game.print();
        Scanner scanner =new Scanner(System.in);
        while(!game.isCompleted()){
            System.out.println(game.getPlayer() + ": Enter i, j coordinate of your next move (1 based)");
            int i =scanner.nextInt();
            int j =scanner.nextInt();
            game.turn(i,j);
            game.print();
        }

        System.out.println("Game : " + game.getGameState().toString() + " By"+ game.getState());

    }
}
