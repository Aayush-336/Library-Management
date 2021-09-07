import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class newBookcontroller extends getAnchorPane {
    @FXML
    TextField bookid;
    @FXML
    TextField bookname;
    @FXML
    TextField author;
    @FXML
    TextField bookprice;
    @FXML
    TextField topic;
    @FXML
    TextField qty;
    @FXML
    DatePicker doa;
    @FXML
    Label confirmationLabel;
    String bid, bname, aut, bprice, top, qt;

    public void setBookid(String bookid) {
        this.bookid.setText(bookid);
        this.bookid.setDisable(true);
    }

    public void submit(ActionEvent event) throws SQLException {
        bid = bookid.getText();
        bname = bookname.getText();
        aut = author.getText();
        bprice = bookprice.getText();
        top = topic.getText();
        qt = qty.getText();
        conn connection = new conn();
        String query = "insert into book values(" + bid + ",'" + bname + "','" + aut + "'," + qt + "," + bprice + ",'" + top + "','" + doa.getValue() + "');";
        connection.stmt.executeUpdate(query);
        connection.con.close();
        confirmationLabel.setText("Book Successfully Added");
    }
}
