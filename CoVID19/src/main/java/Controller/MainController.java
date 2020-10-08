package Controller;

import Model.CoronaDataSupplier;
import Model.Country;
import Model.Utils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.List;

public class MainController {
    @FXML
    private Label lblConfirmed, lblRecovered, lblDeaths, lblMostCaseCountry, lblMostRecoveredCountry, lblMostDeathsCountry, lblSpecCountry;
    @FXML
    private ComboBox<String> comboCountries;

    @FXML
    GridPane gridPane;

    private CoronaDataSupplier supplier;

    public MainController(CoronaDataSupplier supplier) {
        this.supplier = supplier;
        supplier.get().getCountryList().size();
    }

    @FXML
    private void initialize() {
        if (supplier.get().getCountryList() != null) {
            lblConfirmed.setText(Utils.getTotal(Utils.TotalType.CONFIRMED, supplier.get().getCountryList()));
            lblRecovered.setText(Utils.getTotal(Utils.TotalType.RECOVERED, supplier.get().getCountryList()) + "");
            lblDeaths.setText(Utils.getTotal(Utils.TotalType.DEATHS, supplier.get().getCountryList()) + "");

            lblMostCaseCountry.setText(Utils.getMaxCountry(Utils.TotalType.CONFIRMED, supplier.get().getCountryList()));
            lblMostRecoveredCountry.setText(Utils.getMaxCountry(Utils.TotalType.RECOVERED, supplier.get().getCountryList()));
            lblMostDeathsCountry.setText(Utils.getMaxCountry(Utils.TotalType.DEATHS, supplier.get().getCountryList()));

            populateComboBox();
            comboCountries.getSelectionModel().selectFirst();
            comboCountries.valueProperty().addListener(onCountryChange());
        }

    }

    private ChangeListener<? super String> onCountryChange() {
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                supplier.get().getCountryList().stream().filter(country -> country.getCountry().equals(t1)).forEach(country -> {
                    int lastIndex = country.getDays().size()-1;
                    lblSpecCountry.setText("Total deaths: " + String.format("%,.0f", (double)country.getDays().get(lastIndex).getDeaths()) + "\n" +
                                            "Total confirmed: " + String.format("%,.0f", (double)country.getDays().get(lastIndex).getConfirmed()) + "\n" +
                                            "Total recovered: " + String.format("%,.0f", (double)country.getDays().get(lastIndex).getRecovered()));
                });
            }
        };
    }

    private void populateComboBox() {
        ObservableList<String> countryNames = comboCountries.getItems();
        countryNames.add("Select");
        supplier.get().getCountryList().stream().forEach(country -> countryNames.add(country.getCountry()));

        comboCountries.setItems(countryNames);
    }

    public void gotoTableView(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) gridPane.getScene().getWindow();

        URI uri = Path.of(System.getProperty("user.dir") + "/src/main/java/View/TableView.fxml").toUri();
        FXMLLoader loader = new FXMLLoader(uri.toURL());


        List<Country> countries = supplier.get().getCountryList();

        CountryTableViewController countryTableViewController = new CountryTableViewController(countries);
        loader.setControllerFactory(t -> countryTableViewController);

        stage.setTitle("Country Stats");


        Parent root = loader.load();

        Scene scene = new Scene(root, Main.x, Main.y);
        scene.getStylesheets().add(Main.getStyles());

        stage.setScene(scene);
        stage.show();

    }
}