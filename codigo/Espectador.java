package codigo;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe que representa um espectador.
 */
public class Espectador {
    private String Nome, Login, Senha;
    List<Midia> MidiasFuturas;
    List<Midia> MidiasAssistidas;
    List<Avaliacao> avaliacoesEspectador;
    private IPerfilEspectador perfil;

    /**
     * Construtor da classe Espectador.
     *
     * @param Nome      o nome do espectador
     * @param Login     o login do espectador
     * @param Senha     a senha do espectador
     * @param profissao indica se o espectador é um profissional ou não
     */
    public Espectador(String Nome, String Login, String Senha, Boolean profissao) {
        this.Nome = Nome;
        this.Login = Login;
        this.Senha = Senha;
        this.MidiasFuturas = new LinkedList<>();
        this.MidiasAssistidas = new LinkedList<>();
        this.avaliacoesEspectador = new LinkedList<>();
        this.perfil = profissao == true ? new PerfilProfissional() : new PerfilRegular();
    }

    /**
     * Atualiza o perfil do espectador com base em suas avaliações recentes.
     * Se o espectador tiver pelo menos 5 avaliações no mês anterior, seu perfil
     * será atualizado para "PerfilEspecialista".
     * Caso contrário, seu perfil será atualizado para "PerfilRegular".
     */
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
        if (cont >= 5) {
            this.perfil = new PerfilEspecialista();
        } else {
            this.perfil = new PerfilRegular();
        }
    }

    /**
     * Converte a lista de avaliações do espectador em uma representação de string.
     *
     * @return uma string contendo as avaliações do espectador
     */
    public String listaAvaliacoesToString() {
        StringBuilder sb = new StringBuilder();
        List<Avaliacao> listaRetorno = this.avaliacoesEspectador.stream()
                .collect(Collectors.toList());
        if (!this.perfil.podeAssistirLancamento()){
            listaRetorno.forEach(e -> sb.append("\n").append(e.toStringRegular()));
        } else {
            listaRetorno.forEach(e -> sb.append("\n").append(e.toStringEspecialista()));
        }
        return sb.toString();
    }

    /**
     * Adiciona uma avaliação do espectador.
     *
     * @param avaliacao a avaliação a ser adicionada
     */
    public void adicionarAvaliacaoEspectador(Avaliacao avaliacao) {
        this.avaliacoesEspectador.add(avaliacao);
    }

    /**
     * Retorna o perfil atual do espectador.
     *
     * @return o perfil do espectador
     */
    public IPerfilEspectador retornaPerfil() {
        return this.perfil;
    }

    /**
     * Retorna a quantidade de mídias assistidas pelo espectador.
     *
     * @return a quantidade de mídias assistidas
     */
    public int retornaQuantidadeMidiaAssistida() {
        return MidiasAssistidas.size();
    }

    /**
     * Retorna a quantidade de avaliações feitas pelo espectador.
     *
     * @return a quantidade de avaliações
     */
    public int retornaQuantidadeAvaliacao() {
        return avaliacoesEspectador.size();
    }

    /**
     * Adiciona uma mídia à lista de mídias futuras do espectador.
     *
     * @param midia a mídia a ser adicionada
     */
    public void adicionarMidiasArquivoFuturas(Midia midia) {
        if (midia.retornaLancamento() == false) {
            MidiasFuturas.add(midia);
        } else if (midia.retornaLancamento() == true && this.perfil.podeAssistirLancamento() == true) {
            MidiasFuturas.add(midia);
        }
    }

    /**
     * Adiciona uma mídia à lista de mídias assistidas do espectador.
     * Incrementa o número de visualizações da mídia.
     *
     * @param midia a mídia a ser adicionada
     */
    public void adicionarMidiasArquivoAssistidas(Midia midia) {
        if (midia.retornaLancamento() == false) {
            MidiasAssistidas.add(midia);
            midia.adicionarVisualizacao();
        } else if (midia.retornaLancamento() == true && this.perfil.podeAssistirLancamento() == true) {
            MidiasAssistidas.add(midia);
            midia.adicionarVisualizacao();
        }
    }

    /**
     * Adiciona uma mídia à lista de mídias futuras do espectador.
     * Verifica se a mídia já existe na lista antes de adicionar.
     *
     * @param midia a mídia a ser adicionada
     * @return true se a mídia foi adicionada, false caso contrário
     */
    public boolean adicionarMidiaMenuFuturas(Midia midia) {
        boolean adicionada = false;
        boolean encontrada = false;

        for (Midia midiaFor : this.MidiasFuturas) {
            if (midiaFor.retornaNome().equalsIgnoreCase(midia.retornaNome())) {
                encontrada = true;
                break;
            }
        }

        if (!encontrada) {
            this.MidiasFuturas.add(midia);
            adicionada = true;
        }

        return adicionada;
    }

    /**
     * Adiciona uma mídia à lista de mídias assistidas do espectador.
     * Incrementa o número de visualizações da mídia.
     * Verifica se a mídia já existe na lista antes de adicionar.
     *
     * @param midia a mídia a ser adicionada
     * @return true se a mídia foi adicionada, false caso contrário
     */
    public boolean adicionarMidiaMenuAssistidas(Midia midia) {
        boolean adicionada = false;
        boolean encontrada = false;

        for (Midia midiaFor : this.MidiasAssistidas) {
            if (midiaFor.retornaNome().equalsIgnoreCase(midia.retornaNome())) {
                encontrada = true;
                break;
            }
        }

        if (!encontrada) {
            this.MidiasAssistidas.add(midia);
            midia.adicionarVisualizacao();
            adicionada = true;
        }

        return adicionada;
    }

    /**
     * Remove uma mídia da lista de mídias futuras do espectador.
     *
     * @param midia a mídia a ser removida
     * @return true se a mídia foi removida, false caso contrário
     */
    public boolean removerMidiaFuturas(Midia midia) {
        boolean remover = false;
        for (Midia midiaFor : this.MidiasFuturas) {
            if (midiaFor.retornaNome().equalsIgnoreCase(midia.retornaNome())) {
                MidiasFuturas.remove(midia);
                remover = true;
            }
        }
        return remover;
    }

    /**
     * Retorna a senha do espectador.
     *
     * @return a senha do espectador
     */
    public String retornaSenha() {
        return this.Senha;
    }

    /**
     * Retorna o nome do espectador.
     *
     * @return o nome do espectador
     */
    public String retornaNome() {
        return this.Nome;
    }

    /**
     * Retorna o login do espectador.
     *
     * @return o login do espectador
     */
    public String retornaLogin() {
        return this.Login;
    }

    /**
     * Verifica se o espectador já assistiu a uma determinada mídia.
     *
     * @param midia a mídia a ser verificada
     * @return true se o espectador já assistiu à mídia, false caso contrário
     */
    public boolean jaAssistiu(Midia midia) {
        return this.MidiasAssistidas.contains(midia);
    }

    /**
     * Verifica se o espectador já avaliou uma mídia com base no seu ID.
     *
     * @param idMidia o ID da mídia a ser verificada
     * @return true se o espectador já avaliou a mídia, false caso contrário
     */
    public boolean jaAvaliou(int idMidia) {
        long tem = this.avaliacoesEspectador.stream()
                .filter(e -> e.retornaIdMidiaAvaliada() == idMidia)
                .count();
        if (tem == 1) {
            return true;
        }
        return false;
    }

}