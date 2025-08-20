select count(*) as nb_ticket_vendu, t.id_siege_promotion
from reservation r
         join siege_promotion t on t.id_siege_promotion = r.id_siege_promotion
group by t.id_siege_promotion;

select r1_0.id_vol
from siege_promotion s1_0
         join Reservation r1_0 on s1_0.id_siege_promotion = r1_0.id_siege_promotion and
                                  (r1_0.id_reservation not in (select id_reservation from annulation_reservation))
where s1_0.date_fin < '2025-08-29';

select * from reservation where id_reservation not in (select id_reservation from annulation_reservation);
