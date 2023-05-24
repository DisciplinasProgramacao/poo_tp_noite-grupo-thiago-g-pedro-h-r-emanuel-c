package codigo;

import java.util.ArrayList;
import java.util.List;

public abstract class Midia {
    protected int id;
    protected String nome;
    protected String dataDeLancamento;
    protected String genero;
    protected String idioma;
    protected Avaliacao avaliacao;

    public enum Avaliacao {
        NAO_AVALIADO,
        UMA_ESTRELA,
        DUAS_ESTRELAS,
        TRES_ESTRELAS,
        QUATRO_ESTRELAS,
        CINCO_ESTRELAS
    }

    public Midia(int id, String nome, String dataDeLancamento, String genero, String idioma) {
        this.id = id;
        this.nome = nome;
        this.dataDeLancamento = dataDeLancamento;
        this.genero = genero;
        this.idioma = idioma;
        this.avaliacao = Avaliacao.NAO_AVALIADO;
    }

    public int retornaId() {
        return id;
    }

    public String retornaNome() {
        return nome;
    }

    public String retornaGenero() {
        return genero;
    }

    public String retornaIdioma() {
        return idioma;
    }

    public Avaliacao retornaAvaliacao() {
        return avaliacao;
    }

    public void atribuirAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void printaMidia() {
        System.out.println("Id da Série: " + this.id + "\nNome da Série: " + this.nome + "\nData de Lançamento: " + this.dataDeLancamento + "\nAvaliação: " + avaliacao);
    }

    public String toString() {
        return "Nome: " + nome;
    }
}