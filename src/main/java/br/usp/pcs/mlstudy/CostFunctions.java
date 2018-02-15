/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.pcs.mlstudy;

import lombok.Data;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.StatUtils;

/**
 *
 * @author vinicius
 */
@Data
public class CostFunctions {

    private RealMatrix X;
    private RealMatrix Y;
    private RealMatrix Theta;
    private int m;
    private int n;

    public CostFunctions(RealMatrix X, RealMatrix Y, RealMatrix Theta, int m, int n) {
        this.X = X;
        this.Y = Y;
        this.Theta = Theta;
        this.m = m;
        this.n = n;
    }

    private double sum(RealMatrix m) {
        double[][] aux = m.getData();
        double[] tosum = new double[aux.length];
        for (int i = 0; i < aux.length; i++) {
            tosum[i] = StatUtils.sum(aux[i]);
        }
        return StatUtils.sum(tosum);
    }

    private RealMatrix generateEulerMatrix() {
        double[][] aux = new double[1][m];
        for (int i = 0; i < m; i++) {
            aux[0][i] = Math.E;
        }
        RealMatrix euler = MatrixUtils.createRealMatrix(aux);
        return euler;
    }

    public double courseraLinearFunction() {
        RealMatrix costFunction = X.multiply(Theta.transpose());
        RealMatrix jCalc = costFunction.subtract(Y.transpose());
        PowVisitor pw = new PowVisitor(2);
        jCalc.walkInOptimizedOrder(pw);
        return 1.0 / (2.0 * m) * sum(jCalc);
    }

    public double norrisLinearFunction() {
        RealMatrix euler = generateEulerMatrix();
        RealMatrix costFunction = X.multiply(Theta.transpose()).add(euler.transpose());
        RealMatrix jCalc = costFunction.subtract(Y.transpose());
        PowVisitor pw = new PowVisitor(2);
        jCalc.walkInOptimizedOrder(pw);
        return Math.sqrt(sum(jCalc)/(m-2.0));
    }
}
