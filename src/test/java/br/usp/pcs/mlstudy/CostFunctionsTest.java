package br.usp.pcs.mlstudy;

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
        ProblemData lr = new ProblemData(filename);
        lr.readMatrix();
        CostFunctions cf=new CostFunctions(lr.getXMatrix(), lr.getYMatrix(), lr.getThetaMatrix(), lr.getM(), lr.getN());
        double avg= cf.courseraLinearFunction();
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
        ProblemData lr = new ProblemData(filename);
        lr.readMatrix();
        double[] theta=lr.getTheta();
        theta[0]=-0.262323073774029;
        theta[1]=1.00211681802045;
        CostFunctions cf=new CostFunctions(lr.getXMatrix(), lr.getYMatrix(), lr.getThetaMatrix(), lr.getM(), lr.getN());
        double avg= cf.norrisLinearFunction();
        System.out.println(avg);
        Assert.assertEquals(avg, 0.884796396144373,  0.001);
    }
}
