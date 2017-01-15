package br.ufla.dcc.ppoo.dao.lista;
import br.ufla.dcc.ppoo.dao.MusicaDAO;
import br.ufla.dcc.ppoo.modelo.Musica;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** 
 * Implementação da lista de músicas, que é um atributo da Classe Usuário
 *
 * @author alisson-vilaca
 */
public class MusicaDAOLista implements MusicaDAO{    
    
    // instância única da classe (Padrão de Projeto Singleton)
    private static MusicaDAOLista instancia;
    
    // lista em em memória das musicas de um usuario cadastradas
    private final List<Musica> listaMusica;

   /**
    * Constrói o objeto lista de musicas. 
    * 
    * @param auxiliar Envio um parâmetro para que o este 
    * construtor seja chamado quando o método obterInstancia é usado. Não 
    * encontrei uma forma de chamar este construtor de outra maneira.
    */ 
    public MusicaDAOLista(String auxiliar){
        listaMusica = new ArrayList<Musica>();                
    }
     
    /**
     * Retorna a instância única da classe (Padrão de Projeto Singleton)
     * 
     * @return A instância única da classe
     */
    public static MusicaDAOLista obterInstancia() {
        if (instancia == null) {
            instancia = new MusicaDAOLista("auxiliar");
        }
        return instancia;
    }
    
    /**
     * Retorna a lista de músicas do usuário 
     * 
     * @param login
     * @return lista de musicas do usuário
     */
    public List<Musica> obterListaMusica(String login) {
       List<Musica> lista = new ArrayList<>() ;
        for (Musica u : listaMusica) {
            if (u.obterUsuario().equals(login)) {
                lista.add(u);
            }
        }        
        return lista;
    }        
    
    /**
     * Faz a verificação se a música já está na lista do usuário atual
     * 
     * @param musica Musica a ser verificada
     * @return true para musicas com titulo e login iguais
     */
    @Override
    public boolean comparaMusicas (Musica musica){
        for (Musica u : listaMusica) {
            if ((musica.obterTitulo().equals(u.obterTitulo()))
                    && (musica.obterUsuario().equals(u.obterUsuario()))                     
                    ) {
                return true;
            }
        }        
        return false;
    }
    
    /**
     * Adiciona a música passada na lista
     * 
     * @param musica Musica que será adicionada na lista
     */
    @Override
    public void adicionarMusica(Musica musica) {
        listaMusica.add(musica);
    }
    
    /**
     * Edita os dados de uma musica recebida
     * 
     * @param musica Musica a ser alterada
     * @param selecionada NOme da musica que será alterada
     * @param login login do usuario atual
     */
    @Override
    public void editarMusica(Musica musica, String selecionada, String login) {       
        int indice = 0;
        for (Musica u : listaMusica) {
            if (u.obterTitulo().equals(selecionada) && (u.obterUsuario().equals(login))){
                listaMusica.set(indice, musica);        
            }
            indice ++;
        }
    }
    
    /**
     * Deleta uma musica recebida
     * 
     * @param titulo Titulo da música que será removida
     * @param login Login do usuario atual
     */
    @Override
    public void deletarMusica(String titulo, String login) {
        //Tive que usar o iterator para que não ocorresse o erro ConcurrentModificationException
        for (Iterator<Musica> i = listaMusica.iterator(); i.hasNext();) {
          Musica u = i.next();
          if (u.obterTitulo().equals(titulo)&& (u.obterUsuario().equals(login))) {
            i.remove();
          }
        }
    }        
    
}
