package codigo;

/**
 * A classe PerfilProfissional implementa a interface IPerfilEspectador e define
 * um perfil de espectador profissional.
 */
public class PerfilProfissional implements IPerfilEspectador {

    /**
     * O tipo de perfil do espectador.
     */
    private final String tipo = "Profissional";

    /**
     * Indica se o espectador profissional pode comentar.
     */
    private final boolean podeComentar = true;

    /**
     * Indica se o espectador profissional pode assistir a lançamentos.
     */
    private final boolean podeAssistirLancamento = true;

    /**
     * Construtor padrão da classe PerfilProfissional.
     */
    public PerfilProfissional() {
    }

    /**
     * Verifica se o espectador profissional pode comentar.
     * 
     * @return true se o espectador pode comentar, false caso contrário.
     */
    @Override
    public boolean podeComentar() {
        return this.podeComentar;
    }

    /**
     * Retorna o tipo de perfil do espectador.
     * 
     * @return o tipo de perfil do espectador.
     */
    @Override
    public String retornaTipo() {
        return this.tipo;
    }

    /**
     * Verifica se o espectador profissional pode assistir a lançamentos.
     * 
     * @return true se o espectador pode assistir a lançamentos, false caso
     *         contrário.
     */
    @Override
    public boolean podeAssistirLancamento() {
        return this.podeAssistirLancamento;
    }
}
