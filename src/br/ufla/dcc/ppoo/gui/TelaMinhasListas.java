package br.ufla.dcc.ppoo.gui;

import br.ufla.dcc.ppoo.dao.lista.MusicaDAOLista;
import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.imagens.GerenciadorDeImagens;
import br.ufla.dcc.ppoo.modelo.Musica;
import br.ufla.dcc.ppoo.util.Utilidades;
import br.ufla.dcc.ppoo.seguranca.SessaoUsuario;
import br.ufla.dcc.ppoo.servicos.GerenciadorMusicas;
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

public class TelaMinhasListas {
    
    // referência para a tela principal
    private final TelaPrincipal telaPrincipal;
    // objeto de controle de sessão (autenticação) do usuário
    private final SessaoUsuario sessaoUsuario;
    // objeto usado para recuperar a lista de músicas
    private final MusicaDAOLista music;// tela não pode conhecer base de dados, terei que usar  gerenciador musicas ara olbter essa lista. A tela pede pro gerenciador que pede pro ListaMusicaDAO
    // referência para o gerenciador de músicas
    private final GerenciadorMusicas gerenciadorMusicas;
    // variavel para controle do botão salvar;
    private boolean novo = true;
    // variavel auxiliar que recebe o nome da musica selecionada
    private String selecionada = null;
    
    // componentes da tela
    private JDialog janela;
    private GridBagLayout layout;
    private GridBagConstraints gbc;
    private JButton btnNovaMusica;
    private JButton btnEditarMusica;
    private JButton btnDeletarMusica;
    private JButton btnSalvarMusica;
    private JButton btnCancelar;
    private JTable tbMusicas;
    private JLabel lbTitulo;
    private JLabel lbArtista;
    private JLabel lbAno;
    private JLabel lbGenero;
    private JLabel lbLetra;
    private JTextField txtTitulo;
    private JTextField txtArtista;
    private JTextField txtAno;
    private JTextField txtGenero;
    private JTextArea taLetra;

    /**
     * Constrói a tela de autenticação guardando a referência da tela principal 
     * e obtém a instância do usuário logado.
     *
     * @param telaPrincipal Referência da tela principal.
     */
    public TelaMinhasListas(TelaPrincipal telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
        sessaoUsuario = SessaoUsuario.obterInstancia();
        gerenciadorMusicas = new GerenciadorMusicas();
        music = MusicaDAOLista.obterInstancia();
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
            I18N.obterRotuloMusicaTitulo(),
            I18N.obterRotuloMusicaArtista()
        };
        // Lista utilizada para preencher a JTable com a lista de músicas do usuário
        List<String[]> lista = new ArrayList<>();
        
        // Adiciona o título e o artista de cada música na lista de preenchimento da Jtable
        music.obterListaMusica(sessaoUsuario.obterUsuario()).stream().forEach((m) -> {
            lista.add(new String[]{m.obterTitulo(),m.obterArtista()});
        });        
               
        // Modelo utilizado na Jtable de músicas
        DefaultTableModel model = new DefaultTableModel(lista.toArray(new String[lista.size()][]), titulosColunas);
   
        // JTable recebe o modelo criado, com a listas de músicas do usuário
        tbMusicas = new JTable();
        tbMusicas.setModel(model);
        tbMusicas.setPreferredScrollableViewportSize(new Dimension(500, 70));
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

        txtTitulo.setText("");
        txtArtista.setText("");
        txtAno.setText("");
        txtGenero.setText("");
        taLetra.setText("");

        txtTitulo.setEditable(false);
        txtArtista.setEditable(false);
        txtAno.setEditable(false);
        txtGenero.setEditable(false);
        taLetra.setEditable(false);

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
        txtTitulo.setEditable(false);
        txtArtista.setEditable(false);
        txtAno.setEditable(false);
        txtGenero.setEditable(false);
        taLetra.setEditable(false);

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
        tbMusicas.clearSelection();
        tbMusicas.setEnabled(false);

        txtTitulo.setText("");
        txtArtista.setText("");
        txtAno.setText("");
        txtGenero.setText("");
        taLetra.setText("");

        txtTitulo.setEditable(true);
        txtArtista.setEditable(true);
        txtAno.setEditable(true);
        txtGenero.setEditable(true);
        taLetra.setEditable(true);

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
        tbMusicas.setEnabled(false);

        txtTitulo.setEditable(true);
        txtArtista.setEditable(true);
        txtAno.setEditable(true);
        txtGenero.setEditable(true);
        taLetra.setEditable(true);

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
        JScrollPane scrollPaneTabela = new JScrollPane(tbMusicas);
        adicionarComponente(scrollPaneTabela,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                0, 0, 4, 1);

        lbTitulo = new JLabel(I18N.obterRotuloMusicaTitulo());
        adicionarComponente(lbTitulo,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                1, 0, 1, 1);

