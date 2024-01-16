ALTER TABLE colaborador
ADD CONSTRAINT fk_colaborador_hierarquia
FOREIGN KEY (hierarquia_id) REFERENCES hierarquia(id);