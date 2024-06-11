package pl.kurs.komis;

import java.util.List;

public class VolumeDTO {
	long idv;
	Long title;
	List<Long> checkouts;


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

	 public List<Long> getCheckouts() {
	 return checkouts;
	 }
	
	 public void setCheckouts(List<Long> newCheckouts) {
	 checkouts = newCheckouts;
	 }
}
