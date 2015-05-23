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
        DateTimeUtils.currentUserTime.minusDays(10) || false
        DateTimeUtils.currentUserTime.plusDays(10) || true
        DateTimeUtils.currentUserTime || false
    }

    @Unroll("The date #dateCompare isGreaterOrEqualsThanToday? #result")
    void "test isGreaterOrEqualsThanToday"() {
        expect:
        DateTimeUtils.isGreaterOrEqualThanToday(dateCompare) == result

        where:
        dateCompare || result
        DateTimeUtils.currentUserTime.minusDays(10) || false
        DateTimeUtils.currentUserTime.plusDays(10) || true
        DateTimeUtils.currentUserTime || true
    }

    @Unroll("The date #dateCompare isGreaterNow? #result")
    void "test isGreaterNow"() {
        expect:
        DateTimeUtils.isGreaterNow(dateCompare) == result

        where:
        dateCompare || result
        DateTime.now().minusDays(10) || false
        DateTime.now().plusDays(10) || true
        DateTime.now() || false
        DateTime.now().plusSeconds(200) || true
    }

    @Unroll("The date #dateEvaluate isInPeriodWithoutHour(#fromDate, #toDate)? #result")
    void "test isInPeriod"() {
        expect:
        DateTimeUtils.isInPeriodWithoutHour(dateEvaluate, fromDate, toDate) == result

        where:
        dateEvaluate | fromDate| toDate || result
        //DATE
        new LocalDate(2014, 12, 15).toDate() | new LocalDate(2014, 12, 01).toDate() | new LocalDate(2014, 12, 31).toDate() || true
        new LocalDate(2014, 12, 01).toDate() | new LocalDate(2014, 12, 01).toDate() | new LocalDate(2014, 12, 31).toDate() || true
        new LocalDate(2014, 12, 31).toDate() | new LocalDate(2014, 12, 01).toDate() | new LocalDate(2014, 12, 31).toDate() || true
        new LocalDate(2014, 12, 14).toDate() | new LocalDate(2014, 12, 15).toDate() | new LocalDate(2014, 12, 31).toDate() || false
        new LocalDate(2014, 12, 29).toDate() | new LocalDate(2014, 12, 15).toDate() | new LocalDate(2014, 12, 28).toDate() || false
        new LocalDate(2014, 12, 29).toDate() | new LocalDate(2014, 12, 15).toDate() | new LocalDate(2014, 12, 28).toDate() || false
        //LOCALDATE
        new LocalDate(2014, 12, 15) | new LocalDate(2014, 12, 01) | new LocalDate(2014, 12, 31) || true
        new LocalDate(2014, 12, 01) | new LocalDate(2014, 12, 01) | new LocalDate(2014, 12, 31) || true
        new LocalDate(2014, 12, 31) | new LocalDate(2014, 12, 01) | new LocalDate(2014, 12, 31) || true
        new LocalDate(2014, 12, 14) | new LocalDate(2014, 12, 15) | new LocalDate(2014, 12, 31) || false
        new LocalDate(2014, 12, 29) | new LocalDate(2014, 12, 15) | new LocalDate(2014, 12, 28) || false
        new LocalDate(2014, 12, 29) | new LocalDate(2014, 12, 15) | new LocalDate(2014, 12, 28) || false
        //DATETIME
        new DateTime(2014, 12, 15, 12, 00) | new DateTime(2014, 12, 01, 12, 00) | new DateTime(2014, 12, 31, 12, 00) || true
        new DateTime(2014, 12, 01, 12, 00) | new DateTime(2014, 12, 01, 12, 00) | new DateTime(2014, 12, 31, 12, 00) || true
        new DateTime(2014, 12, 31, 12, 00) | new DateTime(2014, 12, 01, 12, 00) | new DateTime(2014, 12, 31, 12, 00) || true
        new DateTime(2014, 12, 14, 12, 00) | new DateTime(2014, 12, 15, 12, 00) | new DateTime(2014, 12, 31, 12, 00) || false
        new DateTime(2014, 12, 29, 12, 00) | new DateTime(2014, 12, 15, 12, 00) | new DateTime(2014, 12, 28, 12, 00) || false
        new DateTime(2014, 12, 29, 12, 00) | new DateTime(2014, 12, 15, 12, 00) | new DateTime(2014, 12, 28, 12, 00) || false

    }

    @Unroll("Evaluate if todayIsInPeriodWithoutHour(#fromDate, #toDate)? #result")
    void "test todayIsInPeriodWithoutHour"() {
        expect:

        DateTimeUtils.todayIsInPeriodWithoutHour(fromDate, toDate) == result

        where:
        fromDate| toDate || result
        currentLocalDate() | currentLocalDate().plusDays(10) || true
        currentLocalDate().minusDays(10) | currentLocalDate() || true
        currentLocalDate().minusDays(10) | currentLocalDate().plusDays(10) || true
        currentLocalDate().plusDays(1) | currentLocalDate().plusDays(10) || false
        currentLocalDate().minusDays(10) | currentLocalDate().minusDays(1) || false
        currentLocalDate() | currentLocalDate().plusDays(1) || true
        currentLocalDate().minusDays(1) | currentLocalDate() || true
    }

    private LocalDate currentLocalDate(){
        DateTime currentUserTime = DateTimeUtils.currentUserTime
        new LocalDate(currentUserTime.year, currentUserTime.monthOfYear, currentUserTime.dayOfMonth)
    }
}
