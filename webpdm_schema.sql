-- Fornitori --
CREATE TABLE fornitori
(
    id             serial PRIMARY KEY,
    partita_iva    varchar(11)  NOT NULL,
    nome_azienda   varchar(255) NOT NULL,
    sede_aziendale varchar(255) NOT NULL
);

-- Ordini effettuati presso fornitori --
CREATE TABLE ordini_fornitori
(
    id             serial PRIMARY KEY,
    descrizione    varchar(255)   NOT NULL,
    data           timestamp      NOT NULL,
    stato_ordine   varchar(255)   NOT NULL,
    importo_totale decimal(10, 2) NOT NULL, -- VINCOLO: Maggiore di 0
    fornitore_id   int            NOT NULL,
    CONSTRAINT ordini_fornitori_fornitore_id_foreign_key FOREIGN KEY (fornitore_id) REFERENCES fornitori (id)
);

-- Approvvigionamenti (Prodotti utilizzati in azienda) --
CREATE TABLE approvvigionamenti
(
    id            serial PRIMARY KEY,
    nome_prodotto varchar(255) NOT NULL,
    quantita      int          NOT NULL -- VINCOLO: Maggiore di 0
);

-- Join Table APPROVVIGIONAMENTI / ORDINI FORNITORI --
-- Lista di approvvigionamenti forniti nell'ordine --
CREATE TABLE approvvigionamenti_ordini_fornitori
(
    approvvigionamento_id int NOT NULL,
    ordine_fornitore_id   int NOT NULL,
    quantita              int NOT NULL,
    CONSTRAINT approvv_ordini_fornitori_approvvigionamento_id_foreign_key FOREIGN KEY (approvvigionamento_id) REFERENCES approvvigionamenti (id),
    CONSTRAINT approvv_ordini_fornitori_ordine_fornitore_id_foreign_key FOREIGN KEY (ordine_fornitore_id) REFERENCES ordini_fornitori (id)
);

-- Dipendenti aziendali --
CREATE TABLE dipendenti
(
    id             serial PRIMARY KEY,
    codice_fiscale varchar(16)   NOT NULL UNIQUE,
    mansioni       varchar(1024) NOT NULL
);

-- Buste paga dei dipendenti --
CREATE TABLE buste_paga
(
    id             serial PRIMARY KEY,
    importo_emesso decimal(10, 2) NOT NULL, -- VINCOLO: Maggiore di 0
    data_emissione timestamp      NOT NULL,
    dipendente_id  int            NOT NULL,
    CONSTRAINT buste_paga_dipendente_id_foreign_key FOREIGN KEY (dipendente_id) REFERENCES dipendenti (id)
);

-- Ruoli degli utenti --
CREATE TABLE ruoli
(
    id    serial PRIMARY KEY,
    nome  varchar(255) NOT NULL UNIQUE,
    grado int          NOT NULL UNIQUE
);

-- Ruoli di default --
INSERT INTO ruoli (nome, grado)
VALUES ('ROLE_USER', 1);
INSERT INTO ruoli (nome, grado)
VALUES ('ROLE_ADMIN', 999);

-- Utenti (Clienti, Amministratori, ecc...) --
CREATE TABLE utenti
(
    id             serial PRIMARY KEY,
    partita_iva    varchar(11)  NOT NULL,
    nome_azienda   varchar(255) NOT NULL,
    sede_aziendale varchar(255) NOT NULL,
    email          varchar(255) NOT NULL UNIQUE,
    username       varchar(255) NOT NULL UNIQUE,
    password       varchar(255) NOT NULL,
    avatar         varchar(255) DEFAULT 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_640.png',
    ruolo_id       int          NOT NULL,
    CONSTRAINT utenti_ruolo_id_foreign_key FOREIGN KEY (ruolo_id) REFERENCES ruoli (id)
);

-- Prodotti (in magazzino) --
CREATE TABLE prodotti
(
    id                serial PRIMARY KEY,
    nome              varchar(255)   NOT NULL,
    descrizione       varchar(255)   NOT NULL,
    prezzo_al_kg      decimal(10, 2) NOT NULL, -- VINCOLO: Maggiore di 0
    data_arrivo       timestamp      NOT NULL,
    quantita_stoccata int            NOT NULL, -- VINCOLO: Maggiore uguale a 0
    immagine          varchar(255)   NOT NULL
);

-- Carrelli creati dagli utenti --
CREATE TABLE carrelli
(
    id         serial PRIMARY KEY,
    acquistato boolean NOT NULL,
    utente_id  int     NOT NULL,
    CONSTRAINT carrelli_utente_id_foreign_key FOREIGN KEY (utente_id) REFERENCES utenti (id)
);

--- Join Table CARRELLI / PRODOTTI ---
-- Prodotti inseriti all'interno dei carrelli --
CREATE TABLE carrelli_prodotti
(
    carrello_id int NOT NULL,
    prodotto_id int NOT NULL,
    quantita    int NOT NULL, -- VINCOLO: Maggiore di 0
    CONSTRAINT carrelli_prodotti_carrello_id_foreign_key FOREIGN KEY (carrello_id) REFERENCES carrelli (id),
    CONSTRAINT carrelli_prodotti_prodotto_id_foreign_key FOREIGN KEY (prodotto_id) REFERENCES prodotti (id)
);

