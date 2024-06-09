package pl.kurs.komis;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/title")
@Consumes({ "application/json" })
@Produces({ "application/json" })

//@Consumes({ "application/xml" })
//@Produces({ "application/xml" })
public class TitleREST {

	@EJB
	TitleEJB bean;
	
	@POST
	public String create(Title title) {
		bean.create(title);
		return "Created title!";
	}
	
	@GET
	public Titles get(@QueryParam("author") String author) {
		List<Title> lt;
		if (author != null) {
			lt = bean.getByAuthor(author);
		}
		else {
			lt = bean.get();
		}
		Titles titles = new Titles(lt);
		return titles;
	}
	
	@GET
	@Path("/{idt}")
	public List<Title> findById(@PathParam("idt") long idt) {
		List<Title> title = bean.findById(idt);
		return title;
	}
	
	@GET
	@Path("/{idt}/volumes")
	public List<Long> findTitlesVolumes(@PathParam("idt") long idt) {
		List<Long> volumes = bean.findTitlesVolumes(idt);
		return volumes;
	}
	
	@PUT
	public String update(Title title) {
		try {
			bean.update(title);
			return "Title updated!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Title not updated :(";
		}
	}
	
	@DELETE
	@Path("/{idt}")
	public void delete(@PathParam("idt") long idt) {
		bean.delete(idt);
	}
	
}
