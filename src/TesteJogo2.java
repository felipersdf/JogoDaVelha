import java.util.Scanner;
 
public class TesteJogo2 {
 
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
         
        JogoDaVelha jogo = new JogoDaVelha("João");
        int linha, coluna;
        boolean jogadavalida;
        int numeroJogador=1;
        do {
         
            do {
                System.out.println("jogador 1");
                System.out.println("digite a linha: ");
                linha = teclado.nextInt();
                System.out.println("digite a coluna: ");
                coluna = teclado.nextInt();
                jogadavalida = jogo.jogarJogador(numeroJogador, linha, coluna);
                linha = jogo.getUltimaLinha();
                coluna = jogo.getUltimaColuna();
                System.out.println("o jogador jogou na posicao " + "[" + linha + "-" + coluna + "]");
            }while(!jogadavalida);
 
            if(!jogo.terminou()) {
                jogo.jogarMaquina();
                linha = jogo.getUltimaLinha();
                coluna = jogo.getUltimaColuna();
                System.out.println("a maquina jogou na posicao "+ "[" + linha + "-" + coluna + "]");
            }   
             
        }while(!jogo.terminou());
 
        switch(jogo.getResultado()) {
        case 1: System.out.println(jogo.getNomeJogador(1) + " venceu"); break;
        case 2: System.out.println(jogo.getNomeJogador(2) + " venceu"); break;
        case 3: System.out.println("ninguem venceu");
        }
         
 
    }
}