package br.usp.pcs.mlstudy;

import org.apache.commons.math3.linear.RealMatrix;

/**
 *
 * @author vinicius
 */
@lombok.Data
public class MatrixHelper {

    private RealMatrix X;
    private RealMatrix Y;
    private RealMatrix Theta;
    private int m;
    private int n;

    public MatrixHelper(RealMatrix X, RealMatrix Y, RealMatrix Theta, int m, int n) {
        this.X = X;
        this.Y = Y;
        this.Theta = Theta;
        this.m = m;
        this.n = n;
    }

    public MatrixHelper() {
    }

}
