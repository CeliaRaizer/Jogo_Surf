import javax.swing.*;
import java.awt.*;

public class Surfista {
    private int x, y;
    private Image imagem;

    public Surfista(int x, int y) {
        this.x = x;
        this.y = y;
        ImageIcon icon = new ImageIcon("C:\\Users\\celia\\OneDrive\\Documentos\\Surf\\silhouette-of-a-surfer-ocean-wave-free-png.png");
        imagem = icon.getImage();
        

    }

    public void moverCima() { y -= 10; }
    public void moverBaixo() { y += 10; }
    public void moverEsquerda() { x -= 10; }
    public void moverDireita() { x += 10; }

    public int getX() { return x; }
    public int getY() { return y; }
    public Image getImagem() { return imagem; }
}
