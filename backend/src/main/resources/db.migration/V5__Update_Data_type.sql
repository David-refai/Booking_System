-- Update start_date to date
ALTER TABLE trip ALTER COLUMN start_date SET DATA TYPE DATE
    USING start_date::DATE; -- This assumes your start_date column contains valid date values.

-- Update end_date to date type and ensure it is greater than start_date
ALTER TABLE trip ALTER COLUMN end_date SET DATA TYPE DATE
    USING end_date::DATE; -- This assumes your end_date column contains valid date values.

-- Add new column created_at with datetime type
ALTER TABLE trip alter COLUMN created_at SET DATA TYPE TIMESTAMP
    USING created_at::TIMESTAMP; -- This assumes your created_at column contains valid timestamp values.

-- Add CHECK constraint to ensure end_date > start_date
ALTER TABLE trip ADD CONSTRAINT valid_date_range CHECK (end_date > start_date);

ALTER TABLE trip ALTER COLUMN price SET DATA TYPE NUMERIC(10,2) USING price::NUMERIC(10,2);


