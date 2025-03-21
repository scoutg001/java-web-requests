import org.json.JSONObject;
import org.json.JSONArray;

public class OpenWeatherMapAPIParser{
    public static double getTemperature(String json, TemperatureUnit unit){
        JSONObject rootData=new JSONObject(json);
        JSONObject main=rootData.optJSONObject("main"); //optJSONObject doesn't error out if the main JSON key doesn't exist due to an API change
        if(main==null){
            System.err.println("JSON String is missing 'main' object.");
        }
        double temperature=main.optDouble("temp", 273.15); //returns 0 degrees celsius by default

        switch(unit){
            case CELSIUS:
                return temperature-273.15;
            case FAHRENHEIT:
                return ((temperature-273.15)*(9.0/5.0))+32;
            default: //KELVIN
                return temperature;
        }
    }

    public static String getCondition(String json){
        JSONArray weather=new JSONObject(json)
            .optJSONArray("weather"); //optJSONArray doesn't error out if the weather JSON array key doesn't exist due to an API change
        if(weather==null){
            return "Unknown";
        }
        return weather.getJSONObject(0)
            .optString("main", "Unknown");
    }
}
