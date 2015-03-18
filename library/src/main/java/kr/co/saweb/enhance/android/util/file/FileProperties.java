package kr.co.saweb.enhance.android.util.file;

import java.io.StringReader;
import java.util.List;
import java.util.Properties;

/**
 * Created by OKS on 2014-11-20.
 */
public class FileProperties {
    private AndroidFile androidFile;
    private Properties properties;
    private String propName;

    public FileProperties() {
    }

    public FileProperties(AndroidFile androidFile, String propName) {
        setUp(androidFile, propName);
    }

    public void setUp(AndroidFile androidFile, String propName) {
        try {
            this.androidFile = androidFile;
            this.propName = propName;

            this.properties = new Properties();
            this.properties.load(new StringReader(androidFile.readText(propName)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void put(String key, Object value) {
        properties.put(key, value);
        save();
    }

    public Properties getProperties() {
        return properties;
    }

    public Object get(String key) {
        return properties.get(key);
    }

    public String getString(String key) {
        return get(key) == null ? null : String.valueOf(get(key)).trim();
    }

    public boolean getBoolean(String key) {
        return Boolean.valueOf(getString(key));
    }

    public int getInt(String key) {
        return Integer.valueOf(getString(key));
    }

    public void save() {
        StringBuffer result = new StringBuffer();

        for (Object key : properties.keySet()) {
            result.append(key + "=" + properties.get(key));
            result.append("\r\n");
        }

        try {
            androidFile.writeText(result.toString(), propName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}