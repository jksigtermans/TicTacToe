-- Table: public.ttt_score

-- DROP TABLE public.ttt_score;

CREATE TABLE public.ttt_score
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    player_id bigint NOT NULL,
    score bigint NOT NULL,
    CONSTRAINT pk_score_id PRIMARY KEY (id),
    CONSTRAINT fk_score_player FOREIGN KEY (player_id)
        REFERENCES public.ttt_player (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.ttt_score
    OWNER to postgres;
-- Index: fki_fk_score_player

-- DROP INDEX public.fki_fk_score_player;

CREATE INDEX fki_fk_score_player
    ON public.ttt_score USING btree
    (player_id ASC NULLS LAST)
    TABLESPACE pg_default;