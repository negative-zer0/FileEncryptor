package net.unix.encryptor.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * @author Unix
 * @since 26.07.2020
 */

public class Translation
{
    private final Properties properties;

    public Translation() throws IOException {
        final InputStream inputStream = this.getClass().getResourceAsStream("/config.properties");
        if (Objects.isNull(inputStream))
            throw new IllegalArgumentException("InputStream cannot be null!");

        properties = new Properties();
        properties.load(inputStream);
    }

    public String getString(final String key) {
        return (String) properties.getOrDefault(key, "Cannot find property.");
    }

    public boolean getBoolean(final String key) {
        return Boolean.parseBoolean((String) properties.getOrDefault(key, "false"));
    }
}