import javax.swing.*;
import java.awt.*;

public class Onda {
    private int x, y;
    private Image imagem;

    public Onda(int x, int y) {
        this.x = x;
        this.y = y;
        ImageIcon icon = new ImageIcon("C:\\Users\\celia\\OneDrive\\Documentos\\Surf\\wave-graphic-clipart-design-free-png.png");
        imagem = icon.getImage();
    }

    public void mover() {
        x -= 3;
        if (x < 0) {
            x = 800;
            y = (int)(Math.random() * 600);
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public Image getImagem() { return imagem; }
}
