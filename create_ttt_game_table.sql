-- Table: public.ttt_game

-- DROP TABLE public.ttt_game;

CREATE TABLE public.ttt_game
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    main_player_id bigint NOT NULL,
    guest_player_id bigint,
    status character varying(50) COLLATE pg_catalog."default" NOT NULL,
    turn_id bigint,
    CONSTRAINT pk_game_id PRIMARY KEY (id),
    CONSTRAINT fk_guest_player FOREIGN KEY (guest_player_id)
        REFERENCES public.ttt_player (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_main_player FOREIGN KEY (main_player_id)
        REFERENCES public.ttt_player (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_turn_player FOREIGN KEY (turn_id)
        REFERENCES public.ttt_player (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.ttt_game
    OWNER to postgres;
-- Index: fki_fk_guest_player

-- DROP INDEX public.fki_fk_guest_player;

CREATE INDEX fki_fk_guest_player
    ON public.ttt_game USING btree
    (guest_player_id ASC NULLS LAST)
    TABLESPACE pg_default;
-- Index: fki_fk_main_player

-- DROP INDEX public.fki_fk_main_player;

CREATE INDEX fki_fk_main_player
    ON public.ttt_game USING btree
    (main_player_id ASC NULLS LAST)
    TABLESPACE pg_default;
-- Index: fki_fk_turn_player

-- DROP INDEX public.fki_fk_turn_player;

CREATE INDEX fki_fk_turn_player
    ON public.ttt_game USING btree
    (turn_id ASC NULLS LAST)
    TABLESPACE pg_default;