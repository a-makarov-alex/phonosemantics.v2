package statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Sample {

    private static HashMap<Object, Sample> allSamples = new HashMap<>();

    private ArrayList<Double> sample;
    private Object phType;
    private double quartile_25;
    private double mean;
    private double quartile_75;
    private double average;

    public Sample(ArrayList<Double> sample, Object phType) {
        Collections.sort(sample, Collections.reverseOrder());
        this.sample = sample;
        calculateMean();
        calculateAverage();
        defineSampleQuartiles();
        allSamples.put(phType, this);
    }

    // Медиана
    private void calculateMean() {
        int size = sample.size();
        if (size % 2 == 1) {
            this.mean = sample.get(size/2);
        } else {
            this.mean = (sample.get((size/2) - 1) + sample.get(size/2))/2.0;
        }
    }

    // Среднее арифметическое
    private void calculateAverage() {
        double sum = 0.0;
        for (Double elem : sample) {
            sum += elem;
        }
        average = sum / sample.size();
    }


    // define 25%, 75% quartiles
    public void defineSampleQuartiles() {
        int size = sample.size();
        if ((size/2) % 2 == 0) {
            quartile_25 = (sample.get(size/4 - 1) + sample.get(size/4)) / 2;
            quartile_75 = (sample.get(size/4*3 - 1) + sample.get(size/4*3)) / 2;
        } else {
            quartile_25 = sample.get(size/4);
            quartile_75 = sample.get(size/2 + size/4);
        }
    }

    // Какой квартиль
    public int whatQuartile(Double d) {
        int q = 0;
        if (d >= quartile_25) {
            q = 1;
        } else if (d >= mean) {
            q = 2;
        } else if (d >= quartile_75) {
            q = 3;
        } else {
            q = 4;
        }
        return q;
    }

    /************ GETTERS AND SETTERS **************/
    public ArrayList<Double> getSample() {
        return sample;
    }
    public double getQuartile_25() {
        return quartile_25;
    }
    public void setQuartile_25(double quartile_25) {
        this.quartile_25 = quartile_25;
    }
    public double getMean() {
        return mean;
    }
    public void setMean(double mean) {
        this.mean = mean;
    }
    public double getQuartile_75() {
        return quartile_75;
    }
    public void setQuartile_75(double quartile_75) {
        this.quartile_75 = quartile_75;
    }
    public double getAverage() {
        return average;
    }
    public void setAverage(double average) {
        this.average = average;
    }
    public static HashMap<Object, Sample> getAllSamples() {
        return allSamples;
    }
}
