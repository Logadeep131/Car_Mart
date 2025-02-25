CREATE TABLE cars (
    car_id SERIAL PRIMARY KEY,
    model VARCHAR(50) NOT NULL,
    company VARCHAR(50) NOT NULL,
    seater INT NOT NULL CHECK (seater > 0),
    fuelType VARCHAR(20) NOT NULL CHECK (fuelType IN ('Petrol','Diesel','Electric','Hybrid')),
    type VARCHAR(20) NOT NULL CHECK (type IN ('Hatchback','Sedan','SUV')),
    price DECIMAL(10,2) NOT NULL CHECK (price >= 0),
    is_sold BOOLEAN DEFAULT FALSE
);

drop table cars;
select * from cars;