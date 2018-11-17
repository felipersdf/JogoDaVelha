import java.awt.Color;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Tabuleiro  {

	JFrame frame;
	JLabel[][] labels = new JLabel[3][3];
	private JLabel label_atual;
	private JLabel label_historico;
	private int numeroJogador=1;
	private JogoDaVelha jogo;

	static String nomeJ1;
	static String nomeJ2;
	private String vencedor;
	static int qtd_jogadores;
	private JButton button;

	public Tabuleiro(int qtd, String j1, String j2) {
		if(qtd == 1) {
			qtd_jogadores = qtd;
			nomeJ1 = j1;
		}else if(qtd == 2) {
			qtd_jogadores = qtd;
			nomeJ1 = j1;
			nomeJ2 = j2;
		}

		initialize();
	}

	private void initialize() {

		jogo = new JogoDaVelha(nomeJ1, nomeJ2);

		frame = new JFrame();
		frame.setTitle("Jogo da Velha");
		frame.setBounds(100, 100, 417, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		label_atual = new JLabel("Jogador Atual: " + nomeJ1);
		label_atual.setBounds(10, 316, 157, 25);
		frame.getContentPane().add(label_atual);

		label_historico = new JLabel("\u00DAltima jogada:");
		label_historico.setBounds(10, 356, 381, 19);
		frame.getContentPane().add(label_historico);

		button = new JButton("Reiniciar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jogo = new JogoDaVelha(nomeJ1,nomeJ2);
				numeroJogador=1;
				label_atual.setText("Jogador Atual: " + nomeJ1);
				for(int i=0; i < 3; i++)
					for(int j=0; j < 3; j++)
						labels[i][j].setBackground(Color.YELLOW);
			}
		});
		button.setBounds(282, 316, 89, 25);
		frame.getContentPane().add(button);

		//inicializar a matriz de labels
		for(int i=0; i < 3; i++){
			for(int j=0; j < 3; j++){
				labels[i][j]=new JLabel(i+","+j);
				frame.getContentPane().add(labels[i][j]);
				labels[i][j].setBounds(i*100, j*100, 80, 80);	
				labels[i][j].setBackground(Color.YELLOW);
				labels[i][j].setBorder(new LineBorder(new Color(0, 0, 0)));
				labels[i][j].setOpaque(true);
				labels[i][j].addMouseListener(new  MouseAdapter(){
					public void mouseClicked(MouseEvent e){		//click
						JLabel b = (JLabel)e.getSource();
						int indicex = b.getX()/100;
						int indicey = b.getY()/100;
						boolean jogadavalida = jogo.jogarJogador(numeroJogador, indicex + 1, indicey + 1);

						if (jogadavalida == false) {	
							JOptionPane.showMessageDialog(frame.getContentPane(), "Jogada Inválida" );
						}else {

							if(numeroJogador==1) {	
								labels[indicex][indicey].setBackground(Color.BLUE);
								label_atual.setText("Jogador Atual: " + nomeJ2);
								label_historico.setText("O jogador " + nomeJ1 + " jogou na posição " + indicex + '-' + indicey);
								}else { 
								labels[indicex][indicey].setBackground(Color.GREEN);
								label_atual.setText("Jogador Atual: " + nomeJ1);
								label_historico.setText("O jogador " + nomeJ2 + " jogou na posição " + indicex + '-' + indicey);
								}
						}
						System.out.println("clicou na celula:"+  indicex + "-" + indicey);

						if(jogadavalida)
							if(numeroJogador==1) 
								numeroJogador=2; 
							else numeroJogador=1;

						if(jogo.terminou()) {

							if(jogo.getResultado2() == 3) {
								JOptionPane.showMessageDialog(frame.getContentPane(), "Ninguém ganhou.");		
							}else {
								vencedor = jogo.getNomeJogador(jogo.getResultado());
								
								JOptionPane.showMessageDialog(frame.getContentPane(), vencedor + " ganhou!");
							}
						}

					}});
			}
		}
	}}