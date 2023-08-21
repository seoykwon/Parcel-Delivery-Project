SELECT
    CASE
        WHEN EXISTS (SELECT * FROM Customer WHERE username = 'mattma') THEN 'Customer'
        WHEN EXISTS (SELECT * FROM Driver WHERE username = 'mattma') THEN 'Driver'
        WHEN EXISTS (SELECT * FROM Manager WHERE username = 'mattma') THEN 'Manager'
        ELSE 'Not found'
        END AS user_table;
