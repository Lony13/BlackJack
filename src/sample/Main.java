package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Created by Lony13 on 2016-12-27.
 */
public class Main extends Application {
    private Pane layout;

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("sample.fxml"));
        try{
            layout = loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }

        Scene scene = new Scene(layout);
        stage.setTitle("Blackjack");
        stage.setScene(scene);
        stage.show();

        Controller controller = loader.getController();

        new Game(controller);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
