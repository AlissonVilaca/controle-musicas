package br.ufla.dcc.ppoo.gui;

import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.imagens.GerenciadorDeImagens;
import br.ufla.dcc.ppoo.seguranca.SessaoUsuario;
import br.ufla.dcc.ppoo.servicos.GerenciadorMusicas;
import br.ufla.dcc.ppoo.servicos.GerenciadorPlaylists;
import br.ufla.dcc.ppoo.servicos.GerenciadorUsuarios;
import br.ufla.dcc.ppoo.util.Utilidades;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRootPane;

/**
 * Classe que representa a Tela Principal
 *
 * @author Julio
 */
public class TelaPrincipal {

    // objeto de controle de sessão (autenticação) do usuário
    private final SessaoUsuario sessaoUsuario;
    // tela de autenticação (login) de usuário
    private final TelaAutenticacao telaAutenticacao;
    // tela de cadastro de usuário
    private final TelaCadastroUsuario telaCadastroUsuario;
    // tela de gestão das músicas
    private final TelaMinhasMusicas telaMinhasMusicas;
    // tela de gestão das playlists
    private final TelaMinhasPlaylists telaMinhasPlaylists;
    // tela de filtro de Playlists
    private final TelaFiltroPlaylists telaFiltroPlaylists;    
    // tela de filtro de Usuarios
    private final TelaFiltroUsuarios telaFiltroUsuarios; 
    
    private GerenciadorMusicas gerenciadorMusicas;
    private GerenciadorUsuarios gerenciadorUsuarios;
    private GerenciadorPlaylists gerenciadorPlaylists;
    
    // janela da tela principal
    private JFrame janela;

    // Menus principais da tela    
    private JMenuBar menuPrincipal;
    private JMenu menuInicio;
    private JMenu menuIdioma;
    private JMenu menuAjuda;

    // Submenus da tela
    private JMenuItem menuEntrar;
    private JMenuItem menuCadastrarUsuario;
    private JMenuItem menuIdiomaPortugues;
    private JMenuItem menuIdiomaIngles;
    private JMenuItem menuSair;
    private JMenuItem menuSobre;

    // Itens de menu específicos para usuários logados no sistema    
    private JMenuItem menuLogout;
    private JMenuItem menuMinhasMusicas;
    private JMenuItem menuMinhasPlaylists;
    private JMenuItem menuVisualizarPlaylists;
    private JMenuItem menuVisualizarUsuarios;

    /**
     * Construtor incializa as demais telas e sessão de usuário.
     */
    public TelaPrincipal() {
        telaAutenticacao = new TelaAutenticacao(this);
        telaCadastroUsuario = new TelaCadastroUsuario(this);
        telaMinhasMusicas = new TelaMinhasMusicas(this);
        sessaoUsuario = SessaoUsuario.obterInstancia();
        telaMinhasPlaylists = new TelaMinhasPlaylists(this);
        telaFiltroPlaylists = new TelaFiltroPlaylists(this);
        telaFiltroUsuarios = new TelaFiltroUsuarios(this);
        gerenciadorMusicas = new GerenciadorMusicas();
        gerenciadorPlaylists = new GerenciadorPlaylists();
        gerenciadorUsuarios = new GerenciadorUsuarios();
    }

    /**
     * Inicializa a tela
     */
    public void inicializar() {       
        // Serve para o caso em que o usuário
        // decidiu mudar o idioma da aplicação.                
        if (janela != null) {
            janela.dispose();
        }
        construirTela();
        configurarEventosTela();
        exibirTela();
    }

