package com.readearth.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.alibaba.fastjson.JSON;

import com.readearth.object.*;
import com.readearth.util.Utils;
import ucar.ma2.ArrayFloat;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

/**
 * Created by hulongping on 2017/3/27.
 */
public class GFSWeatherJson {

    public void createGeoJsonFromNetcdf(String ncFilePath,String jsonPath,String uName,String vName){


        NetcdfFile ncFile = null;
        try {
            ncFile = NetcdfFile.open(ncFilePath);

            Variable varU10 = ncFile.findVariable(uName);
            Variable varV10 = ncFile.findVariable(vName);

            ArrayFloat.D3 u10= (ArrayFloat.D3)varU10.read();
            ArrayFloat.D3 v10= (ArrayFloat.D3)varV10.read();

            NcJsonObject uObject =new NcJsonObject();

            NcJsonHeader uHeader=new NcJsonHeader();
            uHeader.parameterNumber=2;
            uHeader.parameterNumberName="U-component_of_wind";
            uHeader.numberPoints = 181*360;
            uHeader.nx=360;
            uHeader.ny=181;
            uHeader.lo1=0;
            uHeader.lo2=360;
            uHeader.la1 = 90;
            uHeader.la2 = -90;
            uHeader.dx=1;
            uHeader.dy=1;
            uObject.header =uHeader;
            uObject.data = new double[uHeader.numberPoints];

            NcJsonObject vObject =new NcJsonObject();
            NcJsonHeader vHeader=new NcJsonHeader();
            vHeader.parameterNumber=3;
            vHeader.parameterNumberName="V-component_of_wind";
            vHeader.numberPoints = 181*360;
            vHeader.nx=360;
            vHeader.ny=181;
            vHeader.lo1=0;
            vHeader.lo2=360;
            vHeader.la1 = 90;
            vHeader.la2 = -90;
            vHeader.dx=1;
            vHeader.dy=1;
            vObject.header = vHeader;
            vObject.data = new double[vHeader.numberPoints];

            int lonNumber=360;
            int latNumber=181;

            int idx=0;

            for(int j=0;j<latNumber;j++){
                //所有控制点的坐标
                for(int i=0;i<lonNumber;i++){


                    uObject.data[idx] =  Utils.saveValidNumber(u10.get(0, latNumber-j-1, i),2);
                    vObject.data[idx] =  Utils.saveValidNumber(v10.get(0, latNumber-j-1, i),2);
                    idx++;
                }
            }

            List<NcJsonObject> objectFiles = new ArrayList<NcJsonObject>();
            objectFiles.add(uObject);
            objectFiles.add(vObject);

            String msg = JSON.toJSONString(objectFiles);

            BufferedWriter out = new BufferedWriter(new FileWriter(jsonPath));
            out.write(msg);
            out.close();


        }catch (Exception ioe) {
            ioe.printStackTrace();
        } finally {
            if (null != ncFile)
                try {
                    ncFile.close();
                } catch (IOException ioe) {
                }

        }

    }

}
