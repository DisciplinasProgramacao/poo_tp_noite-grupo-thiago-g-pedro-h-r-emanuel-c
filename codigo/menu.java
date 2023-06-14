package codigo;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class menu {
    private Plataforma plataforma;
    private Scanner input;
    private boolean sair = false;

    public menu() throws IOException {
        plataforma = new Plataforma();
        input = new Scanner(System.in);
        exibirMenuSemLogin();
    }

    public void limparTela() {
        System.out.print("\033[H\033[2J");
    }

    public void confirmarLimparTela() {
        System.out.println("\u001B[47mPara sair, pressione enter.\u001B[40m");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        System.out.print("\033[H\033[2J");
    }

    public void exibirMenuSemLogin() {
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

    public void exibirMenuLogado() {
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
            System.out.println("║\u001B[33m8.Informações sobre mídia\u001B[37m ║");
            System.out.println("║\u001B[34m9. Listar avaliações\u001B[37m      ║");
            System.out.println("║\u001B[33m10. Relatórios Gerenciais\u001B[37m ║");
            System.out.println("║\u001B[31m11. Efetuar logout\u001B[37m        ║");
            System.out.println("╚══════════════════════════╝");
            System.out.print("Opção: ");

            try {
                int opcao = input.nextInt();
                input.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println("Digite o nome da mídia para assistir depois: ");
                        String nomeSerieFutura = input.nextLine();
                        plataforma.adicionarMidiaFutura(nomeSerieFutura);
                        confirmarLimparTela();
                        break;
                    case 2:
                        System.out.println("Digite o nome da mídia assistida: ");
                        String nomeSerieAssistida = input.nextLine();
                        plataforma.adicionarMidiaAssistida(nomeSerieAssistida);
                        confirmarLimparTela();
                        break;
                    case 3:
                        System.out.println("Digite o nome da mídia futura a ser removida: ");
                        String nomeSerieFuturaRemover = input.nextLine();
                        plataforma.removerlMidiaFutura(nomeSerieFuturaRemover);
                        confirmarLimparTela();
                        break;
                    case 4:
                        System.out.println("Digite o nome da mídia: ");
                        String nomeMidia = input.nextLine();
                        if (plataforma.retornaMidiaPorNome(nomeMidia) != null) {
                            System.out.println("Digite a avaliação (1 a 5): ");
                            int nota = input.nextInt();
                            if (this.plataforma.getEspectadorLogado().retornaPerfil().podeComentar()) {
                                System.out.println("Insira um comentário:");
                                String comentario = input.nextLine();
                                System.out.println(plataforma.avaliarMidia(nomeMidia, comentario, nota));
                            } else {
                                System.out.println(plataforma.avaliarMidia(nomeMidia, "", nota));
                            }
                        } else {
                            System.out.println("ERRO! Mídia não encontrada, favor digitar o nome novamente.");
                            opcao = 7;
                        }
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
                        plataforma.infoMidia(idSerie);
                        confirmarLimparTela();
                        break;
                    case 9:
                        System.out.println(plataforma.printaListaAvaliacoesDoEspectador());
                        break;
                    case 10:
                        // TO DO: Relatórios Gerenciais Pedro.
                        break;
                    case 11:
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

    private void efetuarLogout() {
        plataforma.efetuarLogout();
        System.out.println("Logout realizado com sucesso!");
        limparTela();
        exibirMenuSemLogin();
    }

    public static void main(String[] args) throws IOException {
        menu menu = new menu();
    }
}