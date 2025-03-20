import org.json.JSONObject;

public class OpenWeatherMapAPIParser{
    public static double getTemperature(String json, TemperatureUnit unit){
        JSONObject rootData=new JSONObject(json);
        JSONObject main=rootData.getJSONObject("main");
        double temperature=main.getFloat("temp");

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
        return new JSONObject(json)
            .getJSONArray("weather")
            .getJSONObject(0)
            .getString("main");
    }
}
