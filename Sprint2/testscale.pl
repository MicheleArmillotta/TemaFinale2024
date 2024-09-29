%====================================================================================
% testscale description   
%====================================================================================
request( start_test, start_test(X) ).
reply( start_test_reply, start_test_reply(X) ).  %%for start_test
dispatch( infotest, infotest(X) ).
dispatch( doread, doread(X) ).
event( scaledata, scaledata(D) ).
dispatch( scalework, scalework(X) ).
%====================================================================================
context(ctxtestscale, "localhost",  "TCP", "8080").
 qactor( wastestorage, ctxtestscale, "it.unibo.wastestorage.Wastestorage").
 static(wastestorage).
  qactor( test_observer, ctxtestscale, "it.unibo.test_observer.Test_observer").
 static(test_observer).
