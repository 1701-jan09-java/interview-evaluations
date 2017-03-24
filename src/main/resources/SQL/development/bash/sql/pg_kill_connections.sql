-- Postgres 9.2 and later
SELECT pg_terminate_backend(pg_stat_activity.pid)
FROM pg_stat_activity
WHERE pg_stat_activity.datname = 'evaluations'
  AND pid <> pg_backend_pid();

-- Postgres 9.1 and earlier  
SELECT pg_terminate_backend(pg_stat_activity.pid)
FROM pg_stat_activity
WHERE pg_stat_activity.datname = 'evaluations'
  AND pid <> pg_backend_pid();