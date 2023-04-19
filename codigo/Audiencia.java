package codigo;

import java.util.*;

public class Audiencia {
    private String idSerie;
    private String Login;
    private List<Serie> SeriesAssistidas, SeriesFuturas;

    public Audiencia(String idSerie, String Login, List<Serie> SeriesAssistidas, List<Serie> SeriesFuturas) {
        this.idSerie = idSerie;
        this.Login = Login;
        this.SeriesAssistidas = new LinkedList<Serie>();
        this.SeriesFuturas = new LinkedList<Serie>();
        this.SeriesAssistidas = SeriesAssistidas;
        this.SeriesFuturas = SeriesFuturas;
    }

    public void adicionarListaA(Serie Serie){
        this.SeriesAssistidas.add(Serie);
    }
    
    public void adicionarListaF(Serie Serie){
        this.SeriesFuturas.add(Serie);
    }
}