package pl.kurs.komis;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ReaderEJB {
	@PersistenceContext(name = "reader")
	EntityManager manager;

	public void create(ReaderDTO r) {
		Reader reader = new Reader();
		reader.setName(r.getName());
		manager.persist(reader);
	}

	public void update(ReaderDTO r) {
		Reader reader = manager.find(Reader.class, r.getIdr());
		if (r.getName() != null) reader.setName(r.getName());
		manager.merge(reader);
	}

	public void delete(long idr) {
		Reader reader = manager.find(Reader.class, idr);
		manager.remove(reader);
	}

	public List<ReaderDTO> get() {
		Query q = manager.createQuery("select r from Reader r");
		@SuppressWarnings("unchecked")
		List<Reader> readers = q.getResultList();

		return readers.stream().map(x -> readerDAOtoDTO(x)).collect(Collectors.toList());
	}

	public ReaderDTO findById(long idr) {
		Reader v = manager.find(Reader.class, idr);
		return this.readerDAOtoDTO(v);
	}
	
	public List<ReaderDTO> getByName(String name) {
		Query q = manager.createQuery("select r from Reader r where r.name like :name");
		q.setParameter("name", name);
		@SuppressWarnings("unchecked")
		List<ReaderDTO> readers = q.getResultList();
		return readers;
	}

	private ReaderDTO readerDAOtoDTO(Reader r) {
		ReaderDTO dto = new ReaderDTO();
		dto.setIdr(r.getIdr());
		dto.setName(r.getName());
		List<Long> checkouts = r.getCheckouts().stream().map(x -> x.getIdc()).collect(Collectors.toList());
		dto.setCheckouts(checkouts);
		return dto;
	}

}
