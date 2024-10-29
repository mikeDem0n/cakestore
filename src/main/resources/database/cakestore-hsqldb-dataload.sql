INSERT INTO sequence VALUES('ordernum', 1000);

INSERT INTO signon VALUES('j2ee','j2ee');
INSERT INTO signon VALUES('ACID','ACID');

INSERT INTO account VALUES('j2ee','yourname@yourdomain.com','ABC', 'XYX', 'OK', '901 San Antonio Road', 'MS UCUP02-206', 'Palo Alto', 'CA', '94303', 'USA',  '555-555-5555');
INSERT INTO account VALUES('ACID','acid@yourdomain.com','ABC', 'XYX', 'OK', '901 San Antonio Road', 'MS UCUP02-206', 'Palo Alto', 'CA', '94303', 'USA',  '555-555-5555');

INSERT INTO profile VALUES('j2ee','english','BREAD',1,1);
INSERT INTO profile VALUES('ACID','english','LITTLE',1,1);

INSERT INTO bannerdata VALUES ('BREAD','<image src="../images/banner_fish.gif">');
INSERT INTO bannerdata VALUES ('LITTLE','<image src="../images/banner_cats.gif">');
INSERT INTO bannerdata VALUES ('BIRTH','<image src="../images/banner_dogs.gif">');
INSERT INTO bannerdata VALUES ('CUSTOM','<image src="../images/banner_reptiles.gif">');
INSERT INTO bannerdata VALUES ('DESSERT','<image src="../images/banner_birds.gif">');

INSERT INTO category VALUES ('BREAD','Bread','<image src="../images/bread_icon.gif"><font size="5" color="blue"> Bread</font>');
INSERT INTO category VALUES ('LITTLE','Little','<image src="../images/little_icon.gif"><font size="5" color="blue"> Little</font>');
INSERT INTO category VALUES ('BIRTH','Birth','<image src="../images/birth_icon.gif"><font size="5" color="blue"> Birth</font>');
INSERT INTO category VALUES ('CUSTOM','Custom','<image src="../images/custom_icon.gif"><font size="5" color="blue"> Custom</font>');
INSERT INTO category VALUES ('DESSERT','Dessert','<image src="../images/dessert_icon.gif"><font size="5" color="blue"> Dessert</font>');

INSERT INTO product VALUES ('FI-SW-01','BREAD','Bagel','<image src="../images/bread1.gif">Bagel circles made from leavened dough');
INSERT INTO product VALUES ('FI-SW-02','BREAD','Toast','<image src="../images/bread2.gif">Square slices of bread baked by fire');
INSERT INTO product VALUES ('FI-FW-01','BREAD', 'Croissant','<image src="../images/bread3.gif">Bread like a goat horn');
INSERT INTO product VALUES ('FI-FW-02','BREAD', 'Baguette','<image src="../images/bread4.gif">Thin and long bread');
INSERT INTO product VALUES ('K9-BD-01','LITTLE','Tiramisu','<image src="../images/little1.gif"Cupcakes from Italy');
INSERT INTO product VALUES ('K9-PO-02','LITTLE','Mousse','<image src="../images/little2.gif">Frozen custard cake');
INSERT INTO product VALUES ('K9-DL-01','LITTLE', 'Redvelvet','<image src="../images/little3.gif">Cake for festival occasions');
INSERT INTO product VALUES ('RP-SN-01','BIRTH','Cream','<image src="../images/birth1.gif">Common cake made with cream');
INSERT INTO product VALUES ('RP-LI-02','BIRTH','Icecream','<image src="../images/birth2.gif">Cake of alternating ice cream and cake');
INSERT INTO product VALUES ('FL-DSH-01','CUSTOM','Animalcream','<image src="../images/custom1.gif">Cream with more fat');
INSERT INTO product VALUES ('FL-DLH-02','CUSTOM','Vegetablecream','<image src="../images/custom2.gif">Cream with less fat');
INSERT INTO product VALUES ('AV-CB-01','DESSERT','Puff','<image src="../images/dessert1.gif">Cream cover by cream crust');
INSERT INTO product VALUES ('AV-SB-02','DESSERT','Eggtart','<image src="../images/dessert2.gif">Western pie filled with egg cream');

INSERT INTO supplier VALUES (1,'XYZ Bakery','AC','600 Avon Way','','Los Angeles','CA','94024','212-947-0797');
INSERT INTO supplier VALUES (2,'ABC Bakery','AC','700 Abalone Way','','San Francisco ','CA','94024','415-947-0797');




