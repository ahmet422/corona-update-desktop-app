package Model;

import java.util.ArrayList;
import java.util.List;

public final class Countries {
    private List<Country> countryList;

    Countries() {
        countryList = new ArrayList<>();
    }

    public List<Country> getCountryList() {
        return countryList;
    }
}
