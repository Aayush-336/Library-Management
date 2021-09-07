import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ReturnBook extends getAnchorPane {
    @FXML
    Label message;
    @FXML
    TextField bookid_field;
    @FXML
    TextField studentid_field;
    @FXML
    GridPane bookgrid;
    @FXML
    GridPane studentgrid;
    @FXML
    Button returnButton;
    @FXML
    DatePicker issueDate;
    @FXML
    GridPane bookShow;
    boolean isBookIdPresent = false;
    boolean isStudentIdPresent = false;
    public void bookSearch() throws SQLException {
        conn con = new conn();
        ResultSet res = con.execute("select bookname,author,price,topic from book where bookid = "+bookid_field.getText());
        while (res.next())
        {
            int row = 1;
            for (int i = 0; i < 4; i++) {
                addNewRow(bookgrid);
                row++;
                bookgrid.add(new Label(res.getString(i+1)),0,row);
            }
            isBookIdPresent = true;
        }
        con.close();
        if (isBookIdPresent & isStudentIdPresent)returnButton.setDisable(false);
    }
    public void studentSearch() throws SQLException {
        conn con = new conn();
        ResultSet res = con.execute("select enn,firstname,branch,semester from student_login where enn = "+studentid_field.getText());
        while (res.next())
        {
            int row = 1;
            for (int i = 0; i < 4; i++) {
                addNewRow(studentgrid);
                row++;
                studentgrid.add(new Label(res.getString(i+1)),0,row);

            }
            isStudentIdPresent = true;
        }
        con.close();
        if (isBookIdPresent & isStudentIdPresent)returnButton.setDisable(false);
    }

    public void returnButton() throws SQLException {
        if (!(isBookIdPresent & isStudentIdPresent)){message.setText("Book Id or Student Id is not Present");return;}
        message.setText("");
        LocalDate date = LocalDate.now();
        LocalDate issue = issueDate.getValue();

        String bookid = bookid_field.getText();
        String studentid = studentid_field.getText();
        conn con = new conn();
        String query = "select record_index,return_date from issued where bookid=" + bookid + " and student_id=" + studentid + " and issued_date='"+issue+"';";
        System.out.println(query);
        ResultSet res = con.execute(query);
        if (!res.next()) {
            message.setText("This book is not issued");
            return;
        }
        String query2 = "update issued set return_date='"+date+"' where bookid=" + bookid + " and student_id=" + studentid + ";";
        con.stmt.executeUpdate(query2);
        message.setText("Book Successfully Returned!");
        con.close();
    }

    public void addNewRow(GridPane gridPane) {
        RowConstraints row = new RowConstraints();
        row.setPrefHeight(30);
        row.setVgrow(Priority.SOMETIMES);
        gridPane.getRowConstraints().add(row);
    }
}
