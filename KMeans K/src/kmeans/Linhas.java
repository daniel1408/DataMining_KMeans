package kmeans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Linhas {

    public ArrayList<Coordenadas> Linha() {

        ArrayList<Coordenadas> texto = new ArrayList<>();
        String nomeArquivo = "c:/dataset.txt";
        String linha;

        File arq = new File(nomeArquivo);
        if (arq.exists()) {
            try {
                FileReader leitorDeArquivo = new FileReader(nomeArquivo);
                BufferedReader bufferDoArquivo = new BufferedReader(leitorDeArquivo);

                while (bufferDoArquivo.ready()) {

                    linha = bufferDoArquivo.readLine();
                    
                    Coordenadas t = new Coordenadas(linha);
                    texto.add(t);
                }
            } catch (Exception e) {
                System.out.println("ERRO NO ARQUIVO\n\n");
            }
        }
        return texto;
    }
}
