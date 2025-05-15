package org.example.constants;

public enum RoomType {
    STANDARD("Обычный номер"),
    LUX("Номер ЛЮКС");

    private final String description;

    RoomType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}