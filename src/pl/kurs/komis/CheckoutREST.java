package pl.kurs.komis;

import java.util.ArrayList;
import java.util.Date;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Path("/checkout")
@Consumes({ "application/json" })
@Produces({ "application/json" })

// @Consumes({ "application/xml" })
// @Produces({ "application/xml" })
public class CheckoutREST {

	@EJB
	CheckoutEJB bean;

	@POST
	public String create(CheckoutDTO checkout) {
		bean.create(checkout);
		return "Created checkout!";
	}

	@GET
	public List<CheckoutDTO> get(@QueryParam("author") String author) {
		List<CheckoutDTO> lt;

		lt = bean.get();

		return lt;
	}

	@GET
	@Path("/{idc}")
	public CheckoutDTO findById(@PathParam("idc") long idc) {
		return bean.findById(idc);
	}

	@GET
	@Path("/{idc}/checkouts")
	public List<Long> findCheckoutsCheckouts(@PathParam("idc") long idc) {
		// TODO implement this in bean
		List<Long> volumes = new ArrayList<Long>();// bean.findCheckoutsCheckouts(idc);
		return volumes;
	}

	@POST
	@Path("/{idc}/end")
	public String endCheckout(@PathParam("idc") long idc) {
		LocalDateTime now = LocalDateTime.now();
		CheckoutDTO dto = this.findById(idc);
		dto.setDateEnd(now.format(DateTimeFormatter.ISO_DATE_TIME));
		bean.update(dto);
		return "Checkout ended on " + now;
	}

	@PUT
	public String update(CheckoutDTO checkout) {
		try {
			bean.update(checkout);
			return "Checkout updated!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Checkout not updated :(";
		}
	}

	@DELETE
	@Path("/{idc}")
	public void delete(@PathParam("idc") long idc) {
		bean.delete(idc);
	}

}
