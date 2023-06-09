package codigo;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class menu {
    private Plataforma plataforma;
    private Scanner input;
    private boolean sair = false;

    /**
     * Construtor da classe menu.
     *
     * @throws IOException exceção de IO
     */
    public menu() throws IOException {
        plataforma = new Plataforma();
        input = new Scanner(System.in);
        exibirMenuSemLogin();
    }

    /**
     * Limpa a tela do console.
     */
    private void limparTela() {
        System.out.print("\033[H\033[2J");
    }

    /**
     * Aguarda a confirmação do usuário para limpar a tela.
     */
    private void confirmarLimparTela() {
        System.out.println("\u001B[47mPara sair, pressione enter.\u001B[40m");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        System.out.print("\033[H\033[2J");
    }

    /**
     * Exibe o menu principal quando não há login.
     */
    private void exibirMenuSemLogin() {
        limparTela();
        while (!sair) {
            System.out.println("╔══════════════════════════════╗");
            System.out.println("║         \u001B[36mStreaming\u001B[37m            ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║\u001B[32m 1. Efetuar login \u001B[37m            ║");
            System.out.println("║\u001B[31m 2. Sair	\u001B[37m               ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("Opção: ");
            int opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    efetuarLogin();
                    break;
                case 2:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Digite novamente.");
                    break;
            }
        }
        System.out.println();
    }

    /**
     * Exibe o menu principal quando há login.
     */
    private void exibirMenuLogado() {
        while (!sair) {
            System.out.println("╔══════════════════════════╗");
            System.out.println("║          \u001B[36mMenu\u001B[37m            ║");
            System.out.println("╠══════════════════════════╣");
            System.out.println("║\u001B[32m1. Adicionar mídia futura\u001B[37m ║");
            System.out.println("║\u001B[32m2. Adicionar mídia assist.\u001B[37m║");
            System.out.println("║\u001B[31m3. Remover mídia futura\u001B[37m   ║");
            System.out.println("║\u001B[32m4. Adicionar avaliação\u001B[37m    ║");
            System.out.println("║\u001B[35m5. Buscar mídia por idioma\u001B[37m║");
            System.out.println("║\u001B[35m6. Buscar mídia por gênero\u001B[37m║");
            System.out.println("║\u001B[35m7. Buscar mídia por nome\u001B[37m  ║");
            System.out.println("║\u001B[33m8. Informações sobre mídia\u001B[37m║");
            System.out.println("║\u001B[34m9. Listar avaliações\u001B[37m      ║");
            System.out.println("║\u001B[34m10. Listar mídias F.\u001B[37m      ║");
            System.out.println("║\u001B[34m11. Listar mídias A.\u001B[37m      ║");
            System.out.println("║\u001B[33m12. Relatórios Gerenciais\u001B[37m ║");
            System.out.println("║\u001B[31m13. Efetuar logout\u001B[37m        ║");
            System.out.println("╚══════════════════════════╝");
            System.out.print("Opção: ");

            try {
                int opcao = input.nextInt();
                input.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println("Digite o nome da mídia para assistir depois: ");
                        String nomeSerieFutura = input.nextLine();
                        System.out.println(plataforma.adicionarMidiaFutura(nomeSerieFutura));
                        confirmarLimparTela();
                        break;
                    case 2:
                        System.out.println("Digite o nome da mídia assistida: ");
                        String nomeSerieAssistida = input.nextLine();
                        System.out.println(plataforma.adicionarMidiaAssistida(nomeSerieAssistida));
                        confirmarLimparTela();
                        break;
                    case 3:
                        System.out.println("Digite o nome da mídia futura a ser removida: ");
                        String nomeSerieFuturaRemover = input.nextLine();
                        System.out.println(plataforma.removerMidiaFutura(nomeSerieFuturaRemover));
                        confirmarLimparTela();
                        break;
                    case 4:
                        avaliarMidia();
                        confirmarLimparTela();
                        break;
                    case 5:
                        System.out.println("Digite o idioma a ser buscado: ");
                        String idioma = input.nextLine();
                        String midiasPorIdioma = plataforma.buscaIdiomaGeneroString(idioma, "idioma");
                        System.out.println(midiasPorIdioma);
                        confirmarLimparTela();
                        break;
                    case 6:
                        System.out.println("Digite o gênero a ser buscado: ");
                        String genero = input.nextLine();
                        String midiasPorGenero = plataforma.buscaIdiomaGeneroString(genero, "genero");
                        System.out.println(midiasPorGenero);
                        confirmarLimparTela();
                        break;
                    case 7:
                        System.out.println("Digite o nome a ser buscado: ");
                        String nome = input.nextLine();
                        String midiasPorNome = plataforma.buscaIdiomaGeneroString(nome, "nome");
                        System.out.println(midiasPorNome);
                        confirmarLimparTela();
                        break;
                    case 8:
                        System.out.println("Digite o ID da mídia: ");
                        int idSerie = input.nextInt();
                        input.nextLine();
                        System.out.println(plataforma.infoMidia(idSerie));
                        confirmarLimparTela();
                        break;
                    case 9:
                        System.out.println(plataforma.printaListaAvaliacoesDoEspectador());
                        confirmarLimparTela();
                        break;
                    case 10:
                        System.out.println(plataforma.printaListaMidiasFuturasDoEspectador());
                        confirmarLimparTela();
                        break;
                    case 11:
                        System.out.println(plataforma.printaListaMidiasAssistidasDoEspectador());
                        confirmarLimparTela();
                        break;
                    case 12:
                        exibirMenuRelatorioGerencial();
                        break;
                    case 13:
                        System.out.println("Efetuando logout...");
                        efetuarLogout();
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                        confirmarLimparTela();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida! Tente novamente.");
                input.nextLine();
                confirmarLimparTela();
            }
        }
        System.out.println();
    }

    /**
     * Exibe o menu relatório quando chamado.
     */
    private void exibirMenuRelatorioGerencial() {
        limparTela();
        while (true) {
            System.out.println("╔═══════════════════════════════════════╗");
            System.out.println("║          \u001B[36mRelatório Gerencial\u001B[37m          ║");
            System.out.println("╠═══════════════════════════════════════╣");
            System.out.println("║\u001B[32m1. Espectador mais ativo\u001B[37m               ║");
            System.out.println("║\u001B[32m2. Espectador mais avaliou\u001B[37m             ║");
            System.out.println("║\u001B[31m3. % Clientes com mais de 15 avaliações\u001B[37m║");
            System.out.println("║\u001B[35m4. Melhores avaliadas (geral)\u001B[37m          ║");
            System.out.println("║\u001B[33m5. Mídias mais assistidas (geral)\u001B[37m      ║");
            System.out.println("║\u001B[35m6. Melhores avaliadas por gênero\u001B[37m       ║");
            System.out.println("║\u001B[33m7. Mídias mais assistidas por gênero\u001B[37m   ║");
            System.out.println("║\u001B[31m8. Voltar ao menu principal\u001B[37m            ║");
            System.out.println("╚═══════════════════════════════════════╝");
            System.out.print("Opção: ");

            try {
                int opcao = input.nextInt();
                input.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println(plataforma.espectadorAssistiuMaisMidias());
                        confirmarLimparTela();
                        break;
                    case 2:
                        System.out.println(plataforma.espectadorMaisAvaliou());
                        confirmarLimparTela();
                        break;
                    case 3:
                        System.out.println(plataforma.porcetagemClientesComMenos15Avaliacao());
                        confirmarLimparTela();
                        break;
                    case 4:
                        System.out.println(plataforma.melhoresAvaliadas());
                        confirmarLimparTela();
                        break;
                    case 5:
                        System.out.println(plataforma.midiasMaisAssistidas());
                        confirmarLimparTela();
                        break;
                    case 6:
                        System.out.println("Digite o gênero: ");
                        String genero = input.nextLine();
                        System.out.println(plataforma.melhoresAvaliadasGenero(genero));
                        confirmarLimparTela();
                        break;
                    case 7:
                        System.out.println("Digite o gênero: ");
                        genero = input.nextLine();
                        System.out.println(plataforma.midiasMaisAssistidasGenero(genero));
                        confirmarLimparTela();
                        break;
                    case 8:
                        limparTela();
                        return;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                        confirmarLimparTela();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida! Tente novamente.");
                input.nextLine();
                confirmarLimparTela();
            }
        }
    }

    /**
     * Efetua o login do usuário.
     */
    private void efetuarLogin() {
        System.out.print("Digite seu nome de usuário: ");
        String usuario = input.nextLine();

        System.out.print("Digite sua senha: ");
        String senha = input.nextLine();

        boolean loginSucesso = plataforma.efetuarLogin(usuario, senha);
        if (loginSucesso) {
            System.out.println("Login realizado com sucesso!");
            limparTela();
            exibirMenuLogado();
        } else {
            System.out.println("Usuário ou senha incorretos. Tente novamente.");
        }
    }

    /**
     * Efetua o logout do usuário.
     */
    private void efetuarLogout() {
        System.out.println(plataforma.efetuarLogout());
        System.out.println("Logout realizado com sucesso!");
        limparTela();
        exibirMenuSemLogin();
    }

    private void avaliarMidia() {
        System.out.println("Digite o nome da mídia: ");
        String nomeMidia = input.nextLine();
        if (plataforma.retornaMidiaPorNome(nomeMidia) != null) {
            System.out.println("Digite a avaliação (1 a 5): ");
            int nota = input.nextInt();
            if (nota > 5 || nota < 1) {
                System.out.println("Informe apenas notas entre 1 e 5.");
                confirmarLimparTela();
                return;
            }
            if (this.plataforma.getEspectadorLogado().podeAvaliar()) {
                System.out.println("Insira um comentário:");
                String comentario = System.console().readLine();
                System.out.println(plataforma.avaliarMidia(nomeMidia, comentario, nota));
            } else {
                System.out.println(plataforma.avaliarMidia(nomeMidia, "", nota));
            }
        } else {
            System.out.println("ERRO! Mídia não encontrada, favor digitar o nome novamente.");
        }
    }

    public static void main(String[] args) throws IOException {
        menu menu = new menu();
    }
}