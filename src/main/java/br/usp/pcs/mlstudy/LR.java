/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;

/**
 *
 * @author vinicius
 */
public class LR {

    public double[][] readMatrix(String filename) {
        double[][] toReturn = null;
        ArrayList<double[]> allLines = new ArrayList<>();
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
                double[] vals = new double[headers.length];
                int i = 0;
                for (String collumname : headers) {
                    vals[i++] = Double.parseDouble(record.get(collumname));
                }
                allLines.add(vals);
            }
            int n = allLines.size();
            int m = headers.length;
            toReturn = new double[n][m];
            for (int i = 0; i < n; i++) {
                toReturn[i] = allLines.get(i);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LR.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LR.class.getName()).log(Level.SEVERE, null, ex);
        }
        return toReturn;
    }

    public static void main(String[] args) {
        String filename = "data/norris.dat";
        LR lr = new LR();
        double[][] matrixData = lr.readMatrix(filename);
        for (int i = 0; i < matrixData.length; i++) {
            System.out.println(Arrays.toString(matrixData[i]));
        }
        int n = matrixData.length;
        int m = matrixData[0].length;
        double[] theta = new double[m];

        RealMatrix thetaMatrix = new Array2DRowRealMatrix(theta);

        RealMatrix X = MatrixUtils.createRealMatrix(matrixData);
        RealMatrix I = MatrixUtils.createRealIdentityMatrix(5);
        RealMatrix pInverse = new QRDecomposition(X).getSolver().getInverse();
        System.out.println("wait");
    }
}
