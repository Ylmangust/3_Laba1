/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi.b22901.lab1.Model;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.stat.descriptive.*;
import org.apache.commons.math3.stat.correlation.*;
import org.apache.commons.math3.stat.descriptive.moment.GeometricMean;
import org.apache.commons.math3.stat.descriptive.rank.*;

/**
 *
 * @author Регина
 */
public class ProcessClass {

    private double[] gMean;
    private double[] aMean;
    private double[] stDeav;
    private double[] range;
    private double[] elNumber;
    private double[][] confInterval;
    private double[] var;
    private double[] max;
    private double[] min;
    private double[] varCoef;

    public ProcessClass(List<List<Double>> data) {
        int columns = data.get(0).size();
        int rows = data.size();
        gMean = new double[columns];
        aMean = new double[columns];
        stDeav = new double[columns];
        range = new double[columns];
        elNumber = new double[columns];
        confInterval = new double[columns][2];
        var = new double[columns];
        max = new double[columns];
        min = new double[columns];
        varCoef = new double[columns];

        for (int j = 0; j < columns; j++) {
            DescriptiveStatistics stats = new DescriptiveStatistics();
            double[] temp = new double[rows];
            for (int i = 0; i < rows; i++) {
                temp[i] = Math.abs(data.get(i).get(j));
                stats.addValue(data.get(i).get(j));
            }

            gMean[j] = new GeometricMean().evaluate(temp);
            aMean[j] = stats.getMean();
            stDeav[j] = stats.getStandardDeviation();
            range[j] = stats.getMax() - stats.getMin();
            elNumber[j] = stats.getN();
            var[j] = stats.getVariance();
            max[j] = stats.getMax();
            min[j] = stats.getMin();
            varCoef[j] = stDeav[j] / aMean[j] * 100;
        }

        for (int i = 0; i < columns; i++) {
            System.out.println(gMean[i]);
        }
    }

    public List<double[]> returnData() {
        List<double[]> data = new ArrayList<>();
        data.add(gMean);
        data.add(aMean);
        data.add(stDeav);
        data.add(range);
        data.add(elNumber);
        data.add(var);
        data.add(max);
        data.add(min);
        data.add(varCoef);
        return data;

    }

}
