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
        return hourToHumanFormat(sHour)
    }

    /**
     * Transforma la hora en formato HHMM en HH:MM.
     * El formato integer está dado como en forma militar, por ejemplo, las 13:30
     * son las 1330. El formato de salida es HH:MM
     * @param hour
     * @return
     */
    public static String hourToHumanFormat(String hour){
        int cerosFaltantes = 4 - hour.size()

        if (cerosFaltantes > 0){
            hour = ("0" * cerosFaltantes) + hour
        }else{
            if (cerosFaltantes < 0){
                throw new IllegalArgumentException("El tamaño de la hora es mayor al permitido. 4 dígitos")
            }
        }
        return hour.substring(0, 2) + ":" + hour.substring(2, 4)
    }

    public static HourMinute getHourAndMinute(Integer hour){
        if (hour < 0){
            throw new IllegalArgumentException("El parámetro hour debe ser mayor a 0")
        }
        String sHour = hour.toString()
        Integer hourIsolated = null
        Integer minuteIsolated = null
        if (sHour.size() > 4){
            throw new IllegalArgumentException("El parámetro hour no puede tener más de 5 dígitos")
        }
        if (sHour.size() == 4){
            hourIsolated = Integer.valueOf(sHour.substring(0, 2))
            minuteIsolated = Integer.valueOf(sHour.substring(2))
        }else{
            if (sHour.size() == 3){
                hourIsolated = Integer.valueOf(sHour.substring(0, 1))
                minuteIsolated = Integer.valueOf(sHour.substring(1))
            }else{
                hourIsolated = 0
                minuteIsolated = hour
            }
        }
        return new HourMinute(hour: hourIsolated, minute: minuteIsolated)
    }

    public static Integer convertHourToJodaTimeHour(Integer hour){
        return hour == 2400 ? 2359 : hour
    }

    public static Integer convertToMilitarHour(HourMinute hourMinute){
        int hour = hourMinute.hour * 100
        int minute = hourMinute.minute
        hour + minute
    }
}
