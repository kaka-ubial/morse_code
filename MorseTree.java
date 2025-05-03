import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MorseTree {

    private static class Node {
        String letter;
        Node left;
        Node right;

        public Node(String letter){
            this.letter = letter;
        }
    }

    private Node root;

    public MorseTree(){
        root = new Node(" ");
    }

    public void insert(String code, String letter){
        Node current = root;

        for (char symbol : code.toCharArray()){
            if (symbol == '.') {
                if (current.left == null){
                    current.left = new Node(letter);
                    current = current.left;
                }
            } else if (symbol == '-'){
                if(current.right == null){
                    current.right = new Node(letter);
                    current = current.right;
                }
            }
        }
    }

    public String decode(String code){
        Node current = root;

        for(char symbol : code.toCharArray()){
            if (symbol == '.') {
                current = current.left;
            } else if (symbol == '-') {
                current = current.right;
            }
            if (current == null) return "?";
        }
        return current.letter;
    }

    public int getHeight() {
        return getHeightRecursive(root);
    }

    private int getHeightRecursive(Node node) {
        if (node == null) return 0;

        int leftHeight = getHeightRecursive(node.left);
        int rightHeight = getHeightRecursive(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    public void drawTree(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        drawNode(gc, root, canvas.getWidth() / 2, 40, canvas.getWidth() / 4,
                1);
    }

    private void drawNode(GraphicsContext gc, MorseTree.Node node, double x, double y,
                          double xOffset, int level) {
        if (node == null) {
            return;
        }
        gc.setStroke(Color.BLACK);
        gc.strokeOval(x - 15, y - 15, 30, 30);
        gc.strokeText(String.valueOf(node.letter.equals(" ") ? ' ' : node.letter), x
                - 5, y + 5);
        if (node.left != null) {
            double newX = x - xOffset;
            double newY = y + 120;
            gc.strokeLine(x, y + 15, newX, newY - 15);
            drawNode(gc, node.left, newX, newY, xOffset / 2, level + 1);
        }
        if (node.right != null) {
            double newX = x + xOffset;
            double newY = y + 120;
            gc.strokeLine(x, y + 15, newX, newY - 15);
            drawNode(gc, node.right, newX, newY, xOffset / 2, level + 1);
        }
    }

}

