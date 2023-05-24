package codigo;

import java.util.LinkedList;
import java.util.List;

public class Espectador {
    private String Nome, Login, Senha;
    List<Midia> FuturaSerie;
    List<Midia> AssistidasSerie;
    List<Midia> FuturoFilme;
    List<Midia> AssistidasFilme;

    public Espectador(String Nome, String Login, String Senha) {
        this.Nome = Nome;
        this.Login = Login;
        this.Senha = Senha;
        this.FuturaSerie = new LinkedList<>();
        this.AssistidasSerie = new LinkedList<>();
        this.FuturoFilme = new LinkedList<>();
        this.AssistidasFilme = new LinkedList<>();
    }

    public void adicionarFuturaSerie(Midia serie) {
        FuturaSerie.add(serie);
    }

    public void adicionarAssistidasSerie(Midia serie) {
        AssistidasSerie.add(serie);
    }

    public void removerSerieFuturaSerie(Midia serie) {
        FuturaSerie.remove(serie);
    }

    public void adicionarFuturaFilme(Midia filme) {
        FuturoFilme.add(filme);
    }

    public void adicionarAssistidasFilme(Midia filme) {
        AssistidasFilme.add(filme);
    }

    public void removerSerieFuturaFilme(Midia filme) {
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