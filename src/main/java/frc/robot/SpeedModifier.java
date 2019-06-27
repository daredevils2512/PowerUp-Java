package frc.robot;

public enum SpeedModifier {
    SLOW(0.7),
    MEDIUM(0.85),
    FAST(1.0);

    final double value;

    SpeedModifier(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}