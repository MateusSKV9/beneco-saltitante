package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import visual.Frame;

public class ControladorFrame implements ActionListener {
	 Frame frame;
	    Timer timer;
	    
	    int posicaoY = 640;
	    double velocidade = 0;
	    double aceleracao = 10;
	    double fatorRebote = 0.983;
	    long tempoIntervalo = 10;
	    int limiteSuperior = 60;
	    int count = 0;

	    // PRECISA CORRIGIR A SUBIDA
	    
	    public ControladorFrame(Frame frame) {
	        this.frame = frame;
	        addEventos();
	    }

	    public void addEventos() {
	        frame.getButtonLancar().addActionListener(this);
	    }

	    public void actionPerformed(ActionEvent e) {
	        if (e.getSource() == frame.getButtonLancar()) {
	            iniciarSimulacao(); // Inicia a simulação quando o botão é pressionado
	        }
	    }

	    private void iniciarSimulacao() {
	        posicaoY = 640; // Posição inicial no solo
	        velocidade = -5; // Velocidade inicial negativa para que a bola suba
	        count = 0; // Reinicia o contador

	        if (timer != null) {
	            timer.cancel(); // Cancela qualquer timer anterior para evitar conflitos
	        }

	        timer = new Timer();
	        timer.scheduleAtFixedRate(new TimerTask() {
	            public void run() {
	                atualizarPosicao(); // Chama a função de atualização da posição
	            }
	        }, 0, tempoIntervalo); // Intervalo definido para cada 10ms
	    }

	    private void atualizarPosicao() {
	        posicaoY -= (int) velocidade; // Atualiza a posição vertical com base na velocidade
	        velocidade += (aceleracao * tempoIntervalo) / 1000; // Aplica a gravidade (aumenta a velocidade)

	        // Verifica se atingiu o limite superior
	        if (posicaoY <= limiteSuperior) {
	            posicaoY = limiteSuperior;
	            velocidade = -velocidade * fatorRebote; // Inverte a direção com fator de rebote
	        }

	        // Verifica se voltou ao solo
	        if (posicaoY >= 640) {
	            posicaoY = 640; // Ajusta a posição para o solo
	            if (count >= 1) { // Se já caiu uma vez, para a simulação
	                velocidade = 0; // Zera a velocidade
	                timer.cancel(); // Para a simulação
	            } else {
	                count++; // Incrementa o contador para detectar a segunda vez
	                velocidade = -velocidade * fatorRebote; // Aplica o rebote
	            }
	        }

	        frame.getBola().setBounds(154, posicaoY, 85, 125); // Atualiza a posição no frame
	    }

	    public static void main(String[] args) {
	        Frame frame = new Frame();
	    	new ControladorFrame(frame);
	    }
}
