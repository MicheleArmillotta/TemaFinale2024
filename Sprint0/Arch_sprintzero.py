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
with Diagram('sprintzeroArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxservicearea', graph_attr=nodeattr):
          wis=Custom('wis','./qakicons/symActorWithobjSmall.png')
          oprobot=Custom('oprobot','./qakicons/symActorSmall.png')
          incinerator=Custom('incinerator','./qakicons/symActorSmall.png')
     with Cluster('ctxmonitoringdevice', graph_attr=nodeattr):
          monitoringdevice=Custom('monitoringdevice','./qakicons/symActorWithobjSmall.png')
     with Cluster('ctxservicegui', graph_attr=nodeattr):
          servicegui=Custom('servicegui','./qakicons/symActorSmall.png')
     with Cluster('ctxbasicrobot', graph_attr=nodeattr):
          basicrobot=Custom('basicrobot(ext)','./qakicons/externalQActor.png')
     sys >> Edge( label='endBurn', **evattr, decorate='true', fontcolor='darkgreen') >> wis
     sys >> Edge( label='endBurn', **evattr, decorate='true', fontcolor='darkgreen') >> oprobot
     sys >> Edge( label='start', **evattr, decorate='true', fontcolor='darkgreen') >> incinerator
     incinerator >> Edge( label='endBurn', **eventedgeattr, decorate='true', fontcolor='red') >> sys
diag
