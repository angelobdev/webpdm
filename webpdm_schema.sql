-- Linguaggio
SELECT lanname
FROM pg_language
WHERE lanname = 'plpgsql';

-- COUPON
CREATE TABLE IF NOT EXISTS coupon
(
    codice_sconto    VARCHAR(255),
    sconto_applicato FLOAT,
    data_scadenza    DATE,
    prezzo_minimo    FLOAT,
    CONSTRAINT check_prezzo_minimo CHECK (prezzo_minimo > 0),
    PRIMARY KEY (codice_sconto)
);

-- RUOLI
CREATE TABLE IF NOT EXISTS ruoli
(
    id   SERIAL,
    nome VARCHAR(32),
    PRIMARY KEY (id)
);

INSERT INTO ruoli(id, nome)
VALUES (DEFAULT, 'ROLE_ADMIN');
INSERT INTO ruoli(id, nome)
VALUES (DEFAULT, 'ROLE_USER');

-- UTENTI
CREATE TABLE IF NOT EXISTS utenti
(
    id            SERIAL,
    -- Dati aziendali
    piva          VARCHAR(255),
    nome          VARCHAR(255),
    sede          VARCHAR(255),
    -- Info accesso
    username      VARCHAR(255),
    password      VARCHAR(255),
    ruolo_id      INT,

    numero_ordini INT,

    PRIMARY KEY (id),
    FOREIGN KEY (ruolo_id) REFERENCES ruoli (id)
);

-- SPEDIZIONE
CREATE TABLE IF NOT EXISTS spedizioni
(
    id              SERIAL,
    data_spedizione DATE,
    data_consegna   DATE,
    destinazione    VARCHAR(255),
    stato           VARCHAR(255),
    PRIMARY KEY (id)
);

