package cl.subtel.business.service.procesos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cl.aba.validar.ValorFecha;
import cl.subtel.business.util.FormatFechaSingle;


@Component
public class ValidaSlaServiceImpl implements ValidaSlaService {


	
	@Autowired
	private FormatFechaSingle fechaSingle;
	
	

	protected String[] formatos() {
		String[] fecha = fechaSingle.getFormatoFecha().split(";");
		String[] hora = fechaSingle.getFormatoHora().split(";");

		int f = fecha.length;
		int h = hora.length;
		int t = f + h + f * h;
		int cont = 0;
		String[] ret = new String[t];
		for (int i = 0; i < f; i++) {
			for (int j = 0; j < h; j++) {
				ret[cont++] = fecha[i] + " " + hora[j];
			}
		}
		
		for (int i = 0; i < f; i++) {
			ret[cont++] = fecha[i];
		}
		
		for (int i = 0; i < h; i++) {
			ret[cont++] = hora[i];
		}
		
		return ret;
	}



	@Override
	public ValorFecha valorFecha(String valor) {
		// TODO Auto-generated method stub
		ValorFecha vlFecha = new ValorFecha(valor, formatos());
		return vlFecha;
	}

	

}
