package Vat;

public class VatNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return message;
    }

    private String message;

    public VatNotFoundException(String message) {

        this.message = message;
    }
}
