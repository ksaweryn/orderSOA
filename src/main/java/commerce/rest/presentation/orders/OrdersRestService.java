package commerce.rest.presentation.orders;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import commerce.persistence.remote.orders.OrderInterface;
import commerce.rest.vo.OrderVO;

@Path("/orders")
@RequestScoped
public class OrdersRestService {

	@EJB
	private static OrderInterface orderInterface;

	@GET
	@Path("/{id}/total")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getOrderTotalValue(@PathParam("id") Long id) {
		BigDecimal total = orderInterface.getOrdersTotalValue(id);
		return Response.ok(total).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response orders(OrderVO data) {
		try {
			return Response.ok(orderInterface.createOrder(data.getName())).build();
		} catch (SQLException e) {
			return Response.status(Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id) {
		orderInterface.deleteOrder(id);
		return Response.ok().build();
	}

}
