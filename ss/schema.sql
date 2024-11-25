-- Create mainfamily table if not exists
CREATE TABLE IF NOT EXISTS mainfamily (
                                          id SERIAL PRIMARY KEY,
                                          name VARCHAR(255) NOT NULL
    );

-- Create family table if not exists
CREATE TABLE IF NOT EXISTS family (
                                      id SERIAL PRIMARY KEY,
                                      name VARCHAR(255) NOT NULL,
    main_family_id INT NOT NULL,
    CONSTRAINT fk_mainfamily FOREIGN KEY (main_family_id) REFERENCES mainfamily (id) ON DELETE CASCADE
    );

-- Create familymember table if not exists
CREATE TABLE IF NOT EXISTS familymember (
                                            id SERIAL PRIMARY KEY,
                                            name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    family_id INT NOT NULL,
    CONSTRAINT fk_family FOREIGN KEY (family_id) REFERENCES family (id) ON DELETE CASCADE
    );

-- Create secretsantahistory table if not exists
CREATE TABLE IF NOT EXISTS secretsantahistory (
                                                  id SERIAL PRIMARY KEY,
                                                  giver_id INT NOT NULL,
                                                  receiver_id INT NOT NULL,
                                                  main_family_id INT NOT NULL,
                                                  year INT NOT NULL,
                                                  CONSTRAINT fk_giver FOREIGN KEY (giver_id) REFERENCES familymember (id) ON DELETE CASCADE,
    CONSTRAINT fk_receiver FOREIGN KEY (receiver_id) REFERENCES familymember (id) ON DELETE CASCADE,
    CONSTRAINT fk_mainfamily_history FOREIGN KEY (main_family_id) REFERENCES mainfamily (id) ON DELETE CASCADE
    );

-- Insert initial data only if not exists

-- Insert main family
INSERT INTO mainfamily (name)
SELECT 'La Colmena'
    WHERE NOT EXISTS (SELECT 1 FROM mainfamily WHERE name = 'La Colmena');

-- Insert families
INSERT INTO family (name, main_family_id)
SELECT 'Kokos', 1
    WHERE NOT EXISTS (SELECT 1 FROM family WHERE name = 'Kokos' AND main_family_id = 1);

INSERT INTO family (name, main_family_id)
SELECT 'Ailos', 1
    WHERE NOT EXISTS (SELECT 1 FROM family WHERE name = 'Ailos' AND main_family_id = 1);

INSERT INTO family (name, main_family_id)
SELECT 'Papi', 1
    WHERE NOT EXISTS (SELECT 1 FROM family WHERE name = 'Papi' AND main_family_id = 1);

-- Insert family members for Kokos family
INSERT INTO familymember (name, email, family_id)
SELECT 'Nube', NULL, 1
    WHERE NOT EXISTS (SELECT 1 FROM familymember WHERE name = 'Nube' AND family_id = 1);

INSERT INTO familymember (name, email, family_id)
SELECT 'Rio', NULL, 1
    WHERE NOT EXISTS (SELECT 1 FROM familymember WHERE name = 'Rio' AND family_id = 1);

INSERT INTO familymember (name, email, family_id)
SELECT 'Koko', NULL, 2
    WHERE NOT EXISTS (SELECT 1 FROM familymember WHERE name = 'Koko' AND family_id = 2);

-- Insert family members for Ailos family
INSERT INTO familymember (name, email, family_id)
SELECT 'Aila', NULL, 2
    WHERE NOT EXISTS (SELECT 1 FROM familymember WHERE name = 'Aila' AND family_id = 2);

INSERT INTO familymember (name, email, family_id)
SELECT 'Cheeto', NULL, 2
    WHERE NOT EXISTS (SELECT 1 FROM familymember WHERE name = 'Cheeto' AND family_id = 2);

-- Insert family member for Papi family
INSERT INTO familymember (name, email, family_id)
SELECT 'Papi', NULL, 3
    WHERE NOT EXISTS (SELECT 1 FROM familymember WHERE name = 'Papi' AND family_id = 3);
