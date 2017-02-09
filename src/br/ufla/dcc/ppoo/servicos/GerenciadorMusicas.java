package br.ufla.dcc.ppoo.servicos;

import br.ufla.dcc.ppoo.dao.MusicaDAO;
import br.ufla.dcc.ppoo.dao.lista.MusicaDAOLista;
import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.modelo.Musica;
import br.ufla.dcc.ppoo.modelo.Playlist;
import br.ufla.dcc.ppoo.modelo.Usuario;
import br.ufla.dcc.ppoo.seguranca.SessaoUsuario;
import java.util.List;

/**
 * Classe que representa a camada de negócios de cadastro de músicas. Permite
 * cadastrar, alterar e excluir uma música.
 * 
 * @author alisson-vilaca
 */
public class GerenciadorMusicas {
    
    // atributo utilizado para podermos manipular a lista de músicas do usuário
    // logado atualmente
    private final SessaoUsuario sessaoUsuario;
    // objeto usado para recuperar a lista de musicas 
    private final MusicaDAO music ; 
    
    /**
     * Constrói o gerenciador de musicas do usuário logado, inicializando as 
     * camadas de acesso.
     */
    public GerenciadorMusicas(){
        sessaoUsuario = SessaoUsuario.obterInstancia();
        music = MusicaDAOLista.obterInstancia();
    }
    
    /**
     * Cadastra uma musica passada no sistema.
     * 
     * @param musica Música a ser cadastrada.
     * @throws Exception Exceção gerada caso a música já esteja cadastrada.
     */
    public void cadastrarMusica(Musica musica) throws Exception{                
        boolean ret = music.comparaMusicas(musica);
        if (ret) {
            throw new Exception(I18N.obterErroMusicaJaCadastrada());
        } else {
            music.adicionarMusica(musica);
        }                
    }
    
    /**
     * Altera uma música passada no sistema
     * 
     * @param musica Música que será alterada
     * @param selecionada Nome da musica a ser alterada.
     * @throws Exception Exceção gerada caso a música nova já exista na lista
     */
    public void alterarMusica(Musica musica, String selecionada) throws Exception{        
        if (musica.obterTitulo().equals(selecionada)){
             music.editarMusica(musica, selecionada, sessaoUsuario.obterUsuario());
        }else{
            boolean ret = music.comparaMusicas(musica);
            if (ret) {
                throw new Exception(I18N.obterErroMusicaJaCadastrada());
            } else { 
               music.editarMusica(musica, selecionada, sessaoUsuario.obterUsuario());                       
            }   
        }
    }        
    
    /**
     * Remove uma música passada no sistema
     * @param titulo Titulo da música a ser removida
     */
    public void removerMusica(String titulo){
        music.deletarMusica(titulo,sessaoUsuario.obterUsuario());
    }    
    
    /**
     * Retorna a lista de músicas do usuário 
     * 
     * @param login
     * @return lista de musicas do usuário
     */
    public List<Musica> obterLista(Usuario login) {        
        return music.obterListaMusica(login);
    }
    
    /**
     * Marca uma música para ser adicionada a uma playlist
     * @param u 
     * @param login 
     */
    public void marcar(Musica u, Usuario login){
        music.marcar(u,login);
    }
    
    /**
     * Desmarca uma música, usado após uma música ser adicionada
     * @param u
     * @param login 
     */
    public void desmarcar(Musica u, Usuario login){
        music.desmarcar(u,login);
    }
    
    /**
     * Obtem todas as Musicas marcadas
     * @return 
     */
    public List<Musica> obterSelecionadas() {
        return music.obterSelecionadas();        
    }
    
    /**
     * Obtem Lista de Musicas não marcadas de determinado usuario
     * @param login
     * @return 
     */
    public List<Musica> obterListaNaoMarcada(Usuario login) {        
        return music.obterListaMusicasNaoMarcadas(login);
    }        
    
    /**
     * Obtem Lista de Musicas não marcadas de determinado usuario
     * @param login
     * @return 
     */        
    public List<Musica> obterListaMarcada(Usuario login) {        
        return music.obterListaMusicasMarcadas(login);
    }
    
    /**
     * retorna o indice da musica desejada na lista de musicas
     * @param list
     * @param j
     * @return 
     */
    public int obterIndice(List<Musica> list, String j) {        
        return music.obterIndice(list,j);
    }
    
    /**
     * Marca músicas de uma playlist
     * @param p 
     */
    public void marcarMusicas (Playlist p){
            music.marcarMusicas(p);
    }
        
    /**
     * Desmarca músicas de uma playlist
     */
    public void desmarcarMusicas (){
            music.desmarcarMusicas();
    }
    
    /**
     * Tratamento se a playlist tem menos de duas músicas
     * @return 
     */
    public boolean musicasInsuficientes(){
        return music.musicasInsuficientes();
    }
    
     /**
     * Faz a busca de uma musica pelo nome e usuario passados
     * @param u
     * @param atual
     * @return 
     */
    public boolean verificaMusicas (Musica musica, Usuario atual){
        return music.verificaMusicas(musica,atual);
    }
    
    /**
     * Verifica seuma musica ja esta cadastrada para o usuario do parâmetro
     * @param musica
     * @param atual
     * @return 
     */
    public Musica buscaMusicasPeloNome(Musica u,Usuario atual){          
        return music.buscaMusicasPeloNome(u, atual);
    }
}
