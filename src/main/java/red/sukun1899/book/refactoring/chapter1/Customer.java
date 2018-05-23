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
        String result = "Rental Record for " + getName() + "\n";
        for (Rental each : rentals) {
            // この貸出に関する数値の表示
            result += "\t" + each.getMovie().getTitle() +
                    "\t" + String.valueOf(each.getCharge()) +
                    "\n";
        }
        // フッタ部分の追加
        result += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
        result += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) +
                " frequent renter points";
        return result;
    }

    public String htmlStatement() {
        String result = "<h1>Rental Record for <em>" + getName() + "</em></h1><p>\n";
        for (Rental each : rentals) {
            // この貸出に関する数値の表示
            result += each.getMovie().getTitle() + ": " +
                    String.valueOf(each.getCharge()) + "<br>\n";
        }
        // フッタ部分の追加
        result += "<p>You owe <em>" + String.valueOf(getTotalCharge()) + "</em><p>\n";
        result += "On this rental you earned <em>" +
                String.valueOf(getTotalFrequentRenterPoints()) +
                "</em> frequent renter points<p>";
        return result;
    }

    private double getTotalCharge() {
        double result = 0;
        for (Rental each : rentals) {
            result += each.getCharge();
        }
        return result;
    }

    private int getTotalFrequentRenterPoints() {
        int result = 0;
        for (Rental each : rentals) {
            result += each.getFrequentRentalPoints();
        }
        return result;
    }
}
