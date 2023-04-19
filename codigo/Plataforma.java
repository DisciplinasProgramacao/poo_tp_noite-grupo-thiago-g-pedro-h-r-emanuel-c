package codigo;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Plataforma {
    private List<Serie> listaSeries;
    private List<Audiencia> listaAudiencia;
    private List<Espectador> listaEspectadores;

    public Plataforma() throws IOException{
        this.listaSeries = new LinkedList<Serie>();
        this.listaSeries = carregaArqSerie();
        this.listaAudiencia = new LinkedList<Audiencia>();
        this.listaAudiencia = carregaArqAudiencia();
        this.listaEspectadores = new LinkedList<Espectador>();
        this.listaEspectadores = carregarArqEspectador();
    }

    public static List<Serie> carregaArqSerie() throws IOException{
        FileReader file = new FileReader("./docs/arquivos/POO_Series.csv");
        BufferedReader reading = new BufferedReader(file);
        String linha = reading.readLine();
        List<Serie> itemList = new LinkedList<Serie>();
        String idCod, nomeSerie, dataLancamento;
        while (linha != null) {
            idCod = linha.split(";")[0];
            nomeSerie = linha.split(";")[1];
            dataLancamento = linha.split(";")[2];
            Serie serie = new Serie(idCod, nomeSerie, dataLancamento);
            itemList.add(serie);
            linha = reading.readLine();
        }
        reading.close();
        file.close();
        return itemList;
    }

    public List<Audiencia> carregaArqAudiencia() throws IOException{
        FileReader file = new FileReader("./docs/arquivos/POO_Audiencia.csv");
        BufferedReader reading = new BufferedReader(file);
        String linha = reading.readLine();
        List<Audiencia> itemList = new LinkedList<Audiencia>();
        String idSerie = "";
        String Login = "";
        String Serie = "";
        List<Serie> SeriesAssistidas = new LinkedList<Serie>();
        List<Serie> SeriesFuturas = new LinkedList<Serie>();
        while (linha != null) {
            Login = linha.split(";")[0];
            Serie = linha.split(";")[1];
            idSerie = linha.split(";")[2];
            if (Serie.equals("A")){
                SeriesAssistidas.add(buscaSerie(idSerie));
            } else if (Serie.equals("F")){
                SeriesFuturas.add(buscaSerie(idSerie));
            }
            Audiencia audiencia = new Audiencia(idSerie, Login, SeriesAssistidas, SeriesFuturas);
            itemList.add(audiencia);
            linha = reading.readLine();
        }
        reading.close();
        file.close();
        return itemList;
    }

    public static List<Espectador> carregarArqEspectador() throws IOException{
        FileReader file = new FileReader("./docs/arquivos/POO_Espectadores.csv");
        BufferedReader reading = new BufferedReader(file);
        String linha = reading.readLine();
        List<Espectador> itemList = new LinkedList<Espectador>();
        String nome, login, senha;
        while (linha != null) {
            nome = linha.split(";")[0];
            login = linha.split(";")[1];
            senha = linha.split(";")[2];
            Espectador espectador = new Espectador(nome, login, senha);
            itemList.add(espectador);
            linha = reading.readLine();
        }
        reading.close();
        file.close();
        return itemList;
    }

    
    public Serie buscaSerie(String idSerie){
        if (idSerie != null){
            for (Serie serie : this.listaSeries) {
                if(serie.idSerie.equals(idSerie)){
                    return serie;
                }
            }
        }
        return null;
    }

    public void infoSerie(String idSerie){
        if (idSerie != null){
            for (Serie serie : this.listaSeries) {
                if(serie.idSerie.equals(idSerie)){
                    serie.printaSerie();
                }
            }
        }
    }
}
