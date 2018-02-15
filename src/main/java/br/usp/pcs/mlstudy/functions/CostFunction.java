package br.usp.pcs.mlstudy.functions;

import br.usp.pcs.mlstudy.MatrixHelper;
import org.apache.commons.math3.linear.RealMatrix;

/**
 *
 * @author vinicius
 */
public interface CostFunction {
    RealMatrix calculateMatrix(MatrixHelper pd);
    double calculateLinearFunction(MatrixHelper pd);
}
