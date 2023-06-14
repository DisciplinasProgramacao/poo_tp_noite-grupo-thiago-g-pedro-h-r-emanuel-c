package codigo;

public class PerfilRegular implements IPerfilEspectador {
    final private String tipo = "Regular";
    final private boolean podeComentar = false;
    final private boolean podeAssistirLancamento = false;

    public PerfilRegular(){}

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
