//Francisco Vazquez
//CPSC-39
//7/18/25
//Final Project: Task Management System
import java.util.HashMap;

public class UserPreferences {
    HashMap<String, String> settings;

    public UserPreferences() {
        settings = new HashMap<>();
        //Set default preferences
        settings.put("DefaultPriority","2");
        settings.put("notifications", "on");
    }

    public void setPref(String key, String value) {
        settings.put(key, value);
    }

    public String getPref(String key) {
        return settings.getOrDefault(key, "");
    }
}