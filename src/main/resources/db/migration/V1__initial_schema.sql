CREATE TABLE comment(
                     id BIGSERIAL PRIMARY KEY NOT NULL ,
                     topic_id BIGSERIAL NOT NULL ,
                     author varchar(255) NOT NULL ,
                     text varchar(255)  NOT NULL ,
                     created_date timestamp NOT NULL ,
                     last_modified_date timestamp NOT NULL
)