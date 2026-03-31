// ==========================================
// ARQUIVO: JogoHanoi.java
// Nomes dos Integrantes e RA:
// - Valter Augusto Santos Narcizo - RA: 10736233
// - Pedro Henrique Sanchez de Souza - RA: 10737795
// ==========================================

import java.util.Scanner;

public class JogoHanoi {
    
    private Torre t1;
    private Torre t2;
    private Torre t3;
    private int numDiscos;
    private int jogadas;

    public JogoHanoi(int numDiscos) {
        this.numDiscos = numDiscos;
        configurarTorresIniciais();
    }

    private void configurarTorresIniciais() {
        this.t1 = new Torre("Torre 1", numDiscos);
        this.t2 = new Torre("Torre 2", numDiscos);
        this.t3 = new Torre("Torre 3", numDiscos);
        this.jogadas = 0;
        
        for (int i = numDiscos; i > 0; i--) {
            t1.insere(new Disco(i));
        }
    }

    // Recebe o Scanner por parametro para evitar vazamento de memoria (Resource leak)
    public void iniciar(Scanner sc) {
        int opcao = 0;
        
        while (opcao != 4) {
            System.out.println("\n--- MENU TORRE DE HANOI ---");
            System.out.println("1 - Mover disco");
            System.out.println("2 - Mostrar torres");
            System.out.println("3 - Reiniciar jogo");
            System.out.println("4 - Sair");
            System.out.print("Sua opcao: ");
            opcao = sc.nextInt();
            
            if (opcao == 1) {
                System.out.print("Mover da torre (1, 2 ou 3): ");
                int origem = sc.nextInt();
                System.out.print("Para a torre (1, 2 ou 3): ");
                int destino = sc.nextInt();
                
                Torre tOrigem = selecionaTorre(origem);
                Torre tDestino = selecionaTorre(destino);
                
                if (tOrigem != null && tDestino != null) {
                    if (tOrigem.isVazia()) {
                        System.out.println("Movimento invalido: Torre de origem vazia.");
                    } else {
                        Disco d = tOrigem.topo();
                        if (tDestino.insere(d)) {
                            tOrigem.remove();
                            jogadas++;
                            System.out.println("Disco movido com sucesso!");
                        } else {
                            System.out.println("Movimento invalido: Nao pode colocar disco maior sobre menor.");
                        }
                    }
                } else {
                    System.out.println("Torre invalida. Escolha 1, 2 ou 3.");
                }
                
            } else if (opcao == 2) {
                imprimirTorres();
                System.out.println("Jogadas ate agora: " + jogadas);
                
            } else if (opcao == 3) {
                configurarTorresIniciais();
                System.out.println("O jogo foi reiniciado.");
                
            } else if (opcao == 4) {
                System.out.println("Saindo...");
            } else {
                System.out.println("Opcao invalida.");
            }
        }
    }
    
    private Torre selecionaTorre(int num) {
        if (num == 1) return t1;
        if (num == 2) return t2;
        if (num == 3) return t3;
        return null;
    }
    
    // Metodo de impressao usando formataçao fixa (printf) para evitar colunas tortas
    private void imprimirTorres() {
        System.out.println();
        // Define dinamicamente o espacamento padrao das colunas (+4 garante uma margem segura)
        int larguraColuna = numDiscos + 4;
        String formato = "%-" + larguraColuna + "s"; // Alinhamento a esquerda com largura fixa
        
        for (int nivel = numDiscos - 1; nivel >= 0; nivel--) {
            String d1 = formataDisco(t1.getDiscoPorNivel(nivel));
            String d2 = formataDisco(t2.getDiscoPorNivel(nivel));
            String d3 = formataDisco(t3.getDiscoPorNivel(nivel));
            
            System.out.printf(formato + formato + formato + "%n", d1, d2, d3);
        }
        System.out.printf(formato + formato + formato + "%n", "TORRE 1", "TORRE 2", "TORRE 3");
    }
    
    // Retorna a string do disco pronta para ser impressa
    private String formataDisco(Disco d) {
        if (d == null) {
            return "|"; 
        }
        String asteriscos = "";
        for (int i = 0; i < d.getTamanho(); i++) {
            asteriscos += "*";
        }
        return asteriscos;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o numero de discos para o jogo: ");
        int numDiscos = sc.nextInt();
        
        JogoHanoi jogo = new JogoHanoi(numDiscos);
        jogo.iniciar(sc); // Passa o Scanner para o metodo
        
        sc.close(); // Fecha o recurso no final da execucao (resolve o Resource leak)
    }
}