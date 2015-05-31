package ar.com.bamboo.commons.hour

import groovy.transform.CompileStatic
import groovy.transform.ToString

/**
 * Created by orko on 31/05/15.
 */
@CompileStatic
@ToString
class HourMinute {

    Integer hour
    Integer minute

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        HourMinute that = (HourMinute) o

        if (hour != that.hour) return false
        if (minute != that.minute) return false

        return true
    }

    int hashCode() {
        int result
        result = (hour != null ? hour.hashCode() : 0)
        result = 31 * result + (minute != null ? minute.hashCode() : 0)
        return result
    }
}
