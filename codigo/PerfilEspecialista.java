package codigo;

public class PerfilEspecialista implements IPerfilEspectador {
    final private String tipo = "Especialista";
    final private boolean podeComentar = true;

    public PerfilEspecialista(){}

    @Override
    public boolean podeComentar() {
        return this.podeComentar;
    }

    @Override
    public String retornaTipo() {
        return this.tipo;
    }
}
