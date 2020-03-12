package br.com.acolita.accellescale.model.error;

public class StringIsAllBlankException extends RuntimeException {
    public StringIsAllBlankException(final String message) {
        super(message,null,true,false);
    }
}