    /**
     * Configura os eventos da tela.
     */
    private void configurarEventosTela() {
        menuSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Utilidades.msgConfirmacao(I18N.obterConfirmacaoSaida())) {
                    //Salva os dados dos arquivos binários
                    gerenciadorMusicas.salvarDadosMusicas();
                    gerenciadorPlaylists.salvarDadosPlaylists();
                    gerenciadorUsuarios.salvarDadosUsuarios();
                    
                    System.exit(0);
                }
            }
        });

        menuIdiomaPortugues.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                I18N.alterarLocalidade(I18N.PT_BR);
                inicializar();
            }
        });

        menuIdiomaIngles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                I18N.alterarLocalidade(I18N.EN_US);
                inicializar();
            }
        });

        menuEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaAutenticacao.inicializar();
            }
        });
        
        menuVisualizarPlaylists.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaFiltroPlaylists.inicializar();
            }
        });
        
         menuVisualizarUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaFiltroUsuarios.inicializar();
            }
        });

        menuLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sessaoUsuario.invalidarSessao();
                inicializar();
            }
        });

        menuMinhasMusicas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaMinhasMusicas.inicializar();
            }
        });
        
        menuMinhasPlaylists.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaMinhasPlaylists.inicializar();
            }
        });

        menuCadastrarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaCadastroUsuario.inicializar();
            }
        });

        menuSobre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Utilidades.msgInformacao(I18N.obterMensagemSobre());
            }
        });

        janela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (Utilidades.msgConfirmacao(I18N.obterConfirmacaoSaida())) {
                    //Salva os dados dos arquivos binários
                    gerenciadorMusicas.salvarDadosMusicas();
                    gerenciadorPlaylists.salvarDadosPlaylists();
                    gerenciadorUsuarios.salvarDadosUsuarios();
                    
                    System.exit(0);
                }
            }
        });
    }

    /**
     * Contrói o Menu Início, trata internacionalização
     */
    private void construirMenuInicio() {
        menuInicio = new JMenu(I18N.obterMenuInicio());
        menuInicio.setMnemonic(I18N.obterMnemonicoMenuInicio());
        menuEntrar = new JMenuItem(I18N.obterMenuEntrar(), GerenciadorDeImagens.ENTRAR);
        menuCadastrarUsuario = new JMenuItem(I18N.obterMenuCadastrarUsuario(), GerenciadorDeImagens.CADASTRAR_USUARIO);
        menuLogout = new JMenuItem(I18N.obterMenuLogout(), GerenciadorDeImagens.LOGOUT);
        menuMinhasMusicas = new JMenuItem(I18N.obterMenuMinhasMusicas(), GerenciadorDeImagens.MINHAS_MUSICAS);
        menuMinhasPlaylists = new JMenuItem(I18N.obterMenuListasMusicas(), GerenciadorDeImagens.MINHAS_MUSICAS);
        menuVisualizarPlaylists = new JMenuItem(I18N.obterMenuVisualizaPlaylist(), GerenciadorDeImagens.MINHAS_MUSICAS);
        menuVisualizarUsuarios = new JMenuItem(I18N.obterMenuVisualizaUsuarios(), GerenciadorDeImagens.CADASTRAR_USUARIO);
        
        if (!sessaoUsuario.estahLogado()) {
            menuInicio.add(menuEntrar);
            menuInicio.add(menuCadastrarUsuario);
            menuInicio.add(menuVisualizarPlaylists);            
        } else {             
            menuInicio.add(menuMinhasMusicas);
            menuInicio.add(menuMinhasPlaylists);
            menuInicio.add(menuVisualizarPlaylists);
            menuInicio.add(menuVisualizarUsuarios);
            menuInicio.add(menuLogout);
            
        }

        menuSair = new JMenuItem(I18N.obterMenuSair(), GerenciadorDeImagens.SAIR);
        menuInicio.addSeparator();
        menuInicio.add(menuSair);
        menuPrincipal.add(menuInicio);
    }

    /**
     * Constrói o menu Idioma, trata internacionalização.
     */
    private void construirMenuIdioma() {
        menuIdioma = new JMenu(I18N.obterMenuIdioma());
        menuIdioma.setMnemonic(I18N.obterMnemonicoMenuIdioma());
        menuIdiomaPortugues = new JMenuItem(I18N.obterMenuIdiomaPortugues(), GerenciadorDeImagens.BANDEIRA_BR);
        menuIdiomaIngles = new JMenuItem(I18N.obterMenuIdiomaIngles(), GerenciadorDeImagens.BANDEIRA_GB);
        menuIdioma.add(menuIdiomaPortugues);
        menuIdioma.add(menuIdiomaIngles);
        menuPrincipal.add(menuIdioma);
    }

    /**
     * Constrói o menu Ajuda, trata internacionalização.
     */
    private void construirMenuAjuda() {
        menuAjuda = new JMenu(I18N.obterMenuAjuda());
        menuAjuda.setMnemonic(I18N.obterMnemonicoMenuAjuda());
        menuSobre = new JMenuItem(I18N.obterMenuSobre(), GerenciadorDeImagens.SOBRE);
        menuAjuda.add(menuSobre);
        menuPrincipal.add(menuAjuda);
    }

    /**
     * Constrói o menu Usuário, trata internacionalização.
     */
    private void construirMenuUsuario() {
        menuPrincipal = new JMenuBar();
        construirMenuInicio();

        construirMenuIdioma();
        construirMenuAjuda();
        janela.setJMenuBar(menuPrincipal);
    }

    /**
     * Constrói a tela.
     */
    private void construirTela() {
        janela = new JFrame(I18N.obterTituloTelaPrincipal());
        janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        construirMenuUsuario();
    }

    /**
     * Exibe a tela.
     */
    private void exibirTela() {
        janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // Permite que apenas o botão de fechar esteja disponível na janela.        
        janela.setUndecorated(true);
        janela.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        
        janela.setVisible(true);
    }

    /**
     * Método main, inicializa o programa.
     *
     * @param args Argumentos passados na execução do programa.
     */
    public static void main(String[] args) {        
        new TelaPrincipal().inicializar();
    }

    /**
     * Retorna uma referência para a janela
     *
     * @return
     */
    public JFrame obterJanela() {
        return this.janela;
    }

}
