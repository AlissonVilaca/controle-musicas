package br.ufla.dcc.ppoo.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Classe que trata a internacionalização do sistema (idiomas)
 *
 * @author Paulo Jr. e Julio Alves
 */
public class I18N {

    // caminho base para os arquivos de internacionalização
    private static final String CAMINHO_ARQUIVOBASE_I18N = "br/ufla/dcc/ppoo/i18n/Strings";
    // objeto utilizado para carregar os textos do sistema de acordo com a localidade
    private static ResourceBundle rb = ResourceBundle.getBundle(CAMINHO_ARQUIVOBASE_I18N, Locale.getDefault());
    // indica a localidade (idioma) Português - Brasil
    public static final Locale PT_BR = new Locale("pt", "BR");
    // indica a localidade (idioma) Inglês - Americano
    public static final Locale EN_US = new Locale("en", "US");

    /**
     * Altera a localidade a ser utilizada.
     *
     * @param localidade Nova localidade a ser utilizada (Português - Brasil é a
     * padrão)
     */
    public static void alterarLocalidade(Locale localidade) {
        Locale.setDefault(localidade);
        rb = ResourceBundle.getBundle(CAMINHO_ARQUIVOBASE_I18N, localidade);
    }

    /**
     * Retorna o nome do sistema.
     *
     * @return Nome do sistema.
     */
    public static String obterNomeDoSistema() {
        return rb.getString("sistema.nome");
    }
    
    /**
     * Retorna o nome do sistema.
     *
     * @return Nome do sistema.
     */
    public static String obterRotuloPalavraChave() {
        return rb.getString("tela.minhasplaylists.palavra.chave");
    }
    
    /**
     * Retorna o nome do sistema.
     *
     * @return Nome do sistema.
     */
    public static String obterRotuloPlaylist() {
        return rb.getString("tela.playlist");
    }
    
     /**
     * Retorna o nome do sistema.
     *
     * @return Nome do sistema.
     */
    public static String obterRotuloUsuario() {
        return rb.getString("tela.usuario");
    }
    
    /**
     * Retorna o nome do sistema.
     *
     * @return Nome do sistema.
     */
    public static String obterRotuloPalavra() {
        return rb.getString("rotulo.playlist.palavra");
    }
    
    /**
     * Retorna o nome do sistema.
     *
     * @return Nome do sistema.
     */
    public static String obterRotuloMusicas() {
        return rb.getString("tela.minhasplaylists.musicas");
    }

    /**
     * Retorna o texto do menu Início.
     *
     * @return Texto do menu Início.
     */
    public static String obterMenuInicio() {
        return rb.getString("menu.inicio");
    }

    /**
     * Retorna o mnemônico do menu Início.
     *
     * @return Mnemônico do menu Início.
     */
    public static char obterMnemonicoMenuInicio() {
        return rb.getString("mnemonico.menu.inicio").charAt(0);
    }

    /**
     * Retorna o texto do menu Entrar.
     *
     * @return Texto do menu Entrar.
     */
    public static String obterMenuEntrar() {
        return rb.getString("menu.inicio.entrar");
    }

    /**
     * Retorna o texto do menu Minhas Músicas.
     *
     * @return Texto do menu Minhas Músicas.
     */
    public static String obterMenuMinhasMusicas() {
        return rb.getString("menu.inicio.minhas_musicas");
    }
    
    /**
     * Retorna o texto do menu Minhas Músicas.
     *
     * @return Texto do menu Minhas Músicas.
     */
    public static String obterMenuListasMusicas() {
        return rb.getString("menu.inicio.listas_musicas");
    }

    /**
     * Retorna o texto do menu Cadastrar Usuário.
     *
     * @return Texto do menu Cadastrar Usuário.
     */
    public static String obterMenuCadastrarUsuario() {
        return rb.getString("menu.inicio.cadastrar");
    }
    
    /**
     * Retorna o texto do menu Cadastrar Usuário.
     *
     * @return Texto do menu Cadastrar Usuário.
     */
    public static String obterMenuVisualizaPlaylist() {
        return rb.getString("menu.inicio.visualizaplay");
    }
    
    

    /**
     * Retorna o texto do menu Sair.
     *
     * @return Texto do menu Sair.
     */
    public static String obterMenuSair() {
        return rb.getString("menu.inicio.sair");
    }

