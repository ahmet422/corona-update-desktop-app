package Model;

import org.json.JSONObject;

public class CountriesFactory {

    private CountriesFactory() {
    }

    public static Countries getCountries(JSONObject data) {
        Countries countries = new Countries();
        Utils.jsonParse(data, countries.getCountryList());
        return countries;
    }

}
