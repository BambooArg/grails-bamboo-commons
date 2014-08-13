package ar.com.bamboo.commons.fiscalUtils

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class CuitUtilsSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test validacion cuit"() {
        given:
        String cuit1Valido = "20111111112"
        String cuit2Valido = "20222222223"
        String cuit3Valido = "20305940128"
        String cuit1InvalidoLenght = "234234"
        String cuit2InvalidoVacio = ""
        String cuit3InvalidoNull = null
        String cuit4InvalidoMalFormado = "20555555553"
        String cuit4InvalidoNoNumero = "sdfsfd"

        when: "Cuando se valida los cuit váidos"
        then: "La validacion devuelve true"
        CuitUtils.validar(cuit1Valido)
        CuitUtils.validar(cuit2Valido)
        CuitUtils.validar(cuit3Valido)

        when: "Cuando se valida los cuit inválidos"
        then: "La validación devuelve false"
        !CuitUtils.validar(cuit1InvalidoLenght)
        !CuitUtils.validar(cuit2InvalidoVacio)
        !CuitUtils.validar(cuit3InvalidoNull)
        !CuitUtils.validar(cuit4InvalidoMalFormado)
        !CuitUtils.validar(cuit4InvalidoNoNumero)

    }
}
