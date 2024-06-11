package pl.kurs.komis;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Reader implements Serializable {
	private static final long serialVersionUID = 1L;
	long idr;
	String name;
	List<Checkout> checkouts;

	@PreRemove
	private void preRemove() {
		for (Checkout c : checkouts) {
			c.setReader(null);
		}
	}

	@Id
	@GeneratedValue
	@XmlAttribute
	public long getIdr() {
		return idr;
	}

	public void setIdr(long newIdr) {
		idr = newIdr;
	}

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
	}

	@OneToMany(mappedBy = "reader", cascade = CascadeType.PERSIST)
	public List<Checkout> getCheckouts() {
		return checkouts;
	}

	public void setCheckouts(List<Checkout> newCheckouts) {
		checkouts = newCheckouts;
	}
}
