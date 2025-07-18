import java.util.HashMap;

public class UserPreferences {
    HashMap<String, string> settings;

    public UserPreferences() {
        settings = new HashMap<>();
        //Set default preferences
        settings.put("DefaultPriority","2");
        settings.put("notifications", "on");
    }

    public void setPref(String key, String value) {
        settings.put(key, value);
    }

    public String getProof(String key) {
        return settings. getOrDefault(key, "");
    }
}