package cl.subtel.business.util;

import java.lang.Character;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class UtilidadesArchivos {

	private Logger log = Logger.getLogger(getClass().getName());
	
	
	public Integer longitudArray(String[] array) {
		
		char a = 29; String b = String.valueOf(a);
		int n = array.length-1;
		
		while(array[n].equals("") || array[n].equals(b)) {
			n--;
		}
		
		return n;
	}
	
	public Integer getVersion(String nombre) {
		
		try {
			
			String version = "";
			String sub = "";
			
			sub = nombre.substring(nombre.indexOf(".") + 1, nombre.length());
			
			if (sub.indexOf(".") >= 0) {
				char[] sub1 = sub.substring(0, sub.indexOf(".")).toCharArray();
				
				for(int i=0;i<sub1.length;i++) {
					
					if((Character.isDigit(sub1[i])) ) version += sub1[i];

				}
				
			} else version = "1";
			
			return Integer.parseInt(version) ;
			
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex.getCause());
			return null;
		}
	}
	
	public BigDecimal getValorBigDecimal(String cadena) {
		
		if (cadena.equals("")) return new BigDecimal(0);
		
		BigDecimal valor = new BigDecimal(0);
		String valorTx = new String();
		char a = 29; String b = String.valueOf(a);
		
		valorTx = cadena.replace("$", "");
		valorTx = valorTx.replace("\"", "");
		valorTx = valorTx.replace("\"$ \"", "");
		valorTx = valorTx.replace(" ", "");
		valorTx = valorTx.replace("%", "");
		valorTx = valorTx.replace(".", "");
		valorTx = valorTx.replace(",", ".");
		valorTx = valorTx.replace(b, "");
	
		try {
			valor = BigDecimal.valueOf(Double.valueOf(valorTx));
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex.getCause());
			return null;
		}
		return valor;
	}
	
	public Double getRoundDecimal(Double number) {
		
		Double numero = (double) Math.round(number*100);
		
		return (numero/100);
		
	}
	
	public Integer getValorInteger(String cadena) {
		char a = 29; String b = String.valueOf(a);
		Integer valor = 0;
		
		try {
			if (cadena.equals("")) return 0;
			
			valor = Integer.parseInt(cadena.replace(b, ""));
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex.getCause());
			return null;
		}
		return valor;
	}
	
	public Date[] getRangoHoras(String cadena1, String cadena2) {
		
		Date[] horas = new Date[2];
		
		SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
		
		try {
			
			if (!cadena1.isEmpty() && !cadena2.isEmpty()) {
				horas[0] = (Date) formato.parse("08:00:00");
				horas[1] = (Date) formato.parse("18:30:00");
			} else if (!cadena1.isEmpty() && cadena2.isEmpty()) {
				horas[0] = (Date) formato.parse("08:00:00");
				horas[1] = (Date) formato.parse("14:00:00");
			} else if (cadena1.isEmpty() && !cadena2.isEmpty()) {
				horas[0] = (Date) formato.parse("14:01:00");
				horas[1] = (Date) formato.parse("18:30:00");
			}
			
			return horas;
			
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex.getCause());
			return null;
		}
		
	}
	
	public Date getValorDate(String cadena) {
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = new Date();
		String separador = "";
		Integer valor = 0;
		Integer dia, mes, ano = 0;
		
		if (cadena.indexOf("/")>0) separador = "/";
		else if (cadena.indexOf("-")>0) separador = "-";
		else return null;
		
		String[] array = cadena.split(separador);
		
		if (array.length!=3) return null;
		
		try {
			
			valor = Integer.parseInt(array[0]);
			
			if (array[0].length()>2) {
				ano = valor;
				mes = Integer.parseInt(array[1]);
				dia = Integer.parseInt(array[2]);
			} else {
				dia = valor;
				mes = Integer.parseInt(array[1]);
				if (array[2].length() > 2)
					ano = Integer.parseInt(array[2]);
				else
					ano = Integer.parseInt("20" + array[2]);
			}
		
			fecha = formato.parse(ano + "-" + mes + "-" + dia);
			
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex.getCause());
			return null;
		}
		
		return fecha;
	}
	
	public String getMes(String mes) {
		
		switch (mes.toUpperCase()) {
		case "ENERO": return "00";
		case "FEBRERO": return "01";
		case "MARZO": return "02";
		case "ABRIL": return "03";
		case "MAYO": return "04";
		case "JUNIO": return "05";
		case "JULIO": return "06";
		case "AGOSTO": return "07";
		case "SEPTIEMBRE": return "08";
		case "OCTUBRE": return "09";
		case "NOVIEMBRE": return "10";
		case "DICIEMBRE": return "11";
		default : return "";
		}
	}
	
}
