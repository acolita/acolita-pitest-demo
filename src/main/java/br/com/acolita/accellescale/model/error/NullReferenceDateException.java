package br.com.acolita.accellescale.model.error;

public class NullReferenceDateException extends RuntimeException {
    public NullReferenceDateException(final String message) {
        super(message, null, true, false);
    }
}
