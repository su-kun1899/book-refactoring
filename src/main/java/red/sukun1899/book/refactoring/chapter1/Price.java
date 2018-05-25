package red.sukun1899.book.refactoring.chapter1;

/**
 * @author su-kun1899
 */
abstract class Price {
    abstract int getPriceCode();
}

class ChildrenPrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.CHILDRENS;
    }
}

class NewReleasePrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.NEW_RELEASE;
    }
}

class RegularPrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.REGULAR;
    }
}
