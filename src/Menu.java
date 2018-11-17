

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu {

	private JFrame frame;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private JTextField textField_jogador2;
	private JTextField textField_jogador1;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JButton button;
	private JLabel label_3;
	
	String nomeJ1, nomeJ2; 
	int qtd;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		
		radioButton = new JRadioButton("Jogador vs Jogador");
		radioButton.setSelected(true);
		radioButton.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				textField_jogador1.setVisible(true);
				textField_jogador2.setVisible(true);
				label_2.setVisible(true);
				label_3.setText("Digite o nome dos jogadores");
				radioButton_1.setFocusable(true);
				radioButton_1.setSelected(false);
			}
		});
		radioButton.setBounds(30, 98, 152, 23);
		frame.getContentPane().add(radioButton);
		
		radioButton_1 = new JRadioButton("Jogador vs Máquina");
		radioButton_1.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				textField_jogador1.setVisible(true);
				textField_jogador2.setVisible(false);
				label_2.setVisible(false);
				label_3.setText("Digite o nome do jogador");
				radioButton.setFocusable(true);
				radioButton.setSelected(false);
			}
		});
		radioButton_1.setBounds(236, 98, 173, 23);
		frame.getContentPane().add(radioButton_1);
		
		textField_jogador2 = new JTextField();
		textField_jogador2.setBounds(128, 200, 123, 23);
		frame.getContentPane().add(textField_jogador2);
		textField_jogador2.setColumns(10);
		
		textField_jogador1 = new JTextField();
		textField_jogador1.setColumns(10);
		textField_jogador1.setBounds(128, 165, 123, 23);
		frame.getContentPane().add(textField_jogador1);
		
		label = new JLabel("Jogo da Velha");
		label.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 20));
		label.setBounds(149, 23, 123, 51);
		frame.getContentPane().add(label);
		
		label_1 = new JLabel("jogador 1:");
		label_1.setBounds(56, 167, 62, 19);
		frame.getContentPane().add(label_1);
		
		label_2 = new JLabel("jogador 2:");
		label_2.setBounds(56, 202, 62, 19);
		frame.getContentPane().add(label_2);
		
		button = new JButton("Iniciar Jogo");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(radioButton.isSelected()) {
					nomeJ1 = textField_jogador1.getText();
					nomeJ2 = textField_jogador2.getText();
					qtd = 2;
					
					frame.dispose();
					Tabuleiro tabuleiro = new Tabuleiro(qtd, nomeJ1, nomeJ2);
					tabuleiro.frame.setVisible(true);
				}
				else if(radioButton_1.isSelected()) {
					nomeJ1 = textField_jogador1.getText();
					nomeJ2 = "Máquina";
					qtd = 1;
					
					
					frame.dispose();
					TabuleiroMaquina tabuleiro = new TabuleiroMaquina(nomeJ1);
					tabuleiro.frame.setVisible(true);
				}
//				
//				frame.dispose();
//				Tabuleiro tabuleiro = new Tabuleiro(qtd, nomeJ1, nomeJ2);
//				tabuleiro.frame.setVisible(true);
//				
				
			}
		});
		button.setBounds(286, 187, 123, 23);
		frame.getContentPane().add(button);
		
		label_3 = new JLabel("Digite o nome dos jogadores:");
		label_3.setBounds(128, 131, 167, 23);
		frame.getContentPane().add(label_3);
	}
}
