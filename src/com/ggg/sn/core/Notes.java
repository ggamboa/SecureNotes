package com.ggg.sn.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents notes objects that will be saved and restored to/from disk file
 * @author GG2712
 *
 */
public class Notes implements Serializable {
	
	private List<Credential> passwordList;
	
	public Notes() {
		passwordList = new ArrayList<Credential>();
	}
	
	public void add(Credential c) {
		passwordList.add(c);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Credential c : passwordList) {
			sb.append(c.toString() + "\n");
		}
		return sb.toString();
	}
	
	

}
