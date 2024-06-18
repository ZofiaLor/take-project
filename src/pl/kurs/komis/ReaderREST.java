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
	public ReaderDTO create(ReaderDTO reader) {
		
		return bean.create(reader);
	}

	@GET
	public List<ReaderDTO> get(@QueryParam("name") String name) {
		List<ReaderDTO> readers;

		if (name != null) {
			readers = bean.getByName(name);
		} else {
			readers = bean.get();
		}

		return readers;
	}

	@GET
	@Path("/{idr}")
	public ReaderDTO findById(@PathParam("idr") long idr) {
		return bean.findById(idr);
	}

	@GET
	@Path("/{idr}/checkouts")
	public List<CheckoutDTO> findReadersCheckouts(@PathParam("idr") long idr) {
		// TODO implement this in bean
		List<CheckoutDTO> c = bean.findReadersCheckouts(idr);
		return c;
	}

	@PUT
	public ReaderDTO update(ReaderDTO reader) {
		return bean.update(reader);
	}

	@DELETE
	@Path("/{idr}")
	public void delete(@PathParam("idr") long idr) {
		bean.delete(idr);
	}

}
