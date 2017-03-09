package org.santi.checklocal;

import java.io.BufferedReader;
import java.io.FileReader;

public class ChequeaFichero {
	
	final String CREATE_PACKAGE      = "CREATE OR REPLACE PACKAGE";
	final String CREATE_PACKAGE_BODY = "CREATE OR REPLACE PACKAGE BODY";
	final String CREATE_TYPE         = "CREATE OR REPLACE TYPE";
	final String CREATE_TYPE_BODY    = "CREATE OR REPLACE TYPE BODY";
	final String DOURO               = "DOURO.DEBUG";
	final String COMENTARIO1         = "--";

	public void chequear(String fichero){
		
		String linea = "";
		boolean siEsPaquete = false;
		boolean create_package = false;
		boolean create_packageb = false;
		boolean create_type = false;
		boolean create_typeb = false;
		boolean douro = false;
		
		// Leemos el fichero de principio a fin
		System.out.println("Chequeando " + fichero);
		try{
			FileReader fr = new FileReader(fichero);
		    BufferedReader b = new BufferedReader(fr);
		    while((linea = b.readLine())!=null) {
		    	if(linea.toUpperCase().contains(CREATE_PACKAGE_BODY)){
		    		create_packageb = true;
		    	}else if(linea.toUpperCase().contains(CREATE_PACKAGE)){
		    		create_package = true;
		    	}else if(linea.toUpperCase().contains(CREATE_TYPE_BODY)){
		    		create_typeb = true;
		    	}else if(linea.toUpperCase().contains(CREATE_TYPE)){
		    		create_type = true;
		    	}else if(linea.toUpperCase().contains(DOURO)){
		    		if(!linea.trim().startsWith(COMENTARIO1)){
		    			douro = true;
		    		}
		    	}
		    }
		    // Cerramos el fichero
		    b.close();
		    if(create_package && !create_packageb)
		    	System.out.println("CREATE PACKAGE sin BODY");
		    if(!create_package && create_packageb)
		    	System.out.println("CREATE PACKAGE BODY sin ESPECIFICACIÓN");
		    if(create_type && !create_typeb)
		    	System.out.println("CREATE TYPE sin BODY");
		    if(!create_type && create_typeb)
		    	System.out.println("CREATE TYPE BODY sin ESPECIFICACIÓN");
		    if(douro)
		    	System.out.println("Posibles líneas DOURO.DEBUG");
		    return;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return;
		}
		
	}

}
