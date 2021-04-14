package pl.allegrotech.weatherapp.examples;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SampleHttpClientResponse {

    private final String userName;
    private final List<Book> books;

    SampleHttpClientResponse(@JsonProperty("userName") String userName, @JsonProperty("books") List<Book> books) {
        this.userName = userName;
        this.books = books;
    }

    public String getUserName() {
        return userName;
    }

    public List<Book> getBooks() {
        return books;
    }

    static class Book {

        private final String title;

        Book(@JsonProperty("title") String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

}
