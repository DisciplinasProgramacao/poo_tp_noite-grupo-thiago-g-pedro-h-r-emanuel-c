package codigo;

/**
 * Classe que representa uma série de mídia.
 * Herda da classe Midia.
 */
public class Serie extends Midia {

    /**
     * Construtor da classe Serie.
     * 
     * @param idSerie          o ID da série
     * @param Nome             o nome da série
     * @param DataDeLancamento a data de lançamento da série
     * @param Genero           o gênero da série
     * @param Idioma           o idioma da série
     * @param Lancamento       indica se a série está em lançamento ou não
     */
    public Serie(int idSerie, String Nome, String DataDeLancamento, String Genero, String Idioma, boolean Lancamento) {
        // Chama o construtor da classe pai (Midia) passando os parâmetros correspondentes
        super(idSerie, Nome, DataDeLancamento, Genero, Idioma, Lancamento);
    }
}