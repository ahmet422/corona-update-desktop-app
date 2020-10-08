package Model;


import Services.CountryCodes;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;

public class Utils {

    private Utils() {}

    public enum TotalType {
        CONFIRMED(0),
        DEATHS(1),
        RECOVERED(2);

        private int value;

        TotalType(int value) {
            this.value = value;
        }
    }

    public static String getTotal(TotalType type, List<? extends Country> list){
        double total = 0;

        for (Country country : list) {
            int lastDay = country.getDays().size();
            Day day = country.getDays().get(lastDay-1);
            total += getCount().apply(day, type);
        }

        return String.format("%,.0f", total);
    }

    private static BiFunction<Day, TotalType, Integer> getCount() {
        return (d, t) -> {
                if (t.value == 0) {
                    return d.getConfirmed();
                }
                else if (t.value == 1) {
                    return d.getDeaths();
                }
                else {
                    return d.getRecovered();
                }
            };
    }

    public static String getMaxCountry(TotalType type, List<? extends Country> list) {
        return list.stream().max(compareCountries(type)).get().getCountry();
    }

    private static Comparator<Country> compareCountries(TotalType type) {
        return (a, b) -> {
            Day dayA = a.getDays().get(a.getDays().size() - 1);
            Day dayB = b.getDays().get(b.getDays().size() - 1);

            return (getCount().apply(dayA, type) - getCount().apply(dayB, type));
        };
    }


    public static void jsonParse(JSONObject data, List<Country> list) {
        for (String countryName : data.keySet()) {
            list.add(createCountry(countryName, data.getJSONArray(countryName)));
        }

        list.sort(Comparator.comparing(Country::getCountry));
    }


    private static Country createCountry(String countryName, JSONArray days) {
        List<Day> dayList = new ArrayList<>();
        for (int i = 0; i < days.length(); i++) {
            JSONObject dayDetails = days.getJSONObject(i);
            dayList.add(createDay(dayDetails));
        }


        String flagLink = CountryCodes.getInstance().getCode(countryName) == null ? null :"https://www.countryflags.io/" + CountryCodes.getInstance().getCode(countryName) + "/flat/64.png";
        return new Country(countryName, dayList, flagLink);
    }


    private static Day createDay(JSONObject dayDetails) {
        try {
            String strDate = dayDetails.getString("date");
            Date date = new SimpleDateFormat("yyyy-mm-dd").parse(strDate);
            int confirmed = dayDetails.getInt("confirmed");
            int deaths = dayDetails.getInt("deaths");
            int recovered = dayDetails.getInt("recovered");

            return new Day(date, confirmed, deaths, recovered);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

}
