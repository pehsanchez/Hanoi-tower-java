// ==========================================
// ARQUIVO: Torre.java
// Nomes dos Integrantes e RA:
// - Valter Augusto Santos Narcizo - RA: 10736233
// - Pedro Henrique Sanchez de Souza - RA: 10737795
// ==========================================

public class Torre {
    private Pilha discos;
    private String nome;

    public Torre(String nome, int maxDiscos) {
        this.nome = nome;
        this.discos = new Pilha(maxDiscos);
    }

    // Resolve o aviso (warning) do VS Code sobre o atributo 'nome' nao ser utilizado
    public String getNome() {
        return nome;
    }

    public boolean insere(Disco d) {
        if (discos.isEmpty() || discos.peek().getTamanho() > d.getTamanho()) {
            discos.push(d);
            return true;
        }
        return false; 
    }
    
    public Disco remove() {
        return discos.pop();
    }

    public Disco topo() {
        return discos.peek();
    }

    public int getTotal() {
        return discos.getQtde();
    }

    public boolean isVazia() {
        return discos.isEmpty();
    }

    public Disco getDiscoPorNivel(int nivel) {
        return discos.getDisco(nivel);
    }
}