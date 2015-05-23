package ar.com.bamboo.commons.fiscalUtils

import ar.com.bamboo.commons.datime.DateTimeUtils
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import org.joda.time.DateTime
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class CuitUtilsSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll("Evaluate if #cuit is valid #result")
    void "test validar"() {
        expect:
        CuitUtils.validar(cuit) == result

        where:
        cuit || result
        "20111111112" || true
        "20222222223" || true
        "20305940128" || true
        "234234" || false
        "" || false
        null || false
        "20555555553" || false
        "234234" || false
    }
}
