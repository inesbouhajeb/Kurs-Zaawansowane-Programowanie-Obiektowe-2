insert into user (firstname,lastname, username,password,enabled) values
('Jan','Kowalski','JanKowalski','{noop}pass',true),
('Maria', 'Markowska','MariaMarkowska','{noop}pass',true);

insert into user_role(username,role) values
('JanKowalski','ROLE_ADMIN'),
('MariaMarkowska','ROLE_USER');

insert into product(thickness,width,length,quantity,grade,tolerance,user_id) values
(9, 3000,9350,2,'A','0/+1,5',1),
(12, 3000,9650,14,'B','0/+1,5',1),
(7, 796,9350,2,'A','0/+1,5',2),
(1, 796,9650,14,'B','0/+1,5',2),
(7, 796,3950,2,'C','0/+1,5',1);


