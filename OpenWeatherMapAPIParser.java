import org.json.JSONObject;
import org.json.JSONArray;

public class OpenWeatherMapAPIParser implements Weather{
    private static double tempurature;
    private static String condition;
    //private static final TempuratureUnit ORIGINAL_UNIT=TempuratureUnit.KELVIN;

    private static void fromJSON(String json){
        JSONObject rootData=new JSONObject(json);
        JSONObject main=rootData.getJSONObject("main");
        OpenWeatherMapAPIParser.tempurature=main.getFloat("temp");
        JSONArray weather=rootData.getJSONArray("weather");
        OpenWeatherMapAPIParser.condition=weather.getJSONObject(0).getString("main");
    }

    public static double getTempurature(String json, TempuratureUnit unit){
        fromJSON(json);
        if(unit.equals(TempuratureUnit.CELSIUS)){
            return OpenWeatherMapAPIParser.tempurature-273.15;
        }
        if(unit.equals(TempuratureUnit.FAHRENHEIT)){
            return ((OpenWeatherMapAPIParser.tempurature-273.15)*(9.0/5.0))+32;
        }
        return OpenWeatherMapAPIParser.tempurature;
    }

    public static String getCondition(String json){
        fromJSON(json);
        return OpenWeatherMapAPIParser.condition;
    }
}
