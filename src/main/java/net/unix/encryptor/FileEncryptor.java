package net.unix.encryptor;

import net.unix.encryptor.api.encrypt.IEncryptor;
import net.unix.encryptor.api.encrypt.SecretType;
import net.unix.encryptor.encrypt.JavaEncryptor;
import net.unix.encryptor.property.Translation;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Unix
 * @since 26.07.2020
 */

public class FileEncryptor
{
    private final Translation translation;

    public FileEncryptor() throws IOException {
        translation = new Translation();
    }

    public void onStart(final File inputFile, final SecretType type) throws IOException {
        final FileInputStream fileInputStream = new FileInputStream(inputFile);
        final Set<IEncryptor> encryptors = new HashSet<>();

        if (translation.getBoolean("javaEncrypt"))
            encryptors.add(new JavaEncryptor());

        byte[] bytes = new byte[0];
        for (final IEncryptor encryptor : encryptors)
            bytes = encryptor.done(getByteArray(fileInputStream), type);

        Files.write(Paths.get("encrypted-" + inputFile.getName()), bytes);
    }

     private byte[] getByteArray(final InputStream inputStream) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int data;
        while ((data = inputStream.read()) != -1)
            byteArrayOutputStream.write(data);

        inputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
}