package controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import javax.swing.JFrame;

public class Testes extends JPanel {
	private BufferedImage spriteSheet;
    private int frameWidth = 128; // Largura de cada quadro
    private int frameHeight = 128; // Altura de cada quadro
    private int currentFrame = 0; // Quadro atual
    private int totalFrames = 100; // Total de quadros na spritesheet
    private Timer timer;
    private BufferedImage[] frames;

    public Testes() {
        try {
            // Carrega a spritesheet
            spriteSheet = ImageIO.read(new File("C:\\Users\\mateu\\git\\repository4\\BonecoSaltitante\\src\\assets\\Arcade - Street Fighter 2 Super Street Fighter 2 - Ryu.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Inicializa o array de quadros
        frames = new BufferedImage[totalFrames];
        int framePerRow = 10; // Número de quadros por linha

        for (int i = 0; i < totalFrames; i++) {
            int x = (i % framePerRow) * frameWidth;
            int y = (i / framePerRow) * frameHeight;

            frames[i] = spriteSheet.getSubimage(x, y, frameWidth, frameHeight);
        }

        // Timer para alternar quadros
        timer = new Timer(250, e -> {
            currentFrame = (currentFrame + 1) % totalFrames; // Avança para o próximo quadro
            repaint(); // Atualiza o painel
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (frames[currentFrame] != null) {
            // Desenha o quadro atual
            g.drawImage(frames[currentFrame], 100, 100, null); // Desenha o quadro na posição (100, 100)
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sprite Animation");
        Testes animation = new Testes();

        frame.add(animation);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
