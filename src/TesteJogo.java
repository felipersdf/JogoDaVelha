import java.util.Scanner;
 
public class TesteJogo {
 
    public static void main(String[] args) {
 
        Scanner teclado = new Scanner(System.in);
        JogoDaVelha jogo = new JogoDaVelha("João", "Maria");
        int linha, coluna;
        boolean jogadavalida;
        int numeroJogador=1;
        do {
 
            do {
                System.out.println("jogador:" + numeroJogador);
                System.out.println("digite a linha: ");
                linha = teclado.nextInt();
                System.out.println("digite a coluna: ");
                coluna = teclado.nextInt();
                jogadavalida = jogo.jogarJogador(numeroJogador, linha, coluna);
                linha = jogo.getUltimaLinha();
                coluna = jogo.getUltimaColuna();
                if (!jogadavalida) {
                	System.out.println("Jogada Inválida. Tente Novamente \n");
                }else {
                    System.out.println("o jogador " + jogo.getUltimoJogador() + " jogou na posicao "+ "[" + linha + "-" + coluna + "]");
                }
            }while(!jogadavalida);
 
            if(numeroJogador==1) 
                numeroJogador=2; 
            else numeroJogador=1;
 
        }while( !jogo.terminou());
 
        switch(jogo.getResultado()) {
        case 1: System.out.println(jogo.getNomeJogador(1) + " venceu"); break;
        case 2: System.out.println(jogo.getNomeJogador(2) + " venceu"); break;
        case 3: System.out.println("Ninguém venceu");
        }
 
    }
}