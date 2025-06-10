-- Piatto 
create table "PIATTO" (
	"ID"  integer  not null,
	"NOME"  varchar(50),
  primary key ("ID")
);


-- Ristorante
create table "RISTORANTE" (
   "ID"  integer  not null,
   "NOME"  varchar(50),
   "CITTA"  varchar(50),
  primary key ("ID")
);

-- Piatto_ristorante
create table "PIATTO_RISTORANTE" (
   "ID_PIATTO"  integer not null,
   "ID_RISTORANTE"  integer not null,
  primary key ("ID_PIATTO", "ID_RISTORANTE")
);

alter table "PIATTO_RISTORANTE"   add constraint FK_PIATTO_RISTORANTE_PIATTO foreign key ("ID_PIATTO") references "PIATTO" ("ID");
alter table "PIATTO_RISTORANTE"   add constraint FK_PIATTO_RISTORANTE_RISTORANTE foreign key ("ID_RISTORANTE") references "RISTORANTE" ("ID");

