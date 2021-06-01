DROP TABLE IF EXISTS listings CASCADE;
DROP TABLE IF EXISTS cars CASCADE;
DROP TABLE IF EXISTS models CASCADE;
DROP TABLE IF EXISTS makes CASCADE;
DROP TABLE IF EXISTS test CASCADE;

CREATE TABLE public.makes (
                              id SERIAL PRIMARY KEY,
                              name character varying(255)
);

CREATE TABLE public.models (
                               id SERIAL PRIMARY KEY,
                               name character varying(255),
                               year integer NOT NULL,
                               make_id integer NOT NULL,
                               CONSTRAINT fk_make_id FOREIGN KEY (make_id) REFERENCES public.makes(id)
);


CREATE TABLE public.cars (
                             id SERIAL PRIMARY KEY,
                             make_id integer NOT NULL,
                             model_id integer NOT NULL,
                             CONSTRAINT fk_make_id FOREIGN KEY (make_id) REFERENCES public.makes(id),
                             CONSTRAINT fk_model_id FOREIGN KEY (make_id) REFERENCES public.makes(id)
);

CREATE TABLE public.listings (
                                 id SERIAL PRIMARY KEY,
                                 created_on timestamp without time zone not null,
                                 description character varying(255) not null,
                                 km integer  not null,
                                 price real  not null,
                                 seller character varying(255) not null,
                                 status integer  not null,
                                 title character varying(255) not null,
                                 car integer not null,
                                 CONSTRAINT fk_car FOREIGN KEY (car) REFERENCES public.cars(id)
);