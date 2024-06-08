package pl.kurs.komis;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Checkout implements Serializable{
	private static final long serialVersionUID = 1L;
	long idc;
	LocalDateTime dateStart;
	LocalDateTime dateEnd;
	Reader reader;
	Volume volume;

	@Id
	@GeneratedValue
	@XmlAttribute
	public long getIdc() {
		return idc;
	}
	
	public void setIdc(long newIdc){
		idc = newIdc;
	}
	
	public LocalDateTime getDateStart() {
		return dateStart;
	}
	
	public void setDateStart(LocalDateTime newDateStart) {
		dateStart = newDateStart;
	}
	
	public LocalDateTime getDateEnd() {
		return dateEnd;
	}
	
	public void setDateEnd(LocalDateTime newDateEnd) {
		dateEnd = newDateEnd;
	}
	
	@ManyToOne
	@JoinColumn(name = "idr")
	public Reader getReader() {
		return reader;
	}
	
	public void setReader(Reader newReader) {
		reader = newReader;
	}
	
	@ManyToOne
	@JoinColumn(name = "idv")
	public Volume getVolume() {
		return volume;
	}
	
	public void setVolume(Volume newVolume) {
		volume = newVolume;
	}

}
