##Zadanie 2

Twoim zadaniem jest dokończenie implementacji endpointu, pozwalającego na zwrócenie dziennej prognozy pogody na kolejne 7 dni dla podanej lokalizacji.
Endpoint ten znajduje się w klasie ```WeatherController``` i ma następującą postać:
```
GET /weather/forecast?latitude=...&longitude=...
```

Do pobrania prognozy pogody wykorzystamy zewnętrzny serwis https://openweathermap.org.
Poniżej znajduje się przykładowe zapytanie, które nam się przyda:
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
1. Przygotuj model odpowiedzi zwracanej przez serwis OpenWeatherMap dla powyższego zapytania:
    * zmiany dokonaj w klasie ```OpenWeatherMapResponse```
    * w pobranej odpowiedzi interesują nas tylko dwie informacje: data (pole ```dt```) i dzienna temperatura (pole ```temp.day```)
2. Dokończ implementację odpytania serwisu OpenWeatherMap:
   * zmianę wykonaj w klasie ```OpenWeatherMapClient```
   * do zbudowania zapytania możesz wykorzystać pola klasy
   * w pliku ```ApiKeys.txt``` znajduje się lista testowych apiKey, wybierz dowolny i ustaw go w pliku ```application.yml```
3. Przemapuj odpowiedź zwróconą przez serwis OpenWeatherMap na model domenowy:
   * zmiany dokonaj w klasie ```OpenWeatherMapForecastProvider```
   * dla pola ```dt``` możesz skorzystać z metody ```Instant.ofEpochSecond()```
