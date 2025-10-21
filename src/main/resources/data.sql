INSERT INTO railway_carriages (name, capacity, base_price) VALUES
    ('Coupe', 20, 500.00),
    ('Seated', 40, 300.00)
ON CONFLICT (name) DO NOTHING;
