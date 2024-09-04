%====================================================================================
% testincinerator description   
%====================================================================================
event( burnEnd, burnEnd(N) ).
dispatch( activation_command, activation_command(N) ).
dispatch( startBurn, startBurn(N) ).
dispatch( info, info(N) ).
request( activation_test, activation_test(N) ).
reply( activation_reply, activation_reply(X) ).  %%for activation_test
request( start_test, start_test(N) ).
reply( start_reply, start_reply(N) ).  %%for start_test
%====================================================================================
context(ctxtest, "localhost",  "TCP", "8080").
 qactor( incinerator, ctxtest, "it.unibo.incinerator.Incinerator").
 static(incinerator).
  qactor( incineratorobserver, ctxtest, "it.unibo.incineratorobserver.Incineratorobserver").
 static(incineratorobserver).
