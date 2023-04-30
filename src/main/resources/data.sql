INSERT INTO employee(name, created_at, updated_at, delete_flag) VALUES ("煌木　太郎", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
INSERT INTO employee(name, created_at, updated_at, delete_flag) VALUES ("田中　太郎", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
INSERT INTO authentication(code, password, role, employee_id) VALUES ("ktaro", "$2a$08$clh9XaYYznpX9WDqySgiCuUu4znpSeu2oJi5l2Q00UJs42Llrbd7S", "管理者", 1);
INSERT INTO authentication(code, password, role, employee_id) VALUES ("ttaro", "$2a$10$F1k.2HZtkRpoSDymdZCTnuI7eVdoKP.Yb8gtiWmVTKejp53Htlm56", "一般", 2);
INSERT INTO report(content, created_at, report_date, title, updated_at, employee_id) VALUES ("レポートその１レポートその１", CURRENT_TIMESTAMP, "2023-01-01", "タイトルその１", CURRENT_TIMESTAMP, 1);
INSERT INTO report(content, created_at, report_date, title, updated_at, employee_id) VALUES ("レポートその２レポートその２", CURRENT_TIMESTAMP, "2023-02-01", "タイトルその２", CURRENT_TIMESTAMP, 2);
INSERT INTO report(content, created_at, report_date, title, updated_at, employee_id) VALUES ("レポートその３レポートその３", CURRENT_TIMESTAMP, "2023-03-01", "タイトルその３", CURRENT_TIMESTAMP, 1);
INSERT INTO report(content, created_at, report_date, title, updated_at, employee_id) VALUES ("レポートその４レポートその４", CURRENT_TIMESTAMP, "2023-04-01", "タイトルその４", CURRENT_TIMESTAMP, 2);