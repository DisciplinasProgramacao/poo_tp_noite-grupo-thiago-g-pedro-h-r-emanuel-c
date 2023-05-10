package codigo;

public class Filme extends Midia{
    private int DuracaoMin;

    public Filme(int idFilme, String Nome, String DataDeLancamento, int DuracaoMin, String Genero, String Idioma) {
        super(idFilme, Nome, DataDeLancamento, Genero, Idioma);
        this.DuracaoMin = DuracaoMin;
    }

    public int retornaId() {
        return this.id;
    }

    public String retornaNome() {
        return this.Nome;
    }

    public void printaMidia() {
        System.out.println("Id do Filme: " + this.id + "\nNome do Filme: " + this.Nome + "\nData de Lançamento: " + this.DataDeLancamento + "Duração: " + this.DuracaoMin);
    }
}
