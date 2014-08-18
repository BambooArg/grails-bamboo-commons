package ar.com.bamboo.commons.combo

import ar.com.bamboo.commons.fiscalUtils.CondicionIva
import groovy.transform.CompileStatic

import java.util.concurrent.ConcurrentHashMap

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
}
