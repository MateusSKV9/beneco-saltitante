package controlador;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import visual.Frame;

public class ControladorFrame implements ActionListener {
	 Frame frame;
	    Timer timer;
	    ImageIcon[] estados;
	    int estadoAtual = 0;
	    int posicaoY = 640;
	    double velocidade = 0;
	    double aceleracao = 10;
	    double fatorRebote = 0.983;
	    long tempoIntervalo = 30;
	    int limiteSuperior = 60;
	    int count = 0;

	    public ControladorFrame(Frame frame) {
	        this.frame = frame;
	        carregarEstados();
	        addEventos();
	    }

	    private void carregarEstados() {
	        // Carrega as imagens separadas para cada estado do boneco
	        estados = new ImageIcon[] {
	            new ImageIcon(getClass().getResource("/assets/sprite1.png")),
	            new ImageIcon(getClass().getResource("/assets/sprite2.png")),
	            new ImageIcon(getClass().getResource("/assets/sprite3.png")),
	            new ImageIcon(getClass().getResource("/assets/sprite4.png")),
	            new ImageIcon(getClass().getResource("/assets/sprite5.png")),
	            new ImageIcon(getClass().getResource("/assets/sprite6.png"))
	        };
	    }

	    public void addEventos() {
	        frame.getButtonLancar().addActionListener(this);
	    }

	    public void actionPerformed(ActionEvent e) {
	        if (e.getSource() == frame.getButtonLancar()) {
	            iniciarSimulacao();
	        }
	    }

	    private void iniciarSimulacao() {
	        posicaoY = 640;
	        velocidade = -5;
	        count = 0;

	        if (timer != null) {
	            timer.cancel();
	        }

	        timer = new Timer();
	        timer.scheduleAtFixedRate(new TimerTask() {
	            public void run() {
	                atualizarEstado();
	                atualizarPosicao();
	            }
	        }, 0, tempoIntervalo);
	    }

	    private void atualizarEstado() {
	        if (velocidade < 0) {
	            // O boneco está subindo
	            estadoAtual = 5;
	        } else if (velocidade > 0) {
	            // O boneco está descendo
	            estadoAtual = (estadoAtual + 1) % estados.length;
	            
	            if(estadoAtual==0 || estadoAtual==1 || estadoAtual==8 || estadoAtual==9 || estadoAtual==10) {
	            	return;
	            }
	        } else {
	        	estadoAtual = 3;
	        }

	        // Quando o boneco aterrissa, ajusta para o estado inicial
	        if (posicaoY >= 635) {
	            estadoAtual = 0; // Assume que o estado inicial é a posição em pé
	        }

	        frame.getBola().setIcon(estados[estadoAtual]);
	    }

	    private void atualizarPosicao() {
	        posicaoY -= (int) velocidade;
	        velocidade += (aceleracao * tempoIntervalo) / 1000;

	        if (posicaoY <= limiteSuperior) {
	            posicaoY = limiteSuperior;
	            velocidade = -velocidade * fatorRebote;
	        }

	        if (posicaoY >= 640) {
	            posicaoY = 640;
	            if (count >= 1) {
	                velocidade = 0;
	                timer.cancel();
	            } else {
	                count++;
	                velocidade = -velocidade * fatorRebote;
	            }
	        }

	        // Atualiza a posição apenas do JLabel
	        frame.getBola().setBounds(154, posicaoY, 85, 125);
	    }

	    public static void main(String[] args) {
	        Frame frame = new Frame();
	        new ControladorFrame(frame);
	    }
}
