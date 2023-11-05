package avaliacaora3_1.listaEncadeada.bibliotecaCSV;
import java.util.Random;

public class Registro {
    private int codigo;  // Armazena um código associado ao registro

    // Construtor que permite definir um código personalizado
    public Registro(int codigo) {
        this.codigo = codigo;
    }

    // Construtor que gera um código aleatório usando um objeto Random
    public Registro(Random random) {
        this.codigo = random.nextInt(1000000000);  // Gera um código aleatório de 0 a 999,999,999
    }

    // Método para obter o código associado ao registro
    public int getCodigo() {
        return codigo;
    }
}
