package ar.com.bamboo.commons.datime

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import org.joda.time.LocalDate
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class DateTimeUtilsSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll("The date #dateCompare isGreaterThanToday? #result")
    void "test isGreaterThanToday"() {
        expect:
        DateTimeUtils.isGreaterThanToday(dateCompare) == result

        where:
        dateCompare || result
        new LocalDate(2014, 12, 31).toDate() || false
        new LocalDate(2015, 12, 31).toDate() || true
        LocalDate.now().toDate() || false
    }

    @Unroll("The date #dateCompare isGreaterOrEqualsThanToday? #result")
    void "test isGreaterOrEqualsThanToday"() {
        expect:
        DateTimeUtils.isGreaterOrEqualThanToday(dateCompare) == result

        where:
        dateCompare || result
        new LocalDate(2014, 12, 31).toDate() || false
        new LocalDate(2015, 12, 31).toDate() || true
        LocalDate.now().toDate() || true
    }
}
