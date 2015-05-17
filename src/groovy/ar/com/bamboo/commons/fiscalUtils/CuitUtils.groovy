package ar.com.bamboo.commons.fiscalUtils

import groovy.transform.CompileStatic
import org.apache.commons.lang.StringUtils

/**
 * Created by orko on 13/08/14.
 */
@CompileStatic
class CuitUtils {

    public static boolean validar(String cuit){
        if(!cuit || cuit.size() != 11){
            return false
        }
        if (!StringUtils.isNumeric(cuit)){
            return false
        }
        long lCuit = Long.valueOf(cuit)
        long sexo = (long) (lCuit / (long) Math.pow(10, 9))
        long dni = (long) ( (long)(lCuit / 10) - (long)(sexo * (long) Math.pow(10, 8)))
        long verificador = lCuit % 10

        String serie = "2345672345"
        String numero = String.valueOf(sexo) + String.valueOf(dni)
        long suma = 0
        for (int i = 0; i < 10; i++) {
            suma += (Long.valueOf(numero[i])) * (Long.valueOf(serie[9 - i]))
        }
        Long digito = 11 - suma % 11
        digito = digito.equals(11) ? 0 : digito
        digito = digito.equals(10) ? 9 : digito

        return verificador == digito
    }

}
