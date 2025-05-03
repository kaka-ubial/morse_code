import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        TreeVisualizer visualizer = new TreeVisualizer();
        visualizer.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