-- Vendite (al dettaglio) --
CREATE TABLE vendite
(
    id             serial PRIMARY KEY,
    data           timestamp      NOT NULL,
    importo_totale decimal(10, 2) NOT NULL -- VINCOLO: Maggiore uguale di 0
);

--- Join Table VENDITE / PRODOTTI ---
-- Prodotti venduti nelle vendite --
CREATE TABLE vendite_prodotti
(
    vendita_id  int NOT NULL,
    prodotto_id int NOT NULL,
    quantita    int NOT NULL, -- VINCOLO: Maggiore di 0
    CONSTRAINT vendite_prodotti_vendita_id_foreign_key FOREIGN KEY (vendita_id) REFERENCES vendite (id),
    CONSTRAINT vendite_prodotti_prodotto_id_foreign_key FOREIGN KEY (prodotto_id) REFERENCES prodotti (id)
);

-- Codici sconto (coupon) --
CREATE TABLE coupon
(
    id               serial PRIMARY KEY,
    codice_sconto    varchar(255)   NOT NULL,
    sconto_applicato decimal(10, 2) NOT NULL, -- VINCOLO: Maggiore di 0
    data_scadenza    timestamp      NOT NULL,
    prezzo_minimo    decimal(10, 2) NOT NULL  -- VINCOLO: Maggiore o uguale a 0
);

