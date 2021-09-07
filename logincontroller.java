import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class logincontroller {
    private final ArrayList<String> usernames = new ArrayList<>();
    private final ArrayList<String> passwords = new ArrayList<>();
    @FXML
    Button cancelbutton;
    @FXML
    Label messegelabel;
    @FXML
    TextField usernametextfield;
    @FXML
    PasswordField enterpasswordfield;
    @FXML
    Button signupbutton;
    Stage currentstage;
    Scene currentScene;
    ResultSet queryResult;

    public void initialize() throws SQLException {
        conn connectNow = new conn();
        queryResult = connectNow.execute("select * from user_pass");
        int i = 0;
        while (queryResult.next()) {
            usernames.add(queryResult.getString(1));
            passwords.add(queryResult.getString(2));

        }
    }

    public void setCurrentStage(Stage stage) {
        currentstage = stage;
    }

    public void setCurrentScene(Scene scene) {
        currentScene = scene;
    }

    public void signinbuttonOnAction() throws SQLException, IOException {

        if (usernametextfield.getText().isEmpty() && enterpasswordfield.getText().isEmpty()) {
            messegelabel.setText("Please Enter Username and Password");
        } else {
            validatesignin();
        }
    }


    public void cancelbuttonOnAction() {
        Stage stage = (Stage) cancelbutton.getScene().getWindow();
        stage.close();
    }

    public void validatesignin() throws IOException {
        boolean flag = false;
        String username = usernametextfield.getText();
        String password = enterpasswordfield.getText();
        if (usernames.contains(username) & passwords.contains(password)) {
            messegelabel.setText("Congratulations...Login Successful.");
            flag = true;
        }
        if (flag)
            openLibrary();
        else
            messegelabel.setText("Username or Password Invalid");
    }

    public void openLibrary() throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("rootfxml.fxml"));
        currentScene.setRoot(root.load());
        rootfxml controller = root.getController();
        controller.setcurrentScene(currentScene);
        controller.setCurrentStage(currentstage);
        controller.arrangeLabel();
        controller.setExit();
        currentstage.sizeToScene();
    }

    public void signuppageopen() throws IOException {

        FXMLLoader root = new FXMLLoader(getClass().getResource("sign_up.fxml"));
        currentScene.setRoot(root.load());
        SignupController controller = root.getController();
        controller.setCurrentStage(currentstage);
        controller.setCurrentScene(currentScene);
        currentstage.sizeToScene();
    }
}
