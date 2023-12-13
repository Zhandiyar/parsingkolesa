CREATE TABLE cars
(
    id              BIGSERIAL PRIMARY KEY,
    brand           varchar(255) NOT NULL,
    price           varchar(255) NOT NULL,
    location        varchar(255) NOT NULL,
    generation      varchar(255) NOT NULL,
    body            varchar(255) NOT NULL,
    engine_volume   varchar(255) NOT NULL,
    transmission    varchar(255) NOT NULL,
    drive           varchar(255) NOT NULL,
    rudder          varchar(255) NOT NULL,
    color           varchar(255) NOT NULL,
    customs_cleared varchar(255) NOT NULL,
    link_car        varchar(255) NOT NULL,
    links_image     TEXT,
    description     TEXT,
    seller_comment  TEXT
);