    /**
     * Retorna o texto do menu Logout.
     *
     * @return Texto do menu Logout.
     */
    public static String obterMenuLogout() {
        return rb.getString("menu.inicio.logout");
    }

    /**
     * Retorna o texto do menu Idioma.
     *
     * @return Texto do menu Idioma.
     */
    public static String obterMenuIdioma() {
        return rb.getString("menu.idioma");
    }

    /**
     * Retorna o mnemônico do menu Idioma.
     *
     * @return Mnemônico do menu Idioma.
     */
    public static char obterMnemonicoMenuIdioma() {
        return rb.getString("mnemonico.menu.idioma").charAt(0);
    }

    /**
     * Retorna o texto do menu Idioma Português.
     *
     * @return Texto do menu Idioma Português.
     */
    public static String obterMenuIdiomaPortugues() {
        return rb.getString("menu.idioma.pt_br");
    }

    /**
     * Retorna o texto do menu Idioma Inglês.
     *
     * @return Texto do menu Idioma Inglês.
     */
    public static String obterMenuIdiomaIngles() {
        return rb.getString("menu.idioma.en_us");
    }

    /**
     * Retorna o texto do menu Ajuda.
     *
     * @return Texto do menu Ajuda.
     */
    public static String obterMenuAjuda() {
        return rb.getString("menu.ajuda");
    }

    /**
     * Retorna o mnemônico do menu Ajuda.
     *
     * @return Mnemônico do menu Ajuda.
     */
    public static char obterMnemonicoMenuAjuda() {
        return rb.getString("mnemonico.menu.ajuda").charAt(0);
    }

    /**
     * Retorna o texto do menu Sobre.
     *
     * @return Texto do menu Sobre.
     */
    public static String obterMenuSobre() {
        return rb.getString("menu.ajuda.sobre");
    }

    /**
     * Retorna o texto da mensagem de confirmação de saída do sistema.
     *
     * @return Texto da mensagem de confirmação de saída do sistema.
     */
    public static String obterConfirmacaoSaida() {
        return rb.getString("confirmacao.saida.descricao");
    }

    /**
     * Retorna o texto da mensagem de confirmação ao deletar.
     *
     * @return Texto da mensagem de confirmação ao deletar.
     */
    public static String obterConfirmacaoDeletar() {
        return rb.getString("confirmacao.deletar.descricao");
    }

    /**
     * Retorna o texto da mensagem de erro de autencicação
     *
     * @return Texto da mensagem de erro de autencicação
     */
    public static String obterErroAutenticacao() {
        return rb.getString("erro.usuario.autenticacao");
    }

    /**
     * Retorna o texto da mensagem de usuário já cadastrado.
     *
     * @return Texto da mensagem de usuário já cadastrado.
     */
    public static String obterErroUsuarioJaCadastrado() {
        return rb.getString("erro.usuario.ja_cadastrado");
    }
    
    /**
     * Retorna o texto da mensagem de senhas não conferem.
     *
     * @return Texto da mensagem de senhas não conferem.
     */
    public static String obterErroSenhasNaoConferem() {
        return rb.getString("erro.usuario.senhas_nao_conferem");
    }

    /**
     * Retorna o texto da mensagem de cadastro de usuário efetuado com sucesso.
     *
     * @return Texto da mensagem de cadastro de usuário efetuado com sucesso.
     */
    public static String obterSucessoCadastroUsuario() {
        return rb.getString("sucesso.usuario.cadastro");
    }
/**
     * Retorna o texto da mensagem de música já cadastrado.
     *
     * @return Texto da mensagem de música já cadastrado.
     */
    public static String obterErroMusicaJaCadastrada() {
        return rb.getString("erro.musica.ja_cadastrado");
    }
    
    /**
     * Retorna o texto da mensagem de ano inválido.
     *
     * @return Texto da mensagem de ano inválido.
     */
    public static String obterErroAnoInvalido() {
        return rb.getString("erro.musica.ano_invalido");
    }
    
    /**
     * Retorna o texto da mensagem de ano inválido.
     *
     * @return Texto da mensagem de ano inválido.
     */
    public static String obterErroValorInvalido() {
        return rb.getString("erro.musica.valor_invalido");
    }
    
