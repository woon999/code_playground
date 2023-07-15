import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ApiTest2 {
    public static void main(String[] args) throws ParseException {
        // JSONParser로 JSONObject 객체
        JSONObject objData = (JSONObject)new JSONParser().parse(jsonData);

        JSONObject movieData1 = (JSONObject)objData.get("movie1");
        JSONObject movieData2 = (JSONObject)objData.get("movie2");

        StringBuilder sb = new StringBuilder();
        sb.append("movie1----\n");
        sb.append("title: " + movieData1.get("title")+"\n");
        sb.append("url: " + movieData1.get("url")+"\n");
        sb.append("movie2----\n");
        sb.append("title: " + movieData2.get("title")+"\n");
        sb.append("url: " + movieData2.get("url")+"\n");

        System.out.println(sb.toString());

    }
    static String jsonData=
            "{"
                +       "\"movie1\": {"
                +           "\"title\": \"Blame\","
                +           "\"url\": \"https://yts.mx/movies/blame-2021\","
                +       "},"
                +       "\"movie2\": {"
                +           "\"title\": \"Tethered\","
                +           "\"url\": \"https://yts.mx/movies/tethered-2021\","
                +       "},"
                + "}";
}
