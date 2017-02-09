package br.ufla.dcc.ppoo.gui;

import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.imagens.GerenciadorDeImagens;
import br.ufla.dcc.ppoo.modelo.Musica;
import br.ufla.dcc.ppoo.modelo.Playlist;
import br.ufla.dcc.ppoo.util.Utilidades;
import br.ufla.dcc.ppoo.seguranca.SessaoUsuario;
import br.ufla.dcc.ppoo.servicos.GerenciadorMusicas;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 * Classe que representa a tela Minhas Playlists
 *
 * @author Álisson Vilaça
 */

public class TelaMinhasPlaylists {
    // referência para a tela de exclusão de músicas da playlist
    private final TelaExclusaoMusicasPlaylist telaExclusaoMusicasPlaylist;
    // referência para a tela de edição de palavras-chave da playlist
    private final TelaEditarPalavra telaEditarPalavra;
    // referência para a tela de seleção de músicas da playlist
    private final TelaSelecaoMusicasPlaylist telaSelecaoMusicas;
    // referência para a tela principal
    private final TelaPrincipal telaPrincipal;
    // objeto de controle de sessão (autenticação) do usuário
    private final SessaoUsuario sessaoUsuario;
    // referência para o gerenciador de playlists
    private final GerenciadorPlaylists gerenciadorPlaylists;
    // referência para o gerenciador de músicas
    private final GerenciadorMusicas gerenciadorMusicas;
    // variavel para controle do botão salvar;
    private boolean novo = false;
    // variavel auxiliar que recebe o nome da musica selecionada
    private String selecionada = null;
    //Lista de Palavras temporaria
    private List<String> listaPalavrasTemporaria;
    //Lista de Musicas temporaria
    private List<Musica> listaMusicasTemporaria;
    
    // componentes da tela
    private JDialog janela;
    private GridBagLayout layout;
    private GridBagConstraints gbc; 
    private JButton btnNovaMusica;
    private JButton btnEditarMusica;
    private JButton btnDeletarMusica;
    private JButton btnSalvarMusica;
    private JButton btnCancelar;
    private JRadioButton rbtnPublico;
    
    private JButton btnAdicionarPalavra;
    private JButton btnAdicionarMusica;
    private JButton btnExlcuirMusica;
    
    private JTable tbPlaylists;  
    
    private JLabel lbNome;
    private JLabel lbNovaPalavra;
    private JLabel lbNovaMusica;
   
    private JTextField txtNome;

