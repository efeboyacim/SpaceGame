import javax.swing.*;

public class main {
    public static void main(String[] args) {

        GameScreen Screen = new GameScreen("Space Game");

        Screen.setResizable(false);
        Screen.setFocusable(false);
        Screen.setSize(800,600);
        Screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Game Game = new Game();

        Game.requestFocus();
        Game.addKeyListener(Game);
        Game.setFocusable(true);
        Game.setFocusTraversalKeysEnabled(false);

        Screen.add(Game);
        Screen.setVisible(true);






    }
}
