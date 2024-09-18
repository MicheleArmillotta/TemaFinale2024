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
with Diagram('testwisArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxtestwis', graph_attr=nodeattr):
          wis=Custom('wis','./qakicons/symActorSmall.png')
          wisobserver=Custom('wisobserver','./qakicons/symActorSmall.png')
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<startRobot &nbsp; startBurn &nbsp; infotest &nbsp; >',  fontcolor='blue') >> wisobserver
     wisobserver >> Edge(color='blue', style='solid',  decorate='true', label='<info &nbsp; >',  fontcolor='blue') >> wis
diag
