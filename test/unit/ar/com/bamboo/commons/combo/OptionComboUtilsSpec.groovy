package ar.com.bamboo.commons.combo

import ar.com.bamboo.commons.fiscalUtils.CondicionIva
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import org.joda.time.DateTime
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class OptionComboUtilsSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test getComboCondicionIva"() {

        when: "Llamo al método que pide la condición iva para combo"
        List<OptionCombo> optionComboList = OptionComboUtils.comboCondicionIva
        then: "Se genera un combo con la misma cantida de datos que el mapa de condicioniva"
        optionComboList
        optionComboList.size() == CondicionIva.nombreCondicionIva.size()
        optionComboList[0].label == CondicionIva.nombreCondicionIva[optionComboList[0].value.toInteger()]
    }

    void "test getComboMonthEspaniol"() {

        when: "Llamo al método que pide los meses"
        List<OptionCombo> optionComboList = OptionComboUtils.comboMonthEspaniol
        then: "Se genera un combo con todos los meses en formato texto"
        optionComboList
        optionComboList.size() == 12
        optionComboList[0].value == String.valueOf(1)
        optionComboList[0].label == "Enero"
    }

    void "test getComboYear"() {

        when: "Llamo al método que pide los años"
        List<OptionCombo> optionComboList = OptionComboUtils.comboYear
        DateTime time = new DateTime();
        int currentYear = time.getYear()
        then: "Se genera un combo con el año actual + dos años anteriores"
        optionComboList
        optionComboList.size() == 3
        optionComboList[0].value == String.valueOf(currentYear)
        optionComboList[0].label == String.valueOf(currentYear)
    }

    void "test getComboYear with parameter From"() {
        given:
        int yearFrom = 2012

        when: "Llamo al método que pide los años"
        List<OptionCombo> optionComboList = OptionComboUtils.getComboYear(yearFrom)

        then: "Se genera un combo con el año actual + dos años anteriores"
        optionComboList
        optionComboList.size() == 3
        optionComboList[0].value == String.valueOf(yearFrom)
        optionComboList[0].label == String.valueOf(yearFrom)
    }
}
