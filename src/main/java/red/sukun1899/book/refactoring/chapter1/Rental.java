package red.sukun1899.book.refactoring.chapter1;

/**
 * 顧客がビデオを借りたことを表現するクラス
 *
 * @author su-kun1899
 */
public class Rental {
    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    double getCharge() {
        return getMovie().getCharge(getDaysRented());
    }

    int getFrequentRentalPoints() {
        if ((getMovie().getPriceCode() == Movie.NEW_RELEASE) && getDaysRented() > 1) {
            // 新作を２日以上借りた場合はボーナスポイント
            return 2;
        } else {
            return 1;
        }
    }
}
