package br.usp.pcs.mlstudy;

import org.apache.commons.math3.linear.DefaultRealMatrixChangingVisitor;

/**
 *
 * @author vinicius
 */
public class PowVisitor extends DefaultRealMatrixChangingVisitor {

    double factor;

    public PowVisitor(double factor) {
        this.factor = factor;
    }
    
    

    @Override
    public double visit(int row, int column, double value) {
        return Math.pow(value, factor);
    }
}
