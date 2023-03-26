# window 
netstat -ano | findstr 8090
tasklist | findstr 15592
taskkill -PID 15592 -F

