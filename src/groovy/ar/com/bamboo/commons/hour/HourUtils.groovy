package ar.com.bamboo.commons.hour

/**
 * Created by orko on 07/10/14.
 */
class HourUtils {

    /**
     * Transforma la hora en formato integer en String.
     * El formato integer está dado como en forma militar, por ejemplo, las 13:30
     * son las 1330. El formato de salida es HH:MM
     * @param hour
     * @return
     */
    public static String intHourToString(Integer hour){
        String sHour = hour.toString()
        int cerosFaltantes = 4 - sHour.size()

        if (cerosFaltantes > 0){
            sHour = ("0" * cerosFaltantes) + sHour
        }else{
            if (cerosFaltantes < 0){
                throw new IllegalArgumentException("El tamaño de la hora es mayor al permitido. 4 dígitos")
            }
        }
        return sHour.substring(0, 2) + ":" + sHour.substring(2, 4)
    }
}
