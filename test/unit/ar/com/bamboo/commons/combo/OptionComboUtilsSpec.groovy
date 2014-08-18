package ar.com.bamboo.commons.combo

import ar.com.bamboo.commons.fiscalUtils.CondicionIva
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
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
}
