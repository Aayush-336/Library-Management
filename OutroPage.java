import javafx.stage.Stage;

public class OutroPage {
    Stage Parentstage;
    Stage Currentstage;

    public void setCurrentStage(Stage stage1) {
        Currentstage = stage1;
    }

    public void setParentStage(Stage stage1) {
        Parentstage = stage1;
    }

    public void no() {
        Parentstage.show();
        Currentstage.close();
    }

    public void yes() {
        Currentstage.close();
    }
}
