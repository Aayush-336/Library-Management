import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class getAnchorPane {
    @FXML
    private AnchorPane mainPane;
    private Scene parentScene;
    private Stage parentStage;
    private Pane changePane;

    public Pane getChangePane() {
        return changePane;
    }

    public void setChangePane(Pane changePane) {
        this.changePane = changePane;
    }

    public AnchorPane getMainPane() {
        return mainPane;
    }

    public void setMainPane() {
        changePane.getChildren().clear();
        changePane.getChildren().add(getMainPane());
        parentStage.sizeToScene();
    }

    public Scene getParentScene() {
        return parentScene;
    }

    public void setParentScene(Scene scene) {
        parentScene = scene;
    }

    public Stage getParentStage() {
        return parentStage;
    }

    public void setParentStage(Stage stage) {
        parentStage = stage;
    }
}
