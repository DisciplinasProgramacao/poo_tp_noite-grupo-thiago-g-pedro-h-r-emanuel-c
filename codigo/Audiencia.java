package codigo;

import java.util.*;

public class Audiencia {
    private int IdSerie;
    private String Login;
    private List<Serie> SeriesAssistidas, SeriesFuturas;

    public Audiencia(int IdSerie, String Login){
        this.IdSerie = IdSerie;
        this.Login = Login;
        List<Serie> SeriesAssistidas = new LinkedList<Serie>();
        List<Serie> SeriesFuturas = new LinkedList<Serie>();
    }
}