insert into category (id, name, active)
values (1, 'Front-End', true);
insert into category (id, name, active)
values (2, 'Back-End', true);
insert into category (id, name, active)
values (3, 'DevOps', true);
insert into category (id, name, active)
values (4, 'Machine Learning', true);
insert into category (id, name, active)
values (5, 'Cybersecurity', true);
insert into category (id, name, active)
values (6, 'Database Administration', true);
insert into category (id, name, active)
values (7, 'CD/CI', true);

insert into account (id, full_name, title, contact, username, bio, github, linked_in, passwd, acc_role, reg_date)
values (1, 'Fabio Frasson', 'Estudante de Java', 'fabio.frass@gmail.com', 'fabiofrasson', 'Bio test',
        'https://github.com/fabiofrasson', 'https://www.linkedin.com/in/fabiofrasson/', '123456', 'ADMIN',
        '2021-06-05 15:35:25.541423');
insert into account (id, full_name, title, contact, username, bio, github, linked_in, passwd, acc_role, reg_date)
values (2, 'Wilian Bueno', 'Estudante de Python', 'wil.bueno@gmail.com', 'wilbueno', 'Bio test',
        'https://github.com/wilbueno', 'https://www.linkedin.com/in/wilbueno/', '123456', 'COMPANYADMIN',
        '2021-06-05 15:35:25.541423');
insert into account (id, full_name, title, contact, username, bio, github, linked_in, passwd, acc_role, reg_date)
values (3, 'Kennedy Bueno', 'Estudante de Lumion', 'kennedy.bueno@gmail.com', 'kenbueno', 'Bio test',
        'https://github.com/kenbueno', 'https://www.linkedin.com/in/kenbueno/', '123456', 'STUDENT',
        '2021-06-05 15:35:25.541423');

insert into course (id, name, description, instructor, user_id, site, price, length, slug, course_status, reg_date)
values (1, 'Front-End completo', 'Curso completo de Front-End', 'José Pereira', 2,
        'https://www.frontendcompleto.com.br', 100.0, 25.0, 'frontend-completo', 'ACTIVE',
        '2021-06-05 15:35:25.554424');
insert into course (id, name, description, instructor, user_id, site, price, length, slug, course_status, reg_date)
values (2, 'Back-End completo', 'Curso completo de Back-End', 'José Pereira', 2, 'https://www.backendcompleto.com.br',
        150.0, 50.0, 'backend-completo', 'PENDING', '2021-06-05 15:35:25.554424');
insert into course (id, name, description, instructor, user_id, site, price, length, slug, course_status, reg_date)
values (3, 'UX completo', 'Curso completo de UX', 'José Pereira', 2, 'https://www.uxcompleto.com.br', 200.0, 75.0,
        'ux-completo', 'REJECTED', '2021-06-05 15:35:25.554424');