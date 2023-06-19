package codigo;

import java.util.LinkedList;
import java.util.List;

public abstract class Midia {
    // Atributos da classe Midia
    protected int id; // ID da mídia
    protected String nome; // Nome da mídia
    protected String dataDeLancamento; // Data de lançamento da mídia
    protected String genero; // Gênero da mídia
    protected String idioma; // Idioma da mídia
    protected List<Avaliacao> avaliacoes; // Lista de avaliações da mídia
    private float notaMedia = 0; // Nota média da mídia
    private boolean lancamento; // Indica se a mídia é um lançamento
    private int quantidadeVisualizacoes = 0; // Quantidade de visualizações da mídia
    private int quantidadeAvaliacoes = 0; // Quantidade de avaliações da mídia

    // Construtor da classe Midia
    public Midia(int id, String nome, String dataDeLancamento, String genero, String idioma, boolean lancamento) {
        this.id = id;
        this.nome = nome;
        this.dataDeLancamento = dataDeLancamento;
        this.genero = genero;
        this.idioma = idioma;
        this.avaliacoes = new LinkedList<Avaliacao>();
        this.lancamento = lancamento;
    }

    // Métodos da classe Midia

    // Retorna o ID da mídia
    public int retornaId() {
        return id;
    }

    // Retorna o nome da mídia em letras minúsculas
    public String retornaNome() {
        return nome.toLowerCase();
    }

    // Retorna o gênero da mídia em letras minúsculas
    public String retornaGenero() {
        return genero.toLowerCase();
    }

    // Retorna o idioma da mídia em letras minúsculas
    public String retornaIdioma() {
        return idioma.toLowerCase();
    }

    // Retorna se a mídia é um lançamento
    public boolean retornaLancamento() {
        return lancamento;
    }

    // Retorna a quantidade de avaliações da mídia
    public int retornaTemAvaliacoes() {
        return quantidadeAvaliacoes;
    }

    // Avalia a mídia com uma avaliação específica
    public void Avaliar(Avaliacao avaliacao) {
        this.avaliacoes.add(avaliacao);
        quantidadeAvaliacoes++;
    }

    // Retorna a nota média da mídia
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

    // Retorna uma representação em string da mídia
    @Override
    public String toString() {
        return "╔════════════════════════════════════════════╗" +
            "\n   » Id da Série: " + this.id +
            "\n   » Nome: " + nome +
            "\n   » Data de Lançamento: " + dataDeLancamento +
            "\n   » Gênero: " + genero +
            "\n   » Idioma: " + idioma +
            "\n   » Nota média: " + (this.retornaNotaMedia() == 0 ? "Mídia não avaliada." : this.retornaNotaMedia()) +
        "\n╚════════════════════════════════════════════╝";
    }

    // Retorna a quantidade de visualizações da mídia
    public int getQuantidadeVisualizacoes() {
        return quantidadeVisualizacoes;
    }

    // Adiciona uma visualização à mídia
    public void adicionarVisualizacao() {
        quantidadeVisualizacoes++;
    }
}