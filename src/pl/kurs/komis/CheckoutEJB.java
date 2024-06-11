package pl.kurs.komis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CheckoutEJB {

	@PersistenceContext(name = "checkout")
	EntityManager manager;

	public void create(CheckoutDTO r) {
		Checkout checkout = new Checkout();
		LocalDateTime now = LocalDateTime.now();
		checkout.setDateStart(now); // set to time of creation
		checkout.setDateEnd(null); // set to null, updated witn the .../end
									// endpoint
		checkout.setReader(manager.find(Reader.class, r.getReader()));
		checkout.setVolume(manager.find(Volume.class, r.getVolume()));
		manager.persist(checkout);
	}

	public void update(CheckoutDTO c) {
		Checkout checkout = manager.find(Checkout.class, c.getIdc());
		if (c.getDateEnd() != null) checkout.setDateEnd(LocalDateTime.parse(c.getDateEnd()));
		if (c.getDateStart() != null) checkout.setDateStart(LocalDateTime.parse(c.getDateStart()));
		if (c.getReader() != null )checkout.setReader(manager.find(Reader.class, c.getReader()));
		if (c.getVolume() != null)checkout.setVolume(manager.find(Volume.class, c.getVolume()));
		manager.merge(checkout);
	}

	public void delete(long idc) {
		Checkout checkout = manager.find(Checkout.class, idc);
		manager.remove(checkout);
	}

	public List<CheckoutDTO> get() {
		Query q = manager.createQuery("select r from Checkout r");
		@SuppressWarnings("unchecked")
		List<Checkout> checkouts = q.getResultList();

		return checkouts.stream().map(x -> checkoutDAOtoDTO(x)).collect(Collectors.toList());
	}

	public CheckoutDTO findById(long idc) {
		Checkout v = manager.find(Checkout.class, idc);
		return this.checkoutDAOtoDTO(v);
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
