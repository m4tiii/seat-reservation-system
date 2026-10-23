-- 1. Przykładowy adres
INSERT INTO address (city, postal_code, street, building_number, flat_number)
VALUES ( 'Kraków', '31-571', 'Stanisława Lema', '7', NULL);

-- 2. Przykładowy obiekt (Tauron Arena)
INSERT INTO venues (name, venue_type, capacity, address_id, is_indoor, created_at)
VALUES ( 'Tauron Arena Kraków', 'ARENA', 15000, 1, true, NOW());

-- 3. Krzesełka w hali (Sektor A, 2 rzędy po 4 miejsca)
INSERT INTO seat (venue_id, seat_sector, seat_row, seat_number, category) VALUES
      ( 1, 'A', '1', '1', 'VIP'),
      ( 1, 'A', '1', '2', 'VIP'),
      ( 1, 'A', '1', '3', 'VIP'),
      ( 1, 'A', '1', '4', 'VIP'),
      ( 1, 'A', '2', '1', 'STANDARD'),
      ( 1, 'A', '2', '2', 'STANDARD'),
      ( 1, 'A', '2', '3', 'STANDARD'),
      ( 1, 'A', '2', '4', 'STANDARD');

-- 4. Przykładowe wydarzenie
INSERT INTO events (venue_id, title, description, start_date, end_date)
VALUES (

           1,
           'Finał Mistrzostw Świata CS2',
           'Wielki finał turnieju esportowego na żywo z Krakowa.',
           NOW() + INTERVAL '7 days',
           NOW() + INTERVAL '7 days 4 hours'
       );

-- Ustawienie sekwencji ID w Postgresie na wypadek późniejszych INSERT-ów z aplikacji
SELECT setval('address_id_seq', (SELECT MAX(id) FROM address));
SELECT setval('venues_id_seq', (SELECT MAX(id) FROM venues));
SELECT setval('seat_id_seq', (SELECT MAX(id) FROM seat));
SELECT setval('events_id_seq', (SELECT MAX(id) FROM events));