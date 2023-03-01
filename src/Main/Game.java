package Main;

import java.util.Scanner;

enum Status {
    // there is a row, column or diagonal consisting of only player1's game pieces:
    PLAYER1_WON,
    // there is a row, column or diagonal consisting of only player2's game pieces:
    PLAYER2_WON,
    // there is no row, column, or diagonal consisting of a single player's pieces:
    TIE,
    // there are spots on the board which are uninitialized;
    UNFINISHED
}

public class Game {
    Player player1;
    Player player2;
    Board board;

    // this function should prompt the players for names and gamePiece and assign it
    // to the player objects:
    private void initPlayers() {
        Scanner scanner = new Scanner(System.in);

        this.board = new Board();

        System.out.println("Player 1 input your name!");
        String name1 = scanner.nextLine();
        System.out.println("Player 1 input your chess piece character!");
        char piece1 = scanner.next().charAt(0);
        scanner.nextLine();

        this.player1 = new Player(name1, piece1);

        System.out.println("Player 2 input your name!");
        String name2 = scanner.nextLine();
        System.out.println("Player 2 input your chess piece character!");
        char piece2 = scanner.next().charAt(0);
        scanner.nextLine();

        this.player2 = new Player(name2, piece2);
        return;
    }

    // this method should let each player pick a spot and also print the board between selections:
    private void round() {
        Scanner scanner = new Scanner(System.in);
        while (true){
            String[] strRep = this.board.toString().split("\n");
            for (String elem: strRep){
                System.out.println(elem);
            }
                
            System.out.println("Player 1 choose a-c for row and 1-3 for column. Enter row then column.");
            
            int row = scanner.next().charAt(0);
            row = row - 97;
            scanner.nextLine();
            int column = scanner.nextInt();
            column = column - 1;

            board.updateBoard(row, column, player1.getGamePiece());
            if (this.checkBoard() != Status.UNFINISHED)
                {
                    System.out.println(this.checkBoard());
                    String[] finished = this.board.toString().split("\n");
                    for (String elem: finished){
                        System.out.println(elem);
                    }
                    scanner.close();
                    return;
                }

            System.out.println(this.board.toString()); 
            System.out.println("Player 2 choose a-c for row and 1-3 for column. Enter row then column.");
            
            row = scanner.next().charAt(0);
            row = row - 97;
            scanner.nextLine();
            column = scanner.nextInt();
            column = column - 1;

            board.updateBoard(row, column, player2.getGamePiece());
            if (this.checkBoard() != Status.UNFINISHED)
                {
                    System.out.println(this.checkBoard());
                    String[] finished = this.board.toString().split("\n");
                    for (String elem: finished){
                        System.out.println(elem);
                    }
                    scanner.close();
                    return;
                }
        }
        

    }

    // This should return a status of the board, using the enum Status:
    private Status getStatus(String status) {
        switch (status){
            case "p1win":
                return Status.PLAYER1_WON;
            case "p2win":
                return Status.PLAYER2_WON;
            case "tie":
                return Status.TIE;
            default:
                return Status.UNFINISHED;
        }

    }
    private boolean isBoardFull(){
        char[][] board = this.board.board;
        for (int i = 0; i < 3; i++){
            for (int k = 0; k < 3; k ++){
                if (board[i][k] == 0){
                    return false;
                }
            }
            
        }
        return true;
    }
    private Status checkBoard(){
        String player1Line = String.valueOf(player1.getGamePiece()).toString().repeat(3); 
        String player2Line = String.valueOf(player1.getGamePiece()).toString().repeat(3);
        char board[][] = this.board.board;
        for (int i = 0; i < 8; i++){
            String line = null;
            
            switch (i){
                case 0:
                    line = new StringBuilder()
                    .append(board[0][0])
                    .append(board[0][1])
                    .append(board[0][2]).toString();
                    break;
                case 1:
                    line = new StringBuilder()
                    .append(board[1][0])
                    .append(board[1][1])
                    .append(board[1][2]).toString();
                    break;
                case 2:
                    line = new StringBuilder()
                    .append(board[2][0])
                    .append(board[2][1])
                    .append(board[2][2]).toString();
                    break;
                case 3:
                    line = new StringBuilder()
                    .append(board[0][0])
                    .append(board[1][0])
                    .append(board[2][0]).toString();
                    break;
                case 4:
                    line = new StringBuilder()
                    .append(board[0][1])
                    .append(board[1][1])
                    .append(board[2][1]).toString();
                    break;
                case 5:
                    line = new StringBuilder()
                    .append(board[0][2])
                    .append(board[1][2])
                    .append(board[2][2]).toString();
                    break;
                case 6:
                    line = new StringBuilder()
                    .append(board[0][0])
                    .append(board[1][1])
                    .append(board[2][2]).toString();
                    break;
                case 7:
                    line = new StringBuilder()
                    .append(board[2][0])
                    .append(board[1][1])
                    .append(board[0][2]).toString();
                    break;
                
                
            }
            if (line.equals(player1Line)){
                return this.getStatus("p1win");
            }
            else if (line.equals(player2Line)){
                return this.getStatus("p2win");
            }
        }
        if (isBoardFull()){
            return this.getStatus("tie");
        }
        
        return this.getStatus("playon");
    }

    // 1. Set up, ask players for names and game piece
    // 2. For each round, let each player pick a spot
    // 3. Print the board after each move:
    // 4. When the game is over, announce the winner or that it is a tie
    public static void main(String[] args) {
        Game game = new Game();

        game.initPlayers();
        
        game.round();
    }


}
