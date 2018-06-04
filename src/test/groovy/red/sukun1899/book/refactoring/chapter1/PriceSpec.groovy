package red.sukun1899.book.refactoring.chapter1;

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author su-kun1899
 */
class PriceSpec extends Specification {
    Price price

    @Unroll
    def '通常価格で #daysRented 日レンタルの場合、[料金: #charge][ポイント: #point]になる'() {
        when:
        price = new RegularPrice()

        then:
        price.getCharge(daysRented) == charge
        price.getFrequentRenterPoints(daysRented) == point

        where:
        daysRented || charge      | point
        1          || 2           | 1
        2          || 2           | 1
        3          || 2 + 1.5 * 1 | 1
        4          || 2 + 1.5 * 2 | 1
        5          || 2 + 1.5 * 3 | 1
    }

    @Unroll
    def '子供向け価格で #daysRented 日レンタルの場合、[料金: #charge][ポイント: #point]になる'() {
        when:
        price = new ChildrenPrice()

        then:
        price.getCharge(daysRented) == charge as double
        price.getFrequentRenterPoints(daysRented) == point

        where:
        daysRented || charge        | point
        1          || 1.5           | 1
        2          || 1.5           | 1
        3          || 1.5           | 1
        4          || 1.5 + 1.5 * 1 | 1
        5          || 1.5 + 1.5 * 2 | 1
    }

    @Unroll
    def '新作価格で #daysRented 日レンタルの場合、[料金: #charge][ポイント: #point]になる'() {
        when:
        price = new NewReleasePrice()

        then:
        price.getCharge(daysRented) == charge as double
        price.getFrequentRenterPoints(daysRented) == point

        where:
        daysRented || charge | point
        1          || 3      | 1
        2          || 3 * 2  | 2
        3          || 3 * 3  | 2
        4          || 3 * 4  | 2
        5          || 3 * 5  | 2
    }
}