    /**
     * Retorna o texto da mensagem de cadastro de música efetuado com sucesso.
     *
     * @return Texto da mensagem de cadastro de música efetuado com sucesso.
     */
    public static String obterSucessoCadastroMusica() {
        return rb.getString("sucesso.musica.cadastro");
    }
    
    /**
     * Retorna o texto da mensagem de música alterada com sucesso.
     *
     * @return Texto da mensagem de música alterada com sucesso..
     */
    public static String obterSucessoAlteracaoMusica() {
        return rb.getString("sucesso.musica.alteracao");
    }    
    
    /**
     * Retorna o texto da mensagem de música removida com sucesso..
     *
     * @return Texto da mensagem de música alterada com sucesso..
     */
    public static String obterSucessoRemocaoMusica() {
        return rb.getString("sucesso.musica.remocao");
    }    
    
    /**
     * Retorna o título da mensagem de confirmação.
     *
     * @return Título da mensagem de confirmação.
     */
    public static String obterTituloMensagemConfirmacao() {
        return rb.getString("confirmacao.titulo");
    }

    /**
     * Retorna o texto da mensagem Sobre.
     *
     * @return Texto da mensagem Sobre.
     */
    public static String obterMensagemSobre() {
        return rb.getString("sistema.sobre");
    }

    /**
     * Retorna o título da mensagem de informação.
     *
     * @return Título da mensagem de informação.
     */
    public static String obterTituloMensagemInformacao() {
        return rb.getString("informacao.titulo");
    }

    /**
     * Retorna o título da mensagem de erro.
     *
     * @return Título da mensagem de erro.
     */
    public static String obterTituloMensagemErro() {
        return rb.getString("erro.titulo");
    }

    /**
     * Retorna o título da tela de autenticação.
     *
     * @return Título da tela de autenticação.
     */
    public static String obterTituloTelaAutenticacao() {
        return rb.getString("tela.autenticacao.titulo");
    }

    /**
     * Retorna o título da tela de principal.
     *
     * @return Título da tela de principal.
     */
    public static String obterTituloTelaPrincipal() {
        return obterNomeDoSistema();
    }

    /**
     * Retorna o título da tela de Minhas Músicas.
     *
     * @return Título da tela de Minhas Músicas.
     */
    public static String obterTituloTelaMinhasMusicas() {
        return rb.getString("tela.minhasmusicas.titulo");
    }
    
    /**
     * Retorna o título da tela de Minhas Músicas.
     *
     * @return Título da tela de Minhas Músicas.
     */
    public static String obterTituloTelaMinhasPlaylists() {
        return rb.getString("tela.minhasplaylists.titulo");
    }

    /**
     * Retorna o título da tela de Cadastro de Usuários.
     *
     * @return Título da tela de Cadastro de Usuários.
     */
    public static String obterTituloTelaCadastrarUsuario() {
        return rb.getString("tela.cadastrousuario.titulo");
    }

    /**
     * Retorna o texto do rótulo login do usuário.
     *
     * @return Texto do rótulo login do usuário.
     */
    public static String obterRotuloUsuarioLogin() {
        return rb.getString("rotulo.usuario.login");
    }

    /**
     * Retorna o texto do rótulo senha do usuário.
     *
     * @return Texto do rótulo senha do usuário.
     */
    public static String obterRotuloUsuarioSenha() {
        return rb.getString("rotulo.usuario.senha");
    }

    /**
     * Retorna o texto do botão Entrar (logar).
     *
     * @return Texto do botão Entrar (logar).
     */
    public static String obterBotaoEntrar() {
        return rb.getString("botao.entrar");
    }
    
    /**
     * Retorna o texto do botão Entrar (logar).
     *
     * @return Texto do botão Entrar (logar).
     */
    public static String obterBotaoAvaliar() {
        return rb.getString("botao.avaliar");
    }
    
    /**
     * Retorna o texto do botão Entrar (logar).
     *
     * @return Texto do botão Entrar (logar).
     */
    public static String obterBotaoImportar() {
        return rb.getString("botao.importar");
    }
    
    /**
     * Retorna o texto do botão Entrar (logar).
     *
     * @return Texto do botão Entrar (logar).
     */
    public static String obterBotaoComentar() {
        return rb.getString("botao.comentar");
    }
    
