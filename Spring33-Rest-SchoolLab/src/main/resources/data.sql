-- students
INSERT INTO students (first_name,last_name,email,user_name,password,birth_day,phone_number,status) VALUES
('Morna','Ions','mions0@tumblr.com','mions0@mail.ru','WoJ6MH0SRH','12/25/2021','324-943-5915',true),
('Kalina','Nabarro','knabarro1@loc.gov','knabarro1@sogou.com','dCErh4F','12/20/2021','421-136-0527',false),
('Lurleen','Motton','lmotton2@sogou.com','lmotton2@scientificamerican.com','C4PwoOd','03/01/2022','901-778-6270',true),
('Jeremy','Mettricke','jmettricke3@cnn.com','jmettricke3@dedecms.com','pwCDXTHSGvYs','03/09/2022','843-503-1329',false),
('Hestia','Dinkin','hdinkin4@epa.gov','hdinkin4@studiopress.com','sQeZDTFW','01/04/2022','603-706-0368',false),
('Idaline','Kwiek','ikwiek5@symantec.com','ikwiek5@wikispaces.com','ocQUwXGs','03/05/2022','242-501-4482',false),
('Grantham','Ramirez','gramirez6@jigsy.com','gramirez6@nifty.com','aiFWIR1oaz','07/27/2022','542-173-0817',true),
('Thorstein','Rime','trime7@harvard.edu','trime7@t-online.de','YGA7733SEdX3','09/14/2021','995-238-5948',false),
('Law','Calles','lcalles8@forbes.com','lcalles8@typepad.com','lKmbihP','09/16/2021','990-212-7493',true),
('Leeanne','Jakubovicz','ljakubovicz9@bloglines.com','ljakubovicz9@ucoz.com','G1VdaUkkt6T','08/16/2022','554-156-5240',true)
ON CONFLICT DO NOTHING;

-- Parent

INSERT INTO parents (first_name,last_name,email,user_name, password ,birth_day,phone_number,status,profession) VALUES
('Janeva','Tarquinio','jtarquinio0@hp.com','jtarquinio0@java.com','Ddf3rmTj','04/24/2022','801-468-8407','true','Pharmacist'),
('Nealon','Spaight','nspaight1@google.nl','nspaight1@reference.com','6BZYS3sczw3','05/27/2022','794-333-6520','true','Administrative Officer'),
('Jammal','Toping','jtoping2@bloglovin.com','jtoping2@about.me','cajdOohTUs','08/16/2022','973-305-3013','true','Senior Editor'),
('Ellsworth','Gooderson','egooderson3@surveymonkey.com','egooderson3@dion.ne.jp','biJEoRuI','04/24/2022','496-508-5709','false','Design Engineer'),
('Irvin','Tremberth','itremberth4@engadget.com','itremberth4@youtube.com','6JDu0gN9','07/02/2022','144-782-4640','true','Analyst Programmer'),
('Nance','Peert','npeert5@tiny.cc','npeert5@tripadvisor.com','NRbMB2zu6ry','08/29/2022','769-678-7999','false','Chemical Engineer'),
('Sally','Youson','syouson6@odnoklassniki.ru','syouson6@hugedomains.com','jPQMWv','08/21/2022','905-931-6749','true','Engineer III'),
('Jarrid','Osgordby','josgordby7@howstuffworks.com','josgordby7@ox.ac.uk','9xima1JjV','04/08/2022','307-828-1609','false','Quality Control Specialist'),
('Claudine','Biggins','cbiggins8@t.co','cbiggins8@bravesites.com','S44g2IG43Q9','07/14/2022','614-305-0490','false','Automation Specialist I'),
('Carolus','DelaField','cdelafield9@mtv.com','cdelafield9@dedecms.com','UfrWVS','09/17/2021','362-332-6442','false','Structural Engineer')
ON CONFLICT DO NOTHING;

