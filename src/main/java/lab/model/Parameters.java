package lab.model;

import java.util.Arrays;

public class Parameters {

    double[] weights;
    double[] speeds;

    public Parameters(double[] weights, double[] speeds) {
        this.weights = weights;
        this.speeds = speeds;
    }

    public double[] getWeights() {
        return weights;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }

    public double[] getSpeeds() {
        return speeds;
    }

    public void setSpeeds(double[] speeds) {
        this.speeds = speeds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameters that = (Parameters) o;
        return Arrays.equals(weights, that.weights) && Arrays.equals(speeds, that.speeds);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(weights);
        result = 31 * result + Arrays.hashCode(speeds);
        return result;
    }
}
