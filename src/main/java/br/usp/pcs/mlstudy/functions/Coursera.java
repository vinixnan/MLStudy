package br.usp.pcs.mlstudy.functions;

import br.usp.pcs.mlstudy.PowVisitor;
import br.usp.pcs.mlstudy.MatrixHelper;
import br.usp.pcs.mlstudy.ProblemReader;
import org.apache.commons.math3.linear.RealMatrix;

/**
 *
 * @author vinicius
 */
public class Coursera implements CostFunction{

    @Override
    public RealMatrix calculateMatrix(MatrixHelper pd) {
        RealMatrix costFunction = pd.getX().multiply(pd.getTheta().transpose());
        RealMatrix jCalc = costFunction.subtract(pd.getY());
        return jCalc;
    }

    @Override
    public double calculateLinearFunction(MatrixHelper pd) {
        RealMatrix jCalc = calculateMatrix(pd);
        PowVisitor pw = new PowVisitor(2);
        jCalc.walkInOptimizedOrder(pw);
        return 1.0 / (2.0 * pd.getM()) * ProblemReader.sum(jCalc);
    }
    
}
