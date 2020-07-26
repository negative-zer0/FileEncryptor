package net.unix.encryptor.api.encrypt;

import javax.crypto.Cipher;

/**
 * @author Unix
 * @since 26.07.2020
 */

public enum SecretType
{
    ENCRYPT,
    DECRYPT;

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