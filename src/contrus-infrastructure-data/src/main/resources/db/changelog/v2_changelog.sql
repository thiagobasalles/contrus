CREATE TABLE hierarquia (
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  hierarquia_superior_colaborador_id BIGINT NULL,
  valor_hierarquia INT NOT NULL DEFAULT 0
);