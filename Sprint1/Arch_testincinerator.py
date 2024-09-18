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
with Diagram('testincineratorArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxtest', graph_attr=nodeattr):
          incinerator=Custom('incinerator','./qakicons/symActorSmall.png')
          incineratorobserver=Custom('incineratorobserver','./qakicons/symActorSmall.png')
     incinerator >> Edge( label='burnEnd', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     sys >> Edge( label='burnEnd', **evattr, decorate='true', fontcolor='darkgreen') >> incineratorobserver
     incinerator >> Edge(color='blue', style='solid',  decorate='true', label='<info &nbsp; >',  fontcolor='blue') >> incineratorobserver
     incineratorobserver >> Edge(color='blue', style='solid',  decorate='true', label='<activation_command &nbsp; startBurn &nbsp; >',  fontcolor='blue') >> incinerator
diag
