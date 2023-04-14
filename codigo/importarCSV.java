package codigo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class importarCSV {
    String item1, item2, item3;

    public importarCSV(String item1, String item2, String item3) {
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }

    public List adicionaArquivo(String localArquivo) throws IOException {
        FileReader arq = new FileReader(localArquivo);
        BufferedReader lerArq = new BufferedReader(arq);
        String linha = lerArq.readLine();
        List<importarCSV> itemList = new ArrayList<importarCSV>();
        while (linha != null) {
            String tamanhoLinha = linha;
            String[] numero = tamanhoLinha.split(";");
            item1 = numero[0];
            item2 = numero[1];
            item3 = numero[3];
            importarCSV item = new importarCSV(item1, item2, item3);
            itemList.add(item);
            linha = lerArq.readLine();
        }
        arq.close();
        return itemList;
    }
}
