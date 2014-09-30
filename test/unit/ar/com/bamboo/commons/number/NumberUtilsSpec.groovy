package ar.com.bamboo.commons.number

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class NumberUtilsSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    void "test the isLine method: the line ('#x0','#x1') is a line? #isline"() {
        expect:
        NumberUtils.isLine(x0, x1) == isline

        where:
        x0 | x1 || isline
        0 | 1 || true
        -1 | 10 || true
        -10 | -2 || true
        0 | 0 || false
        6 | 3 || false
        -10 | -11 || false
    }

    void "test isLine method with thrown exception"() {

        when: "Los dos parametros son nulos"
        NumberUtils.isLine(null, null)
        then: "Se arroja la exception illegalArgument"
        thrown(IllegalArgumentException)

        when: "Los el primer parámetro es nulo"
        NumberUtils.isLine(null, 1)
        then: "Se arroja la exception illegalArgument"
        thrown(IllegalArgumentException)

        when: "Los el segundo parámetro es nulo"
        NumberUtils.isLine(1, null)
        then: "Se arroja la exception illegalArgument"
        thrown(IllegalArgumentException)
    }

    @Unroll
    void "test the intersectionLine method: the line ('#x0', '#x1') and the line ('#y0', '#y1') are intersected? #intersection"() {
        expect:
        NumberUtils.intersectionLine(x0, x1, y0, y1) == intersection

        where:
        x0 | x1 | y0 | y1 || intersection
        0 | 1 | 2 | 3 || false
        0 | 10 | -10 | -1 || false
        5 | 10 | 10 | 15 || true
        10 | 15 | 5 | 10 || true
        10 | 15 | 8 | 11 || true
        10 | 15 | 13 | 20 || true
        20 | 50 | 30 | 35 || true
        20 | 25 | 15 | 35 || true
    }

    void "test intersectionLine method with thrown exception"() {

        when: "Los 4 parametros son nulos"
        NumberUtils.intersectionLine(null, null, null, null)
        then: "Se arroja la exception illegalArgument"
        thrown(IllegalArgumentException)

        when: "El primer parametro null y el resto no"
        NumberUtils.intersectionLine(null, 1, 2, 3)
        then: "Se arroja la exception illegalArgument"
        thrown(IllegalArgumentException)

        when: "El segundo parametro null y el resto no"
        NumberUtils.intersectionLine(1, null, 2, 3)
        then: "Se arroja la exception illegalArgument"
        thrown(IllegalArgumentException)

        when: "El tercer parametro null y el resto no"
        NumberUtils.intersectionLine(1, 2, null, 3)
        then: "Se arroja la exception illegalArgument"
        thrown(IllegalArgumentException)

        when: "El cuarto parametro null y el resto no"
        NumberUtils.intersectionLine(1, 2, 3, null)
        then: "Se arroja la exception illegalArgument"
        thrown(IllegalArgumentException)

        when: "La recta formada por los puntos x0, x1 está mal formada"
        NumberUtils.intersectionLine(2, 1, 3, 4)
        then: "Se arroja la exception illegalArgument"
        thrown(IllegalArgumentException)

        when: "La recta formada por los puntos y0, y1 está mal formada"
        NumberUtils.intersectionLine(0, 1, 8, 4)
        then: "Se arroja la exception illegalArgument"
        thrown(IllegalArgumentException)
    }
}
