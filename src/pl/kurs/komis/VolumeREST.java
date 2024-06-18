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
import javax.ws.rs.WebApplicationException;

@Path("/volume")
@Consumes({ "application/json" })
@Produces({ "application/json" })

// @Consumes({ "application/xml" })
// @Produces({ "application/xml" })
public class VolumeREST {
	@EJB
	VolumeEJB bean;

	@POST
	public VolumeDTO create(VolumeDTO volume) {

		return bean.create(volume);
	}

	@GET
	public List<VolumeDTO> get() {
		return bean.get();
	}

	@GET
	@Path("/{idv}")
	public VolumeDTO findById(@PathParam("idv") long idv) {
		return bean.findById(idv);
	}
	
	@GET
	@Path("/{idv}/checkouts")
	public List<CheckoutDTO> findVolumesCheckouts(@PathParam("idv") long idv) {
		List<CheckoutDTO> checkouts = bean.findVolumesCheckouts(idv);
		return checkouts;
	}

	@PUT
	public VolumeDTO update(VolumeDTO volume) {
		return bean.update(volume);
	}

	@DELETE
	@Path("/{idv}")
	public void delete(@PathParam("idv") long idv) {
		bean.delete(idv);
	}
}
