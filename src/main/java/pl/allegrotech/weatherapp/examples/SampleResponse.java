package pl.allegrotech.weatherapp.examples;

import java.util.Objects;

public class SampleResponse {
    private final String name;
    private final int age;

    public SampleResponse(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SampleResponse response = (SampleResponse) o;
        return Objects.equals(name, response.name) &&
                Objects.equals(age, response.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Response{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
