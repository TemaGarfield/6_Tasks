package by.epam.m6_t1.entity;

import java.util.Objects;

public class Book {
    private String name;
    private String description;
    private boolean isElectro;

    public Book (String name, String description, boolean isElectro) {
        this.name = name;
        this.description = description;
        this.isElectro = isElectro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isElectro() {
        return isElectro;
    }

    public void setElectro(boolean electro) {
        isElectro = electro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return isElectro == book.isElectro &&
                Objects.equals(name, book.name) &&
                Objects.equals(description, book.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, isElectro);
    }
}
