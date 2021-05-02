package pl.com.laweta;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.quarkus.test.junit.QuarkusTestProfile;

public class TestConfiguration implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
        return Stream
                .of(
                    new SimpleEntry<>("MAIL_USER", "test"), 
                    new SimpleEntry<>("MAIL_PASS", "test"),
                    new SimpleEntry<>("MAIL_RECEIVERS", "test"))
                .collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));
    }
}