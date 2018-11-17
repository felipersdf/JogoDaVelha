import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JogoDaVelha {
	private final int TAM_TABULEIRO = 3;
	private String jogador1;
	private String jogador2;
	private String[][] tabuleiro = new String[TAM_TABULEIRO][TAM_TABULEIRO];
	private int lin,col,jog, contador = 0, vencedor, linmaq, colmaq;
	FileWriter jogada;

	// Construtores
	public JogoDaVelha(String j1, String j2) {
		jogador1 = j1;
		jogador2 = j2;
		this.inicializar();
		try {
			jogada = new FileWriter (new File("src/arquivoTexto/jogadas.txt"));
		} catch (IOException e) {
			System.out.println("arquivo não pode ser criado");
		}
	}

	public JogoDaVelha(String j1) {
		jogador1 = j1;
		jogador2 = "Máquina";
		this.inicializar();
		try {
			jogada = new FileWriter (new File("src/arquivoTexto/jogadas.txt"));
		} catch (IOException e) {
			System.out.println("arquivo não pode ser criado");
		}
	}

	public void inicializar() {
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				tabuleiro[i][j] = "";
			}
		}
	}
	// Métodos
	public boolean jogarJogador(int jogador, int linha, int coluna) {
		lin = linha-1;
		col = coluna-1;
		jog = jogador;
		
		if(tabuleiro[lin][col] != "" || tabuleiro[lin][col] == "X" || tabuleiro[lin][col] == "O" ) {
			try {
				jogada.write("O jogador "+ jogador+" fez uma jogada inválida.\n" );
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			return false;	
		}
		if(jogador == 1) {
			tabuleiro[lin][col] = "X";
			contador++;
			try {
				jogada.write("\nO jogador "+ jogador + " jogou na posicao: " + linha + "-" + coluna + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}else{
			tabuleiro[lin][col] = "O";
			contador++;
			try {
				jogada.write("\nO jogador "+ jogador + " jogou na posicao: " + linha + "-" + coluna + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
}
	private int random() {
		return (int)(Math.random()*3);
	}
	
	public void jogarMaquina() {
		boolean valido = false;
		 do {
			 linmaq = this.random();
			 colmaq = this.random();
			 if(tabuleiro[linmaq][colmaq].equals("")) {
				 tabuleiro[linmaq][colmaq] = "O";
				 valido = true;
				 lin = linmaq;
				 col = colmaq;
				 contador++;
				 jog = 2;
				 try {
						jogada.write("\nA Máquina jogou na posicao" + linmaq + "-" + colmaq + "\n");
					} catch (IOException e) {
						e.printStackTrace();
					}
			 } 
		 }while(!valido);					
	}
	
	public int getUltimaLinha() {
		return lin+1;
	}
	public int getUltimaColuna() {
		return col+1;
	}
	public int getUltimoJogador() {
		return jog;
	}
	public String getNomeJogador(int numero) {
		if (numero == 1)
			return jogador1;
		else
			return jogador2;
	}
	
	public int getResultado() {
		 try {
			jogada.write("\n   O vencedor foi " + this.getNomeJogador(vencedor) + "\n");
			jogada.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
			return vencedor;		
	}
	public int getResultado2() {
		return vencedor;
	}
	public boolean terminou() {

		if(contador >= 5 && contador < 9){
			   if(this.checaLinhas() == 1) {
				vencedor = this.getUltimoJogador();
				return true;
			}  if(this.checaColunas() == 1) {
				vencedor = this.getUltimoJogador();
				return true;
			} if(this.checaDiagonais() == 1) {
				vencedor = this.getUltimoJogador();
				return true;
			} if(this.checaLinhas() == -1) {
				vencedor = this.getUltimoJogador();
				return true;
			} if(this.checaColunas() == -1) {
				vencedor = this.getUltimoJogador();
				return true;
			} if(this.checaDiagonais() == -1) {
				vencedor = this.getUltimoJogador();
				return true;
			}
		}else if(contador == 9) {
			vencedor = 3;
			return true;
		}
		return false;
	}

	 public int checaLinhas(){
	            if( (tabuleiro[lin][0] + tabuleiro[lin][1] + tabuleiro[lin][2]).equals("XXX"))
	                return -1;
	            if( (tabuleiro[lin][0] + tabuleiro[lin][1] + tabuleiro[lin][2]).equals("OOO"))
	                return 1;
	        
	        return 0;
	    }
	    
    public int checaColunas(){

	            if( (tabuleiro[0][col] + tabuleiro[1][col] + tabuleiro[2][col]).equals("XXX"))
	                return -1;
	            if( (tabuleiro[0][col] + tabuleiro[1][col] + tabuleiro[2][col]).equals("OOO"))
	                return 1;
	            
	            return 0;
	    }	    
    public int checaDiagonais(){
	        if( (tabuleiro[0][0] + tabuleiro[1][1] + tabuleiro[2][2]).equals("XXX"))
	            return -1;
	        if( (tabuleiro[0][0] + tabuleiro[1][1] + tabuleiro[2][2]).equals("OOO"))
	            return 1;
	        if( (tabuleiro[0][2] + tabuleiro[1][1] + tabuleiro[2][0]).equals("XXX"))
	            return -1;
	        if( (tabuleiro[0][2] + tabuleiro[1][1] + tabuleiro[2][0]).equals("OOO"))
	            return 1;
	        
	        return 0;
	    }
}
