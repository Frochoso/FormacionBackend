PGDMP         "                {           test    15.3    15.3 &    *
           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            +
           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ,
           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            -
           1262    16555    test    DATABASE     w   CREATE DATABASE test WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Spain.1252';
    DROP DATABASE test;
                postgres    false            �            1259    16847 
   asignatura    TABLE     �   CREATE TABLE public.asignatura (
    finish_date date,
    id_asignatura integer NOT NULL,
    initial_date date,
    comments character varying(255),
    nombre_asignatura character varying(255)
);
    DROP TABLE public.asignatura;
       public         heap    postgres    false            �            1259    16842    asignatura_seq    SEQUENCE     x   CREATE SEQUENCE public.asignatura_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.asignatura_seq;
       public          postgres    false            �            1259    16696    employee_seq    SEQUENCE     v   CREATE SEQUENCE public.employee_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.employee_seq;
       public          postgres    false            �            1259    16854    persona    TABLE     �  CREATE TABLE public.persona (
    active boolean,
    created_date date,
    id_persona integer NOT NULL,
    termination_date date,
    city character varying(255),
    company_email character varying(255),
    imagen_url character varying(255),
    name character varying(255),
    password character varying(255),
    personal_email character varying(255),
    surname character varying(255),
    usuario character varying(255)
);
    DROP TABLE public.persona;
       public         heap    postgres    false            �            1259    16843    persona_seq    SEQUENCE     u   CREATE SEQUENCE public.persona_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.persona_seq;
       public          postgres    false            �            1259    16861 	   professor    TABLE     T  CREATE TABLE public.professor (
    id_persona integer,
    id_professor integer NOT NULL,
    branch character varying(255),
    comments character varying(255),
    CONSTRAINT professor_branch_check CHECK (((branch)::text = ANY ((ARRAY['BACK'::character varying, 'FRONT'::character varying, 'FULLSTACK'::character varying])::text[])))
);
    DROP TABLE public.professor;
       public         heap    postgres    false            �            1259    16844 
   professor_seq    SEQUENCE     w   CREATE SEQUENCE public.professor_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.professor_seq;
       public          postgres    false            �            1259    16871    student    TABLE     �  CREATE TABLE public.student (
    id_persona integer,
    id_profesor integer,
    id_student integer NOT NULL,
    num_hours_week integer,
    branch character varying(255),
    comments character varying(255),
    CONSTRAINT student_branch_check CHECK (((branch)::text = ANY ((ARRAY['BACK'::character varying, 'FRONT'::character varying, 'FULLSTACK'::character varying])::text[])))
);
    DROP TABLE public.student;
       public         heap    postgres    false            �            1259    16881    student_asignaturas    TABLE     x   CREATE TABLE public.student_asignaturas (
    id integer NOT NULL,
    id_asignatura integer,
    id_student integer
);
 '   DROP TABLE public.student_asignaturas;
       public         heap    postgres    false            �            1259    16845    student_asignaturas_seq    SEQUENCE     �   CREATE SEQUENCE public.student_asignaturas_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.student_asignaturas_seq;
       public          postgres    false            �            1259    16846    student_seq    SEQUENCE     u   CREATE SEQUENCE public.student_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.student_seq;
       public          postgres    false            #
          0    16847 
   asignatura 
   TABLE DATA           k   COPY public.asignatura (finish_date, id_asignatura, initial_date, comments, nombre_asignatura) FROM stdin;
    public          postgres    false    220   9.       $
          0    16854    persona 
   TABLE DATA           �   COPY public.persona (active, created_date, id_persona, termination_date, city, company_email, imagen_url, name, password, personal_email, surname, usuario) FROM stdin;
    public          postgres    false    221   V.       %
          0    16861 	   professor 
   TABLE DATA           O   COPY public.professor (id_persona, id_professor, branch, comments) FROM stdin;
    public          postgres    false    222   s.       &
          0    16871    student 
   TABLE DATA           h   COPY public.student (id_persona, id_profesor, id_student, num_hours_week, branch, comments) FROM stdin;
    public          postgres    false    223   �.       '
          0    16881    student_asignaturas 
   TABLE DATA           L   COPY public.student_asignaturas (id, id_asignatura, id_student) FROM stdin;
    public          postgres    false    224   �.       .
           0    0    asignatura_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.asignatura_seq', 1, false);
          public          postgres    false    215            /
           0    0    employee_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.employee_seq', 1, false);
          public          postgres    false    214            0
           0    0    persona_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.persona_seq', 1, false);
          public          postgres    false    216            1
           0    0 
   professor_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.professor_seq', 1, false);
          public          postgres    false    217            2
           0    0    student_asignaturas_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.student_asignaturas_seq', 1, false);
          public          postgres    false    218            3
           0    0    student_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.student_seq', 1, false);
          public          postgres    false    219            }           2606    16853    asignatura asignatura_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.asignatura
    ADD CONSTRAINT asignatura_pkey PRIMARY KEY (id_asignatura);
 D   ALTER TABLE ONLY public.asignatura DROP CONSTRAINT asignatura_pkey;
       public            postgres    false    220                       2606    16860    persona persona_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (id_persona);
 >   ALTER TABLE ONLY public.persona DROP CONSTRAINT persona_pkey;
       public            postgres    false    221            �           2606    16870 "   professor professor_id_persona_key 
   CONSTRAINT     c   ALTER TABLE ONLY public.professor
    ADD CONSTRAINT professor_id_persona_key UNIQUE (id_persona);
 L   ALTER TABLE ONLY public.professor DROP CONSTRAINT professor_id_persona_key;
       public            postgres    false    222            �           2606    16868    professor professor_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.professor
    ADD CONSTRAINT professor_pkey PRIMARY KEY (id_professor);
 B   ALTER TABLE ONLY public.professor DROP CONSTRAINT professor_pkey;
       public            postgres    false    222            �           2606    16885 ,   student_asignaturas student_asignaturas_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.student_asignaturas
    ADD CONSTRAINT student_asignaturas_pkey PRIMARY KEY (id);
 V   ALTER TABLE ONLY public.student_asignaturas DROP CONSTRAINT student_asignaturas_pkey;
       public            postgres    false    224            �           2606    16880    student student_id_persona_key 
   CONSTRAINT     _   ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_id_persona_key UNIQUE (id_persona);
 H   ALTER TABLE ONLY public.student DROP CONSTRAINT student_id_persona_key;
       public            postgres    false    223            �           2606    16878    student student_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (id_student);
 >   ALTER TABLE ONLY public.student DROP CONSTRAINT student_pkey;
       public            postgres    false    223            �           2606    16891 #   student fk49t3db8f9giyv4k8dnly8l0up 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.student
    ADD CONSTRAINT fk49t3db8f9giyv4k8dnly8l0up FOREIGN KEY (id_persona) REFERENCES public.persona(id_persona);
 M   ALTER TABLE ONLY public.student DROP CONSTRAINT fk49t3db8f9giyv4k8dnly8l0up;
       public          postgres    false    3199    223    221            �           2606    16906 /   student_asignaturas fkjq6g2deo3kycx1qvbkvxfpic3 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.student_asignaturas
    ADD CONSTRAINT fkjq6g2deo3kycx1qvbkvxfpic3 FOREIGN KEY (id_student) REFERENCES public.student(id_student);
 Y   ALTER TABLE ONLY public.student_asignaturas DROP CONSTRAINT fkjq6g2deo3kycx1qvbkvxfpic3;
       public          postgres    false    224    223    3207            �           2606    16901 /   student_asignaturas fkkkgv999wak7v20v1ov965fd2s 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.student_asignaturas
    ADD CONSTRAINT fkkkgv999wak7v20v1ov965fd2s FOREIGN KEY (id_asignatura) REFERENCES public.asignatura(id_asignatura);
 Y   ALTER TABLE ONLY public.student_asignaturas DROP CONSTRAINT fkkkgv999wak7v20v1ov965fd2s;
       public          postgres    false    224    3197    220            �           2606    16886 %   professor fkpbswr2mokemp1qwd1wrksncjp 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.professor
    ADD CONSTRAINT fkpbswr2mokemp1qwd1wrksncjp FOREIGN KEY (id_persona) REFERENCES public.persona(id_persona);
 O   ALTER TABLE ONLY public.professor DROP CONSTRAINT fkpbswr2mokemp1qwd1wrksncjp;
       public          postgres    false    221    222    3199            �           2606    16896 #   student fkt1eipd9l08vj252blsskd36j4 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.student
    ADD CONSTRAINT fkt1eipd9l08vj252blsskd36j4 FOREIGN KEY (id_profesor) REFERENCES public.professor(id_professor);
 M   ALTER TABLE ONLY public.student DROP CONSTRAINT fkt1eipd9l08vj252blsskd36j4;
       public          postgres    false    222    3203    223            #
   
   x������ � �      $
   
   x������ � �      %
   
   x������ � �      &
   
   x������ � �      '
   
   x������ � �     