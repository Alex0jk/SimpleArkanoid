/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import GameLogic.Game;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author alexm
 */
public class Arkanoid extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Game game=new Game(500,500, 5);
        game.gameRun();
        Scene scene = new Scene(game.getRoot(), game.getScreenWidth(), game.getScreenHeight());
        
        primaryStage.setTitle("Arkanoid");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
