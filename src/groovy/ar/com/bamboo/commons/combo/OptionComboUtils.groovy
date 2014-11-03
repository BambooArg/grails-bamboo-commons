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

    public static List<OptionCombo> getComboMonthEspaniol(){
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

    public static List<OptionCombo> getComboYear(int yearFrom){
        List<OptionCombo> optionComboList = new ArrayList<OptionCombo>(3)
        for (int i = yearFrom; i >= (yearFrom - 2); i--) {
            optionComboList.add(new OptionCombo(value: String.valueOf(i), label: String.valueOf(i)))
        }
        return optionComboList
    }
}
