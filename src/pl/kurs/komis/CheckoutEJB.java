package pl.kurs.komis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.WebApplicationException;

@Stateless
public class CheckoutEJB {

	@PersistenceContext(name = "checkout")
	EntityManager manager;

	public CheckoutDTO create(CheckoutDTO c) {
		Checkout checkout = new Checkout();
		LocalDateTime now = LocalDateTime.now();
		checkout.setDateStart(now); // set to time of creation
		checkout.setDateEnd(null); // set to null, updated witn the .../end
									// endpoint
		Reader r = manager.find(Reader.class, c.getReader());
		if (r == null) throw new WebApplicationException(400);
		checkout.setReader(r);
		Volume v = manager.find(Volume.class, c.getVolume());
		if (v == null) throw new WebApplicationException(400);
		checkout.setVolume(v);
		manager.persist(checkout);
		return this.checkoutDAOtoDTO(checkout);
	}

	public CheckoutDTO update(CheckoutDTO c) {
		Checkout checkout = manager.find(Checkout.class, c.getIdc());
		if (checkout == null) throw new WebApplicationException(404);
		if (c.getDateEnd() != null) checkout.setDateEnd(LocalDateTime.parse(c.getDateEnd()));
		if (c.getDateStart() != null) checkout.setDateStart(LocalDateTime.parse(c.getDateStart()));
		if (c.getReader() != null ) {
			Reader r = manager.find(Reader.class, c.getReader());
			if (r == null) throw new WebApplicationException(400);
			checkout.setReader(r);
		}
		if (c.getVolume() != null) {
			Volume v = manager.find(Volume.class, c.getVolume());
			if (v == null) throw new WebApplicationException(400);
			checkout.setVolume(v);
		}
		manager.merge(checkout);
		return this.checkoutDAOtoDTO(checkout);
	}

	public void delete(long idc) {
		Checkout checkout = manager.find(Checkout.class, idc);
		if (checkout == null) throw new WebApplicationException(404);
		manager.remove(checkout);
	}

	public List<CheckoutDTO> get() {
		Query q = manager.createQuery("select r from Checkout r");
		@SuppressWarnings("unchecked")
		List<Checkout> checkouts = q.getResultList();

		return checkouts.stream().map(x -> checkoutDAOtoDTO(x)).collect(Collectors.toList());
	}

	public CheckoutDTO findById(long idc) {
		Checkout c = manager.find(Checkout.class, idc);
		if (c == null) throw new WebApplicationException(404);
		return this.checkoutDAOtoDTO(c);
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
