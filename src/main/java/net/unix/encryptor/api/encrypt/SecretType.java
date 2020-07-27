package net.unix.encryptor.api.encrypt;

import javax.crypto.Cipher;

/**
 * @author Unix
 * @since 26.07.2020
 */

public enum SecretType
{
    ENCRYPT(Cipher.ENCRYPT_MODE),
    DECRYPT(Cipher.DECRYPT_MODE);

    private final int id;

    SecretType(final int mode) {
        id = mode;
    }

    public int getId() {
        return id;
    }

    public static int of(final SecretType type) {
        switch (type) {
            case ENCRYPT:
                return Cipher.ENCRYPT_MODE;
            case DECRYPT:
                return Cipher.DECRYPT_MODE;
        }

        return -2137;
    }
}