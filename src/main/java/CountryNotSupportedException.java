public class CountryNotSupportedException extends RuntimeException {

    private String message;

    public CountryNotSupportedException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
