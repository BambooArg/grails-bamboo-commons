package ar.com.bamboo.commons

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(CurrencyTagLib)
class CurrencyTagLibSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test formatPrice method"() {
        expect:
        tagLib.formatPrice(price: inputPrice) == outputPrice

        where:
        inputPrice || outputPrice
        0 || "\$0,00"
        1000 || "\$1.000,00"
        10.5 || "\$10,50"
        null || "---"
    }
}
