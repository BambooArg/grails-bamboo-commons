package ar.com.bamboo.commons

import grails.test.mixin.TestFor
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(StringSupportTagLib)
class StringSupportTagLibSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test ellipsis success"() {

        expect:
        applyTemplate('<stringSupport:ellipsis maxLength="10">' + input + '</stringSupport:ellipsis>') == output

        where:
        input || output
        "hola"  || "hola"
        "holaholahola" || "holaholaho..."
        "holaholah ola" || "holaholah ..."

    }

    void "test ellipsis fail GrailsTagException"() {

        when: "No se le pasa el argumento max lenght al taglib"
        applyTemplate('<stringSupport:ellipsis>asdasdad</stringSupport:ellipsis>')

        then: "Se arroja la exception de GRailsTagException ya que el parámetro es requerido"
        thrown(GrailsTagException.class)

        when: "Se pasa un string como maxLength"
        applyTemplate('<stringSupport:ellipsis maxLength="gdfgf">asdasdad</stringSupport:ellipsis>')

        then: "Se arroja GrailsTagException"
        thrown(GrailsTagException.class)
    }

}
