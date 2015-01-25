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

    public static class HourMinute{
        Integer hour
        Integer minute

        boolean equals(o) {
            if (this.is(o)) return true
            if (getClass() != o.class) return false

            HourMinute that = (HourMinute) o

            if (hour != that.hour) return false
            if (minute != that.minute) return false

            return true
        }

        int hashCode() {
            int result
            result = (hour != null ? hour.hashCode() : 0)
            result = 31 * result + (minute != null ? minute.hashCode() : 0)
            return result
        }
    }
}
