%====================================================================================
% testwis description   
%====================================================================================
dispatch( startRobot, startRobot(X) ).
dispatch( startBurn, startBurn(X) ).
dispatch( info, info(X,Y) ).
dispatch( infotest, infotest(X,Y) ).
request( burn_test, burn_test(N) ).
reply( burn_reply, burn_reply(X) ).  %%for burn_test
request( start_test, start_test(N) ).
reply( start_reply, start_reply(N) ).  %%for start_test
%====================================================================================
context(ctxtestwis, "localhost",  "TCP", "8080").
 qactor( wis, ctxtestwis, "it.unibo.wis.Wis").
 static(wis).
  qactor( wisobserver, ctxtestwis, "it.unibo.wisobserver.Wisobserver").
 static(wisobserver).
