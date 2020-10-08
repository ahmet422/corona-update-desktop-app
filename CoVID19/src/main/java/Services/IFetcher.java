package Services;

import org.json.JSONObject;

public interface IFetcher {
    JSONObject fetch(String api);
}
