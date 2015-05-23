package ar.com.bamboo.commons.datime

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.LocalDate
import org.joda.time.LocalTime
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

    @Unroll("The date #dateCompare isGreaterNow? #result")
    void "test isGreaterNow"() {
        expect:
        DateTimeUtils.isGreaterNow(dateCompare) == result

        where:
        dateCompare || result
        new DateTime(2014, 12, 31, 12, 59).toDate() || false
        new DateTime(2015, 12, 31, 12, 59).toDate() || true
        DateTime.now().toDate() || false
        DateTime.now().plusSeconds(200).toDate() || true
    }

    @Unroll("The date #dateEvaluate isInPeriodWithoutHour(#fromDate, #toDate)? #result")
    void "test isInPeriod"() {
        expect:
        DateTimeUtils.isInPeriodWithoutHour(dateEvaluate, fromDate, toDate) == result

        where:
        dateEvaluate | fromDate| toDate || result
        new LocalDate(2014, 12, 15).toDate() | new LocalDate(2014, 12, 01).toDate() | new LocalDate(2014, 12, 31).toDate() || true
        new LocalDate(2014, 12, 01).toDate() | new LocalDate(2014, 12, 01).toDate() | new LocalDate(2014, 12, 31).toDate() || true
        new LocalDate(2014, 12, 31).toDate() | new LocalDate(2014, 12, 01).toDate() | new LocalDate(2014, 12, 31).toDate() || true
        new LocalDate(2014, 12, 14).toDate() | new LocalDate(2014, 12, 15).toDate() | new LocalDate(2014, 12, 31).toDate() || false
        new LocalDate(2014, 12, 29).toDate() | new LocalDate(2014, 12, 15).toDate() | new LocalDate(2014, 12, 28).toDate() || false
        new LocalDate(2014, 12, 29).toDate() | new LocalDate(2014, 12, 15).toDate() | new LocalDate(2014, 12, 28).toDate() || false
    }

    @Unroll("Evaluate if todayIsInPeriodWithoutHour(#fromDate, #toDate)? #result")
    void "test todayIsInPeriodWithoutHour"() {
        expect:
        DateTimeUtils.todayIsInPeriodWithoutHour(fromDate, toDate) == result

        when:

        where:
        fromDate| toDate || result
        LocalDate.now() | LocalDate.now().plusDays(10) || true
        LocalDate.now().minusDays(10) | LocalDate.now() || true
        LocalDate.now().minusDays(10) | LocalDate.now().plusDays(10) || true
        LocalDate.now().plusDays(1) | LocalDate.now().plusDays(10)|| false
        LocalDate.now().minusDays(10) | LocalDate.now().minusDays(1) || false
        LocalDate.now() | LocalDate.now().plusDays(1) || true
        LocalDate.now().minusDays(1) | LocalDate.now() || true
    }
}
