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
        List<OptionCombo> optionComboList = OptionComboUtils.comboMonthSpanish
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

    void "test getComboYear with parameter From and countyear"() {
        given:
        int yearFrom = 2012
        int countYear = 6

        when: "Llamo al método que pide los años con la cantidad de años que quiero = N"
        List<OptionCombo> optionComboList = OptionComboUtils.getComboYear(yearFrom, countYear)

        then: "Se genera un combo con el año actual + (N - 1) años anteriores"
        optionComboList
        optionComboList.size() == countYear
        optionComboList[0].value == String.valueOf(yearFrom)
        optionComboList[0].label == String.valueOf(yearFrom)
        optionComboList[countYear - 1].value == String.valueOf(yearFrom - countYear + 1)
        optionComboList[countYear - 1].label == String.valueOf(yearFrom - countYear + 1)
    }

    void "test getComboYearLastN"() {
        given:
        int countYear = 4
        int lowCountYear = 0
        int hightCountYear = 13
        DateTime time = new DateTime();
        int currentYear = time.getYear()

        when: "llamo al método pidiendo los últimos N años dentro de las especificaciones, sin alterar el default"
        List<OptionCombo> optionComboList = OptionComboUtils.getComboYearLastN(countYear)

        then: "Se genera el combo de los últimos N años, desde la fecha actual"
        optionComboList
        optionComboList.size() == countYear
        optionComboList[0].value == String.valueOf(currentYear)
        optionComboList[0].label == String.valueOf(currentYear)
        optionComboList[countYear - 1].value == String.valueOf(currentYear - countYear + 1)
        optionComboList[countYear - 1].label == String.valueOf(currentYear - countYear + 1)

        when: "Llamo al método con el parámetro lowCountYear = 0, por lo tanto tiene que dejar un mínimo default de 3"
        optionComboList = OptionComboUtils.getComboYearLastN(lowCountYear)

        then: "Se genera el combo de los últimos 3 años"
        optionComboList
        optionComboList.size() == 3

        when: "Llamo al método con el parámetro hightCountYear = 13, por lo tanto tiene que dejar un máximo default de 12"
        optionComboList = OptionComboUtils.getComboYearLastN(hightCountYear)

        then: "Se genera el combo de los últimos 12 años"
        optionComboList
        optionComboList.size() == 12
    }
}
