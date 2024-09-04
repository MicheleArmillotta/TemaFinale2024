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
with Diagram('sprintunoArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxsprintuno', graph_attr=nodeattr):
          wis=Custom('wis','./qakicons/symActorSmall.png')
          incinerator=Custom('incinerator','./qakicons/symActorSmall.png')
          oprobot=Custom('oprobot','./qakicons/symActorSmall.png')
     with Cluster('ctxbasicrobot', graph_attr=nodeattr):
          basicrobot=Custom('basicrobot(ext)','./qakicons/externalQActor.png')
     incinerator >> Edge( label='burnEnd', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     sys >> Edge( label='burnEnd', **evattr, decorate='true', fontcolor='darkgreen') >> oprobot
     oprobot >> Edge(color='magenta', style='solid', decorate='true', label='<engage<font color="darkgreen"> engagedone engagerefused</font> &nbsp; moverobot &nbsp; >',  fontcolor='magenta') >> basicrobot
     oprobot >> Edge(color='blue', style='solid',  decorate='true', label='<info &nbsp; >',  fontcolor='blue') >> wis
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<activation_command &nbsp; startBurn &nbsp; >',  fontcolor='blue') >> incinerator
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<startRobot &nbsp; >',  fontcolor='blue') >> oprobot
diag
