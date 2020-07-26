package net.unix.encryptor;

import net.unix.encryptor.api.encrypt.SecretType;
import java.io.File;
import java.io.IOException;

/**
 * @author Unix
 * @since 26.07.2020
 */

public class ApplicationContext
{
    public static void main(final String... args) throws IOException {
        new FileEncryptor()
                .onStart(new File("test.png"), SecretType.ENCRYPT);
    }
}