-- Ordini (vendite all'ingrosso)
CREATE TABLE ordini
(
    id             serial PRIMARY KEY,
    data           timestamp      NOT NULL,
    importo_totale decimal(10, 2) NOT NULL, -- VINCOLO: Maggiore uguale di 0
    stato_ordine   varchar(255)   NOT NULL,
    carrello_id    int            NOT NULL,
    coupon_id      int,
    CONSTRAINT ordini_carrello_id_foreign_key FOREIGN KEY (carrello_id) REFERENCES carrelli (id),
    CONSTRAINT ordini_coupon_id_foreign_key FOREIGN KEY (coupon_id) REFERENCES coupon (id)
);

-- Spedizioni
CREATE TABLE spedizioni
(
    id               serial PRIMARY KEY,
    data_spedizione  timestamp    NOT NULL,
    data_consegna    timestamp    NOT NULL, -- VINCOLO: Maggiore di data spedizione
    destinazione     varchar(255) NOT NULL,
    stato_spedizione varchar(255) NOT NULL,
    ordine_id        int          NOT NULL,
    CONSTRAINT spedizioni_ordine_id_foreign_key FOREIGN KEY (ordine_id) REFERENCES ordini (id)
);

-- Reclami
CREATE TABLE reclami
(
    id        serial PRIMARY KEY,
    messaggio varchar(1024) NOT NULL,
    data      timestamp     NOT NULL,
    stato     varchar(255)  NOT NULL,
    ordine_id int           NOT NULL,
    CONSTRAINT reclami_ordine_id_foreign_key FOREIGN KEY (ordine_id) REFERENCES ordini (id)
);

-- Rimborsi
CREATE TABLE rimborsi
(
    id             serial PRIMARY KEY,
    data_emissione timestamp NOT NULL,
    reclamo_id     int       NOT NULL,
    CONSTRAINT rimborsi_reclamo_id_foreign_key FOREIGN KEY (reclamo_id) REFERENCES reclami (id)
);

--- VINCOLI ---

-- Totale ordine deve essere >= 0
CREATE OR REPLACE FUNCTION imposta_importo_totale_ordini_fornitori()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.importo_totale < 0 THEN
        NEW.importo_totale := 0;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_imposta_importo_totale_ordini_fornitori
    AFTER INSERT OR UPDATE
    ON ordini_fornitori
    FOR EACH ROW
EXECUTE FUNCTION imposta_importo_totale_ordini_fornitori();

-- Quantita approvvigionamento deve essere >= 0
CREATE OR REPLACE FUNCTION imposta_quantita_approvvigionamenti()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.quantita < 0 THEN
        NEW.quantita := 0;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_imposta_quantita_approvvigionamenti
    AFTER INSERT OR UPDATE
    ON approvvigionamenti
    FOR EACH ROW
EXECUTE FUNCTION imposta_quantita_approvvigionamenti();

-- Importo emesso nelle buste paga deve essere >= 0
CREATE OR REPLACE FUNCTION imposta_importo_emesso_buste_paga()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.importo_emesso < 0 THEN
        NEW.importo_emesso := 0;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_imposta_importo_emesso_buste_paga
    AFTER INSERT OR UPDATE
    ON buste_paga
    FOR EACH ROW
EXECUTE FUNCTION imposta_importo_emesso_buste_paga();

-- Prezzo al kg dei prodotti deve essere >= 0
CREATE OR REPLACE FUNCTION imposta_prezzo_al_kg_prodotti()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.prezzo_al_kg < 0 THEN
        NEW.prezzo_al_kg := 0;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_imposta_prezzo_al_kg_prodotti
    AFTER INSERT OR UPDATE
    ON prodotti
    FOR EACH ROW
EXECUTE FUNCTION imposta_prezzo_al_kg_prodotti();

-- Quantita stoccata dei prodotti deve essere >= 0
CREATE OR REPLACE FUNCTION imposta_quantita_in_magazzino_prodotti()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.quantita_stoccata < 0 THEN
        NEW.quantita_stoccata := 0;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_imposta_quantita_in_magazzino_prodotti
    AFTER INSERT OR UPDATE
    ON prodotti
    FOR EACH ROW
EXECUTE FUNCTION imposta_quantita_in_magazzino_prodotti();

-- Quantita dei prodotti nel carrello deve essere >= 0
CREATE OR REPLACE FUNCTION imposta_quantita_carrelli_prodotti()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.quantita < 0 THEN
        NEW.quantita := 0;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_imposta_quantita_carrelli_prodotti
    AFTER INSERT OR UPDATE
    ON carrelli_prodotti
    FOR EACH ROW
EXECUTE FUNCTION imposta_quantita_carrelli_prodotti();

-- Importo totale delle vendite deve essere >= 0
CREATE OR REPLACE FUNCTION imposta_importo_totale_vendite()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.importo_totale < 0 THEN
        NEW.importo_totale := 0;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_imposta_importo_totale_vendite
    AFTER INSERT OR UPDATE
    ON vendite
    FOR EACH ROW
EXECUTE FUNCTION imposta_importo_totale_vendite();

-- Importo totale degli ordini deve essere >= 0
CREATE OR REPLACE FUNCTION imposta_importo_totale_ordini()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.importo_totale < 0 THEN
        NEW.importo_totale := 0;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_imposta_importo_totale_ordini
    AFTER INSERT OR UPDATE
    ON ordini
    FOR EACH ROW
EXECUTE FUNCTION imposta_importo_totale_ordini();

-- Sconto applicato dai coupon deve essere >= 0
CREATE OR REPLACE FUNCTION imposta_sconto_applicato_coupon()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.sconto_applicato < 0 THEN
        NEW.sconto_applicato := 0;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_imposta_sconto_applicato_coupon
    AFTER INSERT OR UPDATE
    ON coupon
    FOR EACH ROW
EXECUTE FUNCTION imposta_sconto_applicato_coupon();

-- Prezzo minimo dei coupon deve essere >= 0
CREATE OR REPLACE FUNCTION imposta_prezzo_minimo_coupon()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.prezzo_minimo < 0 THEN
        NEW.prezzo_minimo := 0;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_imposta_prezzo_minimo_coupon
    AFTER INSERT OR UPDATE
    ON coupon
    FOR EACH ROW
EXECUTE FUNCTION imposta_prezzo_minimo_coupon();

-- Quando viene fatto un ordine la quantita dei prodotti acquistati deve diminuire (in magazzino)
CREATE OR REPLACE FUNCTION update_quantita_in_magazzino_on_ordine()
    RETURNS TRIGGER AS
$$
BEGIN
    UPDATE prodotti
    SET quantita_stoccata = quantita_stoccata -
                            (SELECT quantita FROM carrelli_prodotti WHERE carrello_id = NEW.carrello_id);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_quantita_in_magazzino_on_ordine
    AFTER INSERT
    ON ordini
    FOR EACH ROW
EXECUTE FUNCTION update_quantita_in_magazzino_on_ordine();

-- Quando viene registrata una vendita la quantita dei prodotti acquistati deve diminuire (in magazzino)
CREATE OR REPLACE FUNCTION update_quantita_in_magazzino_on_vendita()
    RETURNS TRIGGER AS
$$
BEGIN
    UPDATE prodotti
    SET quantita_stoccata = quantita_stoccata - (SELECT quantita FROM vendite_prodotti WHERE vendita_id = NEW.id);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_quantita_in_magazzino_on_vendita
    AFTER INSERT
    ON vendite
    FOR EACH ROW
EXECUTE FUNCTION update_quantita_in_magazzino_on_vendita();

-- Quando viene fatto un ordine il flag "acquistato" del carrello deve essere impostato su true
CREATE OR REPLACE FUNCTION set_carrello_acquistato_to_true()
    RETURNS TRIGGER AS
$$
BEGIN
    UPDATE carrelli
    SET acquistato = true
    WHERE id = NEW.carrello_id;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_set_carrello_acquistato_to_true
    AFTER INSERT
    ON ordini
    FOR EACH ROW
EXECUTE FUNCTION set_carrello_acquistato_to_true();

-- La data di consegna delle spedizioni deve essere successiva alla data di spedizione
ALTER TABLE spedizioni
    ADD CONSTRAINT data_consegna_maggiore_di_data_spedizione
        CHECK (data_consegna > data_spedizione);