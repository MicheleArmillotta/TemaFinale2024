%====================================================================================
% sprint3 description   
%====================================================================================
dispatch( info, info(X,Y) ).
dispatch( data, data(X,Y,Z,K) ).
dispatch( numRP, numRP(X) ).
dispatch( statoIn, statoIn(N) ).
dispatch( statoOp, statoOp(N) ).
dispatch( valAsh, valAsh(X) ).
request( start_test, start_test(X) ).
reply( start_test_reply, start_test_reply(X) ).  %%for start_test
%====================================================================================
context(ctxsprintdue, "localhost",  "TCP", "8080").
 qactor( observedactor, ctxsprintdue, "it.unibo.observedactor.Observedactor").
 static(observedactor).
  qactor( test_observer, ctxsprintdue, "it.unibo.test_observer.Test_observer").
 static(test_observer).
