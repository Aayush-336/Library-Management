import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Page1 extends getAnchorPane {
    @FXML
    Label label_user;
    @FXML
    Pane changepane;


    public void getuserpass() throws SQLException {
        conn con = new conn();
        String query = "select * from user_pass";
        ResultSet resultSet = con.stmt.executeQuery(query);
        String result = "";
        while (resultSet.next())
            result += resultSet.getString(1) + " " + resultSet.getString(2) + "\n";
        label_user.setText(result);
    }

    public void addBook() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("searchBook.fxml"));
        loader.load();
        searchBook controller = loader.getController();
        controller.setChangePane(changepane);
        controller.setParentScene(getParentScene());
        controller.setParentStage(getParentStage());
        controller.setMainPane();
    }
}
