package red.sukun1899.book.refactoring.chapter1;

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author su-kun1899
 */
class PriceSpec extends Specification {
    Price price

    @Unroll
    def '通常価格で #daysRented 日レンタルの場合、料金は #expected になる'() {
        given:
        price = new RegularPrice()

        when:
        def actual = price.getCharge(daysRented)

        then:
        actual == expected

        where:
        daysRented || expected
        1          || 2
        2          || 2
        3          || 2 + 1.5 * 1
        4          || 2 + 1.5 * 2
        5          || 2 + 1.5 * 3
    }

    @Unroll
    def '通常価格で #daysRented 日レンタルの場合、ポイントは #expected になる'() {
        given:
        price = new RegularPrice()

        when:
        def actual = price.getFrequentRenterPoints(daysRented)

        then:
        actual == expected

        where:
        daysRented || expected
        1          || 1
        2          || 1
        3          || 1
    }

    @Unroll
    def '子供向け価格で #daysRented 日レンタルの場合、料金は #expected になる'() {
        given:
        price = new ChildrenPrice()

        when:
        def actual = price.getCharge(daysRented)

        then:
        actual == expected as double

        where:
        daysRented || expected
        1          || 1.5
        2          || 1.5
        3          || 1.5
        4          || 1.5 + 1.5 * 1
        5          || 1.5 + 1.5 * 2
    }

    @Unroll
    def '子供向け価格で #daysRented 日レンタルの場合、ポイントは #expected になる'() {
        given:
        price = new ChildrenPrice()

        when:
        def actual = price.getFrequentRenterPoints(daysRented)

        then:
        actual == expected

        where:
        daysRented || expected
        1          || 1
        2          || 1
        3          || 1
    }

    @Unroll
    def '新作価格で #daysRented 日レンタルの場合、料金は #expected になる'() {
        given:
        price = new NewReleasePrice()

        when:
        def actual = price.getCharge(daysRented)

        then:
        actual == expected

        where:
        daysRented || expected
        1          || 3
        2          || 3 * 2
        3          || 3 * 3
        4          || 3 * 4
        5          || 3 * 5
    }

    @Unroll
    def '新作価格で #daysRented 日レンタルの場合、ポイントは #expected になる'() {
        given:
        price = new NewReleasePrice()

        when:
        def actual = price.getFrequentRenterPoints(daysRented)

        then:
        actual == expected

        where:
        daysRented || expected
        1          || 1
        2          || 2
        3          || 2
    }
}
