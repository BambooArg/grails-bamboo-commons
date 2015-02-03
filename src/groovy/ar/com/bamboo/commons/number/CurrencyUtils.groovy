package ar.com.bamboo.commons.number

import groovy.transform.CompileStatic

import java.text.DecimalFormat
import java.text.NumberFormat

/**
 * Created by orko on 28/01/15.
 */
@CompileStatic
class CurrencyUtils {

    private static final String CURRENCY_PATTERN = "\$###,##0.00"
    private static final Locale LOCALE_AR = new Locale("es", "AR")

    public static <T extends Number>  String formatArgentinianPrice(T price){
        NumberFormat nf = NumberFormat.getNumberInstance(LOCALE_AR);
        DecimalFormat df = (DecimalFormat)nf;
        df.applyPattern(CURRENCY_PATTERN);
        return df.format(price);
    }
}
