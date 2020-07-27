package net.unix.encryptor;

import net.unix.encryptor.api.encrypt.IEncryptor;
import net.unix.encryptor.api.encrypt.SecretType;
import net.unix.encryptor.api.util.SafeUtil;
import net.unix.encryptor.encrypt.AESEncryptor;
import net.unix.encryptor.encrypt.JavaEncryptor;
import net.unix.encryptor.property.Translation;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Unix
 * @since 26.07.2020
 */

public class FileEncryptor
{
    private final List<IEncryptor> encryptors = new ArrayList<>();
    private final Translation translation;

    private SecretType secretType;

    public FileEncryptor() throws IOException {
        translation = new Translation();
    }

    public void onStart(final File inputFile, final SecretType type) throws Throwable {
        secretType = type;
        final FileInputStream fileInputStream = new FileInputStream(inputFile);
        configureEncryptors();
        if (type == SecretType.DECRYPT)
            Collections.reverse(encryptors);

        byte[] bytes = getByteArray(fileInputStream);
        for (final IEncryptor encryptor : encryptors)
            bytes = encryptor.done(bytes, type);

        Files.write(Paths.get(String.format("%sED-%s", type.name(), inputFile.getName())), bytes);
    }

    private void configureEncryptors() {
        SafeUtil.safeExecute(translation.getBoolean("javaEncrypt"), () -> encryptors.add(new JavaEncryptor()));
        SafeUtil.safeExecute(translation.getBoolean("aesEncrypt"), () -> encryptors.add(new AESEncryptor(this)));
    }

    private byte[] getByteArray(final InputStream inputStream) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int data;
        while ((data = inputStream.read()) != -1)
            byteArrayOutputStream.write(data);

        inputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    public Translation getTranslation() {
        return translation;
    }

    public SecretType getSecretType() {
        return secretType;
    }
}