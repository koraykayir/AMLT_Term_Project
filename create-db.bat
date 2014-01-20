createdb -U postgres travelling
pg_restore -U postgres -d travelling -Fc travelling.backup