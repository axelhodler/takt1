package bot.restcalls;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RestAccessor {

    public HttpResponse<JsonNode> addLink(String title, String url, String user) {
        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = Unirest
                    .post("http://localhost:6666/links")
                    .body("{\"title\":" + title + ", " + "\"url\":" + url
                            + ", " + "\"user\":" + user + "}").asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }

}
