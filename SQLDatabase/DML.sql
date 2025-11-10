-- Create schema with the name public if it doesn't exist
CREATE SCHEMA IF NOT EXISTS public;

-- Set it to be the active schema
SET search_path TO students;

-- Drop the table 'students' if it already exists
DROP TABLE IF EXISTS students;

-- Create the table 'students' with the following columns:
CREATE TABLE public.students (
    student_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    enrollment_date DATE NOT NULL
);

-- Insert the following students into the table:
INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES
('John', 'Doe', 'john.doe@example.com', '2023-09-01'),
('Jane', 'Smith', 'jane.smith@example.com', '2023-09-01'),
('Jim', 'Beam', 'jim.beam@example.com', '2023-09-02');