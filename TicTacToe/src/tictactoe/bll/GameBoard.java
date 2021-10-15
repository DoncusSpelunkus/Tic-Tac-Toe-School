/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.bll;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * @author Stegger
 */
public class GameBoard implements IGameModel {
    int ticArray[][] = {
            {-1, -1, -1},
            {-1, -1, -1},
            {-1, -1, -1}
    };

    private String name;

    private String name2;

    int winner;

    int currentplayer = getNextPlayer();

    public int getNextPlayer() {
        return currentplayer;
    }

    public boolean play(int col, int row) {
        if(isGameOver()){
            return false;
        }
        if (ticArray[col][row] == -1) {
            ticArray[col][row] = currentplayer;
            return true;
        } else {
            return false;
        }
    }

    public void switchPlayer(){
        if (getNextPlayer() == 1) {
            currentplayer = 0;
        } else {
            currentplayer = 1;
        }
    }

    public boolean isGameOver() {
        if ((ticArray[0][0] == currentplayer && ticArray[0][1] == currentplayer && ticArray[0][2] == currentplayer) ||
                (ticArray[0][0] == currentplayer && ticArray[1][0] == currentplayer && ticArray[2][0] == currentplayer) ||
                (ticArray[0][0] == currentplayer && ticArray[1][1] == currentplayer && ticArray[2][2] == currentplayer) ||
                (ticArray[1][0] == currentplayer && ticArray[1][1] == currentplayer && ticArray[1][2] == currentplayer) ||
                (ticArray[2][0] == currentplayer && ticArray[2][1] == currentplayer && ticArray[2][2] == currentplayer) ||
                (ticArray[0][1] == currentplayer && ticArray[1][1] == currentplayer && ticArray[2][1] == currentplayer) ||
                (ticArray[0][2] == currentplayer && ticArray[1][2] == currentplayer && ticArray[2][2] == currentplayer) ||
                (ticArray[0][2] == currentplayer && ticArray[1][1] == currentplayer && ticArray[2][0] == currentplayer)) {
            winner = currentplayer;
            return true;
        }
        if (forLoopGameover()) {
            return true;
        }
        return false;
    }


    private boolean forLoopGameover() {
        for (int i = 0; i < ticArray.length; i++) {
            for (int j = 0; j < ticArray[i].length; j++) {
                if (ticArray[i][j] == -1) {
                    return false;
                }
            }
        }
        winner = -1;
        return true;
    }



    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner() {
        return winner;
    }

    public void newGame() {
        for (int i = 0; i < ticArray.length; i++) {
            for (int j = 0; j < ticArray[i].length; j++) {
                ticArray[i][j] = -1;
            }
        }
    }
}
