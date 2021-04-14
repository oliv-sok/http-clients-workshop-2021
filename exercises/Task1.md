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
* dopisz implementację endpointu ```addWeatherForLocation``` w klasie ```WeatherController```
* do zapisania danych pogodowych użyj ```weatherService```
* możesz wykorzystać test integracyjny ```shouldAddWeatherForGivenLocation``` w klasie ```WeatherControllerIntegrationTest``` do sprawdzenia, że endpoint działa poprawnie

###Część dodatkowa
* rozszerz odpowiedź zwracaną przez endpoint o nagłówek ```Location```, który będzie wskazywać na ścieżkę dodanego zasobu, tzn. ```http://localhost:8090/weather?latitude=...&longitude=...```
* w teście integracyjnym dodaj w sekcji ```then``` nową asercję sprawdzająca, że zwrócony nagłówek ```Location``` ma prawidłową wartość