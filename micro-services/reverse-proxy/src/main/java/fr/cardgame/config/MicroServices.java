package fr.cardgame.config;

public enum MicroServices {
    USER("localhost", 8082),
    INVENTORY("localhost", 8083),
    AUTHENTICATION("localhost", 8081);

    private final String address;
    private final int port;

    MicroServices(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public String getUrl() {
        return "http://" + this.address + ":" + this.port;
    }
}