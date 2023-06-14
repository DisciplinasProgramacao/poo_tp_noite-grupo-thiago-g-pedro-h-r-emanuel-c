package codigo;

public class PerfilEspecialista implements IPerfilEspectador {
    final private String tipo = "Especialista";
    final private boolean podeComentar = true;
    final private boolean podeAssistirLancamento = false;

    public PerfilEspecialista(){}

    @Override
    public boolean podeComentar() {
        return this.podeComentar;
    }

    @Override
    public String retornaTipo() {
        return this.tipo;
    }

    @Override
    public boolean podeAssistirLancamento(){
        return this.podeAssistirLancamento;
    }
}
