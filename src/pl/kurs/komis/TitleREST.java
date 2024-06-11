package pl.kurs.komis;

import java.lang.reflect.Array;
import java.util.ArrayList;
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

// @Consumes({ "application/xml" })
// @Produces({ "application/xml" })
public class TitleREST {

	@EJB
	TitleEJB bean;

	@POST
	public String create(TitleDTO title) {
		bean.create(title);
		return "Created title!";
	}

	@GET
	public List<TitleDTO> get(@QueryParam("author") String author) {
		List<TitleDTO> lt;
		try {
			if (author != null) {
				lt = bean.getByAuthor(author);
			} else {
				lt = bean.get();
			}
			return lt;
		} catch (Exception ex) {
			TitleDTO exceptionGuy = new TitleDTO();
			lt = new ArrayList<TitleDTO>();
			exceptionGuy.setTitle(ex.getMessage());
			lt.add(exceptionGuy);
			return lt;
		}
	}

	@GET
	@Path("/{idt}")
	public TitleDTO findById(@PathParam("idt") long idt) {
		return bean.findById(idt);
	}

	@GET
	@Path("/{idt}/volumes")
	public List<Long> findTitlesVolumes(@PathParam("idt") long idt) {
		List<Long> volumes = bean.findTitlesVolumes(idt);
		return volumes;
	}

	@PUT
	public String update(TitleDTO title) {
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
