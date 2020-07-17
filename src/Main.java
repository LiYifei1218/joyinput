import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        primaryStage.setTitle("Joyinput");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        com.sun.glass.ui.Window.getWindows().get(0).setLevel(3);
    }

    public static void main(String[] args) {
        launch(args);
    }
}