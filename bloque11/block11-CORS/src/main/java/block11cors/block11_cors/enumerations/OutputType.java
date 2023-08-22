package block11cors.block11_cors.enumerations;

public enum OutputType {
    simple("simple"),
    full("full");

    private final String value;

    OutputType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static OutputType getDefault() {
        return simple;
    }
}
