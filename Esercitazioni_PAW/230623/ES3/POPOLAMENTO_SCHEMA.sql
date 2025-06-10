-- piatto (id, nome)
INSERT INTO piatto (ID, NOME) VALUES (1, 'pizza');
INSERT INTO piatto (ID, NOME) VALUES (2, 'kebab');
INSERT INTO piatto (ID, NOME) VALUES (3, 'anguria');
INSERT INTO piatto (ID, NOME) VALUES (4, 'cocco');
INSERT INTO piatto (ID, NOME) VALUES (5, 'passatelli');

-- ristorante (id, nome, citta)
INSERT INTO ristorante (ID, NOME, CITTA) VALUES (1, 'pizzeria', 'BO');
INSERT INTO ristorante (ID, NOME, CITTA) VALUES (2, 'fruttoleria', 'BO');
INSERT INTO ristorante (ID, NOME, CITTA) VALUES (3, 'piadineria', 'FE');
INSERT INTO ristorante (ID, NOME, CITTA) VALUES (4, 'rosticceria', 'FE');
INSERT INTO ristorante (ID, NOME, CITTA) VALUES (5, 'sesseria', 'NA');

--piatto_ristorante (piatto_id, ristorante_id)
INSERT INTO piatto_ristorante (id_piatto, id_ristorante) VALUES (1, 1);
INSERT INTO piatto_ristorante (id_piatto, id_ristorante) VALUES (1, 2);
INSERT INTO piatto_ristorante (id_piatto, id_ristorante) VALUES (1, 3);
INSERT INTO piatto_ristorante (id_piatto, id_ristorante) VALUES (2, 4);
INSERT INTO piatto_ristorante (id_piatto, id_ristorante) VALUES (2, 5);
