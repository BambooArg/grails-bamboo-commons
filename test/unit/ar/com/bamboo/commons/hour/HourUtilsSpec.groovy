package ar.com.bamboo.commons.hour

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class HourUtilsSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll("the int hour '#hour' is transform en '#shour'")
    void "test intHourToString"() {
        expect:
        HourUtils.intHourToString(hour) == shour

        where:
        hour || shour
        0 | "00:00"
        2400 | "24:00"
        130 | "01:30"
        10 | "00:10"
        921 | "09:21"
        1721 | "17:21"
    }

    void "test intHourToString method with thrown exception"() {

        when: "Se le pasa al método un número de más de 4 dígitos"
        HourUtils.intHourToString(12122)
        then: "Se arroja la exceptión IllegalArgumentExcepcion"
        thrown(IllegalArgumentException)
    }
}
