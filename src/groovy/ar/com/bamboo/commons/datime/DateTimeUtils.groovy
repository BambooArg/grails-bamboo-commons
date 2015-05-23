package ar.com.bamboo.commons.datime

import groovy.transform.CompileStatic
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

/**
 * Created by orko on 28/01/15.
 */
class DateTimeUtils {

    public static final DateTimeFormatter dateTimeFormatterArgentino = DateTimeFormat.forPattern("dd-MM-yyy")
    public static final DateTimeFormatter dateTimeFormatterArgentinoWithhour = DateTimeFormat.forPattern("dd-MM-yyy HH:mm")
    public static final DateTimeFormatter dateTimeFormatterWithhourISO8601 = DateTimeFormat.forPattern("yyy-MM-dd HH:mm")
    public static final DateTimeFormatter dateTimeFormatterISO8601 = DateTimeFormat.forPattern("yyy-MM-dd")

    /**
     * Por ahora sólo devuelve el timezone Buenos Aires. El día que crecemos metemos otro timezone y mejoramos esto
     * @return
     */
    public static DateTimeZone getCurrentTimeZone(){
        return DateTimeZone.forID("America/Buenos_Aires")
    }

    /**
     * Devuelve el current time del usuario con el time zone buenos aires
     * @return
     */
    public static DateTime getCurrentUserTime(){
        return DateTime.now(DateTimeUtils.currentTimeZone)
    }

    /**
     * Devuelve el día actual del usuario con el time zone buenos aires
     * @return
     */
    public static LocalDate getCurrentUserLocalDate(){
        return LocalDate.now(DateTimeUtils.currentTimeZone)
    }

    /**
     * Convierte el date tomado de la máquina al datetime del usuario con el time zone buenos aires
     * @param dateMachine
     * @return
     */
    public static DateTime convertDateMachineToDateUser(Date dateMachine){
        return new DateTime(dateMachine, DateTimeUtils.currentTimeZone)
    }

    /**
     * Convierte el date tomado de la máquina al datetime del usuario con el time zone buenos aires.
     * Devuelve el date sin horas
     * @param dateMachine
     * @return
     */
    public static Date convertDateMachineToDateUserWithoutHour(Date dateMachine){
        return new LocalDate(dateMachine, DateTimeUtils.currentTimeZone).toDate()
    }

    /**
     * Convierte la fecha ingresada por el usuario, en timezone de maquina (pero con time zone de usuario)
     * al time zone de la máquina con la diferencia de usuario
     * FALTA PROBARLO BIEN PORQUE NO TENIA CASOS EN DONDE EL USUARIO INGRESA UNA FECHA Y HAY QUE BUSCARLA
     * @param dateUser
     * @return
     */
    public static Date convertDateUserToDateMachine(Date dateUser){
        DateTimeZone currentZone = DateTimeZone.default
        long newMilis = currentZone.getMillisKeepLocal(DateTimeUtils.currentTimeZone, dateUser.time)
        return new Date(newMilis)
    }

    public static Date convertStringDDMMAAAToDate(String date){
        return dateTimeFormatterArgentino.parseDateTime(date).toDate();
    }

    public static String convertDateTimeToISO8601WithHour(DateTime time){
        return time.toString(dateTimeFormatterWithhourISO8601)
    }

    public static String convertDateTimeToISO8601(DateTime time){
        return time.toString(dateTimeFormatterISO8601)
    }

    public static Date converStringISO8601WithHourToDate(String date){
        return dateTimeFormatterWithhourISO8601.parseDateTime(date).toDate();
    }

    public static Date converStringISO8601ToDate(String date){
        return dateTimeFormatterISO8601.parseDateTime(date).toDate();
    }

    public static String convertDateTimeToArgentinaDate(DateTime time){
        return time.toString(dateTimeFormatterArgentino)
    }

    public static String convertDateTimeToArgentinaDate(LocalDate date){
        return date.toString(dateTimeFormatterArgentino)
    }

    public static String convertDateTimeToArgentinaDate(Date date){
        DateTime dateTime = new DateTime(date.getTime())
        return dateTime.toString(dateTimeFormatterArgentino)
    }

    /**
     * Compara si la fecha dateTimeToCompare es mayor a now.
     * Se normalizan las fechas en el timezone de Bs As
     * @param dateWithTimeToCompare
     * @return
     */
    public static boolean isGreaterNow(DateTime dateTimeToCompare){
        Date dateToCompare = dateTimeToCompare.withZone(currentTimeZone).toDate()
        Date today = currentUserTime.toDate()
        return dateToCompare.after(today)
    }

    /**
     * Compara si la fecha dateTimeToCompare es mayor a today.
     * Se normalizan las fechas en el timezone de Bs As
     * @param dateTimeToCompare
     * @return
     */
    public static boolean isGreaterThanToday(DateTime dateTimeToCompare){
        Date dateToCompare = dateTimeToCompare.withZone(currentTimeZone).toLocalDate().toDate()
        Date today = currentUserLocalDate.toDate()
        return dateToCompare.after(today)
    }

    /**
     * Compara si la fecha dateTimeToCompare es mayor o igual a today.
     * Se normalizan las fechas en el timezone de Bs As
     * @param dateTimeToCompare
     * @return
     */
    public static boolean isGreaterOrEqualThanToday(DateTime dateTimeToCompare){
        Date dateToCompare = dateTimeToCompare.withZone(currentTimeZone).toLocalDate().toDate()
        Date today = currentUserLocalDate.toDate()
        return dateToCompare.compareTo(today) >= 0
    }

    public static boolean isLowerThanToday(Date dateToCompare){
        Date today = currentUserLocalDate.toDate()
        return dateToCompare.before(today)
    }

    public static boolean isLowerOrEqualThanToday(Date dateToCompare){
        Date today = currentUserLocalDate.toDate()
        return dateToCompare.compareTo(today) <= 0
    }

    /**
     * Comprueba si la fecha dateEvaluate está comprendida en el período fromDate a toDate
     * @param dateEvaluate
     * @param fromDate
     * @param toDate
     * @return
     */
    public static boolean isInPeriodWithoutHour(dateEvaluate, fromDate, toDate){
        boolean isGreaterOrEquals = dateEvaluate.compareTo(fromDate) >= 0
        boolean isLowerOrEquals = dateEvaluate.compareTo(toDate) <= 0
        return isGreaterOrEquals && isLowerOrEquals
    }

    /**
     * Controla si la fecha hoy está comprendida en el período fromDate to toDate
     * Normaliza todas las fechas a time zone Bs As
     * @param fromDateTime
     * @param toDateTime
     * @return
     */
    public static boolean todayIsInPeriodWithoutHour(DateTime fromDateTime, DateTime toDateTime){
        LocalDate fromDate = fromDateTime.withZone(currentTimeZone).toLocalDate()
        LocalDate toDate = toDateTime.withZone(currentTimeZone).toLocalDate()
        LocalDate today = currentUserLocalDate
        return isInPeriodWithoutHour(today, fromDate, toDate)
    }
}