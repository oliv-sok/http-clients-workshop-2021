###Zadanie 1

Twoim zadaniem jest dodanie nowego endpointu, pozwalającego na zapisywanie danych pogodowych dla danej lokalizacji.
Dane powinny być przechowywane w repozytorium in-memory oraz możliwe do pobrania za pomocą istniejącego endpointu typu GET.

Nowy endpoint powinien wyglądać następująco:

* metoda HTTP: POST
* ścieżka: /weather
* body requestu:
    ```
    {
        "location": {
            "latitude": ...
            "longitude": ...
        }
        "temperature": ...
    }
    ```
* w odpowiedzi oprócz zapisanych danych pogodowych powinien znajdować się nagłówek Location wskazujący na ścieżkę dodanego zasobu, tzn. "/weather/{id}"

Dopisz test integracyjny w klasie WeatherControllerIntegrationTest sprawdzający ścieżkę pozytywną.

Informacje pomocnicze:
* do zbudowania requestu możesz użyć adnotacji @PostMapping oraz @RequestBody
* do dodania nagłówka w odpowiedzi możesz użyć klasy UriComponentsBuilder
* w teście integracyjnym możesz użyć metody postForEntity(...) z klasy TestRestTemplate