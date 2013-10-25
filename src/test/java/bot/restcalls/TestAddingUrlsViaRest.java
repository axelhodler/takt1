package bot.restcalls;

import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp;
import static com.xebialabs.restito.semantics.Action.status;
import static com.xebialabs.restito.semantics.Action.stringContent;
import static com.xebialabs.restito.semantics.Condition.*;
import static org.junit.Assert.assertEquals;

import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.xebialabs.restito.server.StubServer;

public class TestAddingUrlsViaRest {

    @Test
    public void setUpStubServer() throws UnirestException {
        StubServer server = new StubServer().run();

        whenHttp(server).match(post("/links"),
                parameter("pw", System.getenv("PASS"))).then(
                status(HttpStatus.OK_200),
                stringContent("{\"title\":\"hello\", "
                        + "\"url\":\"http://www.hello.de\", "
                        + "\"user\":\"pete\"}"));

        Link link = new Link().setTitle("hello").setUrl("http://www.hello.de")
                .setUser("pete");

        HttpResponse<JsonNode> jsonResponse = RestAccessor.getInstance()
                .addLink(link);

        assertEquals(200, jsonResponse.getCode());
    }
}
