package codigo;

import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Plataforma {
    private Map<String, Espectador> listaEspectadores;
    private List<Midia> listaMidia;
    private Espectador espectadorLogado;
    private List<Midia.Avaliacao> listaAvaliacoes;

    public Plataforma() throws IOException {
        this.listaMidia = new LinkedList<Midia>();
        this.listaMidia.addAll(carregaArqSerie());
        this.listaMidia.addAll(carregaArqFilmes());
        this.listaEspectadores = new LinkedHashMap<>();
        this.listaEspectadores = carregarArqEspectador();
        this.carregaListFuturaEAssistida(listaEspectadores);
        this.espectadorLogado = null;
        this.listaAvaliacoes = new ArrayList<>();
    }

    public static List<Midia> carregaArqSerie() throws IOException {
        Path arquivo = Path.of("./docs/arquivos/POO_Series1.csv");
        try (Stream<String> linhas = Files.lines(arquivo)) {
            return linhas.map(linha -> {
                String[] campos = linha.split(";");
                int idCod = Integer.parseInt(campos[0]);
                String nomeSerie = campos[1];
                String dataLancamento = campos[2];
                String genero = campos[3];
                String idioma = campos[4];
                Serie serie = new Serie(idCod, nomeSerie, dataLancamento, genero, idioma);
                return serie;
            }).collect(Collectors.toCollection(LinkedList::new));
        } catch (IOException e) {
            System.err.println("Erro na leitura do arquivo: " + e.getMessage());
            return null;
        }
    }

    public static List<Midia> carregaArqFilmes() throws IOException {
        Path arquivo = Path.of("./docs/arquivos/POO_Filmes1.csv");
        try (Stream<String> linhas = Files.lines(arquivo)) {
            return linhas.map(linha -> {
                String[] campos = linha.split(";");
                int idCod = Integer.parseInt(campos[0]);
                String nomeFilme = campos[1];
                String dataLancamento = campos[2];
                int duracaoMin = Integer.parseInt(campos[3]);
                String genero = campos[4];
                String idioma = campos[5];
                Filme filme = new Filme(idCod, nomeFilme, dataLancamento, duracaoMin, genero, idioma);
                return filme;
            }).collect(Collectors.toCollection(LinkedList::new));
        } catch (IOException e) {
            System.err.println("Erro na leitura do arquivo: " + e.getMessage());
            return null;
        }
    }

    public void carregaListFuturaEAssistida(Map<String, Espectador> listaEspectadores) throws IOException {
        Path arquivo = Path.of("./docs/arquivos/POO_Audiencia2.csv");
        try (Stream<String> linhas = Files.lines(arquivo)) {
            linhas.forEach(linha -> {
                String[] campos = linha.split(";");
                String login = campos[0];
                String serie = campos[1];
                int idSerie = Integer.parseInt(campos[2]);
    
                if (serie.equals("A")) {
                    if (listaEspectadores.containsKey(login)) {
                        Espectador espectador = listaEspectadores.get(login);
                        Midia serieEncontrada = this.buscaSerie(idSerie);
                        if (serieEncontrada == null) {
                            System.out.println("Série com ID " + idSerie + " inexistente");
                        } else {
                            espectador.adicionarAssistidasSerie(serieEncontrada);
                        }
                    } else {
                        System.out.println("Entrada inválida, login inexistente: " + login);
                    }
                } else if (serie.equals("F")) {
                    if (listaEspectadores.containsKey(login)) {
                        Espectador espectador = listaEspectadores.get(login);
                        Midia serieEncontrada = this.buscaSerie(idSerie);
                        if (serieEncontrada == null) {
                            System.out.println("Série com ID " + idSerie + " inexistente");
                        } else {
                            espectador.adicionarFuturaSerie(serieEncontrada);
                        }
                    } else {
                        System.out.println("Entrada inválida, login inexistente: " + login);
                    }
                }
            });
        } catch (IOException e) {
            System.err.println("Erro na leitura do arquivo: " + e.getMessage());
            return;
        }
    }

    public static Map<String, Espectador> carregarArqEspectador() throws IOException {
        Path arquivo = Path.of("./docs/arquivos/POO_Espectadores.csv");
        try (Stream<String> linhas = Files.lines(arquivo)) {
            return linhas.map(linha -> {
                String[] campos = linha.split(";");
                String nome = campos[0];
                String login = campos[1];
                String senha = campos[2];
                Espectador espectador = new Espectador(nome, login, senha);
                return Map.entry(login, espectador);
            }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        } catch (IOException e) {
            System.err.println("Erro na leitura do arquivo: " + e.getMessage());
            return null;
        }
    }

    public String buscaIdiomaMidia(String idioma) {
        StringBuilder sb = new StringBuilder();
        Comparator<String> idiomaComparator = Comparator.comparing(String::toLowerCase);
        List<Midia> listaRetorno = listaMidia.stream()
            .filter(midia -> idiomaComparator.compare(midia.retornaIdioma().toLowerCase(), idioma.toLowerCase()) == 0)
            .collect(Collectors.toList());
        listaRetorno.forEach(e -> sb.append(e.toString()).append(System.lineSeparator()));

        return sb.toString();
    }

    public String buscaGeneroMidia(String genero) {
        StringBuilder sb = new StringBuilder();
        Comparator<String> generoComparator = Comparator.comparing(String::toLowerCase);
        List<Midia> listaRetorno = listaMidia.stream()
            .filter(midia -> generoComparator.compare(midia.retornaGenero().toLowerCase(), genero.toLowerCase()) == 0)
            .collect(Collectors.toList());
        listaRetorno.forEach(e -> sb.append(e.toString()).append(System.lineSeparator()));

        return sb.toString();
    }

    public Midia buscaSerie(int idSerie) {
        for (Midia serie : this.listaMidia) {
            if (serie.retornaId() == idSerie) {
                return serie;
            }
        }
        return null;
    }

    public void infoSerie(int idSerie) {
        for (Midia serie : this.listaMidia) {
            if (serie.id == (idSerie)) {
                serie.printaMidia();
            }
        }
    }

    public Midia buscaFilme(int idFilme) {
        for (Midia filme : this.listaMidia) {
            if (filme.retornaId() == idFilme) {
                return filme;
            }
        }
        return null;
    }

    public void infoFilme(int idFilme) {
        for (Midia filme : this.listaMidia) {
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
        for (Midia serie : this.listaMidia) {
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
        for (Midia serie : this.listaMidia) {
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
        for (Midia serie : this.listaMidia) {
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
        for (Midia filme : this.listaMidia) {
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
        for (Midia filme : this.listaMidia) {
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
        for (Midia filme : this.listaMidia) {
            if (filme.retornaNome().equals(nomeFilme)) {
                this.espectadorLogado.removerSerieFuturaFilme(filme);
                remover = true;
            }
        }
        if (remover = false) {
            System.out.println("Filme nao existe , favor digitar o nome corretamente");
        }
    }

    public void adicionarAvaliacao(int idMidia, Midia.Avaliacao avaliacao) {
        Midia midia = buscaMidia(idMidia);
        if (midia != null) {
        midia.atribuirAvaliacao(avaliacao);
        listaAvaliacoes.add(avaliacao);
        }
    }


    public String getListaAvaliacoes() {
        StringBuilder sb = new StringBuilder();
        List<Midia.Avaliacao> listaRetorno = listaAvaliacoes.stream()
            .collect(Collectors.toList());
        listaRetorno.forEach(e -> sb.append(e.toString()).append(System.lineSeparator()));

        return sb.toString();
    }

    public Midia buscaMidia(int idMidia) {
        for (Midia midia : listaMidia) {
            if (midia.retornaId() == idMidia) {
                return midia;
            }
        }
        return null;
    }
}
