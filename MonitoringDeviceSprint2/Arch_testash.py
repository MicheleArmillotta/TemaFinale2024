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
with Diagram('testashArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxmonitor', graph_attr=nodeattr):
          monitoring_device=Custom('monitoring_device','./qakicons/symActorSmall.png')
          led=Custom('led','./qakicons/symActorSmall.png')
          sonar=Custom('sonar','./qakicons/symActorSmall.png')
          sonar_device=Custom('sonar_device','./qakicons/symActorSmall.png')
          test_observer=Custom('test_observer','./qakicons/symActorSmall.png')
     sonar_device >> Edge( label='sonardata', **eventedgeattr, decorate='true', fontcolor='red') >> sonar
     sonar >> Edge(color='blue', style='solid',  decorate='true', label='<sonar_value &nbsp; >',  fontcolor='blue') >> monitoring_device
     monitoring_device >> Edge(color='blue', style='solid',  decorate='true', label='<nuovo &nbsp; >',  fontcolor='blue') >> test_observer
     monitoring_device >> Edge(color='blue', style='solid',  decorate='true', label='<ledBlink &nbsp; ledOn &nbsp; ledOff &nbsp; >',  fontcolor='blue') >> led
diag
