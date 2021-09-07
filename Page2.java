import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class Page2 extends getAnchorPane {
    public void openReturnBook() throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("returnBook.fxml"));
        root.load();
        ReturnBook controller = root.getController();
        controller.setChangePane(getChangePane());
        controller.setParentScene(getParentScene());
        controller.setParentStage(getParentStage());
        controller.setMainPane();
    }

    public void openIssueBook() throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("bookissue.fxml"));
        root.load();
        issuecontroller controller = root.getController();
        controller.setChangePane(getChangePane());
        controller.setParentScene(getParentScene());
        controller.setParentStage(getParentStage());
        controller.setMainPane();
        controller.disableAll();
    }
}
