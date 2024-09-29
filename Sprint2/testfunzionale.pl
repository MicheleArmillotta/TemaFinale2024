%====================================================================================
% testfunzionale description   
%====================================================================================
request( start_test, start_test(X) ).
reply( start_test_reply, start_test_reply(X) ).  %%for start_test
dispatch( infotest, infotest(X) ).
dispatch( addAsh, addAsh(X) ).
event( sonardata, sonardata(D) ).
dispatch( ledOn, ledOn(X) ).
dispatch( ledOff, ledOff(X) ).
dispatch( ledBlink, ledBlink(X) ).
request( pollingAsh, pollingAsh(X) ).
reply( valueAsh, valueAsh(X) ).  %%for pollingAsh
dispatch( turnLedOn, turnLedOn(X) ).
dispatch( turnLedOff, turnLedOff(X) ).
dispatch( sonar_value, sonar_value(X) ).
event( burnEnd, burnEnd(N) ).
dispatch( activation_command, activation_command(N) ).
dispatch( startBurn, startBurn(N) ).
dispatch( startRobot, startRobot(X) ).
request( engage, engage(OWNER,STEPTIME) ).
reply( engagedone, engagedone(ARG) ).  %%for engage
reply( engagerefused, engagerefused(ARG) ).  %%for engage
request( moverobot, moverobot(TARGETX,TARGETY) ).
dispatch( info, info(X,Y) ).
dispatch( doread, doread(X) ).
event( scaledata, scaledata(D) ).
dispatch( scalework, scalework(X) ).
request( pollingRP, pollingRP(X) ).
reply( numberRP, numberRP(X) ).  %%for pollingRP
request( pollingAsh, pollingAsh(X) ).
reply( valueAsh, valueAsh(X) ).  %%for pollingAsh
dispatch( pickRP, pickRP(X) ).
dispatch( turnLedOn, turnLedOn(X) ).
dispatch( turnLedOff, turnLedOff(X) ).
%====================================================================================
context(ctxtest, "localhost",  "TCP", "8080").
context(ctxbasicrobot, "127.0.0.1",  "TCP", "8020").
 qactor( basicrobot, ctxbasicrobot, "external").
  qactor( wis, ctxtest, "it.unibo.wis.Wis").
 static(wis).
  qactor( incinerator, ctxtest, "it.unibo.incinerator.Incinerator").
 static(incinerator).
  qactor( oprobot, ctxtest, "it.unibo.oprobot.Oprobot").
 static(oprobot).
  qactor( wastestorage, ctxtest, "it.unibo.wastestorage.Wastestorage").
 static(wastestorage).
  qactor( monitoring_device, ctxtest, "it.unibo.monitoring_device.Monitoring_device").
 static(monitoring_device).
  qactor( led, ctxtest, "it.unibo.led.Led").
 static(led).
  qactor( test_observer, ctxtest, "it.unibo.test_observer.Test_observer").
 static(test_observer).
