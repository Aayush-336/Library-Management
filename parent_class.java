import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class parent_class extends Application {
    public void start(Stage stage) throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root.load());
        logincontroller controller = root.getController();
//        basicLogin controller = root.getController();
//        controller.setCurrentStage(stage);
        controller.setCurrentStage(stage);
        controller.setCurrentScene(scene);
        stage.setScene(scene);
        stage.show();
    }
}
