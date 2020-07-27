package net.unix.encryptor;

import net.unix.encryptor.api.encrypt.SecretType;
import java.io.File;

/**
 * @author Unix
 * @since 26.07.2020
 */

public class ApplicationContext
{
    public static void main(final String... args) throws Throwable {
        new FileEncryptor()
                .onStart(new File("ENCRYPTED-test.png"), SecretType.DECRYPT);
    }
}