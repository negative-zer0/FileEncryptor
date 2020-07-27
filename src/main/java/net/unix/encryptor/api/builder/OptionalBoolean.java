package net.unix.encryptor.api.builder;

/**
 * @author Unix
 * @since 27.07.2020
 */

public class OptionalBoolean
{
    private boolean value;

    private OptionalBoolean(final boolean bool) {
        value = bool;
    }

    public void ifPresent(final Runnable runnable) {
        runnable.run();
    }

    public void ifPresentOrElse(final Runnable first, final Runnable second) {
        if (isPresent()) {
            first.run();
            return;
        }

        second.run();
    }

    public void setValue(final boolean bool) {
        value = bool;
    }

    public boolean isPresent() {
        return value;
    }

    public static OptionalBoolean of(final boolean value) {
        return new OptionalBoolean(value);
    }
}