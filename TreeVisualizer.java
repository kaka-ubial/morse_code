import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
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
        input.setPromptText("Digite o código Morse ou a letra (ex: --- ..   - ..- -.. --- ou A)");
        input.setLayoutY(canvasHeight + 10);
        input.setPrefWidth(canvasWidth);
        input.setStyle("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);");

        input.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String inputText = input.getText().trim();

                if (inputText.matches("[.\\s-]+")) {
                    System.out.println("Decodificado (Morse → Texto): " + bst.decode(inputText));
                } else {
                    System.out.println("Codificado (Texto → Morse): " + bst.encode(inputText));
                }
            }
        });

        Group root = new Group(canvas, input);
        Scene scene = new Scene(root, canvasWidth, canvasHeight + 40);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
