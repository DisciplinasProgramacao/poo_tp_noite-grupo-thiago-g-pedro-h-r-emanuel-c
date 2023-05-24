package codigo;

import java.util.LinkedList;
import java.util.List;

public class Espectador {
    private String Nome, Login, Senha;
    List<Midia> MidiasFuturas;
    List<Midia> midiasAssistidas;

    public Espectador(String Nome, String Login, String Senha) {
        this.Nome = Nome;
        this.Login = Login;
        this.Senha = Senha;
        this.MidiasFuturas = new LinkedList<>();
        this.midiasAssistidas = new LinkedList<>();
    }

    public void adicionarMidiasFuturas(Midia midia) {
        MidiasFuturas.add(midia);
    }

    public void adicionarmidiasAssistidas(Midia midia) {
        midiasAssistidas.add(midia);
    }

    public void removerMidiaFuturas(Midia midia) {
        MidiasFuturas.remove(midia);
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