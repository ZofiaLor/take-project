package pl.kurs.komis;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Title implements Serializable{
	private static final long serialVersionUID = 1L;
	long idt;
	String author;
	String title;
	List<Long> volumes;
	
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
	
	//@OneToMany(mappedBy = "title", cascade = CascadeType.ALL)
	@ElementCollection
	public List<Long> getVolumes() {
		return volumes;
	}
	
	public void setVolumes(List<Long> newVolumes) {
		volumes = newVolumes;
	}

}
