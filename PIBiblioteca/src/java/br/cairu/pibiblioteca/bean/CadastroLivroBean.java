
package br.cairu.pibiblioteca.bean;

import br.cairu.pibiblioteca.database.CadastroLivro;
import br.cairu.pibiblioteca.dao.CadastroLivroDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



/**
 *
 * @author ALUNO TI
 */
@ManagedBean
@ViewScoped
public class CadastroLivroBean implements Serializable{
    
      private CadastroLivro cadastroLivro;
      private List<CadastroLivro> cadastroLivros;
      private CadastroLivroDao cadastroLivroDao; 
            
      @PostConstruct
      public void init() {
          this.cadastroLivro = new CadastroLivro();
          this.cadastroLivros = new ArrayList<>();
          this.cadastroLivroDao = new CadastroLivroDao();
          this.cadastroLivros = this.cadastroLivroDao.findAll();
          System.out.println("qtd = "+ this.cadastroLivros.size());
          for (CadastroLivro livro : cadastroLivros) {
              System.out.println("livro = "+ livro.getNomedolivro());
          }
      }
      
      public void salvar() {
        try {
          this.cadastroLivroDao = new CadastroLivroDao();
          this.cadastroLivroDao.Salvar(cadastroLivro);
          System.out.println("Gravado com sucesso.");
          init();
        } catch (Exception e) {
          System.out.println("Erro ao salvar ou editar "+e.getMessage());
        }
      }
   //--------------------------------------------------------------------------- 
      public void edicao(CadastroLivro livro) {
        try {
          this.cadastroLivro = livro;
        } catch (Exception e) {
          System.out.println("Erro na buscar "+e.getMessage());
        }
      }

   //---------------------------------------------------------------------------
      public void excluir(CadastroLivro livro) { 
        try {
          this.cadastroLivroDao = new CadastroLivroDao();
          CadastroLivro book = this.cadastroLivroDao.findById(livro.getId());
          this.cadastroLivroDao.delete(book);
          System.out.println("Excluído com sucesso.");
          init();
        } catch (Exception e) {
          System.out.println("Erro ao excluir "+e.getMessage());
        }
      }



   
   //--------------------------------------------------------------------------- 
   
    public CadastroLivro getCadastroLivro() {
        return cadastroLivro;
    }

    public void setCadastroLivro(CadastroLivro cadastroLivro) {
        this.cadastroLivro = cadastroLivro;
    }

    public List<CadastroLivro> getCadastroLivros() {
        return cadastroLivros;
    }

    public void setCadastroLivros(List<CadastroLivro> cadastroLivros) {
        this.cadastroLivros = cadastroLivros;
    }

    public CadastroLivroDao getCadastroLivroDao() {
        return cadastroLivroDao;
    }

    public void setCadastroLivroDao(CadastroLivroDao cadastroLivroDao) {
        this.cadastroLivroDao = cadastroLivroDao;
    }
  
     
}
