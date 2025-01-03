### conda install diagrams
from diagrams import Cluster, Diagram, Edge
from diagrams.custom import Custom
import os
os.environ['PATH'] += os.pathsep + 'C:/Program Files/Graphviz/bin/'

graphattr = {     #https://www.graphviz.org/doc/info/attrs.html
    'fontsize': '22',
}

nodeattr = {   
    'fontsize': '22',
    'bgcolor': 'lightyellow'
}

eventedgeattr = {
    'color': 'red',
    'style': 'dotted'
}
evattr = {
    'color': 'darkgreen',
    'style': 'dotted'
}
with Diagram('testfunzionaleArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxtest', graph_attr=nodeattr):
          wis=Custom('wis','./qakicons/symActorSmall.png')
          incinerator=Custom('incinerator','./qakicons/symActorSmall.png')
          oprobot=Custom('oprobot','./qakicons/symActorSmall.png')
          wastestorage=Custom('wastestorage','./qakicons/symActorSmall.png')
          monitoring_device=Custom('monitoring_device','./qakicons/symActorSmall.png')
          led=Custom('led','./qakicons/symActorSmall.png')
          test_observer=Custom('test_observer','./qakicons/symActorSmall.png')
     with Cluster('ctxbasicrobot', graph_attr=nodeattr):
          basicrobot=Custom('basicrobot(ext)','./qakicons/externalQActor.png')
     sys >> Edge( label='burnEnd', **evattr, decorate='true', fontcolor='darkgreen') >> wis
     incinerator >> Edge( label='burnEnd', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     sys >> Edge( label='burnEnd', **evattr, decorate='true', fontcolor='darkgreen') >> oprobot
     test_observer >> Edge( label='scaledata', **eventedgeattr, decorate='true', fontcolor='red') >> wastestorage
     oprobot >> Edge(color='magenta', style='solid', decorate='true', label='<engage<font color="darkgreen"> engagedone engagerefused</font> &nbsp; moverobot &nbsp; >',  fontcolor='magenta') >> basicrobot
     wis >> Edge(color='magenta', style='solid', decorate='true', label='<pollingAsh<font color="darkgreen"> valueAsh valueAsh</font> &nbsp; >',  fontcolor='magenta') >> monitoring_device
     wis >> Edge(color='magenta', style='solid', decorate='true', label='<pollingRP<font color="darkgreen"> numberRP</font> &nbsp; >',  fontcolor='magenta') >> wastestorage
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<turnLedOn &nbsp; turnLedOff &nbsp; addAsh &nbsp; >',  fontcolor='blue') >> monitoring_device
     oprobot >> Edge(color='blue', style='solid',  decorate='true', label='<info &nbsp; >',  fontcolor='blue') >> wis
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<activation_command &nbsp; startBurn &nbsp; >',  fontcolor='blue') >> incinerator
     monitoring_device >> Edge(color='blue', style='solid',  decorate='true', label='<infotest &nbsp; >',  fontcolor='blue') >> test_observer
     monitoring_device >> Edge(color='blue', style='solid',  decorate='true', label='<ledBlink &nbsp; ledOn &nbsp; ledOff &nbsp; >',  fontcolor='blue') >> led
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<startRobot &nbsp; >',  fontcolor='blue') >> oprobot
     oprobot >> Edge(color='blue', style='solid',  decorate='true', label='<pickRP &nbsp; >',  fontcolor='blue') >> wastestorage
diag
