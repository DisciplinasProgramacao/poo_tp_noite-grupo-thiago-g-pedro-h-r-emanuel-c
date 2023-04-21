package codigo;

import java.io.IOException;

public class menu {
    public static void main(String[] args) throws IOException {
        Plataforma plataforma = new Plataforma();
        plataforma.infoSerie(3481);
        plataforma.verificaLogin(null, null);

    }
}
