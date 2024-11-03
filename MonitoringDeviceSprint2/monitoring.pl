%====================================================================================
% monitoring description   
%====================================================================================
event( sonardata, sonardata(D) ).
dispatch( ledOn, ledOn(X) ).
dispatch( ledOff, ledOff(X) ).
dispatch( ledBlink, ledBlink(X) ).
dispatch( valAsh, valAsh(X) ).
request( pollingAsh, pollingAsh(X) ).
reply( valueAsh, valueAsh(X) ).  %%for pollingAsh
dispatch( turnLedOn, turnLedOn(X) ).
dispatch( turnLedOff, turnLedOff(X) ).
dispatch( sonar_value, sonar_value(X) ).
dispatch( deposit_ash, deposit_ash(X) ).
%====================================================================================
context(ctxmonitor, "localhost",  "TCP", "8128").
context(ctxsprintdue, "127.0.0.1",  "TCP", "8080").
 qactor( wis, ctxsprintdue, "external").
  qactor( observedactor, ctxsprintdue, "external").
  qactor( monitoring_device, ctxmonitor, "it.unibo.monitoring_device.Monitoring_device").
 static(monitoring_device).
  qactor( led, ctxmonitor, "it.unibo.led.Led").
 static(led).
  qactor( sonar, ctxmonitor, "it.unibo.sonar.Sonar").
 static(sonar).
  qactor( sonar_device, ctxmonitor, "it.unibo.sonar_device.Sonar_device").
 static(sonar_device).
