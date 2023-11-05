package avaliacaora3_1.listaEncadeada.bibliotecaCSV;

public class Node {
    private Registro informacao;  // Armazena informações associadas ao nó
    private Node proximo;  // Mantém uma referência ao próximo nó na lista encadeada

    // Construtor padrão que inicializa as informações e a referência ao próximo nó como nulos
    public Node() {
        this.informacao = null;
        this.proximo = null;
    }

    // Método para obter as informações associadas ao nó
    public Registro getInformacao() {
        return informacao;
    }

    // Método para definir as informações associadas ao nó
    public void setInformacao(Registro informacao) {
        this.informacao = informacao;
    }

    // Método para obter a referência ao próximo nó na lista encadeada
    public Node getProximo() {
        return proximo;
    }

    // Método para definir a referência ao próximo nó na lista encadeada
    public void setProximo(Node proximo) {
        this.proximo = proximo;
    }
}
