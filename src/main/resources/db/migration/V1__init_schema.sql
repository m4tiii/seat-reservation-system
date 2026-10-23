CREATE TABLE address(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    city VARCHAR(50) NOT NULL,
    postal_code VARCHAR(10) NOT NULL,
    street VARCHAR(50) NOT NULL,
    building_number VARCHAR(10) NOT NULL,
    flat_number VARCHAR(10)
);

CREATE TABLE users(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    surname VARCHAR(30) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(20) UNIQUE NOT NULL,
    CONSTRAINT check_user_phone_format
        CHECK(phone_number ~ '^\+?[0-9]{7,15}$')
);

CREATE TABLE reservation(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id BIGINT,
    guest_email VARCHAR(100),
    guest_name VARCHAR(20),
    guest_surname VARCHAR(30),
    guest_phone VARCHAR(20),
    status VARCHAR(10) NOT NULL,
    date TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    expiration_date TIMESTAMPTZ NOT NULL,
    CONSTRAINT fk_reservation_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT check_guest_phone_format CHECK (guest_phone IS NULL OR guest_phone ~ '^\+?[0-9]{7,15}$'),
    CONSTRAINT chk_reservation_user CHECK (
        (user_id IS NOT NULL
        AND guest_email IS NULL
        AND guest_name IS NULL
        AND guest_surname IS NULL
        AND guest_phone IS NULL)
        OR
        (user_id IS NULL
        AND guest_email IS NOT NULL
        AND guest_name IS NOT NULL
        AND guest_surname IS NOT NULL
        AND guest_phone IS NOT NULL)
    )
);

CREATE TABLE venues(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    venue_type VARCHAR(50) NOT NULL,
    capacity BIGINT NOT NULL,
    address_id BIGINT NOT NULL,
    is_indoor BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_venues_capacity CHECK ( capacity > 0 ),
    CONSTRAINT fk_venues_address FOREIGN KEY (address_id) REFERENCES address(id)
);

CREATE TABLE events(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    venue_id BIGINT NOT NULL,
    title VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    start_date TIMESTAMPTZ NOT NULL,
    end_date TIMESTAMPTZ NOT NULL,
    CONSTRAINT fk_events_venues FOREIGN KEY (venue_id) REFERENCES venues(id)
);

CREATE TABLE seat(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    venue_id BIGINT NOT NULL,
    seat_sector VARCHAR(20) NOT NULL,
    seat_row VARCHAR(20) NOT NULL,
    seat_number VARCHAR(20) NOT NULL,
    category VARCHAR(30) NOT NULL,
    CONSTRAINT fk_seat_venue FOREIGN KEY (venue_id) REFERENCES venues(id),
    CONSTRAINT uq_seat UNIQUE (venue_id, seat_number, seat_row, seat_sector)
);

CREATE TABLE tickets(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    event_id BIGINT NOT NULL,
    reservation_id BIGINT NOT NULL,
    seat_id BIGINT NOT NULL,
    price NUMERIC(10,2) NOT NULL,

    CONSTRAINT fk_tickets_reservation FOREIGN KEY (reservation_id) REFERENCES reservation(id) ON DELETE CASCADE ,
    CONSTRAINT fk_tickets_seats FOREIGN KEY (seat_id) REFERENCES seat(id),
    CONSTRAINT fk_tickets_events FOREIGN KEY (event_id) REFERENCES events(id),
    CONSTRAINT uq_event_seat UNIQUE (event_id, seat_id)
);