    /**
     * Retorna o texto do botão Entrar (logar).
     *
     * @return Texto do botão Entrar (logar).
     */
    public static String obterBotaoBuscar() {
        return rb.getString("botao.buscar");
    }
    
    /**
     * Retorna o texto do botão Entrar (logar).
     *
     * @return Texto do botão Entrar (logar).
     */
    public static String obterBotaoSelecionar() {
        return rb.getString("botao.selecionar");
    }
    
    /**
     * Retorna o texto do botão Entrar (logar).
     *
     * @return Texto do botão Entrar (logar).
     */
    public static String obterBotaoAdicionar() {
        return rb.getString("botao.adicionar");
    }
    
    /**
     * Retorna o texto do botão Entrar (logar).
     *
     * @return Texto do botão Entrar (logar).
     */
    public static String obterBotaoNova() {
        return rb.getString("botao.nova");
    }   
    
    /**
     * Retorna o texto do botão Entrar (logar).
     *
     * @return Texto do botão Entrar (logar).
     */
    public static String obterBotaoExcluir() {
        return rb.getString("botao.excluir");
    } 
    
    /**
     * Retorna o texto do botão Cancelar.
     *
     * @return Texto do botão Cancelar.
     */
    public static String obterBotaoCancelar() {
        return rb.getString("botao.cancelar");
    }

    /**
     * Retorna o texto do botão Salvar.
     *
     * @return Texto do botão Salvar.
     */
    public static String obterBotaoSalvar() {
        return rb.getString("botao.salvar");
    }

    /**
     * Retorna o texto do botão Novo.
     *
     * @return Texto do botão Novo.
     */
    public static String obterBotaoNovo() {
        return rb.getString("botao.novo");
    }

    /**
     * Retorna o texto do botão Editar.
     *
     * @return Texto do botão Editar.
     */
    public static String obterBotaoEditar() {
        return rb.getString("botao.editar");
    }

    /**
     * Retorna o texto do botão Excluir (deletar).
     *
     * @return Texto do botão Excluir (deletar).
     */
    public static String obterBotaoDeletar() {
        return rb.getString("botao.deletar");
    }

    /**
     * Retorna o texto do rótulo nome do usuário.
     *
     * @return Texto do rótulo nome do usuário.
     */
    public static String obterRotuloUsuarioNome() {
        return rb.getString("rotulo.usuario.nome");
    }

    /**
     * Retorna o texto do rótulo título da música.
     *
     * @return Texto do rótulo título da música.
     */
    public static String obterRotuloMusicaTitulo() {
        return rb.getString("rotulo.musica.titulo");
    }
    
    /**
     * Retorna o texto do rótulo título da música.
     *
     * @return Texto do rótulo título da música.
     */
    public static String obterRotuloNome() {
        return rb.getString("rotulo.playlist.nomes");
    }

    /**
     * Retorna o texto do rótulo artista da música.
     *
     * @return Texto do rótulo artista da música.
     */
    public static String obterRotuloMusicaArtista() {
        return rb.getString("rotulo.musica.artista");
    }

    /**
     * Retorna o texto do rótulo ano da música.
     *
     * @return Texto do rótulo ano da música.
     */
    public static String obterRotuloMusicaAno() {
        return rb.getString("rotulo.musica.ano");
    }
    
    /**
     * Retorna o texto do rótulo ano da música.
     *
     * @return Texto do rótulo ano da música.
     */
    public static String obterRotuloNovaPalavra() {
        return rb.getString("rotulo.playlist.palavra");
    }
    
    /**
     * Retorna o texto do rótulo ano da música.
     *
     * @return Texto do rótulo ano da música.
     */
    public static String obterRotuloNovaMusica() {
        return rb.getString("rotulo.playlist.musica");
    }

    /**
     * Retorna o texto do rótulo gênero da música.
     *
     * @return Texto do rótulo gênero da música.
     */
    public static String obterRotuloMusicaGenero() {
        return rb.getString("rotulo.musica.genero");
    }

    /**
     * Retorna o texto do rótulo letra da música.
     *
     * @return Texto do rótulo letra da música.
     */
    public static String obterRotuloMusicaLetra() {
        return rb.getString("rotulo.musica.letra");
    }

