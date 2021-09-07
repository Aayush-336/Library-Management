import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class rootfxml {
    @FXML
    Label page1;
    @FXML
    Label page2;
    @FXML
    Label page3;
    @FXML
    Label page4;
    @FXML
    Label page5;
    @FXML
    Label page6;
    @FXML
    Label page7;
    @FXML
    VBox vbox;
    @FXML
    AnchorPane anc;
    @FXML
    Pane changePane;
    @FXML
    ImageView pg1;
    @FXML
    ImageView pg6;
    Label[] labels = new Label[7];
    Scene currentScene;
    Stage currentStage;

    public void initialize() throws FileNotFoundException {
        Image img = new Image(new FileInputStream("src/add_book.png"));
        pg1.setImage(img);
        img = new Image(new FileInputStream("src/about_us.png"));
        pg6.setImage(img);
    }

    public void addVbox(VBox vbox) {
        anc.getChildren().add(vbox);
        this.vbox = vbox;
    }

    public void arrangeLabel() {
        ObservableList<Node> children = vbox.getChildren();
        for (int i = 0; i < labels.length; i++) {
            labels[i] = (Label) children.get(i);
        }
        for (Label label : labels) {
            label.setPrefWidth(vbox.getPrefWidth());
        }
    }

    public void setcurrentScene(Scene scene) {
        this.currentScene = scene;
    }

    public void setCurrentStage(Stage stage) {
        this.currentStage = stage;
    }

    public void removeStyleClass(Label name_label) {
        ObservableList<Node> children = vbox.getChildren();
        for (int i = 0; i < labels.length; i++) {
            labels[i] = (Label) children.get(i);
        }
        for (Label label : labels) {
            label.getStyleClass().removeAll("sel");
        }
        name_label.getStyleClass().add("sel");
    }

    public void setExit() {
        currentStage.setOnCloseRequest(event -> {
            try {
                page7();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void page1(MouseEvent event) throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("page1.fxml"));
        root.load();
        Page1 controller = root.getController();
        controller.setChangePane(changePane);
        controller.setParentScene(currentScene);
        controller.setParentStage(currentStage);
        controller.setMainPane();
        removeStyleClass(page1);
    }

    public void page2(MouseEvent event) throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("page2.fxml"));
        root.load();
        Page2 controller = root.getController();
        controller.setChangePane(changePane);
        controller.setParentScene(currentScene);
        controller.setParentStage(currentStage);
        controller.setMainPane();
        removeStyleClass(page2);
    }

    public void page3(MouseEvent event) throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("studentlogin.fxml"));
        root.load();
        studentloginController controller = root.getController();
        controller.setChangePane(changePane);
        controller.setParentScene(currentScene);
        controller.setParentStage(currentStage);
        controller.setMainPane();
        removeStyleClass(page3);
    }

    public void page4(MouseEvent event) throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("page2.fxml"));
//        root.load();
        setPages(root.load(), page4, root.getController());
    }

    public void page5(MouseEvent event) throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("page5.fxml"));
        root.load();
        Page5 controller = root.getController();
        controller.setChangePane(changePane);
        controller.setParentScene(currentScene);
        controller.setParentStage(currentStage);
        controller.setMainPane();
        removeStyleClass(page5);
    }

    public void page6(MouseEvent event) throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("aboutus.fxml"));
        root.load();
        aboutusController controller = root.getController();
        removeStyleClass(page6);
        controller.setChangePane(changePane);
        controller.setParentScene(currentScene);
        controller.setParentStage(currentStage);
        controller.setMainPane();
    }

    public void page7() throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("outro_page.fxml"));
        Scene scene = new Scene(root.load());
        OutroPage controller = root.getController();
        Stage stage = new Stage();
        stage.setScene(scene);
        controller.setCurrentStage(stage);
        controller.setParentStage(currentStage);
        stage.show();
        currentStage.close();
    }

    public void setPages(Parent root, Label page, rootfxml controller) {
        currentScene.setRoot(root);
        controller.setcurrentScene(currentScene);
        controller.addVbox(vbox);
        removeStyleClass(page);
        arrangeLabel();
//        page.getStyleClass().add("sel");
    }
}
