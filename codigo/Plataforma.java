package codigo;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Plataforma {
    private List<Serie> listaSeries;
    private Map<String, Espectador> listaEspectadores;
    private Espectador espectadorLogado;

    public Plataforma() throws IOException {
        this.listaSeries = new LinkedList<Serie>();
        this.listaSeries = carregaArqSerie();
        this.listaEspectadores = new LinkedHashMap<>();
        this.listaEspectadores = carregarArqEspectador();
        this.carregaListFuturaEAssistida(listaEspectadores);
        this.espectadorLogado = null;
    }

    public static List<Serie> carregaArqSerie() throws IOException {
        FileReader file = new FileReader("./docs/arquivos/POO_Series2.csv");
        BufferedReader reading = new BufferedReader(file);
        String linha = reading.readLine();
        List<Serie> itemList = new LinkedList<Serie>();
        String nomeSerie, dataLancamento;
        int idCod;
        while (linha != null) {
            idCod = Integer.parseInt(linha.split(";")[0]);
            nomeSerie = linha.split(";")[1];
            dataLancamento = linha.split(";")[2];
            Serie serie = new Serie(idCod, nomeSerie, dataLancamento);
            itemList.add(serie);
            linha = reading.readLine();
        }
        reading.close();
        file.close();
        return itemList;
    }

    public void carregaListFuturaEAssistida(Map<String, Espectador> listaEspectadores) throws IOException {
        FileReader file = new FileReader("./docs/arquivos/POO_Audiencia2.csv");
        BufferedReader reading = new BufferedReader(file);
        String linha = reading.readLine();
        int idSerie;
        String Login;
        String Serie;
        while (linha != null) {
            Login = linha.split(";")[0];
            Serie = linha.split(";")[1];
            idSerie = Integer.parseInt(linha.split(";")[2]);
            if (Serie.equals("A")) {
                if (listaEspectadores.containsKey(Login)) {
                    Espectador adiciona = listaEspectadores.get(Login);
                    if (this.buscaSerie(idSerie) == null) {
                        System.out.println("Serie do id " + idSerie + " inexistente");
                    } else {
                        adiciona.adicionarAssistidas(this.buscaSerie(idSerie));
                    }

                } else {
                    System.out.println("Entrada invalida , login inexistente");
                }
            } else if (Serie.equals("F")) {
                if (listaEspectadores.containsKey(Login)) {
                    Espectador adiciona = listaEspectadores.get(Login);
                    if (this.buscaSerie(idSerie) == null) {
                        System.out.println("Serie do id " + idSerie + " inexistente");
                    } else {
                        adiciona.adicionarFutura(this.buscaSerie(idSerie));
                    }
                } else {
                    System.out.println("Entrada invalida , login inexistente");
                }
            }
            linha = reading.readLine();
        }
        reading.close();
        file.close();
    }

    public static Map<String, Espectador> carregarArqEspectador() throws IOException {
        FileReader file = new FileReader("./docs/arquivos/POO_Espectadores.csv");
        BufferedReader reading = new BufferedReader(file);
        String linha = reading.readLine();
        Map<String, Espectador> itemList = new LinkedHashMap<>();
        String nome, login, senha;
        while (linha != null) {
            nome = linha.split(";")[0];
            login = linha.split(";")[1];
            senha = linha.split(";")[2];
            Espectador espectador = new Espectador(nome, login, senha);
            itemList.put(login, espectador);
            linha = reading.readLine();
        }
        reading.close();
        file.close();
        return itemList;
    }

    public Serie buscaSerie(int idSerie) {
        for (Serie serie : this.listaSeries) {
            if (serie.retornaIdSerie() == idSerie) {
                return serie;
            }
        }
        return null;
    }

    public void infoSerie(int idSerie) {
        for (Serie serie : this.listaSeries) {
            if (serie.idSerie == (idSerie)) {
                serie.printaSerie();
            }
        }
    }

    public boolean efetuarLogin(String login, String senha) {
        if (this.listaEspectadores.containsValue(login)) {
            Espectador espectador_atual = listaEspectadores.get(login);
            if (espectador_atual.retornaSenha().equals(senha)) {
                this.espectadorLogado = espectador_atual;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void efetuarLogout() {
        this.espectadorLogado = null;
        System.out.println("Conta deslogada");
    }

    public void adicionarSerieFutura(String nomeSerie) {
        boolean adicionado = false;
        for (Serie serie : this.listaSeries) {
            if (serie.retornaNome().equals(nomeSerie)) {
                this.espectadorLogado.adicionarFutura(serie);
                adicionado = true;
            }
        }
        if (adicionado = false) {
            System.out.println("Serie nao existe , favor digitar o nome corretamente");
        }
    }

    public void adicionarSerieAssistida(String nomeSerie) {
        boolean adicionado = false;
        for (Serie serie : this.listaSeries) {
            if (serie.retornaNome().equals(nomeSerie)) {
                this.espectadorLogado.adicionarAssistidas(serie);
                ;
                adicionado = true;
            }
        }
        if (adicionado = false) {
            System.out.println("Serie nao existe , favor digitar o nome corretamente");
        }
    }

    public void removerSerieFutura(String nomeSerie) {
        boolean remover = false;
        for (Serie serie : this.listaSeries) {
            if (serie.retornaNome().equals(nomeSerie)) {
                this.espectadorLogado.removerSerieFutura(serie);
                remover = true;
            }
        }
        if (remover = false) {
            System.out.println("Serie nao existe , favor digitar o nome corretamente");
        }
    }

}
