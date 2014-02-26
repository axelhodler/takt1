package bot.restcalls;

import bot.config.EnvironmentVars;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RestAccessor {

    private static RestAccessor uniqueInstance = null;

    private RestAccessor() {
    }

    public static RestAccessor getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new RestAccessor();
        return uniqueInstance;
    }

    public HttpResponse<JsonNode> addLink(Link link) {
        HttpResponse<JsonNode> jsonResponse = null;
        return tryGettingJsonResponse(link, jsonResponse);
    }

    private HttpResponse<JsonNode> tryGettingJsonResponse(Link link,
            HttpResponse<JsonNode> jsonResponse) {
        try {
            jsonResponse = Unirest
                    .post(System.getenv(EnvironmentVars.RESTAPIURL)
                            + "/links?pw=" + System.getenv("PASS")) //TODO undocumented
                    .body("{\"title\":\"" + link.getTitle() + "\",\"url\":\""
                            + link.getUrl() + "\",\"user\":\"" + link.getUser()
                            + "\"}").asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }

}
