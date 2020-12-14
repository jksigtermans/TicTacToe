-- Table: public.ttt_player

-- DROP TABLE public.ttt_player;

CREATE TABLE public.ttt_player
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    user_name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    password character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_player_id PRIMARY KEY (id),
    CONSTRAINT unique_user_name UNIQUE (user_name)
)

TABLESPACE pg_default;

ALTER TABLE public.ttt_player
    OWNER to postgres;