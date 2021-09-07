import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class found_book_controller extends getAnchorPane {
    @FXML
    TextField bookid;
    @FXML
    TextField qty;
    @FXML
    Label confirmationLabel;

    String bid, qt;

    public void setBookid(String bid) {
        bookid.setText(bid);
        bookid.setDisable(true);
    }

    public void submit() throws SQLException {
        bid = bookid.getText();
        qt = qty.getText();
        try {
            Integer.parseInt(qt);
        } catch (Exception e) {
            confirmationLabel.setText("Please Enter a valid quantity");
            return;
        }
        conn connection = new conn();
        String query = "update book set quantity=quantity+" + qt + " where bookid=" + bid + ";";
        connection.stmt.executeUpdate(query);
        connection.close();
        confirmationLabel.setText("Quantity of new books added Successfully");
    }
}
