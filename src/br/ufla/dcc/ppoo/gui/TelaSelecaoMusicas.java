package br.ufla.dcc.ppoo.gui;

import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.imagens.GerenciadorDeImagens;
import br.ufla.dcc.ppoo.modelo.Musica;
import br.ufla.dcc.ppoo.modelo.Usuario;
import br.ufla.dcc.ppoo.seguranca.SessaoUsuario;
import br.ufla.dcc.ppoo.servicos.GerenciadorMusicas;
import br.ufla.dcc.ppoo.util.Utilidades;
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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aluno
 */
public class TelaSelecaoMusicas {

    // referência para a tela principal
    private final TelaPrincipal telaPrincipal;
            
    // componentes da tela
    private JDialog janela;
    private GridBagLayout layout;
    private GridBagConstraints gbc;
    private JLabel lbLogin;
    private JLabel lbSenha;
    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JButton btnEntrar;
    private JButton btnCancelar;
    
    // referência para o gerenciador de músicas
    private final GerenciadorMusicas gerenciadorMusicas;
    // objeto de controle de sessão (autenticação) do usuário
    private final SessaoUsuario sessaoUsuario;
    
    private JTable tbMusicas;

    /**
     * Constrói a tela de autenticação guardando a referência da tela principal
     * e criando o gerenciador de usuários.
     * 
     * @param l.
     */
    public TelaSelecaoMusicas(TelaPrincipal telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
        gerenciadorMusicas = new GerenciadorMusicas();
        sessaoUsuario = SessaoUsuario.obterInstancia();
        
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

     private void construirTabela() {              
        Object[] titulosColunas = {
            I18N.obterRotuloMusicaTitulo(),
            I18N.obterRotuloMusicaArtista()
        };
        // Lista utilizada para preencher a JTable com a lista de músicas do usuário
        List<String[]> lista = new ArrayList<>();
        
        // Adiciona o título e o artista de cada música na lista de preenchimento da Jtable
        gerenciadorMusicas.obterLista(sessaoUsuario.obterUsuario()).stream().forEach((m) -> {
            lista.add(new String[]{m.obterTitulo(),m.obterArtista()});
        });        
               
        // Modelo utilizado na Jtable de músicas
        DefaultTableModel model = new DefaultTableModel(lista.toArray(new String[lista.size()][]), titulosColunas);
   
        // JTable recebe o modelo criado, com a listas de músicas do usuário
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
     * Adiciona os componentes da tela tratando layout e internacionalização
     */
    private void adicionarComponentes() {        
        construirTabela();
        JScrollPane scrollPaneTabela = new JScrollPane(tbMusicas);
        adicionarComponente(scrollPaneTabela,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                0, 0, 4, 1);
        
       /* lbLogin = new JLabel(I18N.obterRotuloUsuarioLogin());
        adicionarComponente(lbLogin,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                0, 0, 1, 1);

        lbSenha = new JLabel(I18N.obterRotuloUsuarioSenha());
        adicionarComponente(lbSenha,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                1, 0, 1, 1);

        txtLogin = new JTextField(25);
        adicionarComponente(txtLogin,
                GridBagConstraints.LINE_START,
                GridBagConstraints.NONE,
                0, 1, 1, 1);

        txtSenha = new JPasswordField(10);
        adicionarComponente(txtSenha,
                GridBagConstraints.LINE_START,
                GridBagConstraints.NONE,
                1, 1, 1, 1);*/

        btnEntrar = new JButton(I18N.obterBotaoEntrar(),
                GerenciadorDeImagens.OK);

        btnCancelar = new JButton(I18N.obterBotaoCancelar(),
                GerenciadorDeImagens.CANCELAR);

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnEntrar);
        painelBotoes.add(btnCancelar);

        adicionarComponente(painelBotoes,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                2, 0, 2, 1);
    }

    /**
     * Retorna um novo usuário a partir do login e senha passados.
     * 
     * @return Usuário criado.
     */
    private Usuario carregarUsuario() {
        return new Usuario(txtLogin.getText(), txtSenha.getPassword());
    }

    /**
     * Configura os eventos da tela.
     */
    private void configurarEventosTela() {
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Musica> lista = new ArrayList<>();       
                    lista = gerenciadorMusicas.obterLista(sessaoUsuario.obterUsuario());
        
                    Musica m = lista.get(tbMusicas.getSelectedRow());
                    gerenciadorMusicas.marcar(m,sessaoUsuario.obterUsuario());
                    
                // gerenciadorUsuarios.autenticarUsuario(carregarUsuario());
                    //telaPrincipal.inicializar();
                    janela.dispose();
                } catch (Exception ex) {
                    Utilidades.msgErro(ex.getMessage());
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
