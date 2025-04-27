import javax.swing.*;
import java.awt.*;

public class Tubarao {
    private int x, y;
    private Image imagem;

    public Tubarao(int x, int y) {
        this.x = x;
        this.y = y;
        ImageIcon icon = new ImageIcon("C:\\Users\\celia\\OneDrive\\Documentos\\Surf\\cartoon-style-illustration-of-cute-shark-isolated-on-transparent-background-png.png");
        imagem = icon.getImage();
    }

    public void perseguir(int alvoX, int alvoY) {
        if (x > alvoX) x -= 2;
        if (y < alvoY) y += 2;
        if (y > alvoY) y -= 2;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public Image getImagem() { return imagem; }
}
