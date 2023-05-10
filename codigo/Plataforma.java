package codigo;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Plataforma {
    private List<Serie> listaSeries;
    private List<Filme> listaFilmes;
    private Map<String, Espectador> listaEspectadores;
    private Espectador espectadorLogado;

    public Plataforma() throws IOException {
        this.listaSeries = new LinkedList<Serie>();
        this.listaFilmes = new LinkedList<Filme>();
        this.listaSeries = carregaArqSerie();
        this.listaFilmes = carregaArqFilmes();
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
        String nomeSerie, dataLancamento, genero, idioma;
        int idCod;
        while (linha != null) {
            idCod = Integer.parseInt(linha.split(";")[0]);
            nomeSerie = linha.split(";")[1];
            dataLancamento = linha.split(";")[2];
            genero = linha.split(";")[3];
            idioma = linha.split(";")[4];
            Serie serie = new Serie(idCod, nomeSerie, dataLancamento, genero, idioma);
            itemList.add(serie);
            linha = reading.readLine();
        }
        reading.close();
        file.close();
        return itemList;
    }

    public static List<Filme> carregaArqFilmes() throws IOException {
        FileReader file = new FileReader("./docs/arquivos/POO_Filmes.csv");
        BufferedReader reading = new BufferedReader(file);
        String linha = reading.readLine();
        List<Filme> itemList = new LinkedList<Filme>();
        String nomeFilme, dataLancamento, genero, idioma;
        int idCod, duracaoMin;
        while (linha != null) {
            idCod = Integer.parseInt(linha.split(";")[0]);
            nomeFilme = linha.split(";")[1];
            dataLancamento = linha.split(";")[2];
            duracaoMin = Integer.parseInt(linha.split(";")[3]);
            genero = linha.split(";")[4];
            idioma = linha.split(";")[5];
            Filme filme = new Filme(idCod, nomeFilme, dataLancamento, duracaoMin, genero, idioma);
            itemList.add(filme);
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
                        adiciona.adicionarAssistidasSerie(this.buscaSerie(idSerie));
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
                        adiciona.adicionarFuturaSerie(this.buscaSerie(idSerie));
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
            if (serie.retornaId() == idSerie) {
                return serie;
            }
        }
        return null;
    }

    public void infoSerie(int idSerie) {
        for (Serie serie : this.listaSeries) {
            if (serie.id == (idSerie)) {
                serie.printaMidia();
            }
        }
    }

    public Filme buscaFilme(int idFilme) {
        for (Filme filme : this.listaFilmes) {
            if (filme.retornaId() == idFilme) {
                return filme;
            }
        }
        return null;
    }

    public void infoFilme(int idFilme) {
        for (Filme filme : this.listaFilmes) {
            if (filme.id == (idFilme)) {
                filme.printaMidia();
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
                this.espectadorLogado.adicionarFuturaSerie(serie);
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
                this.espectadorLogado.adicionarAssistidasSerie(serie);
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
                this.espectadorLogado.removerSerieFuturaSerie(serie);
                remover = true;
            }
        }
        if (remover = false) {
            System.out.println("Serie nao existe , favor digitar o nome corretamente");
        }
    }

    public void adicionarFilmeFuturo(String nomeFilme) {
        boolean adicionado = false;
        for (Filme filme : this.listaFilmes) {
            if (filme.retornaNome().equals(nomeFilme)) {
                this.espectadorLogado.adicionarFuturaFilme(filme);
                adicionado = true;
            }
        }
        if (adicionado = false) {
            System.out.println("Filme nao existe , favor digitar o nome corretamente");
        }
    }

    public void adicionarFilmeAssistido(String nomeFilme) {
        boolean adicionado = false;
        for (Filme filme : this.listaFilmes) {
            if (filme.retornaNome().equals(nomeFilme)) {
                this.espectadorLogado.adicionarAssistidasFilme(filme);
                ;
                adicionado = true;
            }
        }
        if (adicionado = false) {
            System.out.println("Filme nao existe , favor digitar o nome corretamente");
        }
    }

    public void removerFilmeFuturo(String nomeFilme) {
        boolean remover = false;
        for (Filme filme : this.listaFilmes) {
            if (filme.retornaNome().equals(nomeFilme)) {
                this.espectadorLogado.removerSerieFuturaFilme(filme);
                remover = true;
            }
        }
        if (remover = false) {
            System.out.println("Filme nao existe , favor digitar o nome corretamente");
        }
    }

}
