package com.object;

import java.io.Serializable;

public class NcJsonHeader implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 60396446737407099L;
	
	public int discipline=0;
	
	public String disciplineName="Meteorological products";
	
	public int gribEdition;
	
	public int gribLength;
	
	public int center=7;
	
	public String centerName="US National Weather Service - NCEP(WMC)";
	
	public int subcenter=0;
	
	public String refTime="2016-09-14T08:00:00.000Z";
	
	public int significanceOfRT=1;
	
	public String significanceOfRTName="Start of forecast";
	
	public int productStatus=0;
	
	public String productStatusName="Operational products";
	
	public int productType=1;
	
	public String productTypeName="Forecast products";
	
	public String productDefinitionTemplate="productDefinitionTemplate";
	
	public String productDefinitionTemplateName="Analysis/forecast at horizontal level/layer at a point in time";
	
	public int parameterCategory=2;
	
	public String parameterCategoryName="Momentum";
	
	
	public int parameterNumber;
		
	public String parameterNumberName;
	
	public String parameterUnit="m.s-1";
	
	public int genProcessType=2;
	
	public String genProcessTypeName="Forecast";
	
	
	public int forecastTime=0;
	
	public int surface1Type=103;
	
	public String surface1TypeName="Specified height level above ground";
	
	public int surface1Value=10;
	
	public int surface2Type=255;
	
	public String surface2TypeName="Missing";
	
	public int surface2Value=0;
	
	public int gridDefinitionTemplate=0;
	
	public String gridDefinitionTemplateName="Latitude_Longitude";
	
	public int numberPoints;
	
	public int shape=0;
	
	public String shapeName="Earth spherical with radius of 6,371,229.0 m";
	
	public String gridUnits="degrees";
	public String winds="true";
	public int scanMode=0;
	public int nx;
	public int ny;
	public int basicAngle=0;
	public int subDivisions=0;
	public double lo1;
	public double la1;
	public double lo2;
	public double la2;
	public double dx;
	public double dy;
	
	
	
	
	

}
