package codigo;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Espectador {
    private String Nome, Login, Senha;
    List<Midia> MidiasFuturas;
    List<Midia> midiasAssistidas;
    List<Avaliacao> avaliacoesEspectador;
    private IPerfilEspectador perfil;

    public Espectador(String Nome, String Login, String Senha) {
        this.Nome = Nome;
        this.Login = Login;
        this.Senha = Senha;
        this.MidiasFuturas = new LinkedList<>();
        this.midiasAssistidas = new LinkedList<>();
        this.avaliacoesEspectador = new LinkedList<>();
        this.perfil = new PerfilRegular();
    }

    public void atualizaPerfil() {
        int cont = 0;
        Data dataHoje = new Data();
        for (Avaliacao avaliacao : avaliacoesEspectador) {
            if (dataHoje.retornaMes() != 1) {
                if (avaliacao.retornaData().retornaMes() == dataHoje.retornaMes() - 1) {
                    cont++;
                }
            } else {
                if (avaliacao.retornaData().retornaMes() == dataHoje.retornaMes() + 11
                        && avaliacao.retornaData().retornaAno() == dataHoje.retornaAno() - 1) {
                    cont++;
                }
            }
        }
        if(cont >= 5){
            this.perfil = new PerfilEspecialista();
        } else {
            this.perfil = new PerfilRegular();
        }
    }

    public String listaAvaliacoesToString(){
        StringBuilder sb = new StringBuilder();
        List<Avaliacao> lista_retorno = this.avaliacoesEspectador.stream()
        .collect(Collectors.toList());
        lista_retorno.forEach(e -> sb.append(e.toStringRegular()).append(System.lineSeparator()));
        return sb.toString();
    }

    public void adicionarAvaliacaoEspectador(Avaliacao avaliacao) {
        this.avaliacoesEspectador.add(avaliacao);
    }

    public IPerfilEspectador retornaPerfil() {
        return this.perfil;
    }

    public boolean adicionarMidiasFuturas(Midia midia) {
        boolean adicionada = false;
        for (Midia midiaFor : this.MidiasFuturas) {
            if (midiaFor.retornaNome().toLowerCase().equals(midia.retornaNome().toLowerCase())) {
                MidiasFuturas.add(midia);
                adicionada = true;
            }
        }
        return adicionada;
    }

    public boolean adicionarmidiasAssistidas(Midia midia) {
        boolean adicionada = false;
        for (Midia midiaFor : this.midiasAssistidas) {
            if (midiaFor.retornaNome().toLowerCase().equals(midia.retornaNome().toLowerCase())) {
                midiasAssistidas.add(midia);
                adicionada = true;
            }
        }
        return adicionada;
    }

    public boolean removerMidiaFuturas(Midia midia) {
        boolean remover = false;
        for (Midia midiaFor : this.MidiasFuturas) {
            if (midiaFor.retornaNome().toLowerCase().equals(midia.retornaNome().toLowerCase())) {
                MidiasFuturas.remove(midia);
                remover = true;
            }
        }
        return remover;
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

    public boolean jaAssistiu(Midia midia) {
        return this.midiasAssistidas.contains(midia);
    }

    public boolean jaAvaliou(int idMidia){
        long tem = this.avaliacoesEspectador.stream()
        .filter(e -> e.retornaIdMidiaAvaliada() == idMidia)
        .count();
        if(tem == 1){
            return true;
        }
        return false;
    }

}