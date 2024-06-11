package pl.kurs.komis;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Title implements Serializable {
	private static final long serialVersionUID = 1L;
	long idt;
	String author;
	String title;
	List<Volume> volumes;

	@Id
	@GeneratedValue
	@XmlAttribute
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

	@OneToMany(mappedBy = "title", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	// @ElementCollection
	public List<Volume> getVolumes() {
		return volumes;
	}

	public void setVolumes(List<Volume> newVolumes) {
		volumes = newVolumes;
	}

}
