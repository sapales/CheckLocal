package org.santi.checklocal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class DirLocalCfg {
	
	final String fichConfig = "checklocal.cfg";
		
	public String getDirLocal(){
		
		String dirLocal;
		String appPath;
		
		// Cogemos el App.Path
		appPath = new File("").getAbsoluteFile().toString();
		if(!appPath.endsWith("\\")){
			appPath = appPath + "\\";
		}

		// Comprobamos si existe el fichero de configuración
		File f = new File(appPath + fichConfig);
		if(!f.exists()){
			System.out.println("No existe el fichero " + appPath + fichConfig);
			return null;
		}
		
		// Leemos el fichero
		try{
			FileReader fr = new FileReader(appPath + fichConfig);
		    BufferedReader b = new BufferedReader(fr);
		    while((dirLocal = b.readLine())!=null) {
		    	System.out.println(dirLocal);
		    	break;
		    }
		    b.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		
		// Comprobamos si existe el directorio
		File directorio = new File(dirLocal);
	    if (directorio.isDirectory()){
	        return dirLocal;    
	    }else{
	        System.out.println(" NO es un directorio");
	        return null;
	    }

	}

}
