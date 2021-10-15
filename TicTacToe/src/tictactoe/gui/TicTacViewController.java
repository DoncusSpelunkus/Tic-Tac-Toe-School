/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tictactoe.bll.AIScript;
import tictactoe.bll.GameBoard;
import tictactoe.bll.IGameModel;

/**
 * @author Stegger
 */
public class TicTacViewController implements Initializable {

    @FXML
    private Label lblPlayer;

    @FXML
    private ComboBox combxModeSelect;

    @FXML
    private GridPane gridPane;

    private String name;
    private String name2;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private static final String TXT_PLAYER = "Player: ";
    private IGameModel game;
    private AIScript ai = new AIScript();

    public void switchtoNameSelect(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("views/NameSelect.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        int selectedIndex = combxModeSelect.getSelectionModel().getSelectedIndex();
        try {
            Integer row = GridPane.getRowIndex((Node) event.getSource());
            Integer col = GridPane.getColumnIndex((Node) event.getSource());
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;
            int player = game.getNextPlayer();
            if (game.play(c, r)) {
                Button btn = (Button) event.getSource();
                String xOrO = player == 0 ? "X" : "O";
                btn.setText(xOrO);
                if (game.isGameOver()) {
                    int winner = game.getWinner();
                    displayWinner(winner);
                } else {
                    game.switchPlayer();
                    setPlayer();
                }
            }
            switch (selectedIndex) {
                case 0:
                    randomMove(c, r);
                    break;
                case 1:
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @FXML
    private void handleNewGame(ActionEvent event) {
        game.newGame();
        setPlayer();
        clearBoard();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        game = new GameBoard();
        setPlayer();
        combxModeSelect.setItems(FXCollections.observableArrayList("Single player", "Multiplayer"));
        combxModeSelect.setVisibleRowCount(2);

    }

    private void setPlayer(){
            int selectedIndex = combxModeSelect.getSelectionModel().getSelectedIndex();
            switch (selectedIndex) {
                case 0:
                    if (game.getNextPlayer() == 0) {
                        lblPlayer.setText(TXT_PLAYER + name);
                    }
                    else{
                        lblPlayer.setText(TXT_PLAYER + " AI");
                    }
                    break;
                case 1:
                    if (game.getNextPlayer() == 0){
                        lblPlayer.setText(TXT_PLAYER + name);
                    }
                    else {
                        lblPlayer.setText(TXT_PLAYER + name2);
                    }
                    break;
            }
        }

    private void displayWinner(int winner) {
        int selectedIndex = combxModeSelect.getSelectionModel().getSelectedIndex();
        String message = "";
        switch (winner) {
            case -1:
                message = "It's a draw :-(";
                break;
            default:
                switch (selectedIndex) {
                    case 0:
                        if(winner == 1){
                            message = "Player " + " AI" + " wins!!!";
                        }
                        else{
                            message = "Player " + name + " wins!!!";
                        }
                    break;
                    case 1:
                        if(winner == 0) {
                            message = "Player " + name + " wins!!!";
                        }
                        else {
                            message = "Player " + name2 + " wins!!!";
                        }
                }

        }
        lblPlayer.setText(message);
    }

    private void clearBoard() {
        for (Node n : gridPane.getChildren()) {
            Button btn = (Button) n;
            btn.setText("");
        }
    }

    private void randomMove(int c, int r) {
        int player = game.getNextPlayer();
        while (player == 1 && !game.isGameOver()) {
            c = ai.getRandomMove();
            r = ai.getRandomMove();
            if (game.play(c, r)) {
                Button btn = (Button) gridPane.getChildren().get(r * 3 + c);
                String xOrO = player == 0 ? "X" : "O";
                btn.setText(xOrO);
                if (game.isGameOver()) {
                    int winner = game.getWinner();
                    displayWinner(winner);
                } else {
                    game.switchPlayer();
                    player = game.getNextPlayer();
                    setPlayer();
                }
            }
        }
    }

    public void setInfo(String inputname, String anotherName) {
        name = inputname;
        name2 = anotherName;
    }
}
