package br.ufla.dcc.ppoo.gui;

import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.imagens.GerenciadorDeImagens;
import br.ufla.dcc.ppoo.seguranca.SessaoUsuario;
import br.ufla.dcc.ppoo.servicos.GerenciadorPlaylists;
import br.ufla.dcc.ppoo.util.Utilidades;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author alisson-vilaca
 */
public class TelaExibePlaylistLogado {
      
    // referência para a tela principal
    private final TelaPrincipal telaPrincipal;
            
    // componentes da tela
    private JDialog janela;
    private GridBagLayout layout;
    private GridBagConstraints gbc;
    private JTextArea txaExibicao;
    
    private JButton btnAvaliar;
    private JButton btnImportar;
    private JButton btnComentar;
    private JButton btnCancelar;    
    
    // referência para o gerenciador de playlists
    private final GerenciadorPlaylists gerenciadorPlaylist;
    // objeto de controle de sessão (autenticação) do usuário
    private final SessaoUsuario sessaoUsuario;
    //
    private final TelaMinhasPlaylists telaMinhasPlaylists;

    /**
     * Constrói a tela de filtro aguardando a referência da tela principal
     * e criando o gerenciador de usuários.
     * 
     * @param 
     */
    public TelaExibePlaylistLogado(TelaPrincipal telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
        sessaoUsuario = SessaoUsuario.obterInstancia();
        gerenciadorPlaylist = new GerenciadorPlaylists();
        telaMinhasPlaylists = new TelaMinhasPlaylists(telaPrincipal);
    }

    /**
     * Inicializa a tela, construindo seus componentes, configurando os eventos
     * e, ao final, exibe a tela.
     */
    public void inicializar() {        
        construirTela();
        configurarEventosTela();
        exibirTela();        
    }
    
    /**
     * Adiciona um componente à tela.
     */
    private void adicionarComponente(Component c,
            int anchor, int fill, int linha,
            int coluna, int largura, int altura) {
        gbc.anchor = anchor;
        gbc.fill = fill;
        gbc.gridy = linha;
        gbc.gridx = coluna;
        gbc.gridwidth = largura;
        gbc.gridheight = altura;
        gbc.insets = new Insets(5, 5, 5, 5);
        layout.setConstraints(c, gbc);
        janela.add(c);
    }
    
    /**
     * Trata o estado inicial da tela
     */
     private void prepararComponentesEstadoInicial() {
                
        txaExibicao.setText(gerenciadorPlaylist.getPlaylistExibida());

        txaExibicao.setEditable(false);
       
        btnAvaliar.setEnabled(true);
        btnImportar.setEnabled(true);
        btnComentar.setEnabled(true);
        btnCancelar.setEnabled(true);
    }    

    /**
     * Adiciona os componentes da tela tratando layout e internacionalização
     */
    private void adicionarComponentes() {        

        btnAvaliar = new JButton(I18N.obterBotaoAvaliar(),
                GerenciadorDeImagens.OK);
        
        btnImportar = new JButton(I18N.obterBotaoImportar(),
                GerenciadorDeImagens.OK);
        
        btnComentar = new JButton(I18N.obterBotaoComentar(),
                GerenciadorDeImagens.OK);

        btnCancelar = new JButton(I18N.obterBotaoCancelar(),
                GerenciadorDeImagens.CANCELAR);
        
        txaExibicao = new JTextArea(25, 45);
        
        adicionarComponente(txaExibicao,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                1, 0, 4, 1);
        
        JPanel painel1 = new JPanel();
        
        painel1.add(btnAvaliar);
        painel1.add(btnImportar);
        painel1.add(btnComentar);
        painel1.add(btnCancelar);
        
        adicionarComponente(painel1,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                2, 0, 4, 1);
       
        prepararComponentesEstadoInicial();
    }

    /**
     * Configura os eventos da tela.
     */
    private void configurarEventosTela() {
        
        btnAvaliar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                ;
                janela.dispose();
                inicializar();
            }
        });
        
        btnImportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sessaoUsuario.obterUsuario() != gerenciadorPlaylist.getExibida().getUsuario()){
                    gerenciadorPlaylist.setImportou(true);
                    janela.dispose();
                    telaMinhasPlaylists.inicializar();    
                } else {
                    Utilidades.msgErro("Você não pode Importar uma lista sua!");
                }    
            }
        });
        
        btnComentar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                janela.dispose();
                inicializar();
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerenciadorPlaylist.zerarExibida();
                janela.dispose();                
            }
        });
    }

    /**
     * Constrói a janela tratando internacionalização, componentes e layout.
     */
    private void construirTela() {
        janela = new JDialog();
        janela.setTitle(I18N.obterTituloTelaAutenticacao());
        layout = new GridBagLayout();
        gbc = new GridBagConstraints();
        janela.setLayout(layout);
        adicionarComponentes();
        janela.pack();
    }

    /**
     * Exibe a tela.
     */
    private void exibirTela() {
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setLocationRelativeTo(telaPrincipal.obterJanela());
        janela.setModal(true);
        janela.setVisible(true);
        janela.setResizable(false);
    }          
}
