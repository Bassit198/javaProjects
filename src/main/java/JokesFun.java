import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class JokesFun {

    public static void main(String[] args) {
        //https://v2.jokeapi.dev/joke/Dark?blacklistFlags=nsfw&contains=run&idRange=1-1367&amount=5

//        String category = "Dark";
//
//        Map<String, String> key = new HashMap<>();
//        key.put("category", category+"?");
//        key.put("blacklistFlag", "blacklistFlags=");
//        key.put("contains", "&contains=");
//        key.put("idRange", "&idRange=");
//        key.put("amount", "&amount=");
//        key.put("language", "&lang=");
//
//
//        String uriFlags = "https://v2.jokeapi.dev/flags";
//        String uriFormats = "https://v2.jokeapi.dev/formats";
//        String uriEndpoint = "https://v2.jokeapi.dev/endpoints";
//        String uriCategoryWithAmount = "https://v2.jokeapi.dev/joke/" + category + "?amount=" + 5;
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://v2.jokeapi.dev/joke/"+key.get("category") + key.get("blacklistFlag") + "" + key.get("contains") + "" + key.get("amount") + 5  ))
//                .method("GET", HttpRequest.BodyPublishers.noBody())
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//
//        System.out.println(response.body());
//    }
    }
}