-- VENDITE
CREATE TABLE IF NOT EXISTS vendite
(
    id            SERIAL,
    data          DATE,
    totale        FLOAT,
    codice_coupon VARCHAR(255),
    FOREIGN KEY (codice_coupon) REFERENCES coupon (codice_sconto),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS vendite_dettaglio
(
    id         SERIAL,
    vendita_id INT,
    FOREIGN KEY (vendita_id) REFERENCES vendite (id),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS vendite_ingrosso
(
    id             SERIAL,
    vendita_id     INT,
    utente_id      INT,
    spedizione_id  INT,
    numero_fattura INT,
    FOREIGN KEY (vendita_id) REFERENCES vendite (id),
    FOREIGN KEY (utente_id) REFERENCES utenti (id),
    FOREIGN KEY (spedizione_id) REFERENCES spedizioni (id),
    PRIMARY KEY (id)
);

-- PRODOTTI
CREATE TABLE IF NOT EXISTS prodotti
(
    id          SERIAL,
    nome        VARCHAR(255),
    prezzo_kg   FLOAT,
    data_arrivo DATE,
    quantita    INT,
    descrizione VARCHAR(512),
    immagine    VARCHAR(1024),
    PRIMARY KEY (id)
);

-- VINCOLO (prezzo_kg prodotti >= 0)
CREATE OR REPLACE FUNCTION elimina_prodotto()
    RETURNS TRIGGER AS
$$
BEGIN
    IF (NEW.prezzo_kg < 0) THEN
        DELETE
        FROM prodotti
        WHERE prodotti.id = NEW.id;

    END IF;

    RETURN NEW;

END;

$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS check_prezzo_kg ON prodotti;

CREATE TRIGGER check_prezzo_kg
    AFTER
        INSERT
    ON prodotti
    FOR EACH ROW
EXECUTE FUNCTION elimina_prodotto();

-- ASSOCIAZIONE vendita - prodotto
CREATE TABLE IF NOT EXISTS acquisti
(
    id          SERIAL,
    vendita_id  INT,
    prodotto_id INT,
    quantita    INT,
    descrizione VARCHAR(512),
    immagine    VARCHAR(1024),
    FOREIGN KEY (vendita_id) REFERENCES vendite (id),
    FOREIGN KEY (prodotto_id) REFERENCES prodotti (id),
    PRIMARY KEY (id)
);

-- VINCOLO
CREATE
    OR REPLACE FUNCTION elimina_acquisto() RETURNS TRIGGER AS
$$
BEGIN
    DECLARE
        prodotto_quantita INT;

    BEGIN
        prodotto_quantita := (SELECT quantita
                              FROM prodotti
                              WHERE id = NEW.prodotto_id);

        IF prodotto_quantita <= 0 THEN
            DELETE
            FROM acquisti
            WHERE id = NEW.id;

        END IF;

    END;

    RETURN NEW;

END;

$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS check_quantita_prodotto ON acquisti;

CREATE TRIGGER check_quantita_prodotto
    BEFORE
        INSERT
    ON acquisti
    FOR EACH ROW
EXECUTE FUNCTION elimina_acquisto();

-- RIMBORSO
CREATE TABLE IF NOT EXISTS rimborsi
(
    id             SERIAL,
    data_emissione DATE,
    PRIMARY KEY (id)
);

-- RECLAMI
CREATE TABLE IF NOT EXISTS reclami
(
    id           SERIAL,
    email        VARCHAR(255),
    segnalazione VARCHAR(255),
    data         DATE,
    stato        VARCHAR(255),
    vendita_id   INT NOT NULL,
    rimborso_id  INT,
    FOREIGN KEY (vendita_id) REFERENCES vendite (id),
    FOREIGN KEY (rimborso_id) REFERENCES rimborsi (id),
    PRIMARY KEY (id)
);

-- CATEGORIA ORDINE
CREATE TABLE IF NOT EXISTS categorie_ordine
(
    id   SERIAL,
    nome VARCHAR(255),
    PRIMARY KEY (id)
);

-- FORNITORE
CREATE TABLE IF NOT EXISTS fornitori
(
    piva          VARCHAR(255),
    nome          VARCHAR(255),
    sede          VARCHAR(255),
    numero_ordini INT,
    PRIMARY KEY (piva)
);

-- ORDINE FORNITORE
CREATE TABLE IF NOT EXISTS ordini_fornitori
(
    id             SERIAL,
    descrizione    VARCHAR(512),
    data           DATE,
    stato          VARCHAR(32),
    totale         DOUBLE PRECISION,
    fornitore_piva VARCHAR(255),
    FOREIGN KEY (fornitore_piva) REFERENCES fornitori (piva),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS categorie_fornitori
(
    id             SERIAL,
    fornitore_piva VARCHAR(255),
    categoria_id   INT,
    FOREIGN KEY (fornitore_piva) REFERENCES fornitori (piva),
    FOREIGN KEY (categoria_id) REFERENCES categorie_ordine (id),
    PRIMARY KEY (id)
);

-- APPROVVIGIONAMENTI
CREATE TABLE IF NOT EXISTS approvvigionamenti
(
    id        SERIAL,
    nome      VARCHAR(255),
    categoria INT,
    quantita  INT,
    CONSTRAINT check_quantita_appr CHECK (quantita > 0),
    FOREIGN KEY (categoria) REFERENCES categorie_ordine (id),
    PRIMARY KEY (id)
);

-- DIPENDENTI
CREATE TABLE IF NOT EXISTS dipendenti
(
    codice_fiscale VARCHAR(255),
    mansioni       VARCHAR(512),
    PRIMARY KEY (codice_fiscale)
);

-- BUSTE PAGA
CREATE TABLE IF NOT EXISTS buste_paga
(
    id             SERIAL,
    totale         FLOAT,
    data_emissione DATE,
    dipendente_cf  VARCHAR(255),
    FOREIGN KEY (dipendente_cf) REFERENCES dipendenti (codice_fiscale),
    PRIMARY KEY (id),
    CONSTRAINT check_bp_totale CHECK (totale > 0)
);