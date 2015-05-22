package ar.com.bamboo.commons.datime

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
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
        LocalDate.now().toDate() | LocalDate.now().plusDays(10).toDate() || true
        LocalDate.now().minusDays(10).toDate() | LocalDate.now().toDate() || true
        LocalDate.now().minusDays(10).toDate() | LocalDate.now().plusDays(10).toDate() || true
        LocalDate.now().plusDays(1).toDate() | LocalDate.now().plusDays(10).toDate()|| false
        LocalDate.now().minusDays(10).toDate() | LocalDate.now().minusDays(1).toDate() || false
        LocalDate.now().toDate() | LocalDate.now().plusDays(1).toDate() || true
        LocalDate.now().minusDays(1).toDate() | LocalDate.now().toDate() || true
        //TIME ZONE NEW YORK
        LocalDate.now(DateTimeZone.forID("America/New_York")).toDate() | LocalDate.now(DateTimeZone.forID("America/New_York")).plusDays(10).toDate() || true
        LocalDate.now(DateTimeZone.forID("America/New_York")).minusDays(10).toDate() | LocalDate.now(DateTimeZone.forID("America/New_York")).toDate() || true
        LocalDate.now(DateTimeZone.forID("America/New_York")).minusDays(10).toDate() | LocalDate.now(DateTimeZone.forID("America/New_York")).plusDays(10).toDate() || true
        LocalDate.now(DateTimeZone.forID("America/New_York")).plusDays(1).toDate() | LocalDate.now(DateTimeZone.forID("America/New_York")).plusDays(10).toDate()|| false
        LocalDate.now(DateTimeZone.forID("America/New_York")).minusDays(10).toDate() | LocalDate.now(DateTimeZone.forID("America/New_York")).minusDays(1).toDate() || false
        LocalDate.now(DateTimeZone.forID("America/New_York")).toDate() | LocalDate.now(DateTimeZone.forID("America/New_York")).plusDays(1).toDate() || true
        LocalDate.now(DateTimeZone.forID("America/New_York")).minusDays(1).toDate() | LocalDate.now(DateTimeZone.forID("America/New_York")).toDate() || true
        //TIME ZONE BS AS
        LocalDate.now(DateTimeZone.forID("America/Buenos_Aires")).toDate() | LocalDate.now(DateTimeZone.forID("America/Buenos_Aires")).plusDays(10).toDate() || true
        LocalDate.now(DateTimeZone.forID("America/Buenos_Aires")).minusDays(10).toDate() | LocalDate.now(DateTimeZone.forID("America/Buenos_Aires")).toDate() || true
        LocalDate.now(DateTimeZone.forID("America/Buenos_Aires")).minusDays(10).toDate() | LocalDate.now(DateTimeZone.forID("America/Buenos_Aires")).plusDays(10).toDate() || true
        LocalDate.now(DateTimeZone.forID("America/Buenos_Aires")).plusDays(1).toDate() | LocalDate.now(DateTimeZone.forID("America/Buenos_Aires")).plusDays(10).toDate()|| false
        LocalDate.now(DateTimeZone.forID("America/Buenos_Aires")).minusDays(10).toDate() | LocalDate.now(DateTimeZone.forID("America/Buenos_Aires")).minusDays(1).toDate() || false
        LocalDate.now(DateTimeZone.forID("America/Buenos_Aires")).toDate() | LocalDate.now(DateTimeZone.forID("America/Buenos_Aires")).plusDays(1).toDate() || true
        LocalDate.now(DateTimeZone.forID("America/Buenos_Aires")).minusDays(1).toDate() | LocalDate.now(DateTimeZone.forID("America/Buenos_Aires")).toDate() || true
        //TIME ZONE ROME
        LocalDate.now(DateTimeZone.forID("Europe/Rome")).toDate() | LocalDate.now(DateTimeZone.forID("Europe/Rome")).plusDays(10).toDate() || true
        LocalDate.now(DateTimeZone.forID("Europe/Rome")).minusDays(10).toDate() | LocalDate.now(DateTimeZone.forID("Europe/Rome")).toDate() || true
        LocalDate.now(DateTimeZone.forID("Europe/Rome")).minusDays(10).toDate() | LocalDate.now(DateTimeZone.forID("Europe/Rome")).plusDays(10).toDate() || true
        LocalDate.now(DateTimeZone.forID("Europe/Rome")).plusDays(1).toDate() | LocalDate.now(DateTimeZone.forID("Europe/Rome")).plusDays(10).toDate()|| false
        LocalDate.now(DateTimeZone.forID("Europe/Rome")).minusDays(10).toDate() | LocalDate.now(DateTimeZone.forID("Europe/Rome")).minusDays(1).toDate() || false
        LocalDate.now(DateTimeZone.forID("Europe/Rome")).toDate() | LocalDate.now(DateTimeZone.forID("Europe/Rome")).plusDays(1).toDate() || true
        LocalDate.now(DateTimeZone.forID("Europe/Rome")).minusDays(1).toDate() | LocalDate.now(DateTimeZone.forID("Europe/Rome")).toDate() || true

    }
}
