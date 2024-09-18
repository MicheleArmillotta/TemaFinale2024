%====================================================================================
% sprintuno description   
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
dispatch( add, add(N) ).
dispatch( update, update(N) ).
dispatch( add2, add2(N) ).
dispatch( update2, update2(N) ).
%====================================================================================
context(ctxsprintuno, "localhost",  "TCP", "8080").
context(ctxbasicrobot, "127.0.0.1",  "TCP", "8020").
 qactor( basicrobot, ctxbasicrobot, "external").
  qactor( wis, ctxsprintuno, "it.unibo.wis.Wis").
 static(wis).
  qactor( incinerator, ctxsprintuno, "it.unibo.incinerator.Incinerator").
 static(incinerator).
  qactor( oprobot, ctxsprintuno, "it.unibo.oprobot.Oprobot").
 static(oprobot).
  qactor( wastestorage, ctxsprintuno, "it.unibo.wastestorage.Wastestorage").
 static(wastestorage).
  qactor( esterno, ctxsprintuno, "it.unibo.esterno.Esterno").
 static(esterno).
  qactor( ashstorage, ctxsprintuno, "it.unibo.ashstorage.Ashstorage").
 static(ashstorage).
