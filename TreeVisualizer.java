import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;




public class TreeVisualizer extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Visualizador de Código Morse");

        MorseTree.MorseBST bst = new MorseTree.MorseBST();
        int height = bst.getHeight();
        int canvasHeight = 100 + height * 100;
        int canvasWidth = (int) Math.pow(2, height) * 40;

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        bst.drawTree(canvas);

        TextField input = new TextField();
        input.setPromptText("Digite o código Morse (ex: --- ..   - ..- -.. ---)");
        input.setLayoutY(canvasHeight + 10);
        input.setPrefWidth(canvasWidth);

        input.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String morse = input.getText();
                System.out.println("Decodificado: " + bst.decode(morse));
            }
        });

        Group root = new Group(canvas, input);
        Scene scene = new Scene(root, canvasWidth, canvasHeight + 40);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
