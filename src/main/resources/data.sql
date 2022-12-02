insert into "user" (id, username, name, password)
values (1,
        'user',
        'Max Mustermann',
        '$2a$10$xdbKoM48VySZqVSU/cSlVeJn0Z04XCZ7KZBjUBC00eKo5uLswyOpe'),
       (2,
        'admin',
        'Andrea Admin',
        '$2a$10$jpLNVNeA7Ar/ZQ2DKbKCm.MuT2ESe.Qop96jipKMq7RaUgCoQedV.');
insert into user_has_role (user_id, role_name)
values (1, 'USER'),
       (2, 'USER'),
       (2, 'ADMIN');
insert into course (id, creation_date, description, title, instructor_user_id)
values (1, '2022-11-29', 'Hier lernen Sie den richtigen Umgang mit einem Anker-Akkusystem.', 'Anker-Akkusystem', 2);
insert into user_attends_course (user_id, course_id)
values (1, 1);
insert into file_type (id, description)
values (1, 'image'),
       (2, 'video');
insert into media_file (id, name, path, file_type_id)
values (1, 'image1', 'images/courses/01.png', 1),
       (2, 'image2', 'images/courses/02.png', 1),
       (3, 'image3', 'images/courses/03.png', 1),
       (4, 'image4', 'images/courses/04.png', 1),
       (5, 'image5', 'images/courses/05.jpg', 1),
       (6, 'image6', 'images/courses/06.png', 1),
       (7, 'image7', 'images/courses/07.png', 1);
insert into details (id, text, media_file_id)
values (1, 'Auf jeder Vorschauseite erhalten sie detaillierte Informationen, die am Ende in Form eines Quizzes abgefragt werden. Der Klick auf "mehr anzeigen" wird bei jedem einzelnen Abschnitt dringend empfohlen, da das hier vorhandene Wissen absolut relevant ist.<br><br>Natürlich können erfahrene Nutzer dieser Gerätschaften die genauen Erklärungen aber gerne überspringen. Bitte beachten Sie allerdings, dass auch Sie als erfahrene Nutzer das Quiz am Ende erfolgreich abschließen müssen, um bei der nächsten Kündigungswelle nicht vorgemerkt zu werden.', null),
       (2, 'Um eine Langlebigkeit des Gerätes zu gewährleisten, empfehlen wir das Gerät mindestens bis zur 60%-Marke aufzuladen!', null),
       (3, 'Gesetzt wird bei dem System auf die LiFePo4-Akkutechnik. Diese glänzt mit einer hervorragenden Zyklenzahl von 3000 und kann daher deutlich länger treue Dienste leisten als gängige Lithium-Akkus.<br><br>Die Zyklenzahl dieses Akkusystems übertrifft Konkurrenzprodukte um den Faktor 6.', null),
       (4, 'Vorsicht! Auch ohne Verbraucher entlädt sich der Akku ganz natürlich über die Monate hinweg. Empfohlen wird daher, dass der Akku alle 3 Monate von 30% auf ca. 60% erneut geladen wird. Für die anfallenden Stromkosten des Firmengerätes müssen Sie aufkommen.', null),
       (5, 'Der Knopf zur Aktivierung des Car Sockets oder eben auch für den gängigen Stromstecker befindet sich unterhalb des jeweiligen Anschlusses. Ist der Stromfluss gegeben, so leuchtet das Lämpchen weiß. Wenn nicht, ist das Lämpchen aus.', null),
       (6, 'Ein kurzer Knopfdruck aktiviert das Licht. Ein langer Knopfdruck aktiviert den SOS-Modus.', null),
       (7, 'Tipp: Liefert der Akku immer Strom oder nur unter gewissen Umständen? Was waren das für Umstände und wie verursacht man diese?', null),
       (8, 'Tipp: Achte genau darauf, was die Frage wirklich als Antwort erwartet und erinnere dich daran, was zur Akkutechnik erklärt wurde!', null),
       (9, 'Tipp: Halte dir vor Augen, dass alle Knöpfe des Systems beschrieben wurden. Welchen Knopf gab es da?', null),
       (10, 'Tipp: Unter welchen Umständen reagiert das Gerät nicht? ', null);
