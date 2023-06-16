package codigo;

/**
 * Classe que representa um filme.
 * Herda da classe Midia.
 */
public class Filme extends Midia {
    private int DuracaoMin;

    /**
     * Construtor da classe Filme.
     *
     * @param idFilme          o ID do filme
     * @param Nome             o nome do filme
     * @param DataDeLancamento a data de lançamento do filme
     * @param DuracaoMin       a duração em minutos do filme
     * @param Genero           o gênero do filme
     * @param Idioma           o idioma do filme
     * @param Lancamento       indica se o filme está em lançamento ou não
     */
    public Filme(int idFilme, String Nome, String DataDeLancamento, int DuracaoMin, String Genero, String Idioma, boolean Lancamento) {
        // Chama o construtor da classe pai (Midia) passando os parâmetros correspondentes
        super(idFilme, Nome, DataDeLancamento, Genero, Idioma, Lancamento);
        this.DuracaoMin = DuracaoMin;
    }
}