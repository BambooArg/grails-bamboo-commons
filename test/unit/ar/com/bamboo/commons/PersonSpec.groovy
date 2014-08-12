package ar.com.bamboo.commons

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Person)
class PersonSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test contraint"() {
        given:
        mockForConstraintsTests(Person.class)

        when: "Guardo una persona sin ningún atributo"
        Person person = new Person()
        then: "La validación es válida"
        person.validate()
        !person.hasErrors()

        when: "Cuando guardo un email inválido"
        person = new Person(email: "josesito")
        then: "La validación falla por el email"
        !person.validate()
        person.hasErrors()
        person.errors['email']

        when: "Cuando guardo un email válido"
        person = new Person(email: "bamboo@commons.com")
        then: "La validación es exitosa y luego la guardo"
        person.validate()
        !person.hasErrors()
        person.save()
    }
}
