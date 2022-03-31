-- Clean database on every start ---------------------------------------------------------------------------

TRUNCATE doctor, patient, health_facility, medical_report, covid_test, blood_test, patient_doctor, patient_medical_report, health_facility_medical_report;

-- Entities ------------------------------------------------------------------------------------------------

INSERT INTO doctor(doctor_id, doctor_firstname, doctor_lastname,doctor_specialization)
VALUES  (1,'Luigi','Sacco','Medico')
     ,(2,'Ernestina','Paper','Medico')
     ,(3,'Agostino','Bertani','Medico')
     ,(4,'Adelasia','Cocco','Ematologo')
     ,(5,'Anna','Kuliscioff','Ginecologo')
     ,(6,'Ida','Silvestroni','Medico')
     ,(7,'Ambrogio','Gherini','Chirurgo');

INSERT INTO patient (patient_id, patient_ifc, patient_firstname, patient_lastname)
VALUES  (1,'123ASD456CVB789D', 'Paziente', 'Uno')
     ,(2,'098HDS321JKL345H', 'Paziente', 'Due')
     ,(3,'RU8DIKSJW0394IKS', 'Paziente', 'Tre');

INSERT INTO health_facility (health_facility_id, health_facility_name, health_facility_tipology, health_facility_region, health_facility_province, health_facility_address, healthFacility_health_facility_id)
VALUES  (1,'Ospedale di Milano', 'Struttura Pubblica', 'Lombardia', 'Milano', 'Via Donizetti, 13', 1)
     ,(2,'Ospedale di Bologna', 'Struttura Privata', 'Emilia Romagna', 'Bologna', 'Via Garibaldi, 13', 2)
     ,(3,'Laboratorio Analisi Covid', 'Laboratorio Pubblico', 'Lombardia', 'Milano', 'Via Donizetti, 13', 1)
     ,(4,'Laboratorio Analisi Sangue', 'Laboratorio Pubblico', 'Lombardia', 'Milano', 'Via Donizetti, 13', 1);

INSERT INTO medical_report (medical_report_id, medical_report_date)
VALUES  (1, '2002-01-01')
     ,(2,'2220-01-01')
     ,(3,'2011-11-11');

INSERT INTO covid_test (ct_result, medical_report_id, medical_report_date)
VALUES  ('Negativo',1,'2002-01-01')
     ,('Positivo',2,'2220-01-01');

INSERT INTO blood_test (bt_wbc, bt_rbc, bt_hb, bt_ht, bt_mcv, bt_mch, bt_mchc, bt_rdw, bt_plts, bt_mpv, bt_descr, medical_report_id, medical_report_date)
VALUES  (1.2,20.3,400.5,10,20,30,40,1.2,100,100.2,'ok',3,'2011-11-11');

-- Relationships -------------------------------------------------------------------------------------------

INSERT INTO patient_doctor (patient_id, doctor_id)
VALUES  (1,1)
     ,(3,1);

INSERT INTO patient_medical_report (patient_id, medical_report_id)
VALUES  (1,1)
     ,(1,3)
     ,(2,2);

INSERT INTO health_facility_medical_report (health_facility_id, medical_report_id)
VALUES  (3,1)
     ,(3,2)
     ,(4,3);