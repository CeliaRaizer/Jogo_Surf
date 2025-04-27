import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Jogo extends JPanel implements ActionListener, KeyListener {
    private Surfista surfista;
    private ArrayList<Obstaculo> obstaculos;
    private ArrayList<Onda> ondas;
    private Tubarao tubarao;
    private Timer timer;
    private int pontuacao;
    private boolean jogoAtivo;
    private boolean tubaraoAtivo;

    public Jogo() {
        surfista = new Surfista(100, 300);
        obstaculos = new ArrayList<>();
        ondas = new ArrayList<>();
        pontuacao = 0;
        jogoAtivo = true;
        tubaraoAtivo = false;

        // Inicialmente cria algumas ondas
        for (int i = 0; i < 3; i++) {
            ondas.add(new Onda(800 + i * 200, (int)(Math.random() * 550)));
        }

        // Obstáculos surgem depois com o tempo

        timer = new Timer(30, this);
        timer.start();
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.cyan);

        // Desenha surfista
        g.drawImage(surfista.getImagem(), surfista.getX(), surfista.getY(), 50, 50, this);

        // Desenha tubarão (se ativo)
        if (tubaraoAtivo && tubarao != null) {
            g.drawImage(tubarao.getImagem(), tubarao.getX(), tubarao.getY(), 60, 60, this);
        }

        // Desenha ondas
        for (Onda onda : ondas) {
            g.drawImage(onda.getImagem(), onda.getX(), onda.getY(), 30, 30, this);
        }

        // Desenha obstáculos
        for (Obstaculo obstaculo : obstaculos) {
            g.drawImage(obstaculo.getImagem(), obstaculo.getX(), obstaculo.getY(), 40, 40, this);
        }

        // Desenha pontuação
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Pontuação: " + pontuacao, 10, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (jogoAtivo) {
            // Move ondas
            for (Onda onda : ondas) {
                onda.mover();
            }

            // Move obstáculos
            Iterator<Obstaculo> it = obstaculos.iterator();
            while (it.hasNext()) {
                Obstaculo obs = it.next();
                obs.mover();
                if (obs.getX() < -40) {
                    it.remove(); // remove obstáculos que saíram da tela
                }
            }

            // Move tubarão (se ativo)
            if (tubaraoAtivo && tubarao != null) {
                tubarao.perseguir(surfista.getX(), surfista.getY());
            }

            verificarColisoes();
            gerarNovosElementos();
            verificarVitoria();

            repaint();
        }
    }

    private void verificarColisoes() {
        Rectangle rSurfista = new Rectangle(surfista.getX(), surfista.getY(), 50, 50);

        // Colisão com ondas
        Iterator<Onda> itOndas = ondas.iterator();
        while (itOndas.hasNext()) {
            Onda onda = itOndas.next();
            Rectangle rOnda = new Rectangle(onda.getX(), onda.getY(), 30, 30);
            if (rSurfista.intersects(rOnda)) {
                fimDeJogo("Você bateu numa onda!");
                return;
            } else if (onda.getX() + 30 < surfista.getX()) {
                // Passou da onda sem bater
                pontuacao++;
                itOndas.remove();
            }
        }

        // Colisão com obstáculos
        for (Obstaculo obstaculo : obstaculos) {
            Rectangle rObstaculo = new Rectangle(obstaculo.getX(), obstaculo.getY(), 40, 40);
            if (rSurfista.intersects(rObstaculo)) {
                fimDeJogo("Você bateu num obstáculo!");
                return;
            }
        }

        // Colisão com tubarão
        if (tubaraoAtivo && tubarao != null) {
            Rectangle rTubarao = new Rectangle(tubarao.getX(), tubarao.getY(), 60, 60);
            if (rSurfista.intersects(rTubarao)) {
                fimDeJogo("O tubarão te pegou!");
            }
        }
    }

    private void gerarNovosElementos() {
        // Gera ondas novas aleatoriamente
        if (Math.random() < 0.02) {
            ondas.add(new Onda(800, (int)(Math.random() * 550)));
        }

        // Gera obstáculos aleatórios
        if (Math.random() < 0.01) {
            obstaculos.add(new Obstaculo(800, (int)(Math.random() * 550)));
        }

        // Ativa tubarão após 10 pontos
        if (pontuacao >= 10 && !tubaraoAtivo) {
            tubaraoAtivo = true;
            tubarao = new Tubarao(800, (int)(Math.random() * 550));
        }
    }

    private void verificarVitoria() {
        if (pontuacao >= 50) {
            jogoAtivo = false;
            timer.stop();
            JOptionPane.showMessageDialog(this, "Parabéns! Você venceu!");
        }
    }

    private void fimDeJogo(String mensagem) {
        jogoAtivo = false;
        timer.stop();
        JOptionPane.showMessageDialog(this, mensagem + " Pontuação final: " + pontuacao);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (jogoAtivo) {
            if (e.getKeyCode() == KeyEvent.VK_UP) surfista.moverCima();
            if (e.getKeyCode() == KeyEvent.VK_DOWN) surfista.moverBaixo();
            if (e.getKeyCode() == KeyEvent.VK_LEFT) surfista.moverEsquerda();
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) surfista.moverDireita();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
