import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Page5 extends getAnchorPane {
    @FXML
    AnchorPane mainPane;
    @FXML
    GridPane dataOutput;
    @FXML
    TextField userField;
    @FXML
    Label searchLabel;
    int totalColumn = 4;


    public void initialize()
    {
        userField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER)
            {
                try {
                    getDetails();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return;
            }
            if (event.getCode().isLetterKey()) {
                searchLabel.setText(userField.getText() + event.getCode().toString().toLowerCase());
                return;
            }
            searchLabel.setText(userField.getText());
        });
    }

    public void getDetails() throws SQLException {
        dataOutput.getChildren().clear();
        setDataOutput();
        setHeading();
        conn con = new conn();
        ResultSet res = con.execute("select * from userdetails where username='" + userField.getText() + "';");
        while (res.next()) {
            for (int i = 0; i < totalColumn; i++) {
                addLabelRow(res.getString(i + 1), i, 1);
            }
        }
    }

    public void getAllDetails() throws SQLException {
        dataOutput.getChildren().clear();
        setDataOutput();
        setHeading();
        conn con = new conn();
        String query = "select * from userdetails";
        ResultSet res = con.execute(query);
        int rows = 0;
        while (res.next()) {
            rows++;
            addNewRow();
            for (int i = 0; i < totalColumn; i++) {
                addLabelRow(res.getString(i + 1), i, rows);
            }
        }
        con.close();
        dataOutput.setLayoutX(50);
        dataOutput.setLayoutY(250);
    }

    public void addLabelRow(String text, int col, int row) {
        dataOutput.add(new Label(text), col, row);
    }

    public void setHeading() {
        dataOutput.add(new Label("Username"), 0, 0);
        dataOutput.add(new Label("First Name"), 1, 0);
        dataOutput.add(new Label("Middle Name"), 2, 0);
        dataOutput.add(new Label("Last Name"), 3, 0);

    }

    public void setDataOutput() {
        for (int i = 0; i < totalColumn; i++) {
            addNewCol();
        }
        addNewRow();

    }

    public void addNewCol() {
        ColumnConstraints col = new ColumnConstraints();
        col.setPrefWidth(100);
        col.setHgrow(Priority.SOMETIMES);
        dataOutput.getColumnConstraints().add(col);
    }

    public void addNewRow() {
        RowConstraints row = new RowConstraints();
        row.setPrefHeight(30);
        row.setVgrow(Priority.SOMETIMES);
        dataOutput.getRowConstraints().add(row);
    }
}