    /**
     * Retorna o texto do rótulo confirmar senha do usuário.
     *
     * @return Texto do rótulo confirmar senha do usuário.
     */
    public static String obterRotuloUsuarioConfirmarSenha() {
        return rb.getString("rotulo.usuario.confirmar_senha");
    }
    
    /**
     * Retorna o texto do rótulo login do usuário.
     *
     * @return Texto do rótulo login do usuário.
     */
    public static String obterRotuloNomePlaylist() {
        return rb.getString("rotulo.playlist.nome");
    }
    
    /**
     * Retorna o texto do rótulo login do usuário.
     *
     * @return Texto do rótulo login do usuário.
     */
    public static String obterRotuloAutor() {
        return rb.getString("rotulo.playlist.autor");
    }
    
    /**
     * Retorna o texto do rótulo login do usuário.
     *
     * @return Texto do rótulo login do usuário.
     */
    public static String obterErroPlaylistJaCadastrada() {
        return rb.getString("erro.playlist.jacadastrada");
    }
    
    /**
     * Retorna o texto do rótulo login do usuário.
     *
     * @return Texto do rótulo login do usuário.
     */
    public static String obterErroPlaylistComMesmoNome() {
        return rb.getString("erro.playlist.mesmonome");
    }   
    
    /**
     * Retorna o texto do rótulo login do usuário.
     *
     * @return Texto do rótulo login do usuário.
     */
    public static String obterSucessoCadastroPlaylist() {
        return rb.getString("sucesso.cadastro.playlist");
    } 
    
    /**
     * Retorna o texto do rótulo login do usuário.
     *
     * @return Texto do rótulo login do usuário.
     */
    public static String obterSucessoEdicaoPlaylist() {
        return rb.getString("sucesso.edicao.playlist");
    } 
    
    /**
     * Retorna o texto do rótulo login do usuário.
     *
     * @return Texto do rótulo login do usuário.
     */
    public static String obterSucessoRemocaoPlaylist() {
        return rb.getString("sucesso.remocao.playlist");
    } 
    
    /**
     * Retorna o texto do rótulo login do usuário.
     *
     * @return Texto do rótulo login do usuário.
     */
    public static String obterErroNomeEmBranco() {
        return rb.getString("erro.playlist.nome");
    }
    
    /**
     * Retorna o texto do rótulo login do usuário.
     *
     * @return Texto do rótulo login do usuário.
     */
    public static String obterErroPalavrasInsuficientes() {
        return rb.getString("erro.playlist.palavras");
    }
    
    /**
     * Retorna o texto do rótulo login do usuário.
     *
     * @return Texto do rótulo login do usuário.
     */
    public static String obterErroMusicasInsuficientes() {
        return rb.getString("erro.playlist.musicas");
    }
    
    /**
     * Retorna o texto do rótulo login do usuário.
     *
     * @return Texto do rótulo login do usuário.
     */
    public static String obterErroImportarPlaylist() {
        return rb.getString("erro.playlist.importar");
    }       
    
    /**
     * Retorna o texto do rótulo login do usuário.
     *
     * @return Texto do rótulo login do usuário.
     */
    public static String obterLabelPalavra() {
        return rb.getString("label.palavra");
    }   
    
    /**
     * Retorna o texto do rótulo login do usuário.
     *
     * @return Texto do rótulo login do usuário.
     */
    public static String erroAvaliarMesmoUsuario() {
        return rb.getString("erro.mesmoUsuario");
    }     
       
    /**
     * Retorna o texto do rótulo login do usuário.
     *
     * @return Texto do rótulo login do usuário.
     */
    public static String erroPontuacaoInvalida() {
        return rb.getString("erro.pontuacao.invalida");
    } 
    
    /**
     * Retorna o texto do rótulo login do usuário.
     *
     * @return Texto do rótulo login do usuário.
     */
    public static String erroUsuarioJaAvaliou() {
        return rb.getString("erro.avaliou");
    } 
    
    /**
     * Retorna o texto do rótulo login do usuário.
     *
     * @return Texto do rótulo login do usuário.
     */
    public static String obterPlaylistAvaliadaSucesso() {
        return rb.getString("sucesso.avaliou");
    }     
}
