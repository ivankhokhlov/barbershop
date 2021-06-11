DROP DATABASE IF EXISTS barbershop;
CREATE DATABASE barbershop;
USE barbershop;

CREATE TABLE user(
                     id INT(11) NOT NULL AUTO_INCREMENT,
                     firstName VARCHAR(50) NOT NULL COLLATE utf8_general_ci,
                     lastName VARCHAR(50) NOT NULL COLLATE utf8_general_ci,
                     patronymic VARCHAR(50) COLLATE utf8_general_ci,
                     login VARCHAR(50) NOT NULL COLLATE utf8_general_ci,
                     password VARCHAR(50) NOT NULL COLLATE utf8_bin,
                     user_type enum('ADMIN','MASTER','CLIENT') NOT NULL,
                     PRIMARY KEY(id),
                     UNIQUE login(login)
)
    ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE admin(
                      user_id INT(11) NOT NULL,
                      position VARCHAR(50) NOT NULL,
                      PRIMARY KEY(user_id),
                      FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE,
                      UNIQUE (user_id)
)
    ENGINE=INNODB DEFAULT CHARSET=utf8,
    COLLATE utf8_general_ci;


CREATE TABLE client(
                        user_id INT(11) NOT NULL,
                        email VARCHAR(50) NOT NULL,
                        address VARCHAR(50) NOT NULL,
                        phone VARCHAR(50) NOT NULL,
                        PRIMARY KEY(user_id),
                        FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
                        UNIQUE (user_id)
)
    ENGINE=INNODB DEFAULT CHARSET=utf8,
    COLLATE utf8_general_ci;


CREATE TABLE service(
                           id INT(11) NOT NULL AUTO_INCREMENT,
                           name VARCHAR(50) NOT NULL,
                           price INT(11)NOT NULL,
                           duration INT(11)NOT NULL,
                           UNIQUE(name),
                           PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8,
 COLLATE utf8_general_ci;



CREATE TABLE specialization(
                     id INT(11) NOT NULL AUTO_INCREMENT,
                     name VARCHAR(50) NOT NULL,
                     UNIQUE(name),
                     PRIMARY KEY(id)
)
    ENGINE=INNODB DEFAULT CHARSET=utf8,
    COLLATE utf8_general_ci;

CREATE TABLE master(
                       user_id INT(11) NOT NULL,
                       specialization_id  INT(11) NOT NULL,
                       FOREIGN KEY (specialization_id) REFERENCES specialization(id) ON DELETE CASCADE,
                       FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
                       UNIQUE (user_id),
                       PRIMARY KEY(user_id)
)
    ENGINE=INNODB DEFAULT CHARSET=utf8,
    COLLATE utf8_general_ci;
CREATE TABLE service_master (
                                   id INT(11) NOT NULL AUTO_INCREMENT,
                                   master_id INT(11) NOT NULL,
                                   service_id INT(11) NOT NULL,
                                   PRIMARY KEY (id),
                                   FOREIGN KEY (service_id) REFERENCES service(id) ON DELETE CASCADE,
                                   FOREIGN KEY (master_id) REFERENCES master(user_id) ON DELETE CASCADE
)
    CHARACTER SET utf8
    COLLATE utf8_general_ci;
CREATE TABLE dayschedule(
                            id INT(11) NOT NULL AUTO_INCREMENT,
                            curDate DATE NOT NULL,
                            master_id INT(11) NOT NULL,
                            timeStart TIME NOT NULL,
                            timeEnd TIME NOT NULL,
                            FOREIGN KEY (master_id) REFERENCES master(user_id) ON DELETE CASCADE,
                            PRIMARY KEY(id)
)
    ENGINE=INNODB DEFAULT CHARSET=utf8,
    COLLATE utf8_general_ci;

CREATE TABLE reservation(
                          id INT(11) NOT NULL AUTO_INCREMENT,
                          timeStart TIME NOT NULL,
                          timeEnd TIME NOT NULL,
                          client_id INT(11)NOT NULL,
                          receipt VARCHAR(50) NOT NULL,
                          dayschedule_id INT(11)NOT NUll,
                          FOREIGN KEY (client_id) REFERENCES client(user_id) ON DELETE CASCADE,
                          FOREIGN KEY (dayschedule_id) REFERENCES dayschedule(id) ON DELETE CASCADE,
                          UNIQUE(receipt),
                          PRIMARY KEY(id)
)
    ENGINE=INNODB DEFAULT CHARSET=utf8,
    COLLATE utf8_general_ci;
CREATE TABLE service_reservation (
                                   id INT(11) NOT NULL AUTO_INCREMENT,
                                   reservation_id INT(11) NOT NULL,
                                   service_id INT(11) NOT NULL,
                                   PRIMARY KEY (id),
                                   FOREIGN KEY (service_id) REFERENCES service(id) ON DELETE CASCADE,
                                   FOREIGN KEY (reservation_id) REFERENCES reservation(id) ON DELETE CASCADE
)
    CHARACTER SET utf8
    COLLATE utf8_general_ci;

CREATE TABLE cookie(
                       user_id INT(11) NOT NULL,
                       uuid VARCHAR(50) NOT NULL,
                       FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
                       UNIQUE (user_id),
                       PRIMARY KEY(uuid)
)
    ENGINE=INNODB DEFAULT CHARSET=utf8,
    COLLATE utf8_general_ci;
CREATE INDEX curDate ON dayschedule(curDate);
CREATE INDEX timeStart ON reservation(timeStart);


INSERT INTO user VALUES(null,"Иван","Иванов","Иванович",
                        "Admin","adminPassword", 'ADMIN');
INSERT INTO admin VALUES(1,"Главный программист");