package br.ufla.dcc.ppoo.gui;

import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.imagens.GerenciadorDeImagens;
import br.ufla.dcc.ppoo.modelo.Playlist;
import br.ufla.dcc.ppoo.seguranca.SessaoUsuario;
import br.ufla.dcc.ppoo.servicos.GerenciadorPlaylists;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 * Tela de Filtro de PLaylists
 * @author alisson-vilaca
 */
public class TelaFiltroPlaylists {
     
    // referência para a tela principal
    private final TelaPrincipal telaPrincipal;
            
    // componentes da tela
    private JDialog janela;
    private GridBagLayout layout;
    private GridBagConstraints gbc;
    private JTextField txtPalavra;
    
  //  private JButton btnBuscar;
    private JButton btnAdicionar;
    private JButton btnSelecionar;
    private JButton btnCancelar;
    
    //lista de palavras que seão usadas na busca
    private List<String> listaPalavras;
    
    // referência para o gerenciador de playlists
    private final GerenciadorPlaylists gerenciadorPlaylist;
    // objeto de controle de sessão (autenticação) do usuário
    private final SessaoUsuario sessaoUsuario;
    //referencia para a tela de exibição de Playlist quando o usuario está logado
    private TelaExibePlaylistLogado telaExibePlaylistLogado;
    //referencia para a tela de exibição de Playlist quando o usuario não está logado.
    //Optei por fazer duas telas pois não consegui fazer o controle corretamente
    // da visibilidade dos botões.
    private TelaExibePlaylistDeslogado telaExibePlaylistDeslogado;
    
    private JTable tbMusicas;

    /**
     * Constrói a tela de filtro aguardando a referência da tela principal
     * e criando o gerenciador de usuários.
     * 
     * @param 
     */
    public TelaFiltroPlaylists(TelaPrincipal telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
        sessaoUsuario = SessaoUsuario.obterInstancia();
        gerenciadorPlaylist = new GerenciadorPlaylists();
        listaPalavras = new ArrayList<>();
        telaExibePlaylistLogado = new TelaExibePlaylistLogado(telaPrincipal);
        telaExibePlaylistDeslogado = new TelaExibePlaylistDeslogado(telaPrincipal);
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

     private void construirTabela(List<Playlist> listaPlay) {              
        Object[] titulosColunas = {
            I18N.obterRotuloPlaylist(),
            I18N.obterRotuloUsuario()
        };
        // Lista utilizada para preencher a JTable com a lista de palavras da playlist
        List<String[]> lista = new ArrayList<>();
        if (listaPlay != null) {
            // Adiciona o palavra lista de preenchimento da Jtable
             listaPlay.stream().forEach((p) -> {
                lista.add(new String[]{p.getNome(),p.getUsuario().obterNome()});
            });
        }
               
        // Modelo utilizado na Jtable de palavras
        DefaultTableModel model = new DefaultTableModel(lista.toArray(new String[lista.size()][]), titulosColunas);
   
        tbMusicas = new JTable();
        tbMusicas.setModel(model);
        tbMusicas.setPreferredScrollableViewportSize(new Dimension(300, 70));
        tbMusicas.setFillsViewportHeight(true);
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
        tbMusicas.clearSelection();
        tbMusicas.setEnabled(true);
                
        txtPalavra.setText("");

        txtPalavra.setEditable(true);
       
        btnSelecionar.setEnabled(false);
        btnAdicionar.setEnabled(true);
        btnCancelar.setEnabled(true);
    }
     
     /**
     * Trata o estado buscou
     */
     private void prepararComponentesEstadoBuscou() {
        tbMusicas.setEnabled(true);
                
        txtPalavra.setText("");

        txtPalavra.setEditable(false);

        btnSelecionar.setEnabled(true);
        btnAdicionar.setEnabled(false);
        btnCancelar.setEnabled(true);
    }

    /**
     * Adiciona os componentes da tela tratando layout e internacionalização
     */
    private void adicionarComponentes() {        
        construirTabela(gerenciadorPlaylist.buscaPlaylists(listaPalavras));
        JScrollPane scrollPaneTabela = new JScrollPane(tbMusicas);
        adicionarComponente(scrollPaneTabela,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                0, 0, 4, 1);
        btnSelecionar = new JButton(I18N.obterBotaoSelecionar(),
                GerenciadorDeImagens.OK);

        btnCancelar = new JButton(I18N.obterBotaoCancelar(),
                GerenciadorDeImagens.CANCELAR);
        
        txtPalavra = new JTextField(16);
        btnAdicionar = new JButton(I18N.obterBotaoAdicionar(),
                GerenciadorDeImagens.OK);
        
        JPanel painel1 = new JPanel();
        
        painel1.add(txtPalavra);
        painel1.add(btnAdicionar);
        
        adicionarComponente(painel1,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                2, 0, 4, 1);

        adicionarComponente(btnSelecionar,
                GridBagConstraints.LINE_START,
                GridBagConstraints.NONE,
                3, 0, 2, 1);
        
        adicionarComponente(btnCancelar,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                3, 2, 2, 1);
        
       
        prepararComponentesEstadoInicial();
    }

     /**
     * Trata a seleção de playlist na grade.
     */
    private void selecionouMusica() {        
        //É usado uma lista auxiliar que recebe somente as playlists do usuario 
        //atual para preencher os componentes

        List<Playlist> lista = new ArrayList<>();       
        lista = gerenciadorPlaylist.buscaPlaylists(listaPalavras);

        Playlist m = lista.get(tbMusicas.getSelectedRow());                
        //Seta a lista temporaria que será exibida
        gerenciadorPlaylist.setarExibida(m);                
    }
    
    /**
     * Configura os eventos da tela.
     */
    private void configurarEventosTela() {
        
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                listaPalavras.add(txtPalavra.getText());
                txtPalavra.setText("");
                txtPalavra.requestFocus();
                janela.dispose();
                inicializar();
            }
        });
        
        btnSelecionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.dispose();
                if (sessaoUsuario.estahLogado()){
                    telaExibePlaylistLogado.inicializar();
                } else {
                    telaExibePlaylistDeslogado.inicializar();
                }
                listaPalavras.removeAll(listaPalavras);
                //gerenciadorPlaylist.zerarExibida();                 
            }
        });

        tbMusicas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                prepararComponentesEstadoBuscou();
                selecionouMusica();
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaPalavras.removeAll(listaPalavras);
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
