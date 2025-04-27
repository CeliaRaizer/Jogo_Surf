import javax.swing.*;
import java.awt.*;

public class Obstaculo {
    private int x, y;
    private Image imagem;

    public Obstaculo(int x, int y) {
        this.x = x;
        this.y = y;
        ImageIcon icon = new ImageIcon("C:\\Users\\celia\\OneDrive\\Documentos\\Surf\\seashell-whelk-flat-47a28b.png"); // imagem de obstáculo
        imagem = icon.getImage();
    }

    public void mover() {
        x -= 4;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public Image getImagem() { return imagem; }
}
