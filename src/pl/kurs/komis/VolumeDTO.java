package pl.kurs.komis;

public class VolumeDTO {
	long idv;
	Long title;
	// List<Checkout> checkouts;

	// @PreRemove
	// public void preRemove() {
	// for (Checkout c : checkouts) {
	// c.setReader(null);
	// }
	// }

	public long getIdv() {
		return idv;
	}

	public void setIdv(long newIdv) {
		idv = newIdv;
	}

	public Long getTitle() {
		return title;
	}

	public void setTitle(Long newTitle) {
		title = newTitle;
	}

	// @OneToMany(mappedBy = "volume", cascade = CascadeType.PERSIST)
	// public List<Checkout> getCheckouts() {
	// return checkouts;
	// }
	//
	// public void setCheckouts(List<Checkout> newCheckouts) {
	// checkouts = newCheckouts;
	// }
}
