package codigo;

/**
 * A classe PerfilEspecialista implementa a interface IPerfilEspectador e define
 * um perfil de espectador especialista.
 */
public class PerfilEspecialista implements IPerfilEspectador {

    /**
     * O tipo de perfil do espectador.
     */
    private final String tipo = "Especialista";

    /**
     * Indica se o espectador especialista pode comentar.
     */
    private final boolean podeComentar = true;

    /**
     * Indica se o espectador especialista pode assistir a lançamentos.
     */
    private final boolean podeAssistirLancamento = false;

    /**
     * Construtor padrão da classe PerfilEspecialista.
     */
    public PerfilEspecialista() {
    }

    /**
     * Verifica se o espectador especialista pode comentar.
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
     * Verifica se o espectador especialista pode assistir a lançamentos.
     * 
     * @return true se o espectador pode assistir a lançamentos, false caso
     *         contrário.
     */
    @Override
    public boolean podeAssistirLancamento() {
        return this.podeAssistirLancamento;
    }
}
