package pl.kurs.komis;

import java.time.LocalDateTime;

public class CheckoutDTO {
	long idc;
	String dateStart;
	String dateEnd;
	Long reader;
	Long volume;

	public long getIdc() {
		return idc;
	}

	public void setIdc(long idc) {
		this.idc = idc;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Long getReader() {
		return reader;
	}

	public void setReader(Long reader) {
		this.reader = reader;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}
}
