set HERE=%CD%
set JAVA_HOME=%JAVA_HOME%
set PATH=%JAVA_HOME%\jre\bin;%JAVA_HOME%\bin;%PATH%
set SELENIUM_VERSION=2.44.0
set PLATFORM=WINDOWS

set CHROME_VERSION=30
set CHROME_WEBDRIVER_VERSION=2.13
set CHROME_DRIVER_LOC=%HERE%/CHROMEDRIVERS/%CHROME_WEBDRIVER_VERSION%/chromedriver.exe
set CHROME_BINARY_LOC=C:\Program Files (x86)\Google\Chrome\Application

set FIREFOX_VERSION=30
set FIREFOX_BINARY_LOC=C:\Program Files (x86)\Mozilla Firefox

set IE_VERSION=11
set IE_WEBDRIVER_VERSION=2.44
set IE_DRIVER_LOC=%HERE%/IEDRIVERS/%IE_WEBDRIVER_VERSION%/IEDriverServer.exe

set HUB_HOST=localhost
set HUB_PORT=4444
set HUB_URL=http://%HUB_HOST%:%HUB_PORT%/grid/register

set NODE_HOST=localhost
set NODE_PORT=5559

start java -jar selenium-server-standalone-%SELENIUM_VERSION%.jar -role node -hub %HUB_URL% -host %NODE_HOST% -port %NODE_PORT% -browser "browserName=firefox,version=%FIREFOX_VERSION%,firefox_binary=%FIREFOX_BINARY_LOC%,maxInstances=5,platform=%PLATFORM%" -browser "browserName=chrome,version=%CHROME_VERSION%,chrome_binary=%CHROME_BINARY_LOC%,maxInstances=5,platform=%PLATFORM%" -browser "browserName=internet explorer,version=%IE_VERSION%,maxInstances=5,platform=%PLATFORM%" -log SELENIUMLOGS\selnode-%NODE_PORT%.log
