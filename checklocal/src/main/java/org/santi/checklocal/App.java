package org.santi.checklocal;

import java.io.File;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String dirLocal="";
    	//DirLocalCfg dirLocalCfg = new DirLocalCfg();
    	//ChequeaFichero chequeaFichero = new ChequeaFichero(); 
    	
    	BeanFactory factory = new ClassPathXmlApplicationContext("app-context.xml");
    	DirLocalCfg dirLocalCfg = (DirLocalCfg) factory.getBean("dirLocalCfg");
    	ChequeaFichero chequeaFichero = (ChequeaFichero) factory.getBean("chequeaFichero");
    	
    	// Obtenemos el directorio local
    	dirLocal = dirLocalCfg.getDirLocal();
    	if(dirLocal==null){
    		System.out.println("Error: salimos");
    		System.exit(-1);
    	}
    	
    	// Leemos todos los ficheros del directorio
        String files;
        File folder = new File(dirLocal);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++)
        {
            if (listOfFiles[i].isFile())
            {
                files = listOfFiles[i].getName();
                if (files.endsWith(".sql") || files.endsWith(".SQL"))
                {
                	chequeaFichero.chequear(dirLocal + files);
                }
            }
        }
        System.out.println("---- Fin ----");
    	
    }

    
}
