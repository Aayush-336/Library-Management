import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class searchBook extends getAnchorPane {
    @FXML
    TextField bookid;

    String bid;

    public void searchAction(ActionEvent event) throws IOException, SQLException {
        bid = bookid.getText();
        conn connection = new conn();
        String query = "select bookid from book;";
        ResultSet res = connection.execute(query);
        int i = 0;
        while (res.next())
            if (bid.equals(res.getString(1))) {
                i = 1;
                break;
            }
        connection.close();
        if (i == 0) {
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("new_book.fxml"));
            loader2.load();
            newBookcontroller controller = loader2.getController();
            controller.setChangePane(this.getChangePane());
            controller.setParentScene(getParentScene());
            controller.setParentStage(getParentStage());
            controller.setMainPane();
            controller.setBookid(bid);

        } else {
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("found_book.fxml"));
            loader2.load();
            found_book_controller controller = loader2.getController();
            controller.setBookid(bid);
            controller.setChangePane(this.getChangePane());
            controller.setParentScene(getParentScene());
            controller.setParentStage(getParentStage());
            controller.setMainPane();
        }
    }
}
