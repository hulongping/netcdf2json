package com.object;

import java.io.Serializable;

public class NcJsonObject implements Serializable {
	
	public NcJsonObject(){
		meta = new NcJsonMeta();
		meta.date="2016-09-14T08:00:00.000Z";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8018539429381292589L;

	public NcJsonHeader header;
	public NcJsonMeta meta;	
	public double[] data;
	
}
