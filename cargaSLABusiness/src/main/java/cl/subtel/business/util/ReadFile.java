package cl.subtel.business.util;

import org.apache.log4j.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import jxl.Sheet;
import jxl.Workbook;

import jxl.Cell;

import cl.subtel.business.util.UtilidadesArchivos;

public class ReadFile {

	private Logger log = Logger.getLogger(getClass().getName());

	public List<String[]> getFilePlain(String filename) {
		
		char a = 9; String b = String.valueOf(a);
		List<String[]> list = new ArrayList<String[]>();
		File file = new File(filename);
		
		log.info("Se procede a leer el archivo");
		
		if (file.exists()) {
			
			FileReader leerOrigen = null;
			
			try {
				
				leerOrigen = new FileReader(file);
				BufferedReader br = new BufferedReader(leerOrigen);
				String linea;
				Integer n =  1;
				UtilidadesArchivos utilidades = new UtilidadesArchivos();
				
				while ((br.ready())) {
					
					linea = br.readLine();
					String[] fila = linea.split(b);
					
					if (n == 1) n = utilidades.longitudArray(fila);
					
					list.add(Arrays.copyOfRange(fila, 0, n+1));
				}
				
				leerOrigen.close();
				
				log.info("Archivo leido exitosamente");
				return list;
				
			} catch (Exception e) {
				log.error("Error al leer el archivo, mensaje " + e.getMessage());
				return null;
			}
			
		} else return null;
		
	}
	
	
	public List<String[]> getFileExcel(String filename, Integer tipo) {
		
		File file = new File(filename);
		UtilidadesArchivos utilidades = new UtilidadesArchivos();
		char a = 9; String b = String.valueOf(a);
		
		try {
			
			Workbook workbook = Workbook.getWorkbook(file);
			int numeroHojas, punteroHojas = 0;
			numeroHojas = workbook.getNumberOfSheets();
			
			for (punteroHojas = 0; punteroHojas < numeroHojas; punteroHojas++) {
				
				List<String[]> list = new ArrayList<String[]>();
				Sheet hoja = workbook.getSheet(punteroHojas);
				Integer filas, columnas = 0;
				Boolean inicio = false;
				
				filas = hoja.getRows(); 
				columnas = hoja.getColumns();
				
				for (int i = 0; i < filas; i++) {
					
					String linea = "";
					
					for (int j = 0; j < columnas; j++) {
						
						Cell celda  = hoja.getCell(j, i);
						
						if (!celda.getContents().isEmpty() || inicio) {
							linea +=  celda.getContents() + b;
		
						}
					}
					
					String[] array = linea.split(b);
					Integer n = array.length;
						
					if ((tipo == 6)) {
						if (n==4) {
							String mes = utilidades.getMes(array[2]);
							if (mes!="") {
								array[2] = mes;
								list.add(array);
								list.add(new String[]{array[2], array[3]});
							}
						} else if (n>=12) {
							if (!inicio) 
								inicio = true;
							list.add(array);
						}
					}	
					
					if ((tipo == 7)) {
						if (!inicio && i==2) {
							inicio = true;
							list.add(array);
						}
						if (n>24 && ( array[n-1].toUpperCase().equals("A") || array[n-1].toUpperCase().equals("APROBADO")) )
							list.add(array);
					}
					
				}
				
				if (inicio) return list;
				
			}

		} catch (Exception e) {
			
			log.error("Error al leer el excel, archivo " + file.toString() + e.getMessage());
		}
		
		return null;
	}
}
