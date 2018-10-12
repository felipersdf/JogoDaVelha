import java.io.FileWriter;

public class JogoDaVelha {
	private final int TAM_TABULEIRO = 3;
	private String jogador1;
	private String jogador2;
	private String[][] tabuleiro = new String[TAM_TABULEIRO][TAM_TABULEIRO];
	private int lin,col,jog, contador = 0, vencedor, linmaq, colmaq;
	FileWriter arq;

	// Construtores
	public JogoDaVelha(String j1, String j2) {
		jogador1 = j1;
		jogador2 = j2;
		this.inicializar();
//		try {
//			arq = new FileWriter (new File("jogadas.txt"));
//		} catch (IOException e) {
//			System.out.println("arquivo n�o pode ser criado");
//		}
	}

	public JogoDaVelha(String j1) {
		jogador1 = j1;
		jogador2 = "M�quina";
		this.inicializar();
//		try {
//			arq = new FileWriter (new File("jogadas.txt"));
//		} catch (IOException e) {
//			System.out.println("arquivo n�o pode ser criado");
//		}
	}

	public void inicializar() {
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				tabuleiro[i][j] = "";
			}
		}
	}
	// M�todos
	public boolean jogarJogador(int jogador, int linha, int coluna) {
		lin = linha-1;
		col = coluna-1;
		jog = jogador;
		
		this.mostrarTabuleiro();
		
		if(tabuleiro[lin][col] != "" || tabuleiro[lin][col] == "X" || tabuleiro[lin][col] == "O" )
			return false;	
		
		if(jogador == 1) {
			tabuleiro[lin][col] = "X";
			contador++;
			return true;
		}else{
			tabuleiro[lin][col] = "O";
			contador++;
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
			 } 
		 }while(!valido);					
	}
	public void mostrarTabuleiro() {
		
		for(int i=0; i>3;i++) {
			for(int j=0; j<3; j++) {
				System.out.println(tabuleiro[i][j]+ "-");
			}
		}
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
