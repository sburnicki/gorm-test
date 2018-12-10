package grailsapp

import grails.gorm.transactions.Transactional
import org.springframework.transaction.annotation.Propagation

@Transactional
class TestService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    def getBooksNewTransaction() {
        return Book.list().collect{ it.name }
    }

    def getBooks() {
        return Book.list().collect{ it.name }
    }
}
