package br.usp.pcs.mlstudy;

import org.apache.commons.math3.linear.DefaultRealMatrixChangingVisitor;

/**
 *
 * @author vinicius
 */
public class LogVisitor extends DefaultRealMatrixChangingVisitor {

    private final double toSubtract;

    public LogVisitor(double toSubtract) {
        this.toSubtract = toSubtract;
    }

    public LogVisitor() {
        toSubtract = 0;
    }

    @Override
    public double visit(int row, int column, double value) {
        if(toSubtract==0)
            return Math.log(value);
        else
            return Math.log(toSubtract - value);
    }
}
