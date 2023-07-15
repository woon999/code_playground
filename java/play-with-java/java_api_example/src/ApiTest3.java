import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ApiTest3 {
    public static void main(String[] args) throws ParseException {
        // JSONParser에 JSON데이터를 넣어 파싱한 다음 JSONObject로 반환한다.
        JSONObject objData = (JSONObject)new JSONParser().parse(jsonData);
        // JSONObject에서 Array데이터를 get하여 JSONArray에 저장한다.
        JSONArray arrData = (JSONArray)objData.get("movies");

        // 배열 데이터 출력하기
        JSONObject tmp;
        JSONArray tmpArr;
        StringBuilder sb= new StringBuilder();
        for(int i=0; i<arrData.size(); i++){
            tmp = (JSONObject)arrData.get(i);
//            System.out.println(tmp);

            sb.append("title("+i+"): " + tmp.get("title")+"\n");
            sb.append("url("+i+"): " + tmp.get("url")+"\n");

            // Array데이터 안에 Array 데이터 꺼내기
            tmpArr = (JSONArray)tmp.get("genres");
            sb.append("genres("+i+"): ");
            for(int j=0; j<tmpArr.size(); j++){
                sb.append(j+"." + tmpArr.get(j));
                if(j!=tmpArr.size()-1) sb.append(", ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());

    }
    static String jsonData=
            "{"
                    +   "\"movies\": ["
                    +       "{"
                    +           "\"title\": \"Blame\","
                    +           "\"url\": \"https://yts.mx/movies/blame-2021\","
                    +           "\"genres\": ["
                    +                        "\"Crime\",\"Thriller\""
                    +            "]"
                    +       "},"
                    +       "{"
                    +           "\"title\": \"Tethered\","
                    +           "\"url\": \"https://yts.mx/movies/tethered-2021\","
                    +           "\"genres\": ["
                    +                        "\"Drama\",\"Mystery\",\"Sci-Fi\""
                    +            "]"
                    +       "},"
                    +       "{"
                    +           "\"title\": \"The Resonator: Miskatonic U\","
                    +           "\"url\": \"https://yts.mx/movies/the-resonator-miskatonic-u-2021\","
                    +           "\"genres\": ["
                    +                        "\"Fantasy\""
                    +            "]"
                    +       "}"
                    +   "]"
                    +"}";
}
