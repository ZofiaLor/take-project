package pl.kurs.komis;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Volume implements Serializable {
	private static final long serialVersionUID = 1L;
	long idv;
	Title title;
	List<Checkout> checkouts;

	 @PreRemove
	 public void preRemove() {
		 for (Checkout c : checkouts) {
		 c.setReader(null);
		 }
	 }

	@Id
	@GeneratedValue
	@XmlAttribute
	public long getIdv() {
		return idv;
	}

	public void setIdv(long newIdv) {
		idv = newIdv;
	}

	@ManyToOne
	@JoinColumn(name = "idt")
	public Title getTitle() {
		return title;
	}

	public void setTitle(Title newTitle) {
		title = newTitle;
	}

	 @OneToMany(mappedBy = "volume", cascade = CascadeType.PERSIST)
	 public List<Checkout> getCheckouts() {
	 return checkouts;
	 }
	
	 public void setCheckouts(List<Checkout> newCheckouts) {
	 checkouts = newCheckouts;
	 }

}
