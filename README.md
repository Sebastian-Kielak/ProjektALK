# Nazwa projektu
> Test automatyczny sklepu internetowego w JAVA z wykorzystaniem biblioteki Selenium WebDriver

## Spis treści
* [Główne informacje](#Główne-informacje)
* [Użyte technologie](#Użyte-technologie)
* [Użyte biblioteki](#Użyte-biblioteki)
* [Wymagania](#Wymagania)
* [Przypadki_testowe](#Przypadki-testowe)
* [Uruchamianie testów](#Uruchamianie-testów)
* [Dodatkowe informacje](#Dodatkowe-informacje)
* [Status projektu](#Status-projektu)
* [Kontakt](#Kontakt)


## Główne informacje
- testowana strona to sklep internetowy "automationpractice.com"
- 1 scenariusz (skłądający się z 5 TC)
- test sprawdzający regresje i działanie głównych funkcjonalności
- program stworzony na potrzeby zaliczenia studiów podyplomowych na ALK


## Użyte technologie
- Page of Patter
- Page Factory
- Data Driven (czytanei z pliku .csv)


## Użyte biblioteki
- Selenium Webdriver
- JavaFaker
- OpenCSV
- Maven
- TestNG
- WebDriverManager


## Wymagania
- Stabilna wersja przeglądarki Chrome
- JAVA w wersji 11 (w przypadku innej wersji należy zmienić wersje w pliku pom.xml)


## Przypadki testowe
1. Sprawdzenie wymagalności pól formularza rejestracji
2. Prawidłowe przejście przez proces rejestracji
3. Prawidłowe przejście przez proces logowania
4. Prawidlowa wysyłka formularza konstaktowego
5. Prawidłowe przejście pełnego procesu sprzedaży


## Uruchomianie testów
1. Skopiuj repozytorium z GitHub'a
2. Otwórz terminal w folderze projektu
3. Wpisz w konsoli "mvn clean test"


## Dodatkowe informacje
Program odpala się w tle (--headless), w przypadku chęci obejrzenia wykonywania testów należy usunąć w pliku "TestBasicScenario.java", opcje "--headless"


## Status projektu
Zakończony


## Kontakt
https://pl.linkedin.com/in/sebastian-kielak-186624183?original_referer=https%3A%2F%2Fwww.google.com%2F
