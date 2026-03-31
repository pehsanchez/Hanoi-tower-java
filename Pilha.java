// ==========================================
// ARQUIVO: Pilha.java
// Nomes dos Integrantes e RA:
// - Valter Auguto Santos Narcizo - RA: 10736233
// - Pedro Henrique Sanchez de Souza - RA: 10737795
// ==========================================

public class Pilha {
    private Disco[] dados; 
    private int topo;
    private int max; 

    public Pilha(int tamanhoMaximo) {
        this.max = tamanhoMaximo;
        this.dados = new Disco[max];
        this.topo = -1; // -1 indica que a pilha está inicialmente vazia
    }

    // Empilha um novo disco
    public void push(Disco d) {
        if (topo < max - 1) {
            topo++;
            dados[topo] = d;
        } else {
            System.out.println("Erro: Pilha cheia!");
        }
    }

    // Desempilha e retorna o disco do topo
    public Disco pop() {
        if (topo >= 0) {
            Disco removido = dados[topo];
            dados[topo] = null; // Limpa a referência no array
            topo--;
            return removido;
        }
        return null;
    }
    
    // Retorna o disco do topo sem remover
    public Disco peek() {
        if (topo >= 0) {
            return dados[topo];
        }
        return null;
    }
    
    // Verifica se a estrutura está vazia
    public boolean isEmpty() {
        return topo == -1;
    }

    // Retorna a quantidade de elementos armazenados
    public int getQtde() {
        return topo + 1;
    }

    // Método auxiliar para acesso direto (usado apenas para a renderização visual)
    public Disco getDisco(int index) {
        if (index >= 0 && index <= topo) {
            return dados[index];
        }
        return null;
    }
}