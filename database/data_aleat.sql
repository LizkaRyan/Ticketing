insert into siege_type(siege_type) values('Economique'),
                                         ('Business');

insert into vol(date_vol,prix_vol,id_avion,destination) values('2025-09-15',100,'AVN001','VLL001');

insert into siege_promotion(date_fin,prix,nombre,id_vol,id_siege_type) values   ('2025-08-20',100,10,'VOL005','SGT001'),
                                                                                ('2025-08-28',150,5,'VOL005','SGT001'),
                                                                                ('2025-09-01',250,4,'VOL005','SGT001');

insert into reservation(date_reservation,prix_reservation,nb_passager,paye,id_siege_promotion,id_type_siege,id_utilisateur,id_vol)
values  ('2025-08-11',100,1,1,'SGP002','TPS001','USR001','VOL001'),
        ('2025-08-12',100,1,1,'SGP002','TPS001','USR001','VOL001'),
        ('2025-08-13',100,1,1,'SGP002','TPS001','USR001','VOL001'),
        ('2025-08-14',100,1,1,'SGP002','TPS001','USR001','VOL001'),
        ('2025-08-15',100,1,0,'SGP002','TPS001','USR001','VOL001'),
        ('2025-08-16',100,1,0,'SGP002','TPS001','USR001','VOL001'),

        ('2025-08-21',150,1,1,'SGP003','TPS001','USR001','VOL001'),
        ('2025-08-22',150,1,1,'SGP003','TPS001','USR001','VOL001'),
        ('2025-08-23',150,1,1,'SGP003','TPS001','USR001','VOL001'),
        ('2025-08-24',150,1,0,'SGP003','TPS001','USR001','VOL001'),
        ('2025-08-25',150,1,0,'SGP003','TPS001','USR001','VOL001'),

        ('2025-08-11',100,1,1,'SGP002','TPS001','USR001','VOL001');


