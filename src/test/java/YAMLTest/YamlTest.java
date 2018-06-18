package YAMLTest;

import YAML.LoadYaml;
import org.junit.Test;

import java.io.IOException;

/**
 * @author gvince01
 */

public class YamlTest {

    @Test
    public void loadYamlTest() throws IOException{
        LoadYaml myYaml = new LoadYaml();;
        assert(myYaml.getAPI("test-api-key").toString().equals("hello World"));
    }
}
