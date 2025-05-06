import java.util.HashMap;
import java.util.Map;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MorseTree {

    static class Node {
        char letter;
        Node left, right;

        Node(char letter) {
            this.letter = letter;
        }
    }

    static class MorseBST {
        private Node root;
        private Map<Character, String> letterToMorse = new HashMap<>();

        public MorseBST() {
            root = new Node(' ');
            insertAll();
        }

        public void insert(char letter, String morseCode) {
            Node current = root;
            for (char c : morseCode.toCharArray()) {
                if (c == '.') {
                    if (current.left == null) current.left = new Node(' ');
                    current = current.left;
                } else if (c == '-') {
                    if (current.right == null) current.right = new Node(' ');
                    current = current.right;
                }
            }
            current.letter = letter;
            letterToMorse.put(letter, morseCode); // adiciona no mapa reverso
        }

        public void insertAll() {
            insert('A', ".-");    insert('B', "-...");  insert('C', "-.-.");
            insert('D', "-..");   insert('E', ".");     insert('F', "..-.");
            insert('G', "--.");   insert('H', "....");  insert('I', "..");
            insert('J', ".---");  insert('K', "-.-");   insert('L', ".-..");
            insert('M', "--");    insert('N', "-.");    insert('O', "---");
            insert('P', ".--.");  insert('Q', "--.-");  insert('R', ".-.");
            insert('S', "...");   insert('T', "-");     insert('U', "..-");
            insert('V', "...-");  insert('W', ".--");   insert('X', "-..-");
            insert('Y', "-.--");  insert('Z', "--..");
        }

        public char decodeLetter(String code) {
            Node current = root;
            for (char c : code.toCharArray()) {
                current = (c == '.') ? current.left : current.right;
                if (current == null) return '?';
            }
            return current.letter;
        }

        public String decode(String morseCode) {
            StringBuilder result = new StringBuilder();
            String[] words = morseCode.trim().split(" {2,}");
            for (String word : words) {
                String[] letters = word.trim().split(" ");
                for (String letter : letters) {
                    if (!letter.isEmpty()) {
                        result.append(decodeLetter(letter));
                    }
                }
                result.append(" ");
            }
            return result.toString().trim();
        }


        public String encode(String text) {
            StringBuilder morse = new StringBuilder();
            for (char c : text.toUpperCase().toCharArray()) {
                if (c == ' ') {
                    morse.append("  "); // separador de palavras (2 espaços)
                } else if (letterToMorse.containsKey(c)) {
                    morse.append(letterToMorse.get(c)).append(" ");
                } else {
                    morse.append("? ");
                }
            }
            return morse.toString().trim();
        }


        public int getHeight() {
            return getHeight(root);
        }

        private int getHeight(Node node) {
            if (node == null) return 0;
            return 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }

        public void drawTree(Canvas canvas) {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(2);
            drawNode(gc, root, canvas.getWidth() / 2, 40, canvas.getWidth() / 4, 1);
        }

        private void drawNode(GraphicsContext gc, Node node, double x, double y, double xOffset, int level) {
            if (node == null) return;
            gc.setStroke(Color.BLACK);
            gc.strokeOval(x - 15, y - 15, 30, 30);
            gc.setFont(new Font(16));
            gc.strokeText(String.valueOf(node.letter), x - 5, y + 5);
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
}
