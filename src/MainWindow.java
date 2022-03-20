import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        super("Roguelike");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320,345);
        setLocation(400,400);
        add(new GameField());
        setVisible(true);
    }

    public static void main(String[] args) {
        MainWindow mw = new MainWindow();
    }
}