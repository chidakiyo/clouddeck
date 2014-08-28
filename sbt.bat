set SCRIPT_DIR=%~dp0
set FLIGHT_RECORDER=-XX:+UnlockCommercialFeatures -XX:+FlightRecorder
set JMX_SETTING=-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.port=1099
set ADDITION=%FLIGHT_RECORDER% %JMX_SETTING%
java -Dsbt.log.noformat=true %ADDITION% -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=256m -Xmx512M -Xss2M -jar "%SCRIPT_DIR%\sbt-launch-0.13.5.jar" %*

