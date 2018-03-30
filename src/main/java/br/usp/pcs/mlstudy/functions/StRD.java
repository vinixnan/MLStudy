package br.usp.pcs.mlstudy.functions;


import br.usp.pcs.mlstudy.ProblemData;
import br.usp.pcs.mlstudy.MatrixHelper;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;

/**
 *
 * @author vinicius
 */
public class StRD implements CostFunction {

    @Override
    public RealMatrix calculateMatrix(ProblemData pd) {
        RealMatrix euler = MatrixHelper.generateEulerMatrix(pd.getM());
        RealMatrix costFunction = pd.getX().multiply(pd.getTheta().transpose()).add(euler.transpose());
        RealMatrix jCalc = costFunction.subtract(pd.getY());
        return jCalc;
    }

    public RealMatrix normalEquation(ProblemData pd) {
        RealMatrix nE = pd.getX().transpose().multiply(pd.getX());
        nE = new QRDecomposition(nE).getSolver().getInverse();
        nE = nE.multiply(pd.getX().transpose().multiply(pd.getY()));
        return nE;
    }

    public double calculateResidualMeanSquare(ProblemData pd) {
        RealMatrix nE = normalEquation(pd);
        RealMatrix ss = pd.getY().transpose().multiply(pd.getY());
        RealMatrix partB = nE.transpose().multiply(pd.getX().transpose()).multiply(pd.getY());
        ss = ss.subtract(partB);
        return MatrixHelper.sum(ss) / (pd.getM() - pd.getN());
    }

    public double calculateResidualStandardDeviation(ProblemData pd) {
        return Math.sqrt(calculateResidualMeanSquare(pd));
    }

    @Override
    public double calculateFunction(ProblemData pd) {
        return calculateResidualStandardDeviation(pd);
    }

}