    /**
     * Constrói a tela de autenticação guardando a referência da tela principal 
     * e obtém a instância do usuário logado.
     *
     * @param telaPrincipal Referência da tela principal.
     */
    public TelaMinhasPlaylists(TelaPrincipal telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
        sessaoUsuario = SessaoUsuario.obterInstancia();
        gerenciadorPlaylists = new GerenciadorPlaylists();
        telaSelecaoMusicas = new TelaSelecaoMusicasPlaylist(telaPrincipal);
        telaExclusaoMusicasPlaylist = new TelaExclusaoMusicasPlaylist(telaPrincipal);
        gerenciadorMusicas = new GerenciadorMusicas();
        telaEditarPalavra =  new TelaEditarPalavra(telaPrincipal);
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
     * Constrói a janela tratando internacionalização, componentes e layout.
     */
    private void construirTabela() {              
        Object[] titulosColunas = {
            I18N.obterRotuloNomePlaylist(),
            I18N.obterRotuloAutor()
        };
        // Lista utilizada para preencher a JTable com a lista de playlists do usuário
        List<String[]> lista = new ArrayList<>();
        
        // Adiciona o nome e o usuario de cada playlist na lista de preenchimento da Jtable
        gerenciadorPlaylists.obterLista(sessaoUsuario.obterUsuario()).stream().forEach((p) -> {
            lista.add(new String[]{p.getNome(),p.getUsuario().obterNome()});
        });        
               
        // Modelo utilizado na Jtable de playlists 
        DefaultTableModel model = new DefaultTableModel(lista.toArray(new String[lista.size()][]), titulosColunas);
   
        // JTable recebe o modelo criado, com a listas de playlist do usuário
        tbPlaylists = new JTable();
        tbPlaylists.setModel(model);
        tbPlaylists.setPreferredScrollableViewportSize(new Dimension(500, 70));
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
        tbPlaylists.clearSelection();
        tbPlaylists.setEnabled(true);
                
        txtNome.setText("");

        txtNome.setEditable(false);
       
        rbtnPublico.setEnabled(false);
        btnAdicionarMusica.setEnabled(false);
        btnAdicionarPalavra.setEnabled(false);
        btnExlcuirMusica.setEnabled(false);
        btnNovaMusica.setEnabled(true);
        btnEditarMusica.setEnabled(false);
        btnSalvarMusica.setEnabled(false);
        btnDeletarMusica.setEnabled(false);
        btnCancelar.setEnabled(true);
    }

    /**
     * Trata o estado da tela para seleção de playlists
     */
    private void prepararComponentesEstadoSelecaoMusica() {
        txtNome.setEditable(false);
        
        btnNovaMusica.setEnabled(true);
        btnEditarMusica.setEnabled(true);
        btnSalvarMusica.setEnabled(false);
        btnDeletarMusica.setEnabled(true);
        btnCancelar.setEnabled(true);
    }

    /**
     * Trata o estado da tela para cadastro de nova playlist
     */
    private void prepararComponentesEstadoNovaMusica() {
        tbPlaylists.clearSelection();
        tbPlaylists.setEnabled(false);

        txtNome.setText("");

        txtNome.setEditable(true);
        
        rbtnPublico.setSelected(false);
        rbtnPublico.setEnabled(true);
        btnAdicionarMusica.setEnabled(true);
        btnAdicionarPalavra.setEnabled(true);
        btnExlcuirMusica.setEnabled(true);
        btnNovaMusica.setEnabled(false);
        btnEditarMusica.setEnabled(false);
        btnSalvarMusica.setEnabled(true);
        btnDeletarMusica.setEnabled(false);
        btnCancelar.setEnabled(true);
    }

    /**
     * Trata o estado da tela para cadastro playlist editada
     */
    private void prepararComponentesEstadoEditouMusica() {
        tbPlaylists.setEnabled(false);
        
        txtNome.setEditable(true);
        
        rbtnPublico.setEnabled(true);
        btnAdicionarMusica.setEnabled(true);
        btnAdicionarPalavra.setEnabled(true);
        btnExlcuirMusica.setEnabled(true);
       
        btnNovaMusica.setEnabled(false);
        btnEditarMusica.setEnabled(false);
        btnSalvarMusica.setEnabled(true);
        btnDeletarMusica.setEnabled(false);
        btnCancelar.setEnabled(true);
    }

    /**
     * Adiciona os componentes da tela tratando layout e internacionalização
     */
    private void adicionarComponentes() {
        construirTabela();
        JScrollPane scrollPaneTabela = new JScrollPane(tbPlaylists);
        adicionarComponente(scrollPaneTabela,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                0, 0, 4, 1);       

        lbNome = new JLabel(I18N.obterRotuloNome());
        adicionarComponente(lbNome,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                1, 0, 1, 1);

        txtNome = new JTextField(25);
        adicionarComponente(txtNome,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                1, 1, 3, 1);
        
        lbNovaPalavra = new JLabel(I18N.obterRotuloNovaPalavra());
        JPanel painel5 = new JPanel();;
        painel5.add(lbNovaPalavra);
        adicionarComponente(painel5,
                GridBagConstraints.LINE_START,
                GridBagConstraints.NONE,
                4, 0, 2, 1); 
        
        lbNovaMusica =  new JLabel(I18N.obterRotuloNovaMusica());
        JPanel painel4 = new JPanel();;
        painel4.add(lbNovaMusica);


        adicionarComponente(painel4,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                4, 0, 4, 1);               
        
        btnAdicionarPalavra = new JButton(I18N.obterBotaoAdicionar(),
                GerenciadorDeImagens.NOVO);
                        
        rbtnPublico = new JRadioButton("Pública");
        
        JPanel painel = new JPanel();
        painel.add(btnAdicionarPalavra);
        painel.add(rbtnPublico);

        adicionarComponente(painel,
                GridBagConstraints.LINE_START,
                GridBagConstraints.NONE,
                5, 0, 2, 1);          
       
        btnAdicionarMusica = new JButton(I18N.obterBotaoAdicionar(),
                GerenciadorDeImagens.NOVO);
        
        btnExlcuirMusica = new JButton(I18N.obterBotaoExcluir(),
                GerenciadorDeImagens.NOVO);
        
        
        JPanel painelBotoes1 = new JPanel();        
        painelBotoes1.add(btnAdicionarMusica);
        painelBotoes1.add(btnExlcuirMusica);

        adicionarComponente(painelBotoes1,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                5, 0, 4, 1);
        
        
        btnNovaMusica = new JButton(I18N.obterBotaoNovo(),
                GerenciadorDeImagens.NOVO);

        btnEditarMusica = new JButton(I18N.obterBotaoEditar(),
                GerenciadorDeImagens.EDITAR);

        btnSalvarMusica = new JButton(I18N.obterBotaoSalvar(),
                GerenciadorDeImagens.OK);

        btnDeletarMusica = new JButton(I18N.obterBotaoDeletar(),
                GerenciadorDeImagens.DELETAR);

        btnCancelar = new JButton(I18N.obterBotaoCancelar(),
                GerenciadorDeImagens.CANCELAR);       
        
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnNovaMusica);
        painelBotoes.add(btnEditarMusica);
        painelBotoes.add(btnSalvarMusica);
        painelBotoes.add(btnDeletarMusica);
        painelBotoes.add(btnCancelar);

        adicionarComponente(painelBotoes,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                6, 0, 4, 1);
        if (!gerenciadorPlaylists.isImportou()){//se uma musica não está sendo importada abre a tela normalmente, se não 
            prepararComponentesEstadoInicial(); // a tela é preparada com a playlist que vai ser importada 
        } else {
            prepararComponentesEstadoEditouMusica();   
            arrumaImportada();
        }
    }

