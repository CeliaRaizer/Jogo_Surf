import javax.swing.*;

public class SurfAventura {
    public static void main(String[] args) {
        JFrame tela = new JFrame("Surf Aventura");
        Jogo jogo = new Jogo();
        tela.add(jogo);
        tela.setSize(800, 600);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setVisible(true);
    }
}