        txtTitulo = new JTextField(25);
        adicionarComponente(txtTitulo,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                1, 1, 3, 1);

        lbArtista = new JLabel(I18N.obterRotuloMusicaArtista());
        adicionarComponente(lbArtista,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                2, 0, 1, 1);

        txtArtista = new JTextField(25);
        adicionarComponente(txtArtista,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                2, 1, 3, 1);

        lbAno = new JLabel(I18N.obterRotuloMusicaAno());
        adicionarComponente(lbAno,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                3, 0, 1, 1);

        txtAno = new JTextField(8);
        adicionarComponente(txtAno,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                3, 1, 1, 1);

        lbGenero = new JLabel(I18N.obterRotuloMusicaGenero());
        adicionarComponente(lbGenero,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                3, 2, 1, 1);

        txtGenero = new JTextField(8);
        adicionarComponente(txtGenero,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                3, 3, 1, 1);

        lbLetra = new JLabel(I18N.obterRotuloMusicaLetra());
        adicionarComponente(lbLetra,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                4, 0, 1, 1);

        taLetra = new JTextArea(7, 25);
        JScrollPane scrollPaneDescricao = new JScrollPane(taLetra,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        adicionarComponente(scrollPaneDescricao,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                4, 1, 3, 1);

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

        prepararComponentesEstadoInicial();

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
    }

    /**
     * Trata a seleção de músicas na grade.
     */
    private void selecionouMusica() {        
        //É usado uma lista auxiliar que recebe somente as musicas do usuario 
        //atual para preencher os Text Fields
        List<Musica> lista = new ArrayList<>();       
        lista = music.obterListaMusica(sessaoUsuario.obterUsuario());
        
        Musica m = lista.get(tbMusicas.getSelectedRow());// Eu estava psquisand na lista uma vez paa cada campo prenchido, dessa                 
        txtTitulo.setText(m.obterTitulo());              // forma atual eu pesquiso na lista uma vez só
        txtArtista.setText(m.obterArtista());
        txtAno.setText(Integer.toString(m.obterAno()));
        txtGenero.setText(m.obterGenero());
        taLetra.setText(m.obterLetra());
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

        tbMusicas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
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
                selecionada = txtTitulo.getText();
                prepararComponentesEstadoEditouMusica();
            }
        });

        btnSalvarMusica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{                                         
                    if (carregarMusica() == null){  //Faz o tratamento de erro caso seja inserido um ano inválido           
                        throw new Exception(I18N.obterErroAnoInvalido());
                    } else if(  carregarMusica().obterTitulo().equals("") || 
                                carregarMusica().obterArtista().equals("") ||
                                carregarMusica().obterGenero().equals("") ||
                                carregarMusica().obterAno() == 0){   //faz o tratamento de erros caso o algum campo esteja em branco
                        throw new Exception(I18N.obterErroValorInvalido());
                    } else{ 
                        if (novo){
                            // chama o método adicionar musica da MúsicaDAOLista
                            gerenciadorMusicas.cadastrarMusica(carregarMusica());
                            Utilidades.msgInformacao(I18N.obterSucessoCadastroMusica());
                        } else {
                            // chama o método editar musica da MúsicaDAOLista
                            gerenciadorMusicas.alterarMusica(carregarMusica(),selecionada);
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
                tbMusicas.disable();
                novo = true;
                construirTabela();
                prepararComponentesEstadoNovaMusica();                                
            }
        });

        btnDeletarMusica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Utilidades.msgConfirmacao(I18N.obterConfirmacaoDeletar())) {
                    // chama o método remover musica da MúsicaDAOLista
                    gerenciadorMusicas.removerMusica(txtTitulo.getText());
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
            prepararComponentesEstadoInicial();      
            janela.dispose();
            inicializar();
    }    
    
    /**
     * Constrói a janela tratando internacionalização, componentes e layout.
     */
    private void construirTela() {
        janela = new JDialog();
        janela.setTitle(I18N.obterTituloTelaMinhasMusicas());
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
    
    /**
     * Retorna uma nova música a partir do dados passados.
     * 
     * @return Nova música
     */
    private Musica carregarMusica() {
        try {            
            if (txtAno.getText().equals("")){   //verifica se o ano está vazio, colocando como ano o valor 0
            return new Musica(txtTitulo.getText(),
                    txtArtista.getText(),
                    0,
                    txtGenero.getText(),
                    taLetra.getText(),
                    sessaoUsuario.obterUsuario());
            } else {
                return new Musica(txtTitulo.getText(),
                    txtArtista.getText(),
                    Integer.parseInt(txtAno.getText()),
                    txtGenero.getText(),
                    taLetra.getText(),
                    sessaoUsuario.obterUsuario());
            }
                } catch (Exception ex) {
                return null;                
        }  
    }
}
