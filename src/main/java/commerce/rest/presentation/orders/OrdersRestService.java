package commerce.rest.presentation.orders;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityNotFoundException;
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
import commerce.rest.vo.AddressVO;
import commerce.rest.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/orders")
@RequestScoped
@Api(tags = { "order" })
public class OrdersRestService {

	@EJB
	private static OrderInterface orderInterface;

	@GET
	@Path("/total/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Retrieves total value of an order")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Response getOrderTotalValue(@PathParam("id") Long id) {
		BigDecimal total = orderInterface.getOrdersTotalValue(id);
		return Response.ok(total).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Sets an order")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad requests"),
			@ApiResponse(code = 406, message = "Not acceptable")})
	public Response orders(OrderVO order, AddressVO address, List<Long> productsId, Long customerId) {
		if (productsId.isEmpty()) {
			Response.status(Status.BAD_REQUEST).entity("Please include products to the order").build();
		}
		if (null != address) {
			Response.status(Status.BAD_REQUEST).entity("Please include shipping address to the order").build();
		}
		if (null != customerId) {
			Response.status(Status.BAD_REQUEST).entity("Please include customer to the order").build();
		}
		try {
			return Response.ok(orderInterface.createOrder(order, address, productsId, customerId)).build();
		} catch (SQLException | EntityNotFoundException e) {
			return Response.status(Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Sets an order")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Response delete(@PathParam("id") Long id) {
		orderInterface.deleteOrder(id);
		return Response.ok().build();
	}

}
