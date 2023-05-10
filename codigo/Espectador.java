package codigo;

import java.util.LinkedList;
import java.util.List;

public class Espectador {
    private String Nome, Login, Senha;
    List<Serie> FuturaSerie;
    List<Serie> AssistidasSerie;
    List<Filme> FuturoFilme;
    List<Filme> AssistidasFilme;

    public Espectador(String Nome, String Login, String Senha) {
        this.Nome = Nome;
        this.Login = Login;
        this.Senha = Senha;
        this.FuturaSerie = new LinkedList<>();
        this.AssistidasSerie = new LinkedList<>();
        this.FuturoFilme = new LinkedList<>();
        this.AssistidasFilme = new LinkedList<>();
    }

    public void adicionarFuturaSerie(Serie serie) {
        FuturaSerie.add(serie);
    }

    public void adicionarAssistidasSerie(Serie serie) {
        AssistidasSerie.add(serie);
    }

    public void removerSerieFuturaSerie(Serie serie) {
        FuturaSerie.remove(serie);
    }

    public void adicionarFuturaFilme(Filme filme) {
        FuturoFilme.add(filme);
    }

    public void adicionarAssistidasFilme(Filme filme) {
        AssistidasFilme.add(filme);
    }

    public void removerSerieFuturaFilme(Filme filme) {
        FuturoFilme.remove(filme);
    }

    public String retornaSenha() {
        return this.Senha;
    }

    public String retornaNome() {
        return this.Nome;
    }

    public String retornaLogin() {
        return this.Login;
    }

}