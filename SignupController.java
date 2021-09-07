import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

public class SignupController {
    Scene currentScene;
    Stage currentstage;
    @FXML
    private Button closebutton;
    @FXML
    private Label registrationmessegelabel;
    @FXML
    private PasswordField setpasswordfield;
    @FXML
    private PasswordField confirmpassword;
    @FXML
    private Label pswmatching;
    @FXML
    private Button registerbutton;
    @FXML
    private TextField idtextfield;
    @FXML
    private TextField firstnametextfield;
    @FXML
    private TextField middlenametextfield;
    @FXML
    private TextField lastnametextfield;
    @FXML
    private TextField usernametextfield;

    @FXML
    public void initialize() {
        // Check database if generated random number is not allocated as primary key
        Random r = new Random();
        String s = "";
        for (int i = 0; i < 6; i++) {
            s += Integer.toString(r.nextInt(10));
        }
        idtextfield.setText(s);
        idtextfield.setDisable(true);
    }

    public void setCurrentStage(Stage stage) {
        currentstage = stage;
    }

    public void setCurrentScene(Scene scene) {
        currentScene = scene;
    }

    public void closebuttonOnAction(ActionEvent event) {
        Stage stage = (Stage) closebutton.getScene().getWindow();
        stage.close();
    }

    public void registerbuttonOnAction(ActionEvent event) throws SQLException {
        if (!setpasswordfield.getText().equals(confirmpassword.getText())) {
            pswmatching.setText("Password doesn't match.");
            return;
        }
        registeruser();
    }

    public void registeruser() {
        conn connection = new conn();
        String id = idtextfield.getText();
        String firstname = firstnametextfield.getText();
        String middlename = middlenametextfield.getText();
        String lastname = lastnametextfield.getText();
        String username = usernametextfield.getText();
        String password = setpasswordfield.getText();
        String query = "insert into user_pass (user,pass) values(\"" + username + "\",\"" + password + "\");";
        try {
            connection.stmt.execute(query);
        } catch (SQLException e) {
            registrationmessegelabel.setText("This Username is already taken");
            return;
        }
//        String insertFields = ""; // insert query
//        String insertValues = id + "','"+ firstname + "','" + middlename + "','" + lastname + "','" + username + "','" + password;
//        String insertToRegister = insertFields + insertValues;
        registrationmessegelabel.setText("User have been registered Successfully.");

    }

    public void back() throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("login.fxml"));
        currentScene.setRoot(root.load());
        logincontroller controller = root.getController();
        controller.setCurrentStage(currentstage);
        controller.setCurrentScene(currentScene);
        currentstage.sizeToScene();
    }

}