insert into learning_unit (id, title, creation_date, course_id)
values (1, 'Wie nutzt man die ANKER-Powerbank korrekt?', '2022-11-29', 1);
insert into learning_page (id, page_number, title, text, learning_unit_id, details_id, is_first_page, is_last_page)
values (1, 1, 'Einführung, worum geht''s?', 'Für den Firmenausflug bei Stultus Industries wurde Ihnen ein firmeneigenes Anker-Akkusystem zur Verfügung gestellt. Dieses Gerät wird ihnen beim Camping treue Dienste erweisen und ihre elektronischen Geräte mit Strom versorgen, damit sie auch auf dem Betriebsausflug, der für Urlaub gedacht ist ihre Pflichtzahl von 50 Arbeitsstunden pro Woche erfüllen können.', 1, 1, true, false),
       (2, 2, 'Einführung, worum geht''s?', 'Um das Gerät in Betrieb nehmen zu können, müssen Sie es mit einer erstmaligen Aufladung an einer gängigen Schuko-Steckdose aktivieren.', 1, 2, false, false),
       (3, 3, 'Einführung, worum geht''s?', 'Akkus der Firma Anker sollten im besten Fall nicht komplett auf und entladen werden.', 1, 3, false, false),
       (4, 4, 'Einführung, worum geht''s?', 'Natürlich kann man sein Anker-Akku-System jederzeit in den Tiefschlaf versetzen, bitte laden sie es allerdings dafür alle 3 Monate wieder erneut ein wenig auf.', 1, 4, false, false),
       (5, 5, 'Einführung, worum geht''s?', 'Die Anschlüsse sind gängigerweise, auch, wenn das Display aktiviert ist, deaktiviert. Drücken Sie zur Aktivierung den dafür vorgesehenen Button.', 1, 5, false, false),
       (6, 6, 'Einführung, worum geht''s?', 'Eingebaut ist eine Lampe, die per Knopfdruck bedient werden kann!', 1, 6, false, true);
insert into learning_page_has_media_file (learning_page_id, media_file_id)
values (1, 1),
       (1, 2),
       (2, 3),
       (3, 4),
       (3, 5),
       (4, 6),
       (4, 4),
       (5, 7),
       (6, 2);
insert into quiz (id, creation_date, is_random_order, title, course_id)
values (1, '2022-11-29', false, 'Es wird Zeit das gelernte Wissen zu prüfen!', 1);
insert into quiz_page (id, is_random_order, page_number, title, text, quiz_id, details_id, is_first_page, is_last_page)
values (1, false, 1, 'Mehrfach-Antworten möglich!', '', 1, 7, true, false),
       (2, false, 2, 'Mehrfach-Antworten möglich!', '', 1, 8, false, false),
       (3, false, 3, 'Nur eine Antwort ist korrekt!', '', 1, 9, false, false),
       (4, false, 4, 'Mehrfach-Antworten möglich!', '', 1, 10, false, true);
insert into question (id, are_answer_options_in_random_order, is_multiple_choice, text, quiz_page_id, media_file_id)
values (1, false, true, 'Warum liefert das Gerät aktuell keinen Strom?', 1, 4),
       (2, false, true, 'In welchen Abständen sollte ich das Gerät von meinem Geld aufladen, um die Haltbarkeit des Firmeneigentums zu gewährleisten?', 2, null),
       (3, false, false, 'Wie aktiviere ich den SOS-Modus?', 3, null),
       (4, false, true, 'Das Gerät reagiert nicht. Was habe ich vergessen?', 4, null);
insert into answer_option (id, is_correct, position, text, question_id, media_file_id)
values (1, false, 1, 'Das Gerät ist nicht voll aufgeladen', 1, null),
       (2, false, 2, 'Steckdose ist nicht aktiv', 1, null),
       (3, true, 3, 'Es steckt kein Stromstecker', 1, null),
       (4, false, 4, 'Das Gerät ist defekt', 1, null),
       (5, false, 1, 'Alle drei Monate, wenn ich plane das Gerät länger nicht zu nutzen', 2, null),
       (6, false, 2, 'Überhaupt nicht, das ist irrelevant', 2, null),
       (7, true, 3, 'Aufgeladen werden soll es auf mind. 60%', 2, null),
       (8, false, 4, 'So oft, dass es durchgehend auf 90% Ladung gehalten wird', 2, null),
       (9, false, 1, 'Per Spracheingabe. Laut "Hilfe, Hilfe, Feuer, Alarm" rufen', 3, null),
       (10, false, 2, 'Dreifach den Licht-Knopf drucken', 3, null),
       (11, true, 3, 'Licht-Knopf gedrückt halten', 3, null),
       (12, false, 4, 'Den SOS-Knopf auf der Unterseite des Gerätes drücken', 3, null),
       (13, false, 1, 'Das Gerät wurde unter Umständen noch nicht per Aufladung aktiviert?', 4, null),
       (14, false, 2, 'Der Speicher ist leer und das Display somit inaktiv', 4, null),
       (15, true, 3, 'Display-Knopf zum Hochfahren gedrückt halten', 4, null),
       (16, false, 4, 'Nichts, das Gerät sollte immer reagieren', 4, null);