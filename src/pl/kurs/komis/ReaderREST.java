package pl.kurs.komis;

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

@Path("/reader")
@Consumes({ "application/json" })
@Produces({ "application/json" })

// @Consumes({ "application/xml" })
// @Produces({ "application/xml" })
public class ReaderREST {

	@EJB
	ReaderEJB bean;

	@POST
	public String create(ReaderDTO reader) {
		bean.create(reader);
		return "Created reader!";
	}

	@GET
	public List<ReaderDTO> get(@QueryParam("author") String author) {
		List<ReaderDTO> lt;

		lt = bean.get();

		return lt;
	}

	@GET
	@Path("/{idr}")
	public ReaderDTO findById(@PathParam("idr") long idr) {
		return bean.findById(idr);
	}

	@GET
	@Path("/{idr}/checkouts")
	public List<Long> findReadersCheckouts(@PathParam("idr") long idr) {
		// TODO implement this in bean
		List<Long> volumes = new ArrayList<Long>();// bean.findReadersCheckouts(idr);
		return volumes;
	}

	@PUT
	public String update(ReaderDTO reader) {
		try {
			bean.update(reader);
			return "Reader updated!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Reader not updated :(";
		}
	}

	@DELETE
	@Path("/{idr}")
	public void delete(@PathParam("idr") long idr) {
		bean.delete(idr);
	}

}