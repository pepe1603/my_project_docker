-- Crear usuario si no existe
DO $$ 
BEGIN
    IF NOT EXISTS (SELECT FROM pg_catalog.pg_user WHERE usename = 'usr_admin') THEN
        CREATE USER usr_admin WITH PASSWORD 'usr_admin';
    END IF;
END $$;

-- Crear base de datos si no existe
DO $$ 
BEGIN
    IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'db_agenda') THEN
        CREATE DATABASE db_agenda OWNER usr_admin;
    END IF;
END $$;

-- Conceder permisos específicos al usuario en la base de datos
GRANT ALL PRIVILEGES ON DATABASE db_agenda TO usr_admin;


--CREATE USER usr_admin WITH PASSWORD 'usr_admin';
--CREATE DATABASE db_agenda OWNER usr_admin;