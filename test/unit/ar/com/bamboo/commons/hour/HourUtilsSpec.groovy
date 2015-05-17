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

    @Unroll("the string hour '#hour' is transform en '#shour'")
    void "test hourToHumanFormat"() {
        expect:
        HourUtils.hourToHumanFormat(hour) == shour

        where:
        hour || shour
        "0" | "00:00"
        "2400" | "24:00"
        "130" | "01:30"
        "10" | "00:10"
        "921" | "09:21"
        "1721" | "17:21"
    }

    void "test hourToHumanFormat method with thrown exception"() {
        when: "Se le pasa al método un número de más de 4 dígitos"
        HourUtils.hourToHumanFormat("12122")
        then: "Se arroja la exceptión IllegalArgumentExcepcion"
        thrown(IllegalArgumentException)
    }

    @Unroll("the int hour '#hour' is transform en '#hourMinute'")
    void "test getHourAndMinute"() {
        expect:
        HourUtils.getHourAndMinute(hour) == hourMinute

        where:
        hour || hourMinute
        100 | new HourUtils.HourMinute(hour: 1, minute: 0)
        2400 | new HourUtils.HourMinute(hour: 24, minute: 0)
        130 | new HourUtils.HourMinute(hour: 1, minute: 30)
        10 | new HourUtils.HourMinute(hour: 0, minute: 10)
        921 | new HourUtils.HourMinute(hour: 9, minute: 21)
    }

    void "test getHourAndMinute method with thrown exception"() {
        when: "Se le pasa al método un número de más de 4 dígitos"
        HourUtils.getHourAndMinute(12122)
        then: "Se arroja la exceptión IllegalArgumentExcepcion"
        thrown(IllegalArgumentException)

        when: "Se le pasa al método un número menor a 0"
        HourUtils.getHourAndMinute(-4)
        then: "Se arroja la exceptión IllegalArgumentExcepcion"
        thrown(IllegalArgumentException)
    }

    void "test convertHourToJodaTimeHour"() {
        expect:
        HourUtils.convertHourToJodaTimeHour(hour) == hourMinute

        where:
        hour || hourMinute
        2400 | 2359
        2334 | 2334
    }
}
