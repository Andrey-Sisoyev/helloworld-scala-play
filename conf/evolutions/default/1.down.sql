
--- !Downs

DROP FUNCTION IF EXISTS getBookHeatMap(integer, integer);
DROP FUNCTION IF EXISTS getBookRDates(integer, integer);
DROP FUNCTION IF EXISTS getBookStickiness(integer, date);
DROP TYPE IF EXISTS PosInterval;
DROP TYPE IF EXISTS PosIntervalHeat;
DROP TYPE IF EXISTS DateReads;
DROP TYPE IF EXISTS ReadAmountPopularity;

DROP TABLE IF EXISTS coverage CASCADE;
drop SEQUENCE IF EXISTS s_coverage_id;

DROP TABLE IF EXISTS books CASCADE;
drop SEQUENCE IF EXISTS s_book_id;
