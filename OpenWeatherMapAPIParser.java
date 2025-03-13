import org.json.JSONObject;
import org.json.JSONArray;

public class OpenWeatherMapAPIParser implements Weather{
    private double tempurature;
    private String condition;
    private TempuratureUnit originalUnit=TempuratureUnit.KELVIN;

    public OpenWeatherMapAPIParser(String json){
        this.fromJSON(json);
    }

    @Override
    public void fromJSON(String json) {
        JSONObject rootData=new JSONObject(json);
        JSONObject main=rootData.getJSONObject("main");
        this.tempurature=main.getFloat("temp");
        JSONArray weather=rootData.getJSONArray("weather");
        this.condition=weather.getJSONObject(0).getString("main");
    }

    @Override
    public double getTempurature(TempuratureUnit unit) {
        if(unit.equals(TempuratureUnit.CELSIUS)){
            return this.tempurature-273.15;
        }
        if(unit.equals(TempuratureUnit.FAHRENHEIT)){
            return ((this.tempurature-273.15)*(9.0/5.0))+32;
        }
        return this.tempurature;
    }

    @Override
    public String getCondition() {
        return condition;
    }
}
