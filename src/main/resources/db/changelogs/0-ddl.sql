create table picture
(
    id  bigserial    not null
        constraint picture_pkey
            primary key,
    url varchar(255) not null
);

create table users
(
    id                 bigserial    not null
        constraint users_pkey
            primary key,
    creation_date      timestamp,
    last_modified_date timestamp,
    disabled           boolean      not null,
    email              varchar(255) not null
        constraint uk_6dotkott2kjsp8vw4d0m25fb7
            unique,
    email_verified     boolean      not null,
    firebase_id        varchar(255) not null,
    first_name         varchar(255) not null,
    last_name          varchar(255) not null,
    pseudo             varchar(255) not null
        constraint uk_r9i2upm423j62a0neosbc8ucq
            unique,
    role               varchar(255) not null,
    avatar_id          bigint
        constraint fkgqvldwxb43ms25d88rmik5q5t
            references picture
);

create table country
(
    id                 bigserial    not null
        constraint country_pkey
            primary key,
    creation_date      timestamp,
    last_modified_date timestamp,
    flag               varchar(255) not null,
    label              varchar(255) not null
);

create table address
(
    id         bigserial        not null
        constraint address_pkey
            primary key,
    city       varchar(255)     not null,
    latitude   double precision not null,
    line       varchar(255)     not null,
    longitude  double precision not null,
    zip_code   varchar(255)     not null,
    country_id bigint
        constraint fke54x81nmccsk5569hsjg1a6ka
            references country
);

create table schedule
(
    id      bigserial    not null
        constraint schedule_pkey
            primary key,
    closing varchar(255) not null,
    day     varchar(255),
    opening varchar(255) not null
);

create table beer_type
(
    id                 bigserial    not null
        constraint beer_type_pkey
            primary key,
    creation_date      timestamp,
    last_modified_date timestamp,
    label              varchar(255) not null
);

create table beer
(
    id                 bigserial        not null
        constraint beer_pkey
            primary key,
    creation_date      timestamp,
    last_modified_date timestamp,
    background         varchar(255)     not null,
    color              varchar(255)     not null,
    degree             double precision not null,
    description        varchar(255),
    name               varchar(255)     not null,
    summary            varchar(255),
    country_id         bigint
        constraint fk5s9cdoh7ppgq03tlkkp4phomw
            references country,
    picture_id         bigint
        constraint fkt5b2226lknfx7sq8plbqkbpcw
            references picture,
    type_id            bigint
        constraint fkpvwqub1sk83b2amuelvau44o4
            references beer_type
);

create table bar
(
    id                 bigserial    not null
        constraint bar_pkey
            primary key,
    creation_date      timestamp,
    last_modified_date timestamp,
    name               varchar(255) not null,
    address_id         bigint
        constraint fk4auwu2t1y1mr0lvvdi3g3917o
            references address
);

create table bar_pictures
(
    bar_id      bigint not null
        constraint fkdylq6i4cphkmksxrc625vewu3
            references bar,
    pictures_id bigint not null
        constraint uk_ff4dicl054fkc83xr2hq6f1ep
            unique
        constraint fkf6uxmq1yg3dv1l1rpd1qmdkci
            references picture
);

create table bar_schedules
(
    bar_id       bigint not null
        constraint fkjfu6j66f96twiqja87k15grtg
            references bar,
    schedules_id bigint not null
        constraint uk_d7un3xgrra95q0wvntneq2dtg
            unique
        constraint fkiodb8cpxwm6ibahe0xq4hikgv
            references schedule
);

create table bar_beers
(
    bar_id   bigint not null
        constraint fks71l8mao2h8uslfd57v8lsylb
            references bar,
    beers_id bigint not null
        constraint fkpj6kf8nlgi7n858dhy7sg212g
            references beer
);
