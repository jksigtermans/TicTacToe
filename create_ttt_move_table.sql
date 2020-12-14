-- Table: public.ttt_move

-- DROP TABLE public.ttt_move;

CREATE TABLE public.ttt_move
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    game_id bigint NOT NULL,
    player_id bigint NOT NULL,
    "time" timestamp without time zone NOT NULL,
    field smallint NOT NULL,
    CONSTRAINT pk_move_id PRIMARY KEY (id),
    CONSTRAINT unique_game_field UNIQUE (field)
        INCLUDE(game_id),
    CONSTRAINT fk_move_game FOREIGN KEY (game_id)
        REFERENCES public.ttt_game (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_move_player FOREIGN KEY (player_id)
        REFERENCES public.ttt_player (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.ttt_move
    OWNER to postgres;
-- Index: fki_fk_move_game

-- DROP INDEX public.fki_fk_move_game;

CREATE INDEX fki_fk_move_game
    ON public.ttt_move USING btree
    (game_id ASC NULLS LAST)
    TABLESPACE pg_default;
-- Index: fki_fk_move_player

-- DROP INDEX public.fki_fk_move_player;

CREATE INDEX fki_fk_move_player
    ON public.ttt_move USING btree
    (player_id ASC NULLS LAST)
    TABLESPACE pg_default;