package pl.kurs.komis;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.WebApplicationException;

@Stateless
public class ReaderEJB {
	@PersistenceContext(name = "reader")
	EntityManager manager;

	public ReaderDTO create(ReaderDTO r) {
		Reader reader = new Reader();
		reader.setName(r.getName());
		reader.setCheckouts(new ArrayList<Checkout>());
		manager.persist(reader);
		return this.readerDAOtoDTO(reader);
	}

	public ReaderDTO update(ReaderDTO r) {
		Reader reader = manager.find(Reader.class, r.getIdr());
		if (reader == null) throw new WebApplicationException(404);
		if (r.getName() != null) reader.setName(r.getName());
		manager.merge(reader);
		return this.readerDAOtoDTO(reader);
	}

	public void delete(long idr) {
		Reader reader = manager.find(Reader.class, idr);
		if (reader == null) throw new WebApplicationException(404);
		manager.remove(reader);
	}

	public List<ReaderDTO> get() {
		Query q = manager.createQuery("select r from Reader r");
		@SuppressWarnings("unchecked")
		List<Reader> readers = q.getResultList();

		return readers.stream().map(x -> readerDAOtoDTO(x)).collect(Collectors.toList());
	}

	public ReaderDTO findById(long idr) {
		Reader r = manager.find(Reader.class, idr);
		if (r == null) throw new WebApplicationException(404);
		return this.readerDAOtoDTO(r);
	}
	
	public List<ReaderDTO> getByName(String name) {
		Query q = manager.createQuery("select r from Reader r where r.name like :name");
		q.setParameter("name", name);
		@SuppressWarnings("unchecked")
		List<Reader> readers = q.getResultList();
		return readers.stream().map(x -> readerDAOtoDTO(x)).collect(Collectors.toList());
	}
	
	public List<CheckoutDTO> findReadersCheckouts(long idr) {
		Reader reader = manager.find(Reader.class, idr);
		if (reader == null) throw new WebApplicationException(404);
		List<CheckoutDTO> c = reader.getCheckouts().stream().map(x -> checkoutDAOtoDTO(x)).collect(Collectors.toList());
		return c;
	}

	private ReaderDTO readerDAOtoDTO(Reader r) {
		ReaderDTO dto = new ReaderDTO();
		dto.setIdr(r.getIdr());
		dto.setName(r.getName());
		List<Long> checkouts = r.getCheckouts().stream().map(x -> x.getIdc()).collect(Collectors.toList());
		dto.setCheckouts(checkouts);
		return dto;
	}
	
	private CheckoutDTO checkoutDAOtoDTO(Checkout r) {
		CheckoutDTO dto = new CheckoutDTO();
		dto.setIdc(r.getIdc());
		if (r.getDateStart() != null)
			dto.setDateStart(r.getDateStart().format(DateTimeFormatter.ISO_DATE_TIME));
		if (r.getDateEnd() != null)
			dto.setDateEnd(r.getDateEnd().format(DateTimeFormatter.ISO_DATE_TIME));
		dto.setReader(r.getReader().getIdr());
		dto.setVolume(r.getVolume().getIdv());
		return dto;
	}

}
