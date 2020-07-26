package net.unix.encryptor.encrypt;

import net.unix.encryptor.api.encrypt.IEncryptor;
import net.unix.encryptor.api.encrypt.SecretType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;

/**
 * @author Unix
 * @since 26.07.2020
 */

public class JavaEncryptor implements IEncryptor
{
    @Override
    public byte[] done(final byte[] bytes, final SecretType type) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        switch (type) {
            case ENCRYPT:
                try (final DeflaterOutputStream deflaterInputStream = new DeflaterOutputStream(byteArrayOutputStream)) {
                    deflaterInputStream.write(bytes);
                }
                break;
            case DECRYPT:
                try (final InflaterOutputStream inflaterOutputStream = new InflaterOutputStream(byteArrayOutputStream)) {
                    inflaterOutputStream.write(bytes);
                }
                break;
        }

        return byteArrayOutputStream.toByteArray();
    }
}