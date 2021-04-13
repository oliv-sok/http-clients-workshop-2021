##Zadanie 2

Twoim zadaniem jest dokończenie implementacji endpointu, pozwalającego na zwrócenie prognozy pogody na kolejne 7 dni dla podanej lokalizacji.
Endpoint ten znajduje się w klasie ```WeatherController``` i ma następującą postać:
```
GET /weather/forecast?latitude=...&longitude=...
```

Do pobrania prognozy pogody wykorzystamy zewnętrzny serwis (https://openweathermap.org).
Poniżej znajduje się przykładowe zapytanie o dzienną prognozę pogody na kolejne 7 dni dla podanej lokalizacji:
```
GET https://api.openweathermap.org/data/2.5/onecall?lat=53.0138&lon=18.5984&exclude=current,hourly,minutely,alerts&units=metric&appid=f8808a0fe2a10e44874330a3e69e1b34
```

Parametry zapytania:
* lat i lon - współrzędne lokalizacji, dla której pobieramy prognozę pogody
* exclude - fragmenty odpowiedzi, które nas nie interesują, w tym przykładzie zostaną zwrócone tylko dane dzienne
* units - rodzaj jednostek używanych do reprezentowania wartości
* appid - apiKey powiązany z użytkownikiem

Format odpowiedzi:

```
{
    ...
    "daily": [
        "dt": 1618135200, // data jako "Unix timeestamp"
        "temp": {
            "day": 15.7,
            ...
        },
        ...
    ],
    ...
}
```

###Kroki do wykonania:
1. Przygotuj model odpowiedzi zwracanej przez zewnętrzny serwis:
    * zmiany dokonaj w klasie ```OpenWeatherMapResponse```
    * z pobranej odpowiedzi interesują nas tylko dwie informacje: data (pole ```dt```) i dzienna temperatura (pole ```temp.day```)
2. Dodaj implementację odpytania zewnętrznego serwisu:
   * zmianę wykonaj w klasie ```OpenWeatherMapClient```
   * do zbudowania zapytania możesz wykorzystać zmienne dostępne w klasie, tzn. RestTemplate itp.
   * w pliku ```apiKeys.txt``` znajdują się apiKey, które możesz wykorzystać i ustawić w pliku ```application.yml```
3. Przemapuj pobraną odpowiedź na model domenowy:
   * zmiany dokonaj w klasie ```OpenWeatherMapForecastProvider```
   * dla pola ```dt``` możesz skorzystać z metody ```Instant.ofEpochSecond()```
