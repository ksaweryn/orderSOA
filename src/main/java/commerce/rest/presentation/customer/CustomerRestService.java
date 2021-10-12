package commerce.rest.presentation.customer;

import java.net.URI;
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import commerce.persistence.entities.Customer;
import commerce.persistence.remote.customers.CustomerInterface;
import commerce.rest.vo.CustomerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/customer")
@RequestScoped
@Api(tags = { "customer" })
public class CustomerRestService {

	@EJB
	private static CustomerInterface customerService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Fetch all customers")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Response customers() {
		return Response.ok(customerService.getCustomers()).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Fetch specific customer")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public Response get(@PathParam("id") Long id) {
		return Response.ok(customerService.getCustomer(id)).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create a new customer")
	@ApiResponses({ @ApiResponse(code = 200, message = "Created") })
	public Response createCustomer(CustomerVO data) {
		Customer customer;
		try {
			customer = customerService.createCustomer(data.getName(), data.getPhone(), data.getEmail());
			URI location = UriBuilder.fromResource(CustomerRestService.class).path("/{id}")
					.resolveTemplate("id", customer.getId()).build();
			return Response.created(location).build();
		} catch (SQLException e) {
			return Response.status(Status.BAD_REQUEST).entity("Data sent not valid, please correct").build();
		}

	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Updates existing customer")
	@ApiResponses({ @ApiResponse(code = 200, message = "Updated"), @ApiResponse(code = 404, message = "Not found") })
	public Response update(@PathParam("id") Long id, CustomerVO customer) {
		try {
			customerService.updateCustomer(id, customer.getName(), customer.getPhone(), customer.getEmail());
			return Response.ok().build();
		} catch (SQLException e) {
			return Response.status(Status.BAD_REQUEST).entity("Update data not valid, please correct").build();
		}
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Remove existing customer")
	@ApiResponses({ @ApiResponse(code = 200, message = "Removed") })
	public Response delete(@PathParam("id") Long id) {
		customerService.deleteCustomer(id);
		return Response.ok().build();
	}
}
