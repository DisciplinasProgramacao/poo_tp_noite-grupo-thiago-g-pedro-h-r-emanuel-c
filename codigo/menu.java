package codigo;

import java.io.IOException;

public class menu {
    public static void main(String[] args) throws IOException {
        Plataforma plataforma = new Plataforma();
        // System.out.println(plataforma.buscaIdiomaMidia("Inglês"));
        System.out.println(plataforma.buscaGeneroMidia("Drama"));
        plataforma.adicionarAvaliacao(3509, Midia.Avaliacao.UMA_ESTRELA);
        plataforma.adicionarAvaliacao(3531, Midia.Avaliacao.UMA_ESTRELA);
        plataforma.adicionarAvaliacao(9413, Midia.Avaliacao.UMA_ESTRELA);
        System.out.println(plataforma.getListaAvaliacoes());
    }
}
