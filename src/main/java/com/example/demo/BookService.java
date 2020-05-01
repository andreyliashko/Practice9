package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    @Transactional
     public Book createUser(String name, String ibsn, String author){
        Book b=new Book(name, ibsn, author);
       bookRepository.saveAndFlush(b);
       return b;
    }
    @Transactional
    public Book createUser(Book b){
        bookRepository.saveAndFlush(b);
        return b;
    }
    public Book getBookById(Integer id){
        return bookRepository.findFirstById(id);
    }

    public Book getByName(String name){
        return bookRepository.findFirstByNameLike(name);
    }
    public Book getByIbsn(String ibsn){
        return bookRepository.findFirstByIbsnLike(ibsn);
    }
    public Book getByAuthor(String author){
        return bookRepository.findFirstByAuthorLike(author);
    }
    public Book getWord(String word){
       return  bookRepository.findTwo(word).get(0);
    }
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public boolean contains(Book b){
        List<Book> list = bookRepository.findTwo(b.getIbsn());
        if(list.size()>0) return true;
        list=bookRepository.findTwo(b.getName());
        if(list.size()>0) return true;
        return false;

    }




   /*// private final EntityManager entityManager;
    @Transactional
    public Book createUser(String name, String ibsn, String author){
        Book book=new Book(name, ibsn, author);
      return entityManager.merge(book);
    };
    public Book getBookById(Integer id){
        return entityManager.find(Book.class, id);
    }
    public List<Book> getAllUsers(){
        return entityManager.createQuery("FROM Book", Book.class).getResultList();
    }
    public long count(){
        return  entityManager.createQuery("select COUNT(b) FROM Book b", Long.class).getSingleResult();
    }
    public Book findByName(String name){
        return entityManager.createNamedQuery(Book.Find_By_Name, Book.class).setParameter("name", name).getSingleResult();
    }
    public Book findByIbsn(String ibsn){
        return entityManager.createNamedQuery(Book.Find_By_Ibsn, Book.class).setParameter("ibsn", ibsn).getSingleResult();
    }
    public Book findByAuthor(String author){
        return entityManager.createNamedQuery(Book.Find_By_Author, Book.class).setParameter("author", author).getSingleResult();
    }
*/
}
