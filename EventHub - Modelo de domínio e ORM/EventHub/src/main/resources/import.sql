INSERT INTO  tb_categoria (descricao) VALUES ('Curso');
INSERT INTO  tb_categoria (descricao) VALUES ('Oficina');


INSERT INTO  tb_bloco (inicio,fim) VALUES (TIMESTAMP WITH TIME ZONE '2017-09-25T08:00:00Z', TIMESTAMP WITH TIME ZONE '2017-09-25T11:00:00Z');
INSERT INTO  tb_bloco (inicio,fim) VALUES (TIMESTAMP WITH TIME ZONE '2017-09-25T14:00:00Z', TIMESTAMP WITH TIME ZONE '2017-09-25T18:00:00Z');
INSERT INTO  tb_bloco (inicio,fim) VALUES (TIMESTAMP WITH TIME ZONE '2017-09-26T08:00:00Z', TIMESTAMP WITH TIME ZONE '2017-09-26T11:00:00Z');



INSERT INTO  tb_atividade (nome, descricao, preco, categoria, bloco_id) VALUES ('Curso de HTML', 'Aprenda HTML de forma prática',80.00, 1,1);
INSERT INTO  tb_atividade (nome, descricao, preco, categoria, bloco_id) VALUES ('Oficina de Github', 'Controle de versão dos seus projetos',50.00, 2,2);
INSERT INTO  tb_atividade (nome, descricao, preco, categoria, bloco_id) VALUES ('Oficina de Github', 'Controle de versão dos seus projetos',50.00, 2,3);


INSERT INTO  tb_participante (nome, email, atividades) VALUES ('José Silva', 'jose@gmail.com',1);
INSERT INTO  tb_participante (nome, email, atividades) VALUES ('José Silva', 'jose@gmail.com',2);
INSERT INTO  tb_participante (nome, email, atividades) VALUES ('Tiago Faria', 'tiago@gmail.com',1);
INSERT INTO  tb_participante (nome, email, atividades) VALUES ('Maria do Rosário', 'maria@gmail.com',1);
INSERT INTO  tb_participante (nome, email, atividades) VALUES ('Maria do Rosário', 'maria@gmail.com',2);
INSERT INTO  tb_participante (nome, email, atividades) VALUES ('Teresa Silva', 'teresa@gmail.com',2);
