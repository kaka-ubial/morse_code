import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TreeVisualizer extends Application {

    @Override
    public void start(Stage primaryStage) {
        MorseTree bst = new MorseTree();
        bst.insert(".", "E");
        bst.insert("-", "T");
        bst.insert("..", "I");
        bst.insert(".-", "A");
        bst.insert("--", "M");
        bst.insert("-.", "N");

        int height = bst.getHeight();
        int canvasHeight = 100 + height * 100;
        int canvasWidth = (int) Math.pow(2, height) * 45;

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        bst.drawTree(canvas);

        Group root = new Group();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, canvasWidth, canvasHeight);

        primaryStage.setTitle("Visualizador de Árvore Morse");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
