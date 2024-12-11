package visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Frame extends JFrame {
	private JLabel labelTitlePanel;
	private JButton buttonLancar;
	private JLabel bola;
	private JPanel panel;
	
	public Frame(){
		setSize(600, 806);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().add(getLabelTitlePanel());
		getContentPane().add(getButtonLancar());
		getContentPane().add(getBola());
		getContentPane().add(getPanel());
		setVisible(true);
	}
	
	public JLabel getLabelTitlePanel() {
		if (labelTitlePanel == null) {
			labelTitlePanel = new JLabel("Desafio Boneco Saltitante");
			labelTitlePanel.setFont(new Font("Tahoma", Font.PLAIN, 25));
			labelTitlePanel.setBounds(154, 10, 277, 31);
		}
		return labelTitlePanel;
	}
	
	public JButton getButtonLancar() {
		if (buttonLancar == null) {
			buttonLancar = new JButton("PULAR");
			buttonLancar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			buttonLancar.setFont(new Font("Tahoma", Font.PLAIN, 14));
			buttonLancar.setBounds(371, 64, 118, 35);
		}
		return buttonLancar;
	}
	public JLabel getBola() {
		if (bola == null) {
			bola = new JLabel("");
			bola.setIcon(new ImageIcon(Frame.class.getResource("/assets/sprites-removebg-preview.png")));
			bola.setBounds(83, 617, 420, 129);
		}
		return bola;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(new Color(255, 68, 68));
			panel.setBounds(64, 750, 457, 19);
		}
		return panel;
	}
}
