package br.ufla.dcc.ppoo.gui;

import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.imagens.GerenciadorDeImagens;
import br.ufla.dcc.ppoo.modelo.Musica;
import br.ufla.dcc.ppoo.modelo.Playlist;
import br.ufla.dcc.ppoo.util.Utilidades;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 * Classe que representa a tela Minhas Listas
 *
 * @author Álisson Vilaça
 */

public class TelaMinhasPlaylists {
    
    private final TelaSelecaoMusicas telaSelecaoMusicas;
    // referência para a tela principal
    private final TelaPrincipal telaPrincipal;
    // objeto de controle de sessão (autenticação) do usuário
    private final SessaoUsuario sessaoUsuario;
    // referência para o gerenciador de músicas
    private final GerenciadorPlaylists gerenciadorPlaylists;
    // variavel para controle do botão salvar;
    private boolean novo = false;
    // variavel auxiliar que recebe o nome da musica selecionada
    private String selecionada = null;
    
    private List<String> listaPalavrasTemporaria;
    
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
    
    private JButton btnAdicionarPalavra;
    private JButton btnAdicionarMusica;
    private JButton btnExlcuirMusica;
    
    private JTable tbPlaylists;
    private JTable tbMusicas;
    private JTable tbPalavras;    
    
    private JLabel lbNome;
    private JLabel lbNovapalavra;
    
    //private JLabel lbArtista;
    //private JLabel lbAno;
   // private JLabel lbGenero;
    //private JLabel lbLetra;
    private JTextField txtNome;
    private JTextField txtPalavraChave;
   // private JTextField txtAno;
   // private JTextField txtGenero;
   // private JTextArea taLetra;

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
        this.telaSelecaoMusicas = new TelaSelecaoMusicas(telaPrincipal);        
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
        // Lista utilizada para preencher a JTable com a lista de músicas do usuário
        List<String[]> lista = new ArrayList<>();
        
        // Adiciona o título e o artista de cada música na lista de preenchimento da Jtable
        gerenciadorPlaylists.obterLista(sessaoUsuario.obterUsuario()).stream().forEach((p) -> {
            lista.add(new String[]{p.getNome(),p.getUsuario().obterNome()});
        });        
               
        // Modelo utilizado na Jtable de músicas
        DefaultTableModel model = new DefaultTableModel(lista.toArray(new String[lista.size()][]), titulosColunas);
   
