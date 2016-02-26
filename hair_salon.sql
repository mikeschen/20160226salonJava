--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.0
-- Dumped by pg_dump version 9.5.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: clients; Type: TABLE; Schema: public; Owner: reviveit
--

CREATE TABLE clients (
    id integer NOT NULL,
    clientname character varying,
    stylistid integer
);


ALTER TABLE clients OWNER TO reviveit;

--
-- Name: client_id_seq; Type: SEQUENCE; Schema: public; Owner: reviveit
--

CREATE SEQUENCE client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE client_id_seq OWNER TO reviveit;

--
-- Name: client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: reviveit
--

ALTER SEQUENCE client_id_seq OWNED BY clients.id;


--
-- Name: stylists; Type: TABLE; Schema: public; Owner: reviveit
--

CREATE TABLE stylists (
    stylist_id integer NOT NULL,
    stylistname character varying
);


ALTER TABLE stylists OWNER TO reviveit;

--
-- Name: stylist_id_seq; Type: SEQUENCE; Schema: public; Owner: reviveit
--

CREATE SEQUENCE stylist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE stylist_id_seq OWNER TO reviveit;

--
-- Name: stylist_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: reviveit
--

ALTER SEQUENCE stylist_id_seq OWNED BY stylists.stylist_id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: reviveit
--

ALTER TABLE ONLY clients ALTER COLUMN id SET DEFAULT nextval('client_id_seq'::regclass);


--
-- Name: stylist_id; Type: DEFAULT; Schema: public; Owner: reviveit
--

ALTER TABLE ONLY stylists ALTER COLUMN stylist_id SET DEFAULT nextval('stylist_id_seq'::regclass);


--
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: reviveit
--

SELECT pg_catalog.setval('client_id_seq', 10, true);


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: reviveit
--

COPY clients (id, clientname, stylistid) FROM stdin;
\.


--
-- Name: stylist_id_seq; Type: SEQUENCE SET; Schema: public; Owner: reviveit
--

SELECT pg_catalog.setval('stylist_id_seq', 10, true);


--
-- Data for Name: stylists; Type: TABLE DATA; Schema: public; Owner: reviveit
--

COPY stylists (stylist_id, stylistname) FROM stdin;
\.


--
-- Name: client_pkey; Type: CONSTRAINT; Schema: public; Owner: reviveit
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);


--
-- Name: stylist_pkey; Type: CONSTRAINT; Schema: public; Owner: reviveit
--

ALTER TABLE ONLY stylists
    ADD CONSTRAINT stylist_pkey PRIMARY KEY (stylist_id);


--
-- Name: public; Type: ACL; Schema: -; Owner: reviveit
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM reviveit;
GRANT ALL ON SCHEMA public TO reviveit;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

