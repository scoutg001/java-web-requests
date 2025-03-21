public enum TemperatureUnit {
    KELVIN{
        public double convert(double kelvin){
            return kelvin;
        }
    },
    CELSIUS{
        public double convert(double kelvin){
            return kelvin-273.15;
        }
    },
    FAHRENHEIT{
        public double convert(double kelvin){
            return ((kelvin-273.15)*(9.0/5.0))+32;
        }
    };

    public abstract double convert(double kelvin);
}
