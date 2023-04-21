package codigo;

import java.util.LinkedList;
import java.util.List;

public class Espectador {
    private String Nome, Login, Senha;
    List<Serie> Futura;
    List<Serie> Assistidas;

    public Espectador(String Nome, String Login, String Senha) {
        this.Nome = Nome;
        this.Login = Login;
        this.Senha = Senha;
        this.Futura = new LinkedList<>();
        this.Assistidas = new LinkedList<>();
    }

    public void adicionarFutura(Serie serie) {
        Futura.add(serie);
    }

    public void adicionarAssistidas(Serie serie) {
        Assistidas.add(serie);
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