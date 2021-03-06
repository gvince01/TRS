package YAML;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author gvince01
 */

public class LoadYaml {
    public String getValue(String arg) throws IOException {
        //returns String from yamlfile
        Yaml yaml = new Yaml();
        InputStream in = new FileInputStream("config.yaml");
        Map<String, Object> object = (Map<String, Object>) yaml.load(in);
        return object.get(arg).toString();
    }
}

