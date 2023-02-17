SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 16462)
-- Name: client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.client (
    user_id integer NOT NULL,
    client_name character varying(50) NOT NULL,
    website character varying(300)
);


ALTER TABLE public.client OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16446)
-- Name: employee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employee (
    user_id integer NOT NULL,
    employee_name character varying(50) NOT NULL,
    employee_role character(1) NOT NULL,
    CONSTRAINT employee_employee_role CHECK ((employee_role = ANY (ARRAY['C'::bpchar, 'D'::bpchar, 'A'::bpchar])))
);


ALTER TABLE public.employee OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16452)
-- Name: employee_project_bridge; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employee_project_bridge (
    user_id integer NOT NULL,
    project_lead_id integer NOT NULL,
    project_id integer NOT NULL
);


ALTER TABLE public.employee_project_bridge OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16457)
-- Name: project; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.project (
    project_id integer NOT NULL,
    client_id integer NOT NULL,
    project_lead_id integer NOT NULL
);


ALTER TABLE public.project OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16491)
-- Name: task; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.task (
    project_id integer NOT NULL,
    task_id integer NOT NULL,
    description character varying(500) NOT NULL,
    deadline date NOT NULL,
    start_date date NOT NULL,
    expected_work_hours double precision NOT NULL,
    progress_in_work_hours double precision DEFAULT 0
);


ALTER TABLE public.task OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16473)
-- Name: time_off; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.time_off (
    time_off_id integer NOT NULL,
    employee_id integer NOT NULL,
    start_date date,
    end_date date,
    status character(1) NOT NULL,
    reason character varying(500) NOT NULL
);


ALTER TABLE public.time_off OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16467)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_account (
    user_id integer NOT NULL,
    type character varying(20) NOT NULL,
    email character varying(100) NOT NULL,
    password character varying(100) NOT NULL,
    CONSTRAINT user_email CHECK (((email)::text ~~ '%_@__%.__%'::text))
);


ALTER TABLE public.user_account OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16486)
-- Name: video_training; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.video_training (
    video_id integer NOT NULL,
    video_link character varying(300) NOT NULL
);


ALTER TABLE public.video_training OWNER TO postgres;

--
-- TOC entry 3211 (class 2606 OID 16466)
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3205 (class 2606 OID 16451)
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3207 (class 2606 OID 16456)
-- Name: employee_project_bridge employee_project_bridge_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee_project_bridge
    ADD CONSTRAINT employee_project_bridge_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3209 (class 2606 OID 16461)
-- Name: project project_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project
    ADD CONSTRAINT project_pkey PRIMARY KEY (project_id);


--
-- TOC entry 3203 (class 2606 OID 16485)
-- Name: time_off status; Type: CHECK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE public.time_off
    ADD CONSTRAINT status CHECK ((status = ANY (ARRAY['A'::bpchar, 'R'::bpchar, 'P'::bpchar]))) NOT VALID;


--
-- TOC entry 3219 (class 2606 OID 16531)
-- Name: task task_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT task_pkey PRIMARY KEY (task_id);


--
-- TOC entry 3215 (class 2606 OID 16479)
-- Name: time_off time_off_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.time_off
    ADD CONSTRAINT time_off_pkey PRIMARY KEY (time_off_id);


--
-- TOC entry 3213 (class 2606 OID 16472)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_account
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3217 (class 2606 OID 16490)
-- Name: video_training video_training_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.video_training
    ADD CONSTRAINT video_training_pkey PRIMARY KEY (video_id);


--
-- TOC entry 3220 (class 2606 OID 16480)
-- Name: time_off employee_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.time_off
    ADD CONSTRAINT employee_id FOREIGN KEY (employee_id) REFERENCES public.employee(user_id);


--
-- TOC entry 3221 (class 2606 OID 16532)
-- Name: task project_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT project_id_fkey FOREIGN KEY (project_id) REFERENCES public.project(project_id) NOT VALID;