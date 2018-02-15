package br.usp.pcs.mlstudy;

import br.usp.pcs.mlstudy.main.LR;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.StatUtils;

/**
 *
 * @author vinicius
 */
public class ProblemReader {

    public static double sum(RealMatrix m) {
        double[][] aux = m.getData();
        double[] tosum = new double[aux.length];
        for (int i = 0; i < aux.length; i++) {
            tosum[i] = StatUtils.sum(aux[i]);
        }
        return StatUtils.sum(tosum);
    }

    public static RealMatrix generateEulerMatrix(int m) {
        double[][] aux = new double[1][m];
        for (int i = 0; i < m; i++) {
            aux[0][i] = Math.E;
        }
        RealMatrix euler = MatrixUtils.createRealMatrix(aux);
        return euler;
    }

    public static MatrixHelper readMatrix(String filename) {
        ArrayList<double[]> allLines = new ArrayList<>();
        ArrayList<Double> allY = new ArrayList<>();
        Reader in;
        try {
            in = new FileReader(filename);
            CSVParser csvParser = new CSVParser(in, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim());
            Set set = csvParser.getHeaderMap().keySet();
            String[] headers = (String[]) set.toArray(new String[set.size()]);
            Iterable<CSVRecord> records = csvParser.getRecords();

            for (CSVRecord record : records) {
                double[] vals = new double[headers.length];//do not consider y, but consider x0
                Arrays.fill(vals, 1);//coluna 0 terá o 1
                int i = 1;//considering x0
                for (String collumname : headers) {
                    if (collumname.equalsIgnoreCase("y")) {
                        allY.add(Double.parseDouble(record.get(collumname)));
                    } else {
                        vals[i++] = Double.parseDouble(record.get(collumname));
                    }
                }
                allLines.add(vals);
            }
            int m = allLines.size();
            int n = headers.length;//do not consider y, but consider x0
            double[][] matrixData = new double[m][n];
            for (int i = 0; i < m; i++) {
                matrixData[i] = allLines.get(i);
            }
            double[] yValues = allY.stream().mapToDouble(Double::doubleValue).toArray();
            double[] theta = new double[n];
            Arrays.fill(theta, 0);
            MatrixHelper pd = new MatrixHelper();
            pd.setX(MatrixUtils.createRealMatrix(matrixData));
            pd.setN(n);
            pd.setM(m);
            double[][] aux = new double[1][m];
            aux[0] = Arrays.copyOf(yValues, yValues.length);
            pd.setY(MatrixUtils.createRealMatrix(aux).transpose());
            aux = new double[1][n];
            aux[0] = Arrays.copyOf(theta, theta.length);
            pd.setTheta(MatrixUtils.createRealMatrix(aux));
            return pd;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(LR.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LR.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
