package red.sukun1899.book.refactoring.chapter1

import spock.lang.Specification

/**
 * @author su-kun1899
 */
class CustomerSpec extends Specification {

    def "旧作、新作、子供向けを1本ずつ3日間レンタルする"() {
        given:
        def customer = new Customer("Taro")

        and:
        def movies = [
                new Movie("ジュラシックパーク", Movie.REGULAR),
                new Movie("スター・ウォーズ", Movie.NEW_RELEASE),
                new Movie("ドラえもん", Movie.CHILDRENS),
        ]

        and:
        movies.each { customer.addRental(new Rental(it, 3)) }

        when:
        def actual = customer.statement()

        then:
        actual == "Rental Record for Taro\n" +
                "\tジュラシックパーク\t3.5\n" +
                "\tスター・ウォーズ\t9.0\n" +
                "\tドラえもん\t1.5\n" +
                "Amount owed is 14.0\n" +
                "You earned 4 frequent renter points"
    }
}
