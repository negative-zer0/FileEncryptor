package net.unix.encryptor.api.util;

/**
 * @author Unix
 * @since 27.07.2020
 */

public final class SafeUtil
{
    private SafeUtil() {
    }

    public interface SafeExecutor<T>
    {
        T execute() throws Throwable;
    }

    public static <T> T safeExecute(final boolean value, final SafeExecutor<T> executor) {
        if (!value)
            return null;

        try {
            return executor.execute();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return null;
    }
}