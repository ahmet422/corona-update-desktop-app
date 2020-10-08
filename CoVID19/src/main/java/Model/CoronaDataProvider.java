package Model;

import Services.IFetcher;
import Services.RestAPI;

public class CoronaDataProvider implements CoronaDataSupplier {
    private static final String coronaAPI = "https://pomber.github.io/covid19/timeseries.json";
    private IFetcher iFetcher;
    private Countries countries;

    public CoronaDataProvider() {
        this.iFetcher = RestAPI.getInstance();
    }

    @Override
    public Countries get() {
        if (countries == null) {
            countries =  CountriesFactory.getCountries(iFetcher.fetch(coronaAPI));
        }
        
        return countries;
    }
}
