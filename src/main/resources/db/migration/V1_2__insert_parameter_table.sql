INSERT INTO parameter (name, description, string_value, integer_value, numeric_value, boolean_value, active)
VALUES
    ('MIN_AGE','Idade Mínima', null, 18, null, null,  TRUE),
    ('MAX_AGE','Idade Máxima', null, 60, null, null,  TRUE),
    ('MIN_SCORE_SERASA','Score mínimo de Serasa', null, 300, null, null,  TRUE),
    ('MAX_VALUE_BACEN_CHECKS','Valor máximo de cheques sem fundo', null, null, 500, null,  TRUE),
    ('MAX_VALUE_FINANCIAL_PENDENCY','Valor máximo de pedências financeiras', null, null, 500, null,  TRUE),
    ('MAX_NO_PAYMENT_RATE','Taxa máxima de inadimplência', null, null, 5, null,  TRUE),
    ('MAX_VALUE_STATE_PROTEST','Valor máximo de protestos estaduais', null, null, 500, null,  TRUE),
    ('RF_DOC_SITUATION','Situação do documento na receita federal', 'REGULAR', null, null, null,  TRUE)