import org.json.JSONObject;
import org.json.JSONArray;

public class OpenWeatherMapAPIParser{
    public static double getTempurature(String json, TempuratureUnit unit){
        JSONObject rootData=new JSONObject(json);
        JSONObject main=rootData.getJSONObject("main");
        double tempurature=main.getFloat("temp");

        if(unit.equals(TempuratureUnit.CELSIUS)){
            return tempurature-273.15;
        }
        if(unit.equals(TempuratureUnit.FAHRENHEIT)){
            return ((tempurature-273.15)*(9.0/5.0))+32;
        }
        return tempurature;
    }

    public static String getCondition(String json){
        JSONObject rootData=new JSONObject(json);
        JSONArray weather=rootData.getJSONArray("weather");
        String condition=weather.getJSONObject(0).getString("main");
        return condition;
    }
}
