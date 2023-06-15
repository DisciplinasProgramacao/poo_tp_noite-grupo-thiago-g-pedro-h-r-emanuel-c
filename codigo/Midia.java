package codigo;

import java.util.LinkedList;
import java.util.List;

public abstract class Midia {
    protected int id;
    protected String nome;
    protected String dataDeLancamento;
    protected String genero;
    protected String idioma;
    protected List<Avaliacao> avaliacoes;
    private float notaMedia = 0;
    private boolean lancamento;
    private int quantidadeVisualizacoes = 0;

    public Midia(int id, String nome, String dataDeLancamento, String genero, String idioma, boolean lancamento) {
        this.id = id;
        this.nome = nome;
        this.dataDeLancamento = dataDeLancamento;
        this.genero = genero;
        this.idioma = idioma;
        this.avaliacoes = new LinkedList<Avaliacao>();
        this.lancamento = lancamento;
    }

    public int retornaId() {
        return id;
    }

    public String retornaNome() {
        return nome.toLowerCase();
    }

    public String retornaGenero() {
        return genero.toLowerCase();
    }

    public String retornaIdioma() {
        return idioma.toLowerCase();
    }

    public boolean retornaLancamento() {
        return lancamento;
    }

    public String printaAvaliacao() {
        return "";
    }

    public void Avaliar(Avaliacao avaliacao) {
        this.avaliacoes.add(avaliacao);
    }

    public float retornaNotaMedia() {
        this.notaMedia = 0;
        for (Avaliacao avaliacao : avaliacoes) {
            this.notaMedia += avaliacao.retornaNota();
        }
        if (avaliacoes.size() == 0) {
            return 0;
        }
        return this.notaMedia /= avaliacoes.size();

    }

    public void printaMidia() {
        System.out.println("Id da Série: " + this.id + "\nNome da Série: " + this.nome + "\nData de Lançamento: "
                + this.dataDeLancamento + "\nNota média: "
                + (this.retornaNotaMedia() == 0 ? "Mídia não avaliada." : this.retornaNotaMedia()));
    }

    public String toString() {
        return "╔════════════════════════════════════════════╗\n   » Nome: " + nome + "\n   » Data de Lançamento: "
                + dataDeLancamento + "\n   » Gênero: " + genero + "\n   » Idioma: " + idioma
                + "\n╚════════════════════════════════════════════╝";
    }

    public int getQuantidadeVisualizacoes() {
        return quantidadeVisualizacoes;
    }

    public void adicionarVisualizacao() {
        quantidadeVisualizacoes++;
    }
}