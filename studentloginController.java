import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class studentloginController extends getAnchorPane {
    @FXML
    private Button cancelbutton;
    @FXML
    private Button registerbutton;
    @FXML
    private PasswordField setpasswordfield;
    @FXML
    private Label registrationmessegelabel;
    @FXML
    private PasswordField confirmpassword;
    @FXML
    private Label pswmatching;
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
    private TextField branchtextfield;
    @FXML
    private TextField semtextfield;


    public void cancelbuttonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelbutton.getScene().getWindow();
        stage.close();
    }

    public void registerbuttonOnAction(ActionEvent event) throws SQLException {
        if (setpasswordfield.getText().equals(confirmpassword.getText())) {
            registerstudent();
            pswmatching.setText("Password Matched.");

        } else {
            pswmatching.setText("Password does not Matched.");
        }
    }

    public void registerstudent() throws SQLException {
        conn connectNow = new conn();
        String enn = idtextfield.getText();
        String firstname = firstnametextfield.getText();
        String middlename = middlenametextfield.getText();
        String lastname = lastnametextfield.getText();
        String username = usernametextfield.getText();
        String branch = branchtextfield.getText();
        String semester = semtextfield.getText();
        String password = setpasswordfield.getText();
        branch = branch.toUpperCase();
        String abcd = "insert into student_login values ('" + enn + "','" + firstname + "','" + middlename + "','" + lastname + "','" + username + "','" + branch + "','" + semester + "','" + password + "');";

        connectNow.stmt.executeUpdate(abcd);
        registrationmessegelabel.setText("Student has been Registered Successfully.");
        connectNow.close();
    }
}
