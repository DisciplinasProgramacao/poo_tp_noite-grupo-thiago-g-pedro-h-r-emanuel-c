package codigo;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    public Midia(int id, String nome, String dataDeLancamento, String genero, String idioma) {
        this.id = id;
        this.nome = nome;
        this.dataDeLancamento = dataDeLancamento;
        this.genero = genero;
        this.idioma = idioma;
        this.avaliacoes = new LinkedList<Avaliacao>();
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
        return this.notaMedia /= avaliacoes.size();

    }

    public void printaMidia() {
        System.out.println("Id da Série: " + this.id + "\nNome da Série: " + this.nome + "\nData de Lançamento: "
                + this.dataDeLancamento + "\nNota média: " + this.retornaNotaMedia());
    }

    public String toString() {
        return "Nome: " + nome;
    }
}