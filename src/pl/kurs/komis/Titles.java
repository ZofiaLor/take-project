package pl.kurs.komis;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Titles {

	private List<Title> titles = new ArrayList<Title>();

	public Titles(List<Title> t) {
		super();
		this.titles = t;
	}

	public Titles() {
	}

	public List<Title> getTitles() {
		return this.titles;
	}

	public void setTitles(List<Title> t) {
		this.titles = t;
	}

}
