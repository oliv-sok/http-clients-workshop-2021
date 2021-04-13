##Zadanie 1

Twoim zadaniem jest dokończenie implementacji endpointu pozwalającego na zapisywanie danych pogodowych dla danej lokalizacji.
Endpoint ten znajduje się w klasie __WeatherController__.
Dane pogodowe powinny być przechowywane w repozytorium in-memory oraz możliwe do pobrania za pomocą istniejącego endpointu typu GET.

Nowy endpoint powinien działać następująco:
* format requestu:
  * metoda HTTP: POST
  * ścieżka: ```/weather```
  * body:
    ```
    {
        "latitude": 52.1
        "longitude": 23.7
        "city": "Warszawa",
        "temperature": 21.5
    }
    ```
* format response'u:
  * status: 201 Created
  * w body zwracamy dane zapisanego zasobu

###Kroki do wykonania
* dopisz implementację endpointu w klasie ```WeatherController```, dodaj klasę reprezentującą body requestu, np. ```WeatherApiRequest```
* dopisz test integracyjny w klasie ```WeatherControllerIntegrationTest``` sprawdzający ścieżkę pozytywną, użyj metody ```postForEntity``` z klasy ```TestRestTemplate```

###Część dodatkowa
* rozszerz zwracany response o nagłówek ```Location```, który będzie wskazywać na ścieżkę dodanego zasobu, tzn. ```/weather?latitude=...&longitude=...```
* do zbudowania nagłówka ```Location``` możesz użyć parametru ```uriComponentsBuilder``` i metody ```ResponseEntity.created(...)```