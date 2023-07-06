CREATE DATABASE carteira_financeira;

USE carteira_financeira;

CREATE TABLE usuario(
                        cpf     VARCHAR(11) NOT NULL,
                        nome    VARCHAR(50) NOT NULL,
                        login   VARCHAR(12) NOT NULL,
                        senha   VARCHAR(30) NOT NULL,
                        email   VARCHAR(30) NOT NULL,
                        PRIMARY KEY (cpf)
);

CREATE TABLE meta_financeira(
                                id          INT         NOT NULL AUTO_INCREMENT,
                                nome        VARCHAR(20) NOT NULL,
                                valor_meta  DECIMAL     NOT NULL,
                                valor_atual DECIMAL     NOT NULL,
                                descricao   VARCHAR(70) NULL,
                                cpf_usuario VARCHAR(11) NOT NULL,
                                PRIMARY KEY (id),
                                FOREIGN KEY (cpf_usuario) REFERENCES usuario (cpf) ON DELETE NO ACTION
                                    ON UPDATE NO ACTION
);

CREATE TABLE conta(
                      id          INT         NOT NULL AUTO_INCREMENT,
                      nome        VARCHAR(30) NOT NULL,
                      saldo       DECIMAL     NOT NULL,
                      instituicao VARCHAR(30) NOT NULL,
                      cpf_usuario VARCHAR(11) NOT NULL,
                      PRIMARY KEY (id),
                      FOREIGN KEY (cpf_usuario) REFERENCES usuario (cpf) ON DELETE NO ACTION
                          ON UPDATE NO ACTION
);

CREATE TABLE cartao_de_credito(
                                  id                INT         NOT NULL AUTO_INCREMENT,
                                  limite            DECIMAL     NOT NULL,
                                  data_fechamento   DATETIME    NOT NULL,
                                  cpf_usuario       VARCHAR(11) NOT NULL,
                                  id_conta          INT         NOT NULL,
                                  PRIMARY KEY (id),
                                  FOREIGN KEY (cpf_usuario) REFERENCES usuario (cpf) ON DELETE NO ACTION
                                      ON UPDATE NO ACTION,
                                  FOREIGN KEY (id_conta) REFERENCES conta(id) ON DELETE NO ACTION
                                      ON UPDATE NO ACTION
);

CREATE TABLE fonte_emprestimo(
                                 id      INT NOT NULL AUTO_INCREMENT,
                                 nome    VARCHAR(45) NOT NULL UNIQUE,
                                 PRIMARY KEY(id)
);

CREATE TABLE fonte_receita(
                              id      INT NOT NULL AUTO_INCREMENT,
                              nome    VARCHAR(45) NOT NULL,
                              PRIMARY KEY(id)
);

CREATE TABLE transacao(
                          id          INT NOT NULL AUTO_INCREMENT,
                          data        DATETIME NOT NULL,
                          valor       DECIMAL NOT NULL,
                          id_conta    INT NOT NULL,
                          PRIMARY KEY(id),
                          FOREIGN KEY(id_conta) REFERENCES conta(id) ON DELETE NO ACTION
                              ON UPDATE NO ACTION
);

CREATE TABLE receita(
                        id_fonte            INT,
                        id_transacao        INT NOT NULL,
                        FOREIGN KEY(id_transacao)   REFERENCES transacao(id) ON DELETE NO ACTION
                            ON UPDATE NO ACTION,
                        FOREIGN KEY(id_fonte)       REFERENCES fonte_receita(id) ON DELETE NO ACTION
                            ON UPDATE NO ACTION
);

CREATE TABLE emprestimo(
                           id                      INT NOT NULL AUTO_INCREMENT,
                           numeros_parcelas        INT NOT NULL,
                           juros                   DECIMAL NOT NULL,
                           id_fonte                INT,
                           id_transacao_receita    INT NOT NULL,
                           PRIMARY KEY (id),
                           FOREIGN KEY (id_fonte) REFERENCES fonte_emprestimo(id) ON DELETE NO ACTION
                               ON UPDATE NO ACTION,
                           FOREIGN KEY (id_transacao_receita) REFERENCES receita(id_transacao) ON DELETE NO ACTION
                               ON UPDATE NO ACTION
);

CREATE TABLE categoria_despesa(
                                  id      INT NOT NULL AUTO_INCREMENT,
                                  nome    VARCHAR(45) NOT NULL UNIQUE,
                                  PRIMARY KEY (id)
);

CREATE TABLE despesa(
                        status_pagamento BOOLEAN NOT NULL,
                        id_categoria INT,
                        id_transacao INT NOT NULL PRIMARY KEY,
                        FOREIGN KEY (id_categoria) REFERENCES categoria_despesa(id) ON DELETE NO ACTION
                            ON UPDATE NO ACTION,
                        FOREIGN KEY (id_transacao) REFERENCES transacao(id) ON DELETE NO ACTION
                            ON UPDATE NO ACTION
);

CREATE TABLE fatura(
                       mes                     INT NOT NULL,
                       ano                     INT NOT NULL,
                       id_cartao               INT NOT NULL,
                       id_transacao_despesa    INT NOT NULL,
                       PRIMARY KEY (mes, ano, id_cartao),
                       FOREIGN KEY (id_cartao) REFERENCES cartao_de_credito(id) ON DELETE NO ACTION
                           ON UPDATE NO ACTION,
                       FOREIGN KEY (id_transacao_despesa) REFERENCES despesa(id_transacao) ON DELETE NO ACTION
                           ON UPDATE NO ACTION
);


CREATE TABLE parcela(
                        mes                     INT NOT NULL,
                        ano                     INT NOT NULL,
                        id_emprestimo           INT NOT NULL,
                        id_transacao_despesa    INT NOT NULL,
                        PRIMARY KEY (mes, ano, id_emprestimo),
                        FOREIGN KEY (id_emprestimo) REFERENCES emprestimo(id) ON DELETE NO ACTION
                            ON UPDATE NO ACTION,
                        FOREIGN KEY (id_transacao_despesa) REFERENCES despesa(id_transacao) ON DELETE NO ACTION
                            ON UPDATE NO ACTION
);

CREATE TABLE transferencia(
                              id                  INT NOT NULL AUTO_INCREMENT,
                              id_conta_origem     INT NOT NULL,
                              id_conta_destino    INT NOT NULL,
                              valor               DECIMAL NOT NULL,
                              PRIMARY KEY(id),
                              FOREIGN KEY (id_conta_origem) REFERENCES conta(id) ON DELETE NO ACTION
                                  ON UPDATE NO ACTION,
                              FOREIGN KEY (id_conta_destino) REFERENCES conta(id) ON DELETE NO ACTION
                                  ON UPDATE NO ACTION
);