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
@CompileStatic
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

    public static boolean isGreaterNow(Date dateWithTimeToCompare){
        Date dateToCompareMachineTimeZone = convertDateUserToDateMachine(dateWithTimeToCompare)
        Date today = convertDateUserToDateMachine(DateTime.now().toDate())
        return dateToCompareMachineTimeZone.after(today)
    }

    public static boolean isGreaterThanToday(Date dateToCompare){
        Date dateToCompareMachineTimeZone = convertDateUserToDateMachine(dateToCompare)
        Date today = convertDateUserToDateMachine(LocalDate.now().toDate())
        return dateToCompareMachineTimeZone.after(today)
    }

    public static boolean isGreaterOrEqualThanToday(Date dateToCompare){
        Date dateToCompareMachineTimeZone = convertDateUserToDateMachine(dateToCompare)
        Date today = LocalDate.now().toDate()
        return dateToCompareMachineTimeZone.compareTo(today) >= 0
    }

    public static boolean isLowerThanToday(Date dateToCompare){
        Date dateToCompareMachineTimeZone = convertDateUserToDateMachine(dateToCompare)
        Date today = LocalDate.now().toDate()
        return dateToCompareMachineTimeZone.before(today)
    }

    public static boolean isLowerOrEqualThanToday(Date dateToCompare){
        Date dateToCompareMachineTimeZone = convertDateUserToDateMachine(dateToCompare)
        Date today = LocalDate.now().toDate()
        return dateToCompareMachineTimeZone.compareTo(today) <= 0
    }

    public static boolean isInPeriodWithoutHour(Date dateEvaluate, Date fromDate, Date toDate){
        boolean isGreaterOrEquals = dateEvaluate.compareTo(fromDate) >= 0
        boolean isLowerOrEquals = dateEvaluate.compareTo(toDate) <= 0
        return isGreaterOrEquals && isLowerOrEquals
    }

    /**
     * Controla si la fecha hoy está comprendida en el período fromDate to toDate
     * La fecha de hoy está configurado en el timezone del service
     * @param fromDate
     * @param toDate
     * @return
     */
    public static boolean todayIsInPeriodWithoutHour(LocalDate fromDate, LocalDate toDate){
        Date today = LocalDate.now().toDate()
        return isInPeriodWithoutHour(today, fromDate.toDate(), toDate.toDate())
    }
}