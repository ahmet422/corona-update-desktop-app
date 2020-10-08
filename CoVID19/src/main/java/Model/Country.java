package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Country {
    private String flag;
    private Image flagImage;
    private String country;
    private List<Day> days;


    Country(String countryName, List<Day> days, String flag) {
        this.country = countryName;
        this.days = days;
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public ImageView getFlag() {
        if (flag == null) {
           flag = "file://C:/Users/User/Downloads/CoVID19/CoVID19/build/resources/main/defaultIcon.png";
            // flag = Paths.get(getClass().getResource("/defaultIcon.png").toString()).toString();
        }

        if (flagImage == null) {
            flagImage = new Image(flag);
        }

        return new ImageView(flagImage);
    }

    public int getConfirmed() {
        return days.get(days.size()-1).getConfirmed();
    }

    public int getDeaths() {
        return days.get(days.size()-1).getDeaths();
    }

    public int getRecovered() {
        return days.get(days.size()-1).getRecovered();
    }

    public List<Day> getDays() {
        return days;
    }

    @Override
    public String toString() {
        return country + ' ' +
                ", days=" + days;
    }
}
