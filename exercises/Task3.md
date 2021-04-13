##Zadanie 3

Zadanie składa się z dwóch części:
* chcemy sprawić, żeby endpoint ```/weather/forecast``` zwracał poprawne kody błędów w zależności od tego, jaki błąd wystąpił w komunikacji z serwisem OpenWeatherMap:
   * dla błędów po stronie serwera (5xx) -> HttpStatus.INTERNAL_SERVER_ERROR
   * dla błędów po stronie klienta (4xx) -> HttpStatus.BAD_REQUEST
   * dla błędów związanych z timeoutami -> HttpStatus.REQUEST_TIMEOUT
* chcemy skonfigurować timeouty dla klienta HTTP, odpytującego serwis OpenWeatherMap

###Kroki do wykonania

1. Dodaj obsługę błędów:
   * zmianę wykonaj w klasie ```WebExceptionHandler```
   * weź pod uwagę wyjątki rzucane przez RestTemplate: ```HttpClientException```, ```HttpServerException```, ```ResourceAccessException```
2. Skonfiguruj timeouty dla klienta HTTP:
   * zmianę wykonaj w klasie ```OpenWeatherMapConfig```
   * timeouty ustawisz za pomocą ```RestTemplateBuilder```

Testowanie:
* jak wywołać błąd po stronie klienta? podaj w konfiguracji nieprawidłowy apiKey
* jak wywołać błąd związany z timeoutem? podaj zbyt małe wartości timeoutów

####Pytania dodatkowe
* jak wywołać błąd po stronie serwera?
* jak testować automatycznie różne scenariusze związane z komunikacją między usługami?