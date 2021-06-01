DROP TABLE IF EXISTS listings CASCADE;
DROP TABLE IF EXISTS cars CASCADE;
DROP TABLE IF EXISTS models CASCADE;
DROP TABLE IF EXISTS makes CASCADE;
DROP TABLE IF EXISTS test CASCADE;

CREATE TABLE public.makes (
                              id integer NOT NULL,
                              name character varying(255)
);

CREATE TABLE public.models (
                               id integer NOT NULL,
                               name character varying(255),
                               year integer NOT NULL,
                               make_id integer
);


CREATE TABLE public.cars (
                             id integer NOT NULL,
                             make_id integer,
                             model_id integer
);

CREATE TABLE public.listings (
                                 id integer NOT NULL,
                                 created_on timestamp without time zone,
                                 description character varying(255),
                                 km integer,
                                 price real,
                                 seller character varying(255),
                                 status integer,
                                 title character varying(255),
                                 car integer
);

ALTER TABLE ONLY public.makes
    ADD CONSTRAINT makes_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.makes
    ADD CONSTRAINT uk_9pxjtve5c50fx3aq64dhb4f8m UNIQUE (name);

ALTER TABLE ONLY public.models
    ADD CONSTRAINT models_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.models
    ADD CONSTRAINT fk8fm3p80h4dh513qubnxd2qefl FOREIGN KEY (make_id) REFERENCES public.makes(id);

ALTER TABLE ONLY public.cars
    ADD CONSTRAINT cars_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.cars
    ADD CONSTRAINT fk1r9ljf5fdgjbm7jr4ljbagfso FOREIGN KEY (make_id) REFERENCES public.makes(id);
ALTER TABLE ONLY public.cars
    ADD CONSTRAINT fkrwe6b2vd08hb4gnst223xsna4 FOREIGN KEY (model_id) REFERENCES public.models(id);

ALTER TABLE ONLY public.listings
    ADD CONSTRAINT listings_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.listings
    ADD CONSTRAINT fkdtu09li3gnxfovtbc8gcy0ch1 FOREIGN KEY (car) REFERENCES public.cars(id);