package Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryCodes {

    private static Map<String, String> codes;
    private static CountryCodes countryCodes = new CountryCodes();


    public static CountryCodes getInstance() {
        if (countryCodes == null) {
            countryCodes = new CountryCodes();
        }

        if (codes == null) {
            countryCodes.populate();
        }
        return countryCodes;
    }

    public String getCode(String countryName) {

        if (countryCodes == null) {
            countryCodes = new CountryCodes();
        }

        if (codes.containsKey(countryName)) {
            return codes.get(countryName);
        }

        return null;
    }

    private void populate() {
        codes = new HashMap<>();
        try {

            Path path = Paths.get("C:/Users/User/Downloads/CoVID19/CoVID19/build/resources/main/countryCodeList.txt");
          //  Path path = Paths.get(getClass().getResource("/countryCodeList.txt").getPath());
            List<String> lines = Files.readAllLines(path);

            for (String line : lines) {
                String[] arr = line.split("\\|");
                codes.put(arr[1], arr[0]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