-- teacher
INSERT INTO teachers (first_name,last_name,email,user_name,password,birth_day,phone_number,status)  VALUES
('Gwendolyn','Rodell','grodell0@addtoany.com','grodell0@histats.com','s0lBv4','09/05/2022','907-540-8863','true'),
('Reilly','McFeat','rmcfeat1@independent.co.uk','rmcfeat1@skype.com','DuWZFvV','07/08/2022','646-834-5316','true'),
('Rene','Swaine','rswaine2@vk.com','rswaine2@fastcompany.com','6C56MP','08/04/2022','549-727-3501','false'),
('Vikky','Matschuk','vmatschuk3@ustream.tv','vmatschuk3@plala.or.jp','YsoUBmcw','09/28/2021','429-953-0978','false'),
('Corry','Yorath','cyorath4@reddit.com','cyorath4@ebay.co.uk','HDsY5HhC0','06/12/2022','850-892-4629','true'),
('Vail','Ashley','vashley5@geocities.com','vashley5@mapquest.com','Fyz5Svu','06/03/2022','963-639-3461','true'),
('Payton','Sunners','psunners6@dailymotion.com','psunners6@scribd.com','kSPcxiYUU1sG','06/29/2022','596-734-2760','false'),
('Solomon','Lythgoe','slythgoe7@deviantart.com','slythgoe7@reuters.com','ByhOfe','11/05/2021','215-528-1726','false'),
('Ollie','Bromilow','obromilow8@illinois.edu','obromilow8@reverbnation.com','lc3KCHDLMScX','03/08/2022','273-698-8818','false'),
('Otis','Cornelius','ocornelius9@jugem.jp','ocornelius9@cnet.com','ClBlnq46','09/21/2021','417-837-4748','true')
ON CONFLICT DO NOTHING;

-- address

INSERT INTO address (street ,country,state,city,postal_code,address_type,current_temperature) VALUES
('3 Brown Circle','United States','California','San Francisco','94110','true',64),
('180 7th Junction','United States','New York','Rochester','14604','false',77),
('78 Buena Vista Plaza','United States','Maryland','Hagerstown','21747','false',96),
('2026 Monterey Street','United States','Ohio','Dayton','45432','true',82),
('3089 5th Hill','United States','Alabama','Birmingham','35254','true',38),
('0 Reindahl Center','United States','New York','Rochester','14652','false',16),
('8 Buena Vista Crossing','United States','Pennsylvania','Pittsburgh','15279','true',11),
('3 Barby Junction','United States','Michigan','Detroit','48211','true',88),
('54 Jackson Park','United States','Missouri','Saint Louis','63121','true',25),
('87 Hoepker Trail','United States','Louisiana','Baton Rouge','70836','false',9),
('45 Spenser Pass','United States','Colorado','Colorado Springs','80920','true',65)
ON CONFLICT DO NOTHING;

-- class
INSERT INTO class (name, year) VALUES ('math', 1);
INSERT INTO class (name, year) VALUES ('physics', 1);
INSERT INTO class (name, year) VALUES ('health', 1);
INSERT INTO class (name, year) VALUES ('art', 1);
INSERT INTO class (name, year) VALUES ('literature', 1);
INSERT INTO class (name, year) VALUES ('applied chemistry', 1);
INSERT INTO class (name, year) VALUES ('geophysics', 1);
INSERT INTO class (name, year) VALUES ('oceanography', 1);
INSERT INTO class (name, year) VALUES ('astronomy', 1);
INSERT INTO class (name, year) VALUES ('meteorites', 1);

-- course
INSERT INTO course (name, description) VALUES ('Biology', 'Beginner');
INSERT INTO course (name, description) VALUES ('Zoology', 'descriptive');
INSERT INTO course (name, description) VALUES ('Chemistry', 'applicational');
INSERT INTO course (name, description) VALUES ('Physics', 'Experimental');
INSERT INTO course (name, description) VALUES ('Geology', 'Theoretical');
INSERT INTO course (name, description) VALUES ('Art', 'History');
INSERT INTO course (name, description) VALUES ('History', 'advanced');
INSERT INTO course (name, description) VALUES ('Politics', 'principles');
INSERT INTO course (name, description) VALUES ('Pedagogy', 'Beginner');
INSERT INTO course (name, description) VALUES ('Pharmacy', 'Theory');