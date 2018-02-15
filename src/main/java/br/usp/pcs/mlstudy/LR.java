/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.pcs.mlstudy;

/**
 *
 * @author vinicius
 */
public class LR {

    public static void main(String[] args) {
        String filename = "data/norris.dat";
        ProblemData lr = new ProblemData(filename);
        lr.readMatrix();
        double[] theta=lr.getTheta();
        theta[0]=-0.262323073774029;
        theta[1]=1.00211681802045;
        CostFunctions cf=new CostFunctions(lr.getXMatrix(), lr.getYMatrix(), lr.getThetaMatrix(), lr.getM(), lr.getN());
        double avg= cf.norrisLinearFunction();
        System.out.println(avg);
    }
}
