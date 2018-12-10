package grailsapp

import grails.testing.mixin.integration.Integration
import grails.transaction.*
import spock.lang.Specification

@Rollback
@Integration(applicationClass = Application.class)
class IntegrationTestSpec extends Specification {
    TestService testService

    def setup() {
    }

    def cleanup() {
    }

    void "get book names"() {
        given:
        new Book(name: "TestBook").save(flush: true)

        when:
        def names = testService.getBooks()

        then:
        names == ["TestBook"]
    }

    void "get book names (new transaction)"() {
        given:
        new Book(name: "TestBook").save(flush: true)

        when:
        def names = testService.getBooksNewTransaction()

        then:
        names == ["TestBook"]
    }

    void "get book names (new transaction, saved in new session)"() {
        given:
        Book.withNewSession { session ->
            new Book(name: "TestBook").save(flush: true)
            session.flush()
            session.clear()
        }

        when:
        def names = testService.getBooksNewTransaction()

        then:
        names == ["TestBook"]
    }
}
