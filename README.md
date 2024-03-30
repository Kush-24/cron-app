In this Application, I have provided the application and cmd tool file. 

1> For application I have exposed the one api where we need to pass the cron expression and 
that cron expression will validate the cron expression with basic checks and return the cron 
expression in human redable form

Request:
curl --location --request GET 'http://localhost:8080/cronexpression/parser' \
--header 'Content-Type: application/json' \
--data '{
  "cronExpression":"*/15 0 1,15 * 1-5"
}'

Response:
{
    "map": {
        "hour": "0",
        "month": "1 10 11 12 2 3 4 5 6 7 8 9",
        "day of month": "1 15",
        "day of week": "1 2 3 4 5",
        "minute": "0 15 30 45"
    }
}
