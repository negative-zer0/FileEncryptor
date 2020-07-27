package net.unix.encryptor.encrypt;

import net.unix.encryptor.FileEncryptor;
import net.unix.encryptor.api.encrypt.IEncryptor;
import net.unix.encryptor.api.encrypt.SecretType;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Unix
 * @since 27.07.2020
 */

public class AESEncryptor implements IEncryptor
{
    private final Cipher cipher;

    public AESEncryptor(final FileEncryptor encryptor) throws Throwable {
        final SecretKeySpec secretKeySpec = new SecretKeySpec(encryptor.getTranslation().getString("aesKey").getBytes(), "AES");
        cipher = Cipher.getInstance("AES");
        cipher.init(encryptor.getSecretType().getId(), secretKeySpec);
    }

    @Override
    public byte[] done(final byte[] bytes, final SecretType type) throws Throwable {
        return cipher.doFinal(bytes);
    }
}