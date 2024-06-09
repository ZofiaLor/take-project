package pl.kurs.komis;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class TitleEJB{
	
	@PersistenceContext(name="title")
	EntityManager manager;
	
	public void create(Title t) {
		manager.persist(t);
	}
	
	public void update(Title t) {
		manager.merge(t);
	}
	
	public void delete(Object t) {
		Object ref = manager.merge(t);
		manager.remove(ref);
	}
	
	public List<Title> get(){
		Query q = manager.createQuery("select t.idt, t.author, t.title from Title t");
		@SuppressWarnings("unchecked")
		List<Title> titles = q.getResultList();
		return titles;
	}
	
	public List<Title> findById(long idt) {
		Query q = manager.createQuery("select t.idt, t.author, t.title from Title t where t.idt = :idt");
		System.out.println(idt);
		q.setParameter("idt", idt);
		@SuppressWarnings("unchecked")
		List<Title> title = q.getResultList();
		System.out.println(title.size());
		return title;
	}
	
	public List<Long> findTitlesVolumes(long idt){
		Query q = manager.createQuery("select t from Title t where t.idt = :idt");
		q.setParameter("idt", idt);
		@SuppressWarnings("unchecked")
		List<Title> titles = q.getResultList();
		List<Long> vols = new ArrayList<Long>();
		for (Title t : titles) {
			vols.addAll(t.getVolumes());
		}
		return vols;
	}
	
	public List<Title> getByAuthor(String author){
		Query q = manager.createQuery("select t.idt, t.author, t.title from Title t where t.author like :author");
		q.setParameter("author", author);
		@SuppressWarnings("unchecked")
		List<Title> titles = q.getResultList();
		return titles;
	}

}
