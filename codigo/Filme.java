package codigo;

public class Filme extends Midia{
    private int DuracaoMin;

    public Filme(int idFilme, String Nome, String DataDeLancamento, int DuracaoMin, String Genero, String Idioma) {
        super(idFilme, Nome, DataDeLancamento, Genero, Idioma);
        this.DuracaoMin = DuracaoMin;
    }
}
