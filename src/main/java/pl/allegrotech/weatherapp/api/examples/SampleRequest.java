package pl.allegrotech.weatherapp.api.examples;

import java.util.Objects;

public class SampleRequest {
    private final String name;
    private final int birthYear;

    public SampleRequest(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SampleRequest response = (SampleRequest) o;
        return Objects.equals(name, response.name) &&
                Objects.equals(birthYear, response.birthYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthYear);
    }

    @Override
    public String toString() {
        return "Response{" +
                "name='" + name + '\'' +
                ", birthYear='" + birthYear + '\'' +
                '}';
    }
}
