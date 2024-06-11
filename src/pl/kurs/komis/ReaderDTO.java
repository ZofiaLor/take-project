package pl.kurs.komis;

import java.util.List;

public class ReaderDTO {
	long idr;
	String name;
	List<Long> checkouts;

	public long getIdr() {
		return idr;
	}

	public void setIdr(long idr) {
		this.idr = idr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Long> getCheckouts() {
		return checkouts;
	}

	public void setCheckouts(List<Long> checkouts) {
		this.checkouts = checkouts;
	}

}
