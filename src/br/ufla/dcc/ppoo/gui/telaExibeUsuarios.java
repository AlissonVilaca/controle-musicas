
package br.ufla.dcc.ppoo.gui;

import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.imagens.GerenciadorDeImagens;
import br.ufla.dcc.ppoo.modelo.Playlist;
import br.ufla.dcc.ppoo.modelo.Usuario;
import br.ufla.dcc.ppoo.servicos.GerenciadorPlaylists;
import br.ufla.dcc.ppoo.servicos.GerenciadorUsuarios;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pichau
 */
public class telaExibeUsuarios {
  // referência para a tela principal
    private final TelaPrincipal telaPrincipal;
            
    // componentes da tela
    private JDialog janela;
    private GridBagLayout layout;
    private GridBagConstraints gbc;
    private JTextArea txaExibicao;
    private JTable tbPlaylists;
    
    //referencia para a tela de exibição de Playlist quando o usuario está logado
    private TelaExibePlaylistLogado telaExibePlaylistLogado;
    
    private JButton btnCancelar;    
    
    // referência para o gerenciador de playlists
    private final GerenciadorPlaylists gerenciadorPlaylist;
    // referência para o gerenciador de Usuarios
    private final GerenciadorUsuarios gerenciadorUsuarios;
    

    /**
     * Constrói a tela de filtro aguardando a referência da tela principal
     * e criando o gerenciador de usuários.
     * 
     * @param 
     */
    public telaExibeUsuarios(TelaPrincipal telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
        gerenciadorPlaylist = new GerenciadorPlaylists();
        gerenciadorUsuarios = new GerenciadorUsuarios();
        telaExibePlaylistLogado = new TelaExibePlaylistLogado(telaPrincipal);
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
          //  I18N.obterRotuloUsuario()
        };
        // Lista utilizada para preencher a JTable com a lista de palavras da playlist
        List<String[]> lista = new ArrayList<>();
        if (listaPlay != null) {
            // Adiciona o palavra lista de preenchimento da Jtable
             listaPlay.stream().forEach((p) -> {
                lista.add(new String[]{p.getNome()});
            });
        }
               
        // Modelo utilizado na Jtable de palavras
        DefaultTableModel model = new DefaultTableModel(lista.toArray(new String[lista.size()][]), titulosColunas);
   
        tbPlaylists = new JTable();
        tbPlaylists.setModel(model);
        tbPlaylists.setPreferredScrollableViewportSize(new Dimension(270,150));
        tbPlaylists.setFillsViewportHeight(true);
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
                
        txaExibicao.setText(gerenciadorUsuarios.getUsuarioExibido());
         
        
        txaExibicao.setEditable(false);
       
        btnCancelar.setEnabled(true);
    }    

     /**
     * Trata a seleção de playlist na grade.
     */
    private void selecionouPlaylist() {        
        //É usado uma lista auxiliar que recebe as playlists que contem qualquer 
        //uma das palavras digitadas pelo usuario ou no nome ou nas palavras-chave

        List<Playlist> lista = new ArrayList<>();       
        lista = gerenciadorPlaylist.getPlaylists();

        Playlist m = lista.get(tbPlaylists.getSelectedRow());                
        //Seta a lista temporaria que será exibida
        gerenciadorPlaylist.setarExibida(m);   //seta /seta a variavel de usuario exibido como o usuario que o usuario selecionou na tabela             
        telaExibePlaylistLogado.inicializar();
    }
    
     
    /**
     * Adiciona os componentes da tela tratando layout e internacionalização
     */
    private void adicionarComponentes() {    
        construirTabela(gerenciadorPlaylist.getPlaylists());                
        JScrollPane scrollPaneTabela = new JScrollPane(tbPlaylists);
        
        btnCancelar = new JButton(I18N.obterBotaoFechar(),
                GerenciadorDeImagens.CANCELAR);
        
        txaExibicao = new JTextArea(11, 25);
        
        JPanel painel1 = new JPanel();
        painel1.setLayout(new FlowLayout());
        painel1.add(txaExibicao);
        painel1.add(scrollPaneTabela);
        
        adicionarComponente(painel1,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                1, 0, 4, 1);                
        
        adicionarComponente(btnCancelar,
                GridBagConstraints.LAST_LINE_END,
                GridBagConstraints.NONE,
                2, 0, 4, 1);
       
        prepararComponentesEstadoInicial();
    }
    
    /**
     * Configura os eventos da tela.
     */
    private void configurarEventosTela() {
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerenciadorPlaylist.zerarExibida();
                janela.dispose();                
            }
        });
        
        tbPlaylists.getSelectionModel().addListSelectionListener(new ListSelectionListener() {//chama a proximo tela de exibição da playlist
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selecionouPlaylist();
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
