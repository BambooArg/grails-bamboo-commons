package ar.com.bamboo.commons.combo

import ar.com.bamboo.commons.fiscalUtils.CondicionIva
import groovy.transform.CompileStatic
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.Months
import org.joda.time.YearMonth

/**
 * Created by orko on 18/08/14.
 */
@CompileStatic
class OptionComboUtils {

    public static List<OptionCombo> getComboCondicionIva(){
        List<OptionCombo> optionComboList = new ArrayList<OptionCombo>(CondicionIva.nombreCondicionIva.size())
        for (key in CondicionIva.nombreCondicionIva.keySet()){
            optionComboList.add(new OptionCombo(value: key.toString(), label: CondicionIva.nombreCondicionIva[key]))
        }
        return optionComboList
    }

    public static List<OptionCombo> getComboMonthSpanish(){
        List<OptionCombo> optionComboList = new ArrayList<OptionCombo>(12)
        Locale locate = new Locale("es")
        YearMonth md = null;
        for (int i = 1; i < 13; i++) {
            md = new YearMonth(1992, i);
            optionComboList.add(new OptionCombo(value: String.valueOf(i), label: md.monthOfYear().getAsText(locate)
                    .capitalize()))
        }
        return optionComboList
    }

    public static List<OptionCombo> getComboYear(){
        DateTime time = new DateTime();
        int currentYear = time.getYear()
       return getComboYear(currentYear)
    }

    /**
     * Devuelve una lista de OptionCombo de los últimos n (lastCountYear) años.
     * Se inicia desde el año actual.
     * Si lastCountYear es <= 0, setea un default de 3
     * Si lastCountYear es >= 12, setea un límite de 12
     * @param lastCountYear
     * @return
     */
    public static List<OptionCombo> getComboYearLastN(int lastCountYear){
        if (lastCountYear <= 0){
            lastCountYear = 3
        }else{
            if (lastCountYear > 12){
                lastCountYear = 12
            }
        }
        DateTime time = new DateTime();
        int currentYear = time.getYear()
        return getComboYear(currentYear, lastCountYear)
    }

    public static List<OptionCombo> getComboYear(int yearFrom){
        List<OptionCombo> optionComboList = new ArrayList<OptionCombo>(3)
        for (int i = yearFrom; i >= (yearFrom - 2); i--) {
            optionComboList.add(new OptionCombo(value: String.valueOf(i), label: String.valueOf(i)))
        }
        return optionComboList
    }

    public static List<OptionCombo> getComboYear(int yearFrom, int countYear){
        List<OptionCombo> optionComboList = new ArrayList<OptionCombo>(countYear)
        int yearUntil = yearFrom - countYear
        for (int i = yearFrom; i > yearUntil; i--) {
            optionComboList.add(new OptionCombo(value: String.valueOf(i), label: String.valueOf(i)))
        }
        return optionComboList
    }
}
