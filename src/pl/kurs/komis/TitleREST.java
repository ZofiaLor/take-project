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
	public TitleDTO create(TitleDTO title) {
		
		return bean.create(title);
	}

	@GET
	public List<TitleDTO> get(@QueryParam("author") String author) {
		List<TitleDTO> lt;
		if (author != null) {
			lt = bean.getByAuthor(author);
		} else {
			lt = bean.get();
		}
		return lt;
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
	public TitleDTO update(TitleDTO title) {
		return bean.update(title);
	}

	@DELETE
	@Path("/{idt}")
	public void delete(@PathParam("idt") long idt) {
		bean.delete(idt);
	}

}
