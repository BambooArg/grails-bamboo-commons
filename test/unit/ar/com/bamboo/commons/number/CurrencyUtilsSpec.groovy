package ar.com.bamboo.commons.number

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class CurrencyUtilsSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll("The price without format '#priceWithoutFormat' is transform in '#priceWithFormat'")
    void "test formatArgentinianPrice"() {
        expect:
        CurrencyUtils.formatArgentinianPrice(priceWithoutFormat) == priceWithFormat

        where:
        priceWithoutFormat || priceWithFormat
        0 || "\$0,00"
        0.5 || "\$0,50"
        10 || "\$10,00"
        100 || "\$100,00"
        1000 || "\$1.000,00"
        10.4 || "\$10,40"
        100.4 || "\$100,40"
        1000.4 || "\$1.000,40"
        10.49 || "\$10,49"
        100.49 || "\$100,49"
        1000.49 || "\$1.000,49"
        10.499 || "\$10,50"
        100.499 || "\$100,50"
        1000.499 || "\$1.000,50"
    }
}
