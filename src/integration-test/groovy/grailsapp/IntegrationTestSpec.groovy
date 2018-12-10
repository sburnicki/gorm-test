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

    void "list book names"() {
        given:
        new Book(name: "TestBook").save(flush: true)

        when:
        def names = testService.listBooks()

        then:
        names == ["TestBook"]
    }

    void "list book names (new transaction)"() {
        given:
        new Book(name: "TestBook").save(flush: true)

        when:
        def names = testService.listBooksNewTransaction()

        then:
        names == ["TestBook"]
    }

    void "list book names (new transaction, saved in new session)"() {
        given:
        Book.withNewSession { session ->
            new Book(name: "TestBook").save(flush: true)
        }

        when:
        def names = testService.listBooksNewTransaction()

        then:
        names == ["TestBook"]
    }
}
