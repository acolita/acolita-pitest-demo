package br.com.acolita.accellescale.model.error;

public class IncompleteObjectConstructionException extends RuntimeException {
    public IncompleteObjectConstructionException(final String message) {
        super(message, null, true, false);
    }
}
