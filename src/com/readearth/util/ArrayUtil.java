package com.readearth.util;

import ucar.ma2.Array;
import ucar.ma2.ArrayFloat;

/**
 * Created by harveyhu on 2017/3/26.
 */
public class ArrayUtil {

    public static float[][] inverseMatrix(float[][] values)
    {
        int rows=values.length;
        int cols = values[0].length;

        float[][] nValues = new float[rows][cols];
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                nValues[i][j] = values[rows-i-1][j];
            }
        }
        return nValues;

    }


    public static float[][] getFloatArr(ArrayFloat.D2 arr)
    {
        int[] shape = arr.getShape();
        int row=shape[0];
        int column=shape[1];
        float[][] values =new float[row][column];

        for(int i=0;i<row;i++)
        {
            for(int j=0;j<column;j++)
            {
                values[i][j] = arr.get(i, j);
            }
        }

        return values;
    }


    public static int[] getIntArray(String idx)
    {
        String[] tokens=idx.split(",");
        int[] arr=new int[tokens.length];
        for(int i=0;i<arr.length;i++)
        {
            arr[i]=Integer.parseInt(tokens[i]);
        }
        return arr;
    }


    public static float[] reverseArr(float[] arr)
    {
        float[] reArr = new float[arr.length];
        for(int i=0;i<arr.length;i++)
        {
            reArr[i]=arr[arr.length-i-1];
        }
        return reArr;
    }

    public static float[] getFloatLonLatArr(Array arr){
        int number = arr.getShape()[0];
        float[] arrValues = new float[number];

        for(int i=0;i<number;i++){
            float v= arr.getFloat(i);
            if(v>180)
                v=v-360;
            arrValues[i] = v;
        }
        return arrValues;
    }




    public static float[] getFloatArr(Array arr) {

        int number = (int) arr.getSize();
        float[] arrValues = new float[number];

        for(int i=0;i<number;i++){
            arrValues[i] = (float) arr.getFloat(i);
        }
        return arrValues;
    }
}
