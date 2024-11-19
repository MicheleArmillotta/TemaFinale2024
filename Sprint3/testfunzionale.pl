%====================================================================================
% testfunzionale description   
%====================================================================================
event( burnEnd, burnEnd(N) ).
dispatch( activation_command, activation_command(N) ).
dispatch( startBurn, startBurn(N) ).
dispatch( startRobot, startRobot(X) ).
request( engage, engage(OWNER,STEPTIME) ).
reply( engagedone, engagedone(ARG) ).  %%for engage
reply( engagerefused, engagerefused(ARG) ).  %%for engage
request( moverobot, moverobot(TARGETX,TARGETY) ).
dispatch( info, info(X,Y) ).
dispatch( numRP, numRP(X) ).
dispatch( statoIn, statoIn(N) ).
dispatch( statoOp, statoOp(N) ).
dispatch( valAsh, valAsh(X) ).
dispatch( addAsh, addAsh(X) ).
dispatch( doread, doread(X) ).
event( scaledata, scaledata(D) ).
dispatch( scalework, scalework(X) ).
request( pollingRP, pollingRP(X) ).
reply( numberRP, numberRP(X) ).  %%for pollingRP
request( pollingAsh, pollingAsh(X) ).
reply( valueAsh, valueAsh(X) ).  %%for pollingAsh
dispatch( pickRP, pickRP(X) ).
dispatch( deposit_ash, deposit_ash(X) ).
dispatch( turnLedOn, turnLedOn(X) ).
dispatch( turnLedOff, turnLedOff(X) ).
event( sonardata, sonardata(D) ).
dispatch( ledOn, ledOn(X) ).
dispatch( ledOff, ledOff(X) ).
dispatch( ledBlink, ledBlink(X) ).
dispatch( valAsh, valAsh(X) ).
request( pollingAsh, pollingAsh(X) ).
reply( valueAsh, valueAsh(X) ).  %%for pollingAsh
dispatch( turnLedOn, turnLedOn(X) ).
dispatch( turnLedOff, turnLedOff(X) ).
request( start_test, start_test(X) ).
reply( start_test_reply, start_test_reply(X) ).  %%for start_test
dispatch( infotest, infotest(X) ).
dispatch( sonar_value, sonar_value(X) ).
dispatch( deposit_ash, deposit_ash(X) ).
%====================================================================================
context(ctxtext, "localhost",  "TCP", "8080").
context(ctxbasicrobot, "127.0.0.1",  "TCP", "8020").
 qactor( basicrobot, ctxbasicrobot, "external").
  qactor( wis, ctxtext, "it.unibo.wis.Wis").
 static(wis).
  qactor( incinerator, ctxtext, "it.unibo.incinerator.Incinerator").
 static(incinerator).
  qactor( oprobot, ctxtext, "it.unibo.oprobot.Oprobot").
 static(oprobot).
  qactor( scale, ctxtext, "it.unibo.scale.Scale").
 static(scale).
  qactor( scale_device, ctxtext, "it.unibo.scale_device.Scale_device").
 static(scale_device).
  qactor( monitoring_device, ctxtext, "it.unibo.monitoring_device.Monitoring_device").
 static(monitoring_device).
  qactor( led, ctxtext, "it.unibo.led.Led").
 static(led).
  qactor( sonar, ctxtext, "it.unibo.sonar.Sonar").
 static(sonar).
  qactor( sonar_device, ctxtext, "it.unibo.sonar_device.Sonar_device").
 static(sonar_device).
  qactor( test_observer, ctxtext, "it.unibo.test_observer.Test_observer").
 static(test_observer).
