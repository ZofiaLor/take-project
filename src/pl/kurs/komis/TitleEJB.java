package pl.kurs.komis;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class TitleEJB {

	@PersistenceContext(name = "title")
	EntityManager manager;

	public void create(TitleDTO t) {
		Title title = new Title();
		title.setAuthor(t.getAuthor());
		title.setTitle(t.getTitle());
		title.setVolumes(new ArrayList<Volume>());
		manager.persist(title);
	}

	public void update(TitleDTO t) {
		Title title = manager.find(Title.class, t.getIdt());
		if (t.getAuthor() != null)
			title.setAuthor(t.getAuthor());
		if (t.getTitle() != null)
			title.setTitle(t.getTitle());
		manager.merge(title);
	}

	public void delete(long idt) {
		Title title = manager.find(Title.class, idt);
		manager.remove(title);
	}

	public List<TitleDTO> get() {

		Query q = manager.createQuery("select t from Title t");
		List<Title> titlesRaw = q.getResultList();
		List<TitleDTO> titles = titlesRaw.stream().map(x -> titleToTitleDTO(x)).collect(Collectors.toList());
		return titles;
	}

	public TitleDTO findById(long idt) {
		Title t = manager.find(Title.class, idt);
		return this.titleToTitleDTO(t);
	}

	public List<Long> findTitlesVolumes(long idt) {
		Title title = manager.find(Title.class, idt);
		List<Long> v = new ArrayList<Long>();
		for (Volume vol : title.getVolumes()) {
			v.add(vol.getIdv());
		}
		return v;
	}

	public List<TitleDTO> getByAuthor(String author) {
		Query q = manager.createQuery("select t from Title t where t.author like :author");
		q.setParameter("author", author);
		@SuppressWarnings("unchecked")
		List<TitleDTO> titles = q.getResultList();
		return titles;
	}

	private TitleDTO titleToTitleDTO(Title t) {
		TitleDTO dto = new TitleDTO();
		dto.setIdt(t.getIdt());
		dto.setAuthor(t.getAuthor());
		dto.setTitle(t.getTitle());
		List<Long> volumes = t.getVolumes().stream().map(x -> x.getIdv()).collect(Collectors.toList());
		dto.setVolumes(volumes);
		return dto;
	}
}
