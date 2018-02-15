package br.usp.pcs.mlstudy;

import br.usp.pcs.mlstudy.functions.CostFunction;
import lombok.Data;
import org.apache.commons.math3.linear.RealMatrix;

/**
 *
 * @author vinicius
 */
@Data
public class GradientDescent {

    private double alpha;
    private int maxIterations;

    public GradientDescent(double alpha, int maxIterations) {
        this.alpha = alpha;
        this.maxIterations = maxIterations;
    }

    public void run(CostFunction cf, ProblemData pd) {
        for (int iteration = 0; iteration < maxIterations; iteration++) {
            RealMatrix savedTable = cf.calculateMatrix(pd);//tem que ser mudado
            RealMatrix ThetaCpy=pd.getTheta().copy();
            for (int j = 0; j < pd.getN(); j++) {
                RealMatrix partialX = pd.getX().getSubMatrix(0, pd.getM()-1, j, j);
                RealMatrix multiply = savedTable.transpose().multiply(partialX);
                double J = (1.0 / pd.getM()) * MatrixHelper.sum(multiply);
                ThetaCpy.setEntry(0, j, ThetaCpy.getEntry(0, j) - alpha * J);
            }
            pd.setTheta(ThetaCpy);
        }
    }
}
