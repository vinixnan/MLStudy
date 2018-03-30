package br.usp.pcs.mlstudy;

import br.usp.pcs.mlstudy.functions.CostFunction;
import br.usp.pcs.mlstudy.functions.Coursera;
import br.usp.pcs.mlstudy.functions.CourseraLogistic;
import br.usp.pcs.mlstudy.functions.StRD;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author vinicius
 */
public class CostFunctionsTest {

    @Test
    public void courseraLogisticGradientFunction() {
        System.out.println("courseraLogisticGradientFunction");
        String filename = "data/ex2Data/ex2data1.dat";
        ProblemData lr = MatrixHelper.readMatrix(filename);
        CourseraLogistic cf = new CourseraLogistic();
        double[] got = cf.calculateGradient(lr);
        double[] expected0 = {-0.1000, -12.0092, -11.2628};
        Assert.assertArrayEquals(got, expected0, 0.001);
    }

    /**
     * Test of courseraLinearFunction method, of class CostFunctions.
     */
    @Test
    public void courseraLogisticFunction() {
        System.out.println("courseraLogisticFunction");
        String filename = "data/ex2Data/ex2data1.dat";
        ProblemData lr = MatrixHelper.readMatrix(filename);
        CourseraLogistic cf = new CourseraLogistic();
        double avg = cf.calculateFunction(lr);
        System.out.println(avg);
        Assert.assertEquals(avg, 0.693, 0.001);
    }
    
    @Test
    public void courseraLogisticFunctionRegularizated() {
        /*
            this test does not use mapFeature, it just add ones collumn
        */
        System.out.println("courseraLogisticFunctionRegularizated");
        String filename = "data/ex2Data/ex2data2.dat";
        ProblemData lr = MatrixHelper.readMatrix(filename);
        CourseraLogistic cf = new CourseraLogistic();
        lr.setTheta(MatrixHelper.generateOneNumberMatrix(lr.getN(), 1));
        double avg = cf.calculateFunctionRegularized(lr, 10);
        System.out.println(avg);
        Assert.assertEquals(avg, 1.0238, 0.001);
    }

    @Test
    public void courseraSigmoid() {
        System.out.println("courseraSigmoid");
        String filename = "data/ex2Data/ex2data1.dat";
        ProblemData lr = MatrixHelper.readMatrix(filename);
        CourseraLogistic cf = new CourseraLogistic();
        RealMatrix tab = cf.calculateMatrix(lr);
        double[] row0 = tab.getRow(0);
        double[] expected0 = {0.5};
        Assert.assertArrayEquals(row0, expected0, 0.001);
    }
    
    @Test
    public void courseraRegularization1Function() {
        /*
            this test does not use mapFeature, it just add ones collumn
        */
        System.out.println("courseraRegularization1Function");
        String filename = "data/ex2Data/ex2data2.dat";
        ProblemData lr = MatrixHelper.readMatrix(filename);
        CourseraLogistic cf = new CourseraLogistic();
        lr.setTheta(MatrixHelper.generateOneNumberMatrix(lr.getN(), 1));
        double avg = cf.regularization1(lr.getM(), 10.0, lr.getTheta());
        System.out.println(avg);
        Assert.assertEquals(avg, 0.084746, 0.001);
    }
    
    @Test
    public void courseraRegularization2Function() {
        System.out.println("courseraRegularization2Function");
        String filename = "data/ex2Data/ex2data2.dat";
        ProblemData lr = MatrixHelper.readMatrix(filename);
        CourseraLogistic cf = new CourseraLogistic();
        lr.setTheta(MatrixHelper.generateOneNumberMatrix(lr.getN(), 1));
        double avg = cf.regularization2(lr.getM(), 10.0, lr.getTheta());
        System.out.println(avg);
        Assert.assertEquals(avg, 0.084746, 0.001);
    }
    
    /**
     * Test of courseraLinearFunction method, of class CostFunctions.
     */
    @Test
    public void testCourseraLinearFunction() {
        System.out.println("courseraLinearFunction");
        String filename = "data/test.dat";
        ProblemData lr = MatrixHelper.readMatrix(filename);
        CostFunction cf = new Coursera();
        double avg = cf.calculateFunction(lr);
        System.out.println(avg);
        Assert.assertEquals(avg, 32.073, 0.001);
    }

    /**
     * Test of norrisLinearFunction method, of class CostFunctions.
     */
    @Test
    public void testNorrisLinearFunction() {
        System.out.println("norrisLinearFunction");
        String filename = "data/norris.dat";
        ProblemData lr = MatrixHelper.readMatrix(filename);
        double[][] theta = lr.getTheta().getData();
        theta[0][0] = -0.262323073774029;
        theta[0][1] = 1.00211681802045;
        CostFunction cf = new StRD();
        double avg = cf.calculateFunction(lr);
        System.out.println(avg);
        Assert.assertEquals(avg, 0.884796396144373, 0.001);
    }
}
