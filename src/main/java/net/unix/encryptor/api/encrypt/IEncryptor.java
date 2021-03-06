package net.unix.encryptor.api.encrypt;

/**
 * @author Unix
 * @since 26.07.2020
 */

public interface IEncryptor
{
    byte[] done(final byte[] bytes, final SecretType type) throws Throwable;
}