package br.usp.pcs.mlstudy;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Data;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

/**
 *
 * @author vinicius
 */
@Data
public class ProblemData {

    private String filename;
    private double[] theta;
    private int m;
    private int n;
    private double[][] matrixData;
    private double[] yValues;

    public ProblemData(String filename) {
        this.filename = filename;
    }
    
    public RealMatrix getXMatrix(){
        return MatrixUtils.createRealMatrix(matrixData);
    }
    
    public RealMatrix getYMatrix(){
        double[][] mY=new double[1][m];
        mY[0]=Arrays.copyOf(yValues, yValues.length);
        return MatrixUtils.createRealMatrix(mY);
    }
    
    public RealMatrix getThetaMatrix(){
        double[][] mtheta=new double[1][n];
        mtheta[0]=Arrays.copyOf(theta, theta.length);
        return MatrixUtils.createRealMatrix(mtheta);
    }

    public void readMatrix() {
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
            m = allLines.size();
            n = headers.length;//do not consider y, but consider x0
            matrixData = new double[m][n];
            for (int i = 0; i < m; i++) {
                matrixData[i] = allLines.get(i);
            }
            yValues = allY.stream().mapToDouble(Double::doubleValue).toArray();
            theta=new double[n];
            Arrays.fill(theta,0);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LR.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
