package ar.com.bamboo.commons

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(HourTagLib)
class HourTagLibSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test humanFormat method"() {
        expect:
        tagLib.humanFormat(hour: "1300") == "13:00"
    }

    void "test humanFormat method when not argument hour is passed"() {
        when: "No se le pasa el argument hour"
        tagLib.humanFormat()
        then: "IllegalArgumentException es arrojada"
        thrown(IllegalArgumentException)
    }
}
