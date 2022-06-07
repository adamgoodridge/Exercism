import java.text.DecimalFormat;

class SpaceAge {

    private final double EARTH_ORBITAL_PERIOD_SECONDS = 31557600;
    private final double MERCURY_ORBITAL_PERIOD = 0.2408467;
    private final double VENUS_ORBITAL_PERIOD = 0.61519726;
    private final double MARS_ORBITAL_PERIOD = 1.8808158;
    private final double JUPITER_ORBITAL_PERIOD = 11.862615;
    private final double SATURN_ORBITAL_PERIOD = 29.447498;
    private final double URANUS_ORBITAL_PERIOD = 84.016846;
    private final double NEPTUNE_ORBITAL_PERIOD = 164.79132;
    private DecimalFormat df = new DecimalFormat("#.##");
    private double seconds;
    SpaceAge(double seconds) {
        this.seconds = seconds;
    }

    double getSeconds() {
        return this.seconds;
    }

    double onEarth() {
        //as method already calculate on Earth 1 means that I don't want to divide the result again
        return calculate(1);
    }
    private double calculate(double period) {
        double result = (seconds/EARTH_ORBITAL_PERIOD_SECONDS)/period;
        result = Math.round(result*100);
        result = result/100;
        return result;
    }
    double onMercury() {
      return calculate(MERCURY_ORBITAL_PERIOD);
    }

    double onVenus() {
        return calculate(VENUS_ORBITAL_PERIOD);
    }

    double onMars() {
        return calculate(MARS_ORBITAL_PERIOD);
    }

    double onJupiter() {
        return calculate(JUPITER_ORBITAL_PERIOD);
    }

    double onSaturn() {
        return calculate(SATURN_ORBITAL_PERIOD);
    }

    double onUranus() {
        return calculate(URANUS_ORBITAL_PERIOD);
    }

    double onNeptune() {
        return calculate(NEPTUNE_ORBITAL_PERIOD);
    }

}
