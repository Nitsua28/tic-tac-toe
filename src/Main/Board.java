package Main;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    // a 2D array to represent the pieces on the board:
    char [][] board;

    // should instantiate the board to be a 3x3 array:
    public Board() {
        this.board = new char[3][3];
    }

    // this method should take in a row, column, and a newChar and update the board accordingly:
    public void updateBoard(int row, int column, char newChar) {
        this.board[row][column] = newChar;
    }

    // this method should return a string representation of the board:
    @Override
    public String toString() {
        String str = "";
        int count = 97;
        str += "  1  2  3  \n";
        for (char[] elem: this.board){
            String line = "";
            for (char item: elem){
                line += " " + item + " ";
            }
            str += (char)count + "[ " + line + " ]" + "\n";
            count++;
        }
        return str;
    }
}
