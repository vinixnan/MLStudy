package br.usp.pcs.mlstudy.main;

import br.usp.pcs.mlstudy.GradientDescent;
import br.usp.pcs.mlstudy.ProblemData;
import br.usp.pcs.mlstudy.MatrixHelper;
import br.usp.pcs.mlstudy.functions.CostFunction;
import br.usp.pcs.mlstudy.functions.Coursera;

/**
 *
 * @author vinicius
 */
public class LR {

    public static void main(String[] args) {
        String filename = "data/test.dat";
        ProblemData lr=MatrixHelper.readMatrix(filename);
        CostFunction cf=new Coursera();
        GradientDescent gd=new GradientDescent(0.01, 1500);
        gd.run(cf, lr);
        System.out.println(lr.getTheta());
        System.out.println(cf.calculateLinearFunction(lr));
    }
}
