package codigo;

public class Filme extends Midia{
    private int DuracaoMin;

    public Filme(int idFilme, String Nome, String DataDeLancamento, int DuracaoMin, String Genero, String Idioma, boolean Lancamento) {
        super(idFilme, Nome, DataDeLancamento, Genero, Idioma, Lancamento);
        this.DuracaoMin = DuracaoMin;
    }
}
