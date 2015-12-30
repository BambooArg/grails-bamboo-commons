package ar.com.bamboo.commons.fiscalUtils

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class CondicionIvaSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test carga del mapa de nombres"() {

        when: "Pruebo que los nombres estén bien cargados"

        then: "Valido la existencia de los nombres en el mapa real"
        CondicionIva.nombreCondicionIva.size() == 5
        CondicionIva.nombreCondicionIva.get(CondicionIva.RESPONSABLE_INSCRIPTO) == 'Responsable Inscripto'
        CondicionIva.nombreCondicionIva.get(CondicionIva.CONSUMIDOR_FINAL) == 'Consumidor Final'
        CondicionIva.nombreCondicionIva.get(CondicionIva.EXENTO) == 'Exento'
        CondicionIva.nombreCondicionIva.get(CondicionIva.MONOTRIBUTISTA) == 'Monotributista'
        CondicionIva.nombreCondicionIva.get(CondicionIva.MONOTRIBUTO_SOCIAL) == 'Monotributista Social'
    }
}
