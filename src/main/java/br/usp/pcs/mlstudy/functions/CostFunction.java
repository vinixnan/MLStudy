package br.usp.pcs.mlstudy.functions;

import br.usp.pcs.mlstudy.ProblemData;
import org.apache.commons.math3.linear.RealMatrix;

/**
 *
 * @author vinicius
 */
public interface CostFunction {
    RealMatrix calculateMatrix(ProblemData pd);
    double calculateFunction(ProblemData pd);
}
