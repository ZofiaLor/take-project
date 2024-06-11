package pl.kurs.komis;

import java.util.List;

public class TitleDTO {
	long idt;
	String author;
	String title;
	List<Long> volumes;

	public long getIdt() {
		return idt;
	}

	public void setIdt(long newIdt) {
		idt = newIdt;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String newAuthor) {
		author = newAuthor;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String newTitle) {
		title = newTitle;
	}

	public List<Long> getVolumes() {
		return volumes;
	}

	public void setVolumes(List<Long> newVolumes) {
		volumes = newVolumes;
	}
}
