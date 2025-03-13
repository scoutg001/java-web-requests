public interface Weather {
    public void fromJSON(String json);
    public double getTempurature(TempuratureUnit unit);
    public String getCondition();
}
