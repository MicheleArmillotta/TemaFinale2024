%====================================================================================
% sprintzero description   
%====================================================================================
event( start, start(x) ).
event( endBurn, endBurn(x) ).
%====================================================================================
context(ctxservicearea, "localhost",  "TCP", "8001").
context(ctxmonitoringdevice, "localhost",  "TCP", "8002").
context(ctxservicegui, "localhost",  "TCP", "8003").
context(ctxbasicrobot, "127.0.0.1",  "TCP", "8020").
 qactor( basicrobot, ctxbasicrobot, "external").
  qactor( wis, ctxservicearea, "it.unibo.wis.Wis").
 static(wis).
  qactor( oprobot, ctxservicearea, "it.unibo.oprobot.Oprobot").
 static(oprobot).
  qactor( incinerator, ctxservicearea, "it.unibo.incinerator.Incinerator").
 static(incinerator).
  qactor( monitoringdevice, ctxmonitoringdevice, "it.unibo.monitoringdevice.Monitoringdevice").
 static(monitoringdevice).
  qactor( servicegui, ctxservicegui, "it.unibo.servicegui.Servicegui").
 static(servicegui).
