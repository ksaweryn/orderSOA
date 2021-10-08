package commerce.rest.presentation;

import static java.util.Collections.singletonMap;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/health")
public class HealtService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response amIAlive() {
		Map<String, String> response = singletonMap("message", "Rest service is working");
		return Response.ok(response).build();
	}

}
