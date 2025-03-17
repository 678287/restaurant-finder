

-- Sletter hele sulamitten og oppretter på nytt.
DROP SCHEMA IF EXISTS user_schema CASCADE;
CREATE SCHEMA user_schema;
SET search_path TO user_schema;
    
-- 


CREATE TABLE users
(
    phone CHAR(8) PRIMARY KEY,
    passwordhash VARCHAR(100) NOT NULL,
    salt VARCHAR(100) NOT NULL
);

-- 




    