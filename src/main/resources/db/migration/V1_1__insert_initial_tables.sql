INSERT INTO analysis_validation (id, name, description, active, created_at, updated_at) VALUES
    (1, 'DOCUMENT_SITUATION', 'Situação do documento', TRUE, now(), NULL),
    (2, 'IS_PEP', 'Se é cliente politicamente exposto', TRUE, now(), NULL),
    (3, 'MAXIMUM_VALUE_FINANCIAL_PENDENCY', 'Valor máximo de pendências financeiras', TRUE, now(), NULL),
    (4, 'MAXIMUM_VALUE_BACEN_PENDENCY', 'Valor máximo de cheques sem fundo', TRUE, now(), NULL),
    (5, 'MAXIMUM_VALUE_STATE_PROTEST', 'Valor máximo de protestos estaduais', TRUE, now(), NULL),
    (6, 'MINIMUM_RATE_SCORE_SERASA', 'Taxa mínima de score Serasa', TRUE, now(), NULL),
    (7, 'MAXIMUM_RATE_NON_PAYMENT', 'Taxa máxima de inadimplência', TRUE, now(), NULL),
    (8, 'AGE', 'Idade mínima e máxima', TRUE, now(), NULL),
    (9, 'DEATH', 'Se consta óbito', TRUE, now(), NULL);