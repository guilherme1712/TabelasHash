package avaliacaora3_1.listaEncadeada.bibliotecaCSV;

public class TabelaHash {
    private int tamanho;         // Tamanho da tabela hash
    private Node[] tabela;       // Array de nós para armazenar os elementos
    private long comparacoes;    // Contador de comparações durante a busca
    private int colisoes;        // Contador de colisões durante as inserções

    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        tabela = new Node[tamanho];
        comparacoes = 0;
        colisoes = 0;
    }

    // Método para inserir um registro na tabela hash
    public void inserir(Registro registro, int codigo) {
        int index = multiplicacao(codigo);  // Calcula o índice usando a função de dobramento
        if (tabela[index] == null) {
            // Se a posição na tabela estiver vazia, crie um novo nó e insira o registro
            tabela[index] = new Node();
            tabela[index].setInformacao(registro);
        } else {
            // Se a posição já estiver ocupada, tratamos colisões
            Node novoNode = new Node();
            novoNode.setInformacao(registro);
            Node anterior = null;
            Node atual = tabela[index];
            
            // Percorre a lista encadeada para encontrar a posição correta para inserir o novo registro
            while (atual != null && atual.getInformacao().getCodigo() < codigo) {
                anterior = atual;
                atual = atual.getProximo();
            }

            // Insere o novo registro na posição correta da lista encadeada
            if (anterior == null) {
                // O novo registro se torna o primeiro na lista encadeada
                novoNode.setProximo(tabela[index]);
                tabela[index] = novoNode;
            } else {
                // Inserção entre nós existentes na lista encadeada
                anterior.setProximo(novoNode);
                novoNode.setProximo(atual);
            }
            colisoes++; // Aumenta o contador de colisões
        }
    }

    // Método para buscar um registro na tabela hash
    public Node busca(int codigo) {
        int index = multiplicacao(codigo);  // Calcula o índice usando a função de dobramento
        Node atual = tabela[index];
        while (atual != null) {
            comparacoes++; // Incrementa o contador de comparações
            if (atual.getInformacao().getCodigo() == codigo) {
                return atual;  // Retorna o nó se o código for encontrado
            }
            atual = atual.getProximo();
        }
        return null;  // Retorna null se o código não for encontrado
    }
    
    // Método para calcular o índice usando o operador de resto da divisão
    public int restoDivisao(int codigo) {
        return codigo % tamanho; // Calcula o índice na tabela usando o operador de resto da divisão
    }

    // Método para calcular o índice usando a função de multiplicação
    public int multiplicacao(int codigo) {
        double A = 0.6180339887; // Um número em ponto flutuante entre 0 e 1
        double valor = codigo * A;
        double parteFracionaria = valor - (int) valor; // Pega a parte fracionária
        return (int) (tamanho * parteFracionaria);  // Calcula o índice da tabela usando multiplicação
    }

    // Método para calcular o índice usando o operador de dobramento com deslocamento
    public int dobramento(int codigo) {
        int soma = 10;
        int p1 = codigo >> soma;
        int p2 = codigo & (tamanho-1);
        int index = (p1 + p2) % tamanho; // Usado o operador de módulo para obter um índice válido
        return index;
    }

    // Método para obter o número total de comparações durante as operações de busca
    public long getComparacoes() {
        return comparacoes;
    }

    // Método para obter o número total de colisões durante as operações de inserção
    public int getColisoes() {
        return colisoes;
    }
}
