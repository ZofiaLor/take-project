package pl.kurs.komis;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class VolumeEJB {
	@PersistenceContext(name = "volume")
	EntityManager manager;

	public void create(VolumeDTO v) {
		Volume volume = new Volume();
		volume.setTitle(manager.find(Title.class, v.getTitle()));
		manager.persist(volume);
	}

	public void update(VolumeDTO v) {
		Volume volume = manager.find(Volume.class, v.getIdv());
		// ...
		manager.merge(volume);
	}

	public void delete(long idv) {
		Volume volume = manager.find(Volume.class, idv);
		manager.remove(volume);
	}

	public List<VolumeDTO> get() {
		Query q = manager.createQuery("select v from Volume v");
		@SuppressWarnings("unchecked")
		List<Volume> volumes = q.getResultList();

		return volumes.stream().map(x -> volumeDAOtoDTO(x)).collect(Collectors.toList());
	}

	public VolumeDTO findById(long idv) {
		Volume v = manager.find(Volume.class, idv);
		return this.volumeDAOtoDTO(v);
	}
	
	public List<CheckoutDTO> findVolumesCheckouts(long idv) {
		Volume v = manager.find(Volume.class, idv);
		List<CheckoutDTO> c = v.getCheckouts().stream().map(x -> checkoutDAOtoDTO(x)).collect(Collectors.toList());
		return c;
	}

	private VolumeDTO volumeDAOtoDTO(Volume v) {
		VolumeDTO dto = new VolumeDTO();
		dto.setIdv(v.getIdv());
		dto.setTitle(v.getTitle().getIdt());
		List<Long> checkouts = v.getCheckouts().stream().map(x -> x.getIdc()).collect(Collectors.toList());
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
