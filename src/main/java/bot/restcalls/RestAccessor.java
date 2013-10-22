package bot.restcalls;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RestAccessor {

    public HttpResponse<JsonNode> addLink(Link link) {
        HttpResponse<JsonNode> jsonResponse = null;
        return tryGettingJsonResponse(link, jsonResponse);
    }

    private HttpResponse<JsonNode> tryGettingJsonResponse(Link link,
            HttpResponse<JsonNode> jsonResponse) {
        try {
            jsonResponse = Unirest
                    .post("http://localhost:6666/links")
                    .body("{\"title\":" + link.getTitle() + ", " + "\"url\":"
                            + link.getUrl() + ", " + "\"user\":"
                            + link.getUser() + "}").asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }

}
