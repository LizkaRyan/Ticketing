insert into ville(ville) values('Addis Abeba');

insert into siege_type(siege_type) values('Economique'),
                                         ('Business');

insert into vol(date_vol,prix_vol,id_avion,destination) values('2025-09-05 08:00:00',100,'AVN001','VLL004'),
                                                              ('2025-09-15 21:00:00',100,'AVN002','VLL003');

insert into siege_promotion(date_fin,prix,nombre,id_vol,id_siege_type) values   ('2025-08-27',200,4,'VOL006','SGT001'),
                                                                                ('2025-09-03',300,2,'VOL006','SGT001'),
                                                                                ('2025-09-05',350,3,'VOL007','SGT001'),
                                                                                ('2025-09-13',400,1,'VOL007','SGT001');

insert into reservation(date_reservation,prix_reservation,nb_passager,paye,id_siege_promotion,id_type_siege,id_utilisateur,id_vol)
values  ('2025-08-20',200,1,1,'SGP005','TPS001','USR001','VOL006'),
        ('2025-08-21',200,1,0,'SGP005','TPS001','USR001','VOL006'),
        ('2025-08-21',200,1,1,'SGP005','TPS001','USR001','VOL006'),

        ('2025-08-28',300,1,1,'SGP006','TPS001','USR001','VOL006'),
        ('2025-08-29',300,1,1,'SGP006','TPS001','USR001','VOL006'),
        ('2025-09-01',300,1,0,'SGP006','TPS001','USR001','VOL006'),
        ('2025-09-02',300,1,1,'SGP006','TPS001','USR001','VOL006'),

        ('2025-09-01',350,1,1,'SGP007','TPS001','USR001','VOL007'),
        ('2025-09-02',350,1,0,'SGP007','TPS001','USR001','VOL007'),
        ('2025-09-08',400,1,1,'SGP008','TPS001','USR001','VOL007'),
        ('2025-09-10',400,1,1,'SGP008','TPS001','USR001','VOL007');


