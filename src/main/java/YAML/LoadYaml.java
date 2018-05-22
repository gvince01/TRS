package YAML;

import jdk.internal.util.xml.impl.Input;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author gvince01
 */

public class LoadYaml {
    public Object getAPI(String arg) throws IOException {
        Yaml yaml = new Yaml();
        InputStream in = new FileInputStream("config.yaml");
        Map<String, Object> object = (Map<String, Object>) yaml.load(in);
        return object.get(arg);
    }
}

