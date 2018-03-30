package br.usp.pcs.mlstudy;

import org.apache.commons.math3.linear.DefaultRealMatrixChangingVisitor;

/**
 *
 * @author vinicius
 */
public class SigmoidVisitor extends DefaultRealMatrixChangingVisitor {

    @Override
    public double visit(int row, int column, double value) {
        return 1.0/(1.0+Math.exp(-value));
    }
}
