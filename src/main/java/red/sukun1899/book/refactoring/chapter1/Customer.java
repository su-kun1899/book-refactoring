package red.sukun1899.book.refactoring.chapter1;

import java.util.ArrayList;
import java.util.List;

/**
 * 店で取り扱う顧客を表すクラス
 *
 * @author su-kun1899
 */
public class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    /**
     * 計算書作成
     *
     * @return 計算書
     */
    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";

        for (Rental each : rentals) {
            double thisAmount = 0;

            // 一行ごとに金額を計算
            thisAmount = amountFor(each);

            // レンタルポイントを加算
            frequentRenterPoints++;
            // 新作を２日以上借りた場合はボーナスポイント
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE)
                    && each.getDaysRented() > 1) {
                frequentRenterPoints++;
            }

            // この貸出に関する数値の表示
            result += "\t" + each.getMovie().getTitle() +
                    "\t" + String.valueOf(thisAmount) +
                    "\n";
            totalAmount += thisAmount;
        }
        // フッタ部分の追加
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) +
                " frequent renter points";
        return result;
    }

    private double amountFor(Rental rental) {
        return rental.getCharge();
    }
}