    /**
     * Trata a seleção de playlist na grade.
     */
    private void selecionouMusica() {        
        //É usado uma lista auxiliar que recebe somente as playlists do usuario 
        //atual para preencher os componentes
        
        List<Playlist> lista = new ArrayList<>();       
        lista = gerenciadorPlaylists.obterLista(sessaoUsuario.obterUsuario());
        
        Playlist m = lista.get(tbPlaylists.getSelectedRow());                
        //Seta a lista temporaria para a selecionada na tela
        gerenciadorPlaylists.setarEditada(m);        
        gerenciadorMusicas.marcarMusicas(m);
        selecionada = m.getNome();
        rbtnPublico.setSelected(m.isVisilidade());
        
        novo = false; 
        txtNome.setText(m.getNome());
    }

    private void arrumaImportada(){ // meodo que organiza a tela çom a playlist que está sendo importada
        //Importa todas as musicas com nome diferente das já cadastradas pelo usuario
        gerenciadorPlaylists.arrumaMusicasImportadas(sessaoUsuario.obterUsuario()); 
                        
        tbPlaylists.disable();
        Playlist p = gerenciadorPlaylists.getExibida();
        
        listaMusicasTemporaria = gerenciadorPlaylists.getMusicasImportadas(sessaoUsuario.obterUsuario());
        
        listaPalavrasTemporaria = p.getPalavras();
        
        Playlist nova = new Playlist(p.getNome(), sessaoUsuario.obterUsuario(), listaPalavrasTemporaria, listaMusicasTemporaria, true);
        
        txtNome.setText(nova.getNome());
        gerenciadorPlaylists.setarEditada(nova);
        gerenciadorMusicas.marcarMusicas(nova);
        novo = true;
        rbtnPublico.setSelected(nova.isVisilidade());
        gerenciadorPlaylists.zerarExibida();        
        
    }
    
    
    /**
     * Configura os eventos da tela.
     */
    private void configurarEventosTela() {
        btnExlcuirMusica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaExclusaoMusicasPlaylist.inicializar(); 
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerenciadorPlaylists.setImportou(false);
                janela.dispose();
            }
        });
        
        btnAdicionarMusica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaSelecaoMusicas.inicializar();                
            }
        });
        
        btnAdicionarPalavra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaEditarPalavra.inicializar();         
            }
        }); 
       
        tbPlaylists.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                prepararComponentesEstadoSelecaoMusica();
                selecionouMusica();
            }
        });

        btnEditarMusica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novo = false;
                selecionada = txtNome.getText();                
                Playlist p = new Playlist(selecionada, sessaoUsuario.obterUsuario(), 
                        gerenciadorPlaylists.obterPalavras(sessaoUsuario.obterUsuario(), selecionada), 
                        gerenciadorPlaylists.obterMusicas(sessaoUsuario.obterUsuario(), selecionada),
                        rbtnPublico.isSelected());                
                gerenciadorPlaylists.setarEditada(p);
                gerenciadorMusicas.marcarMusicas(p);
                prepararComponentesEstadoEditouMusica();     
                
            }
        });

        btnSalvarMusica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                               
                try{                                                    
                    if (txtNome.getText() == ""){  //Faz o tratamento de erro caso nome esteja em branco          
                        throw new Exception(I18N.obterErroNomeEmBranco());
                    } else if(gerenciadorPlaylists.obterPlaylistTemporaria().getPalavras().size() == 0){   //faz o tratamento de erros caso nao tenha palavras-chave
                        throw new Exception(I18N.obterErroPalavrasInsuficientes());
                    } else if(gerenciadorMusicas.musicasInsuficientes()){   //faz o tratamento de erros caso nao tenha musicas suficientes
                        throw new Exception(I18N.obterErroMusicasInsuficientes());                                        
                    } else{ 
                        if (novo) { 
                            //preenche a playlist temporaria com os dados da tela
                            boolean publi;
                            if (rbtnPublico.isSelected()){
                                publi = true;
                            } else {                                
                                publi = false;
                            }
                            gerenciadorPlaylists.setarEditada(new Playlist(txtNome.getText(),
                            sessaoUsuario.obterUsuario(), 
                            listaPalavrasTemporaria, 
                            listaMusicasTemporaria,
                            publi)); 
                            //Cadastra a playlist
                            gerenciadorPlaylists.cadastrarPlaylist();
                            gerenciadorPlaylists.setImportou(false);
                            Utilidades.msgInformacao(I18N.obterSucessoCadastroPlaylist());
                        } else {
                            //Edita a PLaylist                            
                            gerenciadorPlaylists.editarPlaylist(selecionada,txtNome.getText(),rbtnPublico.isSelected());
                            gerenciadorPlaylists.setImportou(false);
                            Utilidades.msgInformacao(I18N.obterSucessoEdicaoPlaylist());
                        } 
                            }
                } catch (Exception ex) {
                    gerenciadorPlaylists.setImportou(false);
                    Utilidades.msgErro(ex.getMessage());  
                }    
                //desmarca as musicas que estavam selecionadas durante o salvamento
                gerenciadorMusicas.desmarcarMusicas();
                atualizaTabela();
            }
        });

        btnNovaMusica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tbPlaylists.disable();
                construirTabela();
                listaPalavrasTemporaria = new ArrayList<String>();
                listaMusicasTemporaria = new ArrayList<Musica>();
                novo = true;
                //seta a playlist temporario com uma nova playlist em branco
                gerenciadorPlaylists.setarEditada(new Playlist("", sessaoUsuario.obterUsuario(), listaPalavrasTemporaria, listaMusicasTemporaria,false));         
                prepararComponentesEstadoNovaMusica(); 
                gerenciadorMusicas.desmarcarMusicas();
            }
        });

        btnDeletarMusica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Utilidades.msgConfirmacao(I18N.obterConfirmacaoDeletar())) {
                    // chama o método remover playlist da PlaylistDAOLista
                    gerenciadorPlaylists.removerPlaylist(txtNome.getText());
                    Utilidades.msgInformacao(I18N.obterSucessoRemocaoPlaylist());
                    atualizaTabela();
                }
            }
        });
    }

    /**
     * Função que faz o refresh na tabela, atualizando sua exibição
     */
    public void atualizaTabela(){ 
        gerenciadorMusicas.desmarcarMusicas();
        construirTabela();
        prepararComponentesEstadoInicial();      
        janela.dispose();
        inicializar();
    }    
    
    /**
     * Constrói a janela tratando internacionalização, componentes e layout.
     */
    private void construirTela() {
        janela = new JDialog();
        janela.setTitle(I18N.obterTituloTelaMinhasPlaylists());
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
