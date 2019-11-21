/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cairu.pibiblioteca.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import br.cairu.pibiblioteca.database.CadastroLivro;

/**
 *
 * @author ALUNO TI
 */

public class GenericDao<T,Type extends Serializable> {
    
   private Class<T> persistenceClass; 
        public GenericDao(Class persistenceClass){ 
            this.persistenceClass=persistenceClass;    
        }
 //---------------------------------------------------------------------------       
       public T Salvar(T entity){
         T retorno = null;
         org.hibernate.Session session = br.cairu.pibiblioteca.dao.HibernateUtil.geSession();
         Transaction tt = null;
            try {
             tt = session.beginTransaction();
             retorno= (T) session.merge(entity);
             session.flush();
             tt.commit();
         } catch (Exception e) {
             if(tt!=null){
                tt.rollback();
             }
             throw e;
         }
     
     return retorno;
     }   
 //---------------------------------------------------------------------------

    public List<CadastroLivro> findAll() {
        Session sessao = br.cairu.pibiblioteca.dao.HibernateUtil.geSession();
        try {
            sessao.beginTransaction();
            List<CadastroLivro> books = (List<CadastroLivro>) sessao.createQuery("from CadastroLivro").list();
            return books;
        } catch (Exception e) {
            throw e;
        }finally{
            sessao.close();
        }
    }
    

 //---------------------------------------------------------------------------
      public void delete(T t) {
        Session sessao = br.cairu.pibiblioteca.dao.HibernateUtil.geSession();
        try {
            sessao.beginTransaction();
            sessao.delete(t);
            sessao.getTransaction().commit();
            sessao.close();
        } catch (Exception e) {
            throw e;
        }
    } 
 
 //---------------------------------------------------------------------------  
    public CadastroLivro findById(Integer id) {
        try {
            org.hibernate.Session session = br.cairu.pibiblioteca.dao.HibernateUtil.geSession();
            CadastroLivro book = (CadastroLivro) session.get(CadastroLivro.class, id);
            return book; 
        } catch (Exception e) {
            throw e;
        }
    }    
 //---------------------------------------------------------------------------  
}