        // JTable recebe o modelo criado, com a listas de músicas do usuário
        tbPlaylists = new JTable();
        tbPlaylists.setModel(model);
        tbPlaylists.setPreferredScrollableViewportSize(new Dimension(500, 70));
        tbPlaylists.setFillsViewportHeight(true);
    }
    
    /**
     * Constrói a janela tratando internacionalização, componentes e layout.
     */
    private void construirTabelaPalavras(boolean controle) {              
        Object[] titulosColunas = {
            I18N.obterRotuloPalavraChave()
        };
        // Lista utilizada para preencher a JTable com a lista de músicas do usuário
        List<String[]> lista = new ArrayList<>();
        
        if (!controle) {
            
            // Adiciona o palavra na lista de preenchimento da Jtable
            gerenciadorPlaylists.obterPalavras(sessaoUsuario.obterUsuario(),selecionada).stream().forEach((p) -> {
                lista.add(new String[]{p});
            }); 
        } else {
          //  System.out.println("teste");
            listaPalavrasTemporaria.stream().forEach((p) -> {
                lista.add(new String[]{p});
            });                         
        }
               
        // Modelo utilizado na Jtable de músicas
        DefaultTableModel model = new DefaultTableModel(lista.toArray(new String[lista.size()][]), titulosColunas);
   
        // JTable recebe o modelo criado, com a listas de músicas do usuário
        tbPalavras = new JTable();
        tbPalavras.setModel(model);
        tbPalavras.setPreferredScrollableViewportSize(new Dimension(200, 70));
        tbPalavras.setFillsViewportHeight(true);
    }
    
    /**
     * Constrói a janela tratando internacionalização, componentes e layout.
     */
    private void construirTabelaMusicas(boolean controle) {              
        Object[] titulosColunas = {
            I18N.obterRotuloMusicas()
        };
        // Lista utilizada para preencher a JTable com a lista de músicas do usuário
        List<String[]> lista = new ArrayList<>();
        if (!controle) {
            // Adiciona o palavra na lista de preenchimento da Jtable
            gerenciadorPlaylists.obterMusicas(sessaoUsuario.obterUsuario(),selecionada).stream().forEach((m) -> {
                lista.add(new String[]{m.obterTitulo()});
            });        
        } else {
            
            listaMusicasTemporaria.stream().forEach((m) -> {
                lista.add(new String[]{m.obterTitulo()});
            });
        }               
            
        // Modelo utilizado na Jtable de músicas
        DefaultTableModel model = new DefaultTableModel(lista.toArray(new String[lista.size()][]), titulosColunas);
   
        // JTable recebe o modelo criado, com a listas de músicas do usuário
        tbMusicas = new JTable();
        tbMusicas.setModel(model);
        tbMusicas.setPreferredScrollableViewportSize(new Dimension(200, 70));
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
        tbPlaylists.clearSelection();
        tbPlaylists.setEnabled(true);
                
        tbPalavras.clearSelection();
        tbPalavras.setEnabled(false);
        
        tbMusicas.clearSelection();
        tbMusicas.setEnabled(false);
        
        txtNome.setText("");

        txtNome.setEditable(false);
       
       
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
     * Trata o estado da tela para seleção de músicas
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
     * Trata o estado da tela para cadastro de nova música
     */
    private void prepararComponentesEstadoNovaMusica() {
        tbPlaylists.clearSelection();
        tbPlaylists.setEnabled(false);
        
        tbPalavras.disable();
        tbPalavras.clearSelection();
        tbPalavras.setEnabled(true);
        
        tbMusicas.disable();
        tbMusicas.clearSelection();
        tbMusicas.setEnabled(true);

        txtNome.setText("");

        txtNome.setEditable(true);
      
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
     * Trata o estado da tela para cadastro música editada
     */
    private void prepararComponentesEstadoEditouMusica() {
        tbPlaylists.setEnabled(false);

        tbPalavras.clearSelection();
        tbPalavras.setEnabled(true);
        
        tbMusicas.clearSelection();
        tbMusicas.setEnabled(true);
        
        txtNome.setEditable(true);

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
        
        construirTabelaPalavras(false);
        JScrollPane scrollPaneTabela1 = new JScrollPane(tbPalavras);
        adicionarComponente(scrollPaneTabela1,
                GridBagConstraints.LINE_START,
                GridBagConstraints.NONE,
                2, 0, 2, 1);
        
        construirTabelaMusicas(false);
        JScrollPane scrollPaneTabela2 = new JScrollPane(tbMusicas);
        adicionarComponente(scrollPaneTabela2,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                2, 1, 4, 1);

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
        
        btnNovaMusica = new JButton(I18N.obterBotaoNovo(),
                GerenciadorDeImagens.NOVO);
        
        lbNovapalavra = new JLabel(I18N.obterRotuloNovaPalavra());
        
        btnAdicionarPalavra = new JButton(I18N.obterBotaoAdicionar(),
                GerenciadorDeImagens.NOVO);
        
        txtPalavraChave = new JTextField(10);
        
        JPanel painel = new JPanel();
        painel.add(lbNovapalavra);
        painel.add(txtPalavraChave);
        painel.add(btnAdicionarPalavra);

        adicionarComponente(painel,
                GridBagConstraints.LINE_START,
                GridBagConstraints.NONE,
                5, 0, 2, 1);          
       
        btnAdicionarMusica = new JButton(I18N.obterBotaoNova(),
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
        
            prepararComponentesEstadoInicial();
    }

    /**
     * Trata a seleção de músicas na grade.
     */
    private void selecionouMusica() {        
        //É usado uma lista auxiliar que recebe somente as musicas do usuario 
        //atual para preencher os Text Fields        
        
        List<Playlist> lista = new ArrayList<>();       
        lista = gerenciadorPlaylists.obterLista(sessaoUsuario.obterUsuario());
        
        Playlist m = lista.get(tbPlaylists.getSelectedRow());// Eu estava psquisand na lista uma vez paa cada campo prenchido, dessa                 
        
        selecionada = m.getNome();
        novo = false; 
        atualizaTabelaPalavras();
        txtNome.setText(m.getNome());
    }

    /**
     * Configura os eventos da tela.
     */
    private void configurarEventosTela() {
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                listaPalavrasTemporaria.add(txtPalavraChave.getText());
                txtPalavraChave.setText("");               
            }
        });

        tbPalavras.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                prepararComponentesEstadoSelecaoMusica();
                selecionouMusica();
            }
        });
        
        tbMusicas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                prepararComponentesEstadoSelecaoMusica();
                selecionouMusica();
            }
        });
        
        tbPlaylists.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                prepararComponentesEstadoSelecaoMusica();
                selecionouMusica();
                novo = false;
            }
        });

        btnEditarMusica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novo = false;
                selecionada = txtNome.getText();
                prepararComponentesEstadoEditouMusica();
            }
        });

        btnSalvarMusica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerenciadorPlaylists.cadastrarPlaylist(listaPalavrasTemporaria,txtNome.getText(),sessaoUsuario.obterUsuario());

                    //Musica mus = carregarMusica();  
                  Musica mus = new Musica("T","t",1,"i","o",sessaoUsuario.obterUsuario());  
                try{                                                    
                    if (mus == null){  //Faz o tratamento de erro caso seja inserido um ano inválido           
                        throw new Exception(I18N.obterErroAnoInvalido());
                    } else if(  mus.obterTitulo().equals("") || 
                                mus.obterArtista().equals("") ||
                                mus.obterGenero().equals("") ||
                                mus.obterAno() == 0){   //faz o tratamento de erros caso o algum campo esteja em branco
                        throw new Exception(I18N.obterErroValorInvalido());
                    } else{ 
                        if (novo){
                            // chama o método adicionar musica da MúsicaDAOLista
                         //   gerenciadorMusicas.cadastrarMusica(mus);
                            Utilidades.msgInformacao(I18N.obterSucessoCadastroMusica());
                        } else {
                            // chama o método editar musica da MúsicaDAOLista
                         //   gerenciadorMusicas.alterarMusica(mus,selecionada);
                            Utilidades.msgInformacao(I18N.obterSucessoAlteracaoMusica());
                        }
                    }
                } catch (Exception ex) {
                    Utilidades.msgErro(ex.getMessage());  
                }    
                atualizaTabela();
            }
        });

        btnNovaMusica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaPalavrasTemporaria = new ArrayList<String>();
                listaMusicasTemporaria = new ArrayList<Musica>();
                tbPlaylists.disable();
                novo = true;
                construirTabela();
                construirTabelaPalavras(novo);
                construirTabelaMusicas(novo);
                prepararComponentesEstadoNovaMusica();                
            }
        });

        btnDeletarMusica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Utilidades.msgConfirmacao(I18N.obterConfirmacaoDeletar())) {
                    // chama o método remover musica da MúsicaDAOLista
                   // gerenciadorMusicas.removerMusica(txtTitulo.getText());
                    Utilidades.msgInformacao(I18N.obterSucessoRemocaoMusica());
                    atualizaTabela();
                }
            }
        });
    }

    /**
     * Função que faz o refresh na tabela, atualizando sua exibição
     */
    public void atualizaTabela(){        
        construirTabela();
        construirTabelaMusicas(novo);
        construirTabelaPalavras(novo);
        prepararComponentesEstadoInicial();      
        janela.dispose();
        inicializar();
    }    

    /**
     * Função que faz o refresh na tabela, atualizando sua exibição
     */
    public void atualizaTabelaPalavras(){        
        janela.dispose();
        inicializar();                        
        construirTabela();
        construirTabelaPalavras(novo);
        construirTabelaMusicas(novo);
        prepararComponentesEstadoNovaMusica(); 
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
