package br.usp.pcs.mlstudy;

import br.usp.pcs.mlstudy.functions.CostFunction;
import br.usp.pcs.mlstudy.functions.Coursera;
import br.usp.pcs.mlstudy.functions.StRD;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author vinicius
 */
public class CostFunctionsTest {
    
    /**
     * Test of courseraLinearFunction method, of class CostFunctions.
     */
    @Test
    public void testCourseraLinearFunction() {
        System.out.println("courseraLinearFunction");
        String filename = "data/test.dat";
        MatrixHelper lr=ProblemReader.readMatrix(filename);
        CostFunction cf=new Coursera();
        double avg= cf.calculateLinearFunction(lr);
        System.out.println(avg);
        Assert.assertEquals(avg, 32.073,  0.001);
    }
    
    /**
     * Test of norrisLinearFunction method, of class CostFunctions.
     */
    @Test
    public void testNorrisLinearFunction() {
        System.out.println("norrisLinearFunction");
        String filename = "data/norris.dat";
        MatrixHelper lr=ProblemReader.readMatrix(filename);
        double[][] theta=lr.getTheta().getData();
        theta[0][0]=-0.262323073774029;
        theta[0][1]=1.00211681802045;
        CostFunction cf=new StRD();
        double avg= cf.calculateLinearFunction(lr);
        System.out.println(avg);
        Assert.assertEquals(avg, 0.884796396144373,  0.001);
    }
}
