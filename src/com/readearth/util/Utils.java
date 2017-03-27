package com.readearth.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by hulongping on 2017/3/27.
 */
public class Utils {

    public static String noParamValue = "-9999";
    public static double rad = Math.PI / 180.0;

    public static double saveValidNumber(double value, int validCount) {
        String df = ".";
        for (int i = 0; i < validCount; i++) {
            df = df + "0";
        }
        return new Double(new DecimalFormat(df).format(value));
    }

    public static boolean isContainedByRect(float x, float y, float xmin,
                                            float xmax, float ymin, float ymax) {
        float lon = x;
        float lat = y;

        if (xmin <= -180)
            xmin = xmin + 360;
        if (xmax >= 180)
            xmax = xmax - 360;

        if (xmax > xmin) {
            if (lon >= xmin && lon <= xmax && lat >= ymin && lat <= ymax)
                return true;
            else
                return false;
        } else {
            if ((lon > xmin || lon < xmax) && lat >= ymin && lat <= ymax)
                return true;
            else
                return false;
        }
    }


    public static final double EARTH_RADIUS = 6378.1370;
    /**
     * 计算两点之间距离
     *
     * @param _lat1
     *            - start纬度
     * @param _lon1
     *            - start经度
     * @param _lat2
     *            - end纬度
     * @param _lon2
     *            - end经度
     * @return km(四舍五入)
     */
    public static double getCircleDistance(double _lat1, double _lon1, double _lat2,
                                     double _lon2) {
        try {
            double lat1 = (Math.PI / 180) * _lat1;
            double lat2 = (Math.PI / 180) * _lat2;
            double lon1 = (Math.PI / 180) * _lon1;
            double lon2 = (Math.PI / 180) * _lon2;

            double d = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                    + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1))
                    * EARTH_RADIUS;
            return new BigDecimal(d).setScale(4, BigDecimal.ROUND_HALF_UP)
                    .doubleValue();
        } catch (Exception e) {
            // e.printStackTrace();
            return 0;
        }
    }


    public static double calculateSpeed(double u, double v) {
        double speed = Math.sqrt(u * u + v * v);
        return speed;
    }

    /**
     * 获得地理上的角度
     *
     * @param u
     * @param v
     * @return
     */
    public static int calculateDirection(double u, double v) {
        double dir = Math.atan2(u, v) / rad + 180;
        return (int) (dir);
    }

    public static int calculateIndex(double lon, double lat) {
        int lonClass = reClass(lon, -180, 0.5);
        int latClass = reClass(lat, -90, 0.5);
        if (lonClass > latClass)
            return latClass;
        else
            return lonClass;
    }

    public static int reClass(double position, double startValue, double gapValue) {
        int positionIndex = (int) ((position - startValue) / gapValue);
        if (positionIndex % 16 == 0)
            return 4;
        else if (positionIndex % 8 == 0)
            return 3;
        else if (positionIndex % 4 == 0)
            return 2;
        else if (positionIndex % 2 == 0)
            return 1;
        else
            return 0;
    }

    public static void createWGS1984Prj(String outputPrj) throws IOException {

        // 生成坐标文件
        FileWriter prjFileWrite = new FileWriter(outputPrj);
        BufferedWriter prjWriter = new BufferedWriter(prjFileWrite);
        prjWriter.write("Projection    GEOGRAPHIC");
        prjWriter.newLine();
        prjWriter.write("Datum         WGS84");
        prjWriter.newLine();
        prjWriter.write("Spheroid      WGS84");
        prjWriter.newLine();
        prjWriter.write("Units         DD");
        prjWriter.newLine();
        prjWriter.write("Zunits        NO");
        prjWriter.newLine();
        prjWriter.write("Parameters    ");

        prjWriter.flush();
        prjWriter.close();
        prjFileWrite.close();
    }




}
