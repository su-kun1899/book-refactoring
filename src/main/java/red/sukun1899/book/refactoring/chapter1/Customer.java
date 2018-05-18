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
            // レンタルポイントを加算
            frequentRenterPoints += each.getFrequentRentalPoints();

            // この貸出に関する数値の表示
            result += "\t" + each.getMovie().getTitle() +
                    "\t" + String.valueOf(each.getCharge()) +
                    "\n";
            totalAmount += each.getCharge();
        }
        // フッタ部分の追加
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) +
                " frequent renter points";
        return result;
    }
}
