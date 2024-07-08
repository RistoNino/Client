package org.uid.ristonino.client.model;

public class ErrorHandler extends Exception {

    // Campi per contenere i dettagli dell'errore
    private String errorCode;

    // Costruttore senza argomenti
    public ErrorHandler() {
        super("An unknown error occurred.");
        this.errorCode = "UNKNOWN";
    }

    // Costruttore con solo messaggio di errore
    public ErrorHandler(String errorMessage) {
        super(errorMessage);
        this.errorCode = "UNKNOWN";
    }

    // Costruttore con codice di errore e messaggio
    public ErrorHandler(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    // Costruttore con codice di errore, messaggio e causa
    public ErrorHandler(String errorCode, String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return getMessage();
    }

    public void printError() {
        System.err.println("Error Code: " + errorCode);
        System.err.println("Error Message: " + getMessage());
        if (getCause() != null) {
            getCause().printStackTrace();
        }
    }

    public void handleError() {
        printError();
    }
}
