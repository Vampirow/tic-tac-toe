import java.util.Scanner;

public class JogoDaVelha {
 //cria o tabuleiro
    public static char[][] tabuleiro = { {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '} };

    public static String jogador1;
    public static String jogador2;
    public static char jogadorAtual = 'X';
//mostra o tabuleiro no terminal
    public static void exibirTabuleiro() {
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j]);
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("  -----");
        }
    }

    public static boolean jogar(int linha, int coluna) {
        if (linha < 0 || linha > 2 || coluna < 0 || coluna > 2 || tabuleiro[linha][coluna] != ' ') {
            return false;
        }
        tabuleiro[linha][coluna] = jogadorAtual;
        return true;
    }

    public static boolean verificarVitoria() {
        // Verificar linhas e colunas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == jogadorAtual && tabuleiro[i][1] == jogadorAtual && tabuleiro[i][2] == jogadorAtual)
                return true;
            if (tabuleiro[0][i] == jogadorAtual && tabuleiro[1][i] == jogadorAtual && tabuleiro[2][i] == jogadorAtual)
                return true;
        }
        // Verificar diagonais
        if (tabuleiro[0][0] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][2] == jogadorAtual)
            return true;
        if (tabuleiro[0][2] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][0] == jogadorAtual)
            return true;

        return false;
    }
//verifica se tem campos vazios no campo
    public static boolean verificarEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void trocarJogador() {
        jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
    }

    public static String obterNomeJogadorAtual() {
        return (jogadorAtual == 'X') ? jogador1 : jogador2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar o nome dos jogadores
        System.out.println("Insira o nome do Jogador 1 (X): ");
        jogador1 = scanner.nextLine();
        System.out.println("Insira o nome do Jogador 2 (O): ");
        jogador2 = scanner.nextLine();

        boolean jogoAtivo = true;

        while (jogoAtivo) {
            exibirTabuleiro();
            System.out.println(obterNomeJogadorAtual() + " (" + jogadorAtual + "), insira sua jogada (linha e coluna): ");
            int linha = scanner.nextInt();
            int coluna = scanner.nextInt();
            //verificacoes de empate vitoria oujogo duplicado
            if (jogar(linha, coluna)) {
                if (verificarVitoria()) {
                    exibirTabuleiro();
                    System.out.println("Parabéns, " + obterNomeJogadorAtual() + "! Você venceu!");
                    jogoAtivo = false;
                } else if (verificarEmpate()) {
                    exibirTabuleiro();
                    System.out.println("O jogo empatou!");
                    jogoAtivo = false;
                } else {
                    trocarJogador();
                }
            } else {
                System.out.println("Jogada inválida. Jogue novamente em um campo ainda nao jogado.");
            }
        }
        scanner.close();
    }
}
