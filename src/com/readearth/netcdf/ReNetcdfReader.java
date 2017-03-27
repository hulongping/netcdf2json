package com.readearth.netcdf;

import java.io.IOException;

import com.readearth.util.ArrayUtil;

import ucar.ma2.Array;
import ucar.ma2.InvalidRangeException;
import ucar.nc2.Attribute;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

public class ReNetcdfReader {

    private String _filePath;
    private NetcdfFile _ncfile = null;

    public ReNetcdfReader(String filePath) throws IOException {
        _filePath = filePath;
        try {
            _ncfile = NetcdfFile.open(_filePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * get all the array value
     *
     * @param varaName
     * @return
     */
    public Array getVaraAllValue(String varaName) {
        Variable var = _ncfile.findVariable(varaName);
        try {
            return var.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get value by rect index
     *
     * @param varaName
     * @param orgins
     * @param rects
     * @return
     * @throws IOException ,InvalidRangeException
     */
    public Array getValueByRect(String varaName, String origins, String rects) throws IOException ,InvalidRangeException {
        Variable varBean = _ncfile.findVariable(varaName);
        int[] start = ArrayUtil.getIntArray(origins);
        int[] rect = ArrayUtil.getIntArray(rects);

        try {
            return varBean.read(start, rect);
        } catch (IOException | InvalidRangeException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * get the vara attr
     *
     * @param varaName
     * @param attrName
     * @return
     */
    public Attribute getVaraAttribute(String varaName, String attrName) {
        Variable varBean = _ncfile.findVariable(varaName);
        return varBean.findAttribute(attrName);
    }

    public void close() {
        if (_ncfile != null) {
            try {
                _ncfile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
