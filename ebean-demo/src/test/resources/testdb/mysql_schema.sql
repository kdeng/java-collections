drop table if exists default_cw_member;

CREATE TABLE OSNZ.default_cw_member (
  id                  BIGINT          unsigned NOT NULL AUTO_INCREMENT,
  email               VARCHAR(50)     DEFAULT NULL,
  mobile              VARCHAR(20)     NOT NULL,
  password            VARCHAR(50)     NOT NULL,
  created_on          DATETIME        NOT NULL,
  updated_on          DATETIME        NOT NULL,
  date_verified_email DATETIME        DEFAULT NULL,
  verified_email      INT             NOT NULL,
  role                VARCHAR(50)     NOT NULL,
  credit_point        INTEGER         NOT NULL,
  pending_expense     DECIMAL(12, 2)  NOT NULL,
  total_expense       DECIMAL(12, 2)  NOT NULL,
  active              INT             NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mobile_UNIQUE` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1;
