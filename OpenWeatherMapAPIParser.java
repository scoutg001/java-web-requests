import org.json.JSONObject;
import org.json.JSONArray;

public class OpenWeatherMapAPIParser{
    public static double getTemperature(String json, TempuratureUnit unit){
        JSONObject rootData=new JSONObject(json);
        JSONObject main=rootData.getJSONObject("main");
        double temperature=main.getFloat("temp");

        if(unit.equals(TempuratureUnit.CELSIUS)){
            return temperature-273.15;
        }
        if(unit.equals(TempuratureUnit.FAHRENHEIT)){
            return ((temperature-273.15)*(9.0/5.0))+32;
        }
        return temperature;
    }

    public static String getCondition(String json){
        JSONObject rootData=new JSONObject(json);
        JSONArray weather=rootData.getJSONArray("weather");
        String condition=weather.getJSONObject(0).getString("main");
        return condition;
    }
}
