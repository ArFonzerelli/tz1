# tz1

Условия задачи:
Написать программу, которая высисляет бизнес-часы между датами в различных часовых поясах в рамках рабочей бизнес-недели. Праздничные дни не учитываются.

Для работы с временем и датой можно использовать библиотеку Joda-Time.
 
Рабочая бизнес-неделя начинается с понедельника 08:00 AM часового пояса Сингапура и заканчивается пятницей в 19:00 часового пояса Лондона.

Тестовые данные должны быть подготовлены в csv-файле, в количестве не меньше пяти с различными часовыми поясами. Результат выводиттся в виде txt-файла.

Нмиже представлен пример файла с заголовком описания:

startDateTime,startTimeZone,stopDateTime,StopTimeZone

2018-02-05 08:00,Asia/Singapore,2018-02-05 08:00,Asia/Singapore
2018-02-09 09:00,Asia/Singapore,2018-02-20 17:00,Asia/Singapore
2012-02-06 08:00,Asia/Singapore,2012-02-10 19:00,America/New_York
2012-02-10 09:00,Europe/London,2012-02-20 09:00,Europe/London
2012-02-10 09:00,Asia/Singapore,2012-02-20 09:00,Europe/Londom
