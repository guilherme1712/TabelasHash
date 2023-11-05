package avaliacaora3_1.listaEncadeada.bibliotecaCSV;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import java.io.FileOutputStream;
import java.io.IOException;

// import para fazer os numeros aleatórios
import java.util.Random;

public class Main {
    // Função utilitária para calcular o comprimento de um array
    public static int length(int[] array) {
        for (int element : array) {
            return element;
        }
        return 0;
    }

    public static void main(String[] args) {
        // Nome do arquivo Excel que será criado
        String excelFile = "resultsDobramento.xlsx";
        // Crie um novo arquivo Excel e uma planilha dentro dele
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Resultados");

        // Crie o cabeçalho da planilha
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Tamanho da Tabela", "Tamanho dos Dados", "Tempo de Inserção (ns)", "Número de Colisões", "Tempo de Busca (ns)", "Número de Comparações"};

        for (int col = 0; col < headers.length; col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(headers[col]);
        }

        long semente = 12345;
        Random random = new Random(semente);

        for (int i = 0; i < 5; i++) {  // Loop para iterar sobre diferentes cenários
            int tamanhoTabela = 0;
            int tamanhoDados = 0;

            switch (i) {
                case 0:
                    tamanhoTabela = 20200;
                    tamanhoDados = 20000;
                    break;
                case 1:
                    tamanhoTabela = 105000;
                    tamanhoDados = 100000;
                    break;
                case 2:
                    tamanhoTabela = 510000;
                    tamanhoDados = 500000;
                    break;
                case 3:
                    tamanhoTabela = 1020000;
                    tamanhoDados = 1000000;
                    break;
                case 4:
                    tamanhoTabela = 5010000;
                    tamanhoDados = 5000000;
                    break;
            }

            // Crie uma tabela de hash com o tamanho especificado
            TabelaHash tabela = new TabelaHash(tamanhoTabela);
            long colisoes = 0;
            long totalTempoInsercao = 0;
            long totalTempoBusca = 0;
            long comparacoes = 0;

            for (int j = 0; j < tamanhoDados; j++) {
                // Crie um registro com base em valores aleatórios
                Registro registro = new Registro(random);
                int codigo = registro.getCodigo();

                // Meça o tempo de inserção na tabela de hash
                long inicioInsercao = System.nanoTime();
                tabela.inserir(registro, codigo);
                long fimInsercao = System.nanoTime();
                totalTempoInsercao += (fimInsercao - inicioInsercao);

                for (int k = 0; k < 5; k++) {
                    // Realiza 5 buscas e mede o tempo de busca e o número de comparações
                    long inicioBusca = System.nanoTime();
                    Node buscaNaTabela = tabela.busca(codigo);
                    long fimBusca = System.nanoTime();
                    totalTempoBusca += (fimBusca - inicioBusca);
                    comparacoes += tabela.getComparacoes();
                }
                colisoes += tabela.getColisoes();
            }
            // Calcule os tempos médios de inserção e busca
            double tInsercaoMedio = (double) totalTempoInsercao / tamanhoDados;
            double tBuscaMedio = (double) totalTempoBusca / tamanhoDados;

            // Crie uma nova linha na planilha para cada conjunto de resultados
            Row dataRow = sheet.createRow(i + 1);
            dataRow.createCell(0).setCellValue(tamanhoTabela);
            dataRow.createCell(1).setCellValue(tamanhoDados);
            dataRow.createCell(2).setCellValue(tInsercaoMedio);
            dataRow.createCell(3).setCellValue(colisoes);
            dataRow.createCell(4).setCellValue(tBuscaMedio);
            dataRow.createCell(5).setCellValue(comparacoes);
        }

        try (FileOutputStream fileOut = new FileOutputStream(excelFile)) {
            // Salve o arquivo Excel
            workbook.write(fileOut);
            System.out.println("Resultados exportados para " + excelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
