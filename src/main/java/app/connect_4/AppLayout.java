package app.connect_4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppLayout extends Application {
//    public static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppLayout.class.getResource("Connect_4.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 700);
        stage.setTitle("Connect_4");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
//        new GraphBoard().addGraphLevel();
    }

    public static void main(String[] args) {
        launch();
    }
}