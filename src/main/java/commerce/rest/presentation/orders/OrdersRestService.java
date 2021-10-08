package commerce.rest.presentation.orders;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import commerce.persistence.remote.orders.OrderInterface;
import commerce.rest.vo.OrderVO;

@Path("/orders")
@RequestScoped
public class OrdersRestService {

	@EJB
	private static OrderInterface orderInterface;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response orders(OrderVO data) {
		return Response.ok(orderInterface.createOrder(data.getName())).build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id) {
		orderInterface.deleteOrder(id);
		return Response.ok().build();
	}

}
