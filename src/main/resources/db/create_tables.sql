-- Clean database on every start (comment this in production) ----------------------------------------------

DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

-- Entities ------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS doctor
(
    doctor_id BIGSERIAL PRIMARY KEY
    ,doctor_firstname VARCHAR(30) NOT NULL
    ,doctor_lastname VARCHAR(30) NOT NULL
    ,doctor_specialization VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS patient
(
    patient_id BIGSERIAL PRIMARY KEY
    ,patient_ifc VARCHAR (16) UNIQUE NOT NULL
    ,patient_firstname VARCHAR(30) NOT NULL
    ,patient_lastname VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS health_facility
(
    health_facility_id BIGSERIAL PRIMARY KEY
    ,health_facility_name VARCHAR(30) NOT NULL
    ,health_facility_tipology VARCHAR(30) NOT NULL
    ,health_facility_region VARCHAR(150) NOT NULL
    ,health_facility_province VARCHAR(150) NOT NULL
    ,health_facility_address VARCHAR(150) NOT NULL
    ,healthFacility_health_facility_id BIGINT NOT NULL
    ,FOREIGN KEY (healthFacility_health_facility_id) REFERENCES health_facility (health_facility_id)
);

CREATE TABLE IF NOT EXISTS medical_report
(
    medical_report_id BIGSERIAL PRIMARY KEY
    ,medical_report_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS covid_test
(
    PRIMARY KEY (medical_report_id)
    ,ct_result VARCHAR(8) NOT NULL
) INHERITS (medical_report);

CREATE TABLE IF NOT EXISTS blood_test
(
    PRIMARY KEY (medical_report_id)
    ,bt_wbc DOUBLE PRECISION NOT NULL
    ,bt_rbc DOUBLE PRECISION NOT NULL
    ,bt_hb DOUBLE PRECISION NOT NULL
    ,bt_ht INTEGER NOT NULL
    ,bt_mcv INTEGER NOT NULL
    ,bt_mch INTEGER NOT NULL
    ,bt_mchc INTEGER NOT NULL
    ,bt_rdw DOUBLE PRECISION NOT NULL
    ,bt_plts INTEGER NOT NULL
    ,bt_mpv DOUBLE PRECISION NOT NULL
    ,bt_descr VARCHAR(1000) NOT NULL
) INHERITS (medical_report);

-- Relationships -------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS patient_doctor
(
    patient_id BIGINT REFERENCES patient (patient_id) ON UPDATE CASCADE ON DELETE CASCADE
    ,doctor_id BIGINT REFERENCES doctor (doctor_id) ON UPDATE CASCADE ON DELETE CASCADE
    ,CONSTRAINT patient_doctor_pkey PRIMARY KEY (patient_id, doctor_id)
);

CREATE TABLE IF NOT EXISTS patient_medical_report
(
    patient_id BIGINT REFERENCES patient (patient_id) ON UPDATE CASCADE ON DELETE CASCADE
    ,medical_report_id BIGINT REFERENCES medical_report (medical_report_id) ON UPDATE CASCADE ON DELETE CASCADE
    ,CONSTRAINT patient_medical_report_pkey PRIMARY KEY (patient_id, medical_report_id)
);

CREATE TABLE IF NOT EXISTS health_facility_medical_report
(
    health_facility_id BIGINT REFERENCES health_facility (health_facility_id) ON UPDATE CASCADE ON DELETE CASCADE
    ,medical_report_id BIGINT REFERENCES medical_report (medical_report_id) ON UPDATE CASCADE ON DELETE CASCADE
    ,CONSTRAINT health_facility_medical_report_pkey PRIMARY KEY (health_facility_id, medical_report_id)
);