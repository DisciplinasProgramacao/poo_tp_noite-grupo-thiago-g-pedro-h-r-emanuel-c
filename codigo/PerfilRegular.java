package codigo;

/**
 * A classe PerfilRegular implementa a interface IPerfilEspectador e define um perfil de espectador regular.
 */
public class PerfilRegular implements IPerfilEspectador {
    
    /**
     * O tipo de perfil do espectador.
     */
    private final String tipo = "Regular";
    
    /**
     * Indica se o espectador regular pode comentar.
     */
    private final boolean podeComentar = false;
    
    /**
     * Indica se o espectador regular pode assistir a lançamentos.
     */
    private final boolean podeAssistirLancamento = false;

    /**
     * Construtor padrão da classe PerfilRegular.
     */
    public PerfilRegular() {}

    /**
     * Verifica se o espectador regular pode comentar.
     * @return true se o espectador pode comentar, false caso contrário.
     */
    @Override
    public boolean podeComentar() {
        return this.podeComentar;
    }

    /**
     * Retorna o tipo de perfil do espectador.
     * @return o tipo de perfil do espectador.
     */
    @Override
    public String retornaTipo() {
        return this.tipo;
    }

    /**
     * Verifica se o espectador regular pode assistir a lançamentos.
     * @return true se o espectador pode assistir a lançamentos, false caso contrário.
     */
    @Override
    public boolean podeAssistirLancamento() {
        return this.podeAssistirLancamento;
    }
}
