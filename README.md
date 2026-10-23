🎟️ Seat Reservation System

System do rezerwacji miejsc i sprzedaży biletów na wydarzenia (koncerty, mecze, spektakle). Backend napisany w Javie ze Spring Bootem, oparty na relacyjnej bazie PostgreSQL.

Projekt obsługuje zarówno zarejestrowanych użytkowników, jak i szybkie zakupy dla gości (bez tworzenia konta).

🛠️ Tech Stack

Język: Java 25

Framework: Spring Boot 4

Baza danych: PostgreSQL

Migracje bazy: Flyway

ORM: Spring Data JPA / Hibernate

Narzędzia: Maven, Lombok

🧠 Jak to działa pod maską?

Moduły domenowe: Projekt zorganizowany wokół kontekstów biznesowych (user, venue, event, reservation).

Elastyczna rezerwacja: Tabela rezerwacji pozwala przypisać bilet do konta zarejestrowanego użytkownika lub zebrać dane gościa (guest_*), z wymuszeniem poprawności przez ograniczenia CHECK na poziomie bazy.

Ochrona przed podwójną rezerwacją: Unikalny indeks na parze (event_id, seat_id) uniemożliwia wielokrotne zarezerwowanie tego samego miejsca na jedno wydarzenie.

Automatyczne migracje: Flyway wykonuje skrypty SQL przy starcie aplikacji, dbając o wersjonowanie schematu bazy.

## 🗄️ Schemat bazy danych (ERD)

![Schemat bazy danych](docs/images/database-erd.png)

🚀 Jak odpalić lokalnie

1. Wymagania

Zainstalowany JDK 25

Działająca baza danych PostgreSQL (domyślnie port 5432)

2. Konfiguracja bazy

W katalogu src/main/resources/ stwórz plik application-local.yaml (plik jest w .gitignore, więc Twoje dane nie trafią do repozytorium):

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/seat_reservation_system
    username: postgres
    password: twoje_haslo
```

3. Uruchomienie w IntelliJ

W konfiguracji uruchomieniowej aplikacji dodaj aktywny profil: local.

Uruchom klasę główną: SeatReservationSystemApplication.

Flyway automatycznie utworzy tabele i schemat bazy przy pierwszym uruchomieniu.

📌 Status prac

[x] Projekt bazy danych i ograniczeń integralności

[x] Migracja Flyway V1 (V1__init_schema.sql)

[x] Konfiguracja profilu lokalnego i połączenia z bazą

[ ] Mapowanie encji JPA

[ ] Repozytoria Spring Data JPA

[ ] Logika biznesowa rezerwacji i wygaszania miejsc

[ ] Endpointy REST API
