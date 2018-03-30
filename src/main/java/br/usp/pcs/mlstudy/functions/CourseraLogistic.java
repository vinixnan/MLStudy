package br.usp.pcs.mlstudy.functions;

import br.usp.pcs.mlstudy.SigmoidVisitor;
import br.usp.pcs.mlstudy.LogVisitor;
import br.usp.pcs.mlstudy.ProblemData;
import br.usp.pcs.mlstudy.MatrixHelper;
import br.usp.pcs.mlstudy.PowVisitor;
import org.apache.commons.math3.linear.RealMatrix;

/**
 *
 * @author vinicius
 */
public class CourseraLogistic implements CostFunction {

    private void sigmoid(RealMatrix z) {
        z.walkInColumnOrder(new SigmoidVisitor());
    }

    @Override
    public RealMatrix calculateMatrix(ProblemData pd) {
        RealMatrix h = pd.getX().multiply(pd.getTheta().transpose());
        sigmoid(h);
        return h;
    }

    @Override
    public double calculateFunction(ProblemData pd) {

        RealMatrix g = calculateMatrix(pd);
        RealMatrix g2 = g.copy();
        RealMatrix y = pd.getY();
        RealMatrix y2 = y.copy();

        //J = -1/m * sum(y'*log(g) + (1-y)'*log(1 -g));
        g.walkInOptimizedOrder(new LogVisitor());//log(g)
        //log(1 -g)
        g2.walkInOptimizedOrder(new LogVisitor(1));
        //(1-y)
        RealMatrix ones = MatrixHelper.generateOneNumberMatrix(pd.getM(), 1);
        y2 = ones.transpose().subtract(y2);

        //y'*log(g)
        RealMatrix p1 = y.transpose().multiply(g);
        // + (1-y)'*log(1 -g)
        RealMatrix p2 = y2.transpose().multiply(g2);
        //y'*log(g) + (1-y)'*log(1 -g)
        RealMatrix jCalc = p1.add(p2);
        //J = -1/m * sum(y'*log(g) + (1-y)'*log(1 -g));
        return (-1.0 / (pd.getM())) * MatrixHelper.sum(jCalc);
    }

    public double calculateFunctionRegularized(ProblemData pd, double lambda) {
        double standard = calculateFunction(pd);
        double regularization = regularization1(pd.getM(), lambda, pd.getTheta());
        return standard + regularization;
    }

    public double[] calculateGradient(ProblemData pd) {
        //grad=(1/m)*sum((g -y) .* X)
        RealMatrix g = calculateMatrix(pd);
        RealMatrix jCalc = g.subtract(pd.getY());
        jCalc = jCalc.preMultiply(pd.getX().transpose());
        RealMatrix multiplier = MatrixHelper.generateOneNumberMatrix(jCalc.getRowDimension(), 1.0 / (pd.getM()));
        jCalc = multiplier.preMultiply(jCalc);
        return jCalc.getColumn(0);
    }

    public double regularization1(double m, double lambda, RealMatrix theta) {
        //regularization1=(lambda/(2*m))*sum(power(theta(2:length(theta)),2));
        /*
          theta in this implementation aways has the inserted 1
         */
        theta = theta.getSubMatrix(0, theta.getRowDimension() - 1, 1, theta.getColumnDimension() - 1);
        PowVisitor pw = new PowVisitor(2);
        theta.walkInOptimizedOrder(pw);
        double sum = MatrixHelper.sum(theta);
        double p1 = (lambda / (2.0 * m));
        return p1 * sum;
    }

    public double regularization2(double m, double lambda, RealMatrix theta) {
        //regularization2=(lambda/(m)).*theta(2:length(theta));
        /*
          theta in this implementation aways has the inserted 1
         */
        theta = theta.getSubMatrix(0, theta.getRowDimension() - 1, 2, theta.getColumnDimension() - 1);
        RealMatrix lambdaMatrix = MatrixHelper.generateOneNumberMatrix(theta.getColumnDimension(), (lambda / (m)));
        RealMatrix ret = lambdaMatrix.preMultiply(theta);
        return MatrixHelper.sum(ret);
    }
}
