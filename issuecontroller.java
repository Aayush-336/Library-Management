import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class issuecontroller extends getAnchorPane {
    @FXML
    TextField bookid;
    @FXML
    TextField studentid;
    @FXML
    TextField bookname;
    @FXML
    TextField author;
    @FXML
    TextField price;
    @FXML
    TextField studentname;
    @FXML
    TextField stream;
    @FXML
    DatePicker issuedate;
    @FXML
    DatePicker rdate;
    String bid;
    String sid;

    public void disableAll() {
        setdate();
        setBookname();
        setStream();
        setStudentname();
        setAuthor();
        setPrice();
    }

    public void setBookname() {
        bookname.setDisable(true);
    }

    public void setStudentname() {
        studentname.setDisable(true);
    }

    public void setStream() {
        stream.setDisable(true);
    }

    public void setAuthor() {
        author.setDisable(true);
    }

    public void setPrice() {
        price.setDisable(true);
    }

    public void setdate() {
        LocalDate issdate = LocalDate.now();
        issuedate.setValue(issdate);

    }

    public void search() throws SQLException {
        conn con = new conn();
        String query = "select bookname,author,price from book where bookid=" + bookid.getText();
        ResultSet res = con.execute(query);
        while (res.next()) {
            bookname.setText(res.getString(1));
            author.setText(res.getString(2));
            price.setText(res.getString(3));
        }
        con.close();
    }

    public void submit(ActionEvent event) throws SQLException {
        conn con = new conn();
        String sql = "insert into issued (bookid,student_id,issued_date) values(?,?,?);";
        PreparedStatement stmt = con.con.prepareStatement(sql);
        stmt.setString(1, bookid.getText());
        stmt.setString(2, studentid.getText());
        stmt.setString(3, String.valueOf(issuedate.getValue()));
//    stmt.setString(4,rdate.getConverter().toString());
        stmt.executeUpdate();
        con.close();
    }
}