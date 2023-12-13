CREATE TABLE car_description (
     id BIGSERIAL PRIMARY KEY,
     car_id BIGINT,
     title varchar(255) NOT NULL,
     body varchar(255) NOT NULL,
     engine_volume varchar(255) NOT NULL,
     transmission varchar(255) NOT NULL,
     drive varchar(255) NOT NULL,
     rudder varchar(255) NOT NULL,
     color varchar(255) NOT NULL,
     customs_cleared varchar(255) NOT NULL,
     description TEXT,
     seller_comment TEXT,
     FOREIGN KEY (car_id) REFERENCES car(id) ON DELETE CASCADE
);