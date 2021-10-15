package tictactoe.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class NameSelectController {

    @FXML
    private TextField player2Txt;

    @FXML
    private TextField player1Txt;

    @FXML
    private Button playConfirm;


    private Stage stage;
    private Scene scene;
    private Parent root;



    public void switchToPlay(ActionEvent event) throws IOException {
        String input = player1Txt.getText();
        String input2 = player2Txt.getText();
        FXMLLoader fxfloader =new FXMLLoader(getClass().getResource("views/TicTacView.fxml"));
        Parent root = (Parent) fxfloader.load();
        fxfloader.<TicTacViewController>getController().setInfo(input, input2);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /** private void setGetset(){
        String input = player1Txt.getText();
        GetterNSetter.setPlayerName(input);
        String input2 = player2Txt.getText();
        GetterNSetter.setPlayer2Name(input2);
    } **/
}
