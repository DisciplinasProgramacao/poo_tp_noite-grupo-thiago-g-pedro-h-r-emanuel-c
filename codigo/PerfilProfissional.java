package codigo;

public class PerfilProfissional implements IPerfilEspectador {
    final private String tipo = "Especialista";
    final private boolean podeComentar = true;
    final private boolean podeAssistirLancamento = true;

    public PerfilProfissional(){}

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
