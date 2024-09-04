%====================================================================================
% testoprobot description   
%====================================================================================
event( burnEnd, burnEnd(N) ).
dispatch( startBurn, startBurn(N) ).
dispatch( startRobot, startRobot(X) ).
request( engage, engage(OWNER,STEPTIME) ).
reply( engagedone, engagedone(ARG) ).  %%for engage
reply( engagerefused, engagerefused(ARG) ).  %%for engage
request( moverobot, moverobot(TARGETX,TARGETY) ).
dispatch( info, info(X,Y) ).
request( start_test, start_test(X) ).
reply( start_test_reply, start_test_reply(X) ).  %%for start_test
request( fine_test, fine_test(X) ).
reply( fine_test_reply, fine_test_reply(X) ).  %%for fine_test
%====================================================================================
context(ctxtestop, "localhost",  "TCP", "10010").
context(ctxbasicrobot, "127.0.0.1",  "TCP", "8020").
 qactor( basicrobot, ctxbasicrobot, "external").
  qactor( oprobot, ctxtestop, "it.unibo.oprobot.Oprobot").
 static(oprobot).
  qactor( test_observer, ctxtestop, "it.unibo.test_observer.Test_observer").
 static(test_observer).
  qactor( incinerator, ctxtestop, "it.unibo.incinerator.Incinerator").
 static(incinerator).
