/* Generated by AN DISI Unibo */ 
package it.unibo.oprobot

import it.unibo.kactor.*
import alice.tuprolog.*
import unibo.basicomm23.*
import unibo.basicomm23.interfaces.*
import unibo.basicomm23.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import it.unibo.kactor.sysUtil.createActor   //Sept2023

//User imports JAN2024

class Oprobot ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		
				var Position = ""
				var State = ""
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outyellow("opRobot ready...")
						delay(300) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="invio", cond=doswitch() )
				}	 
				state("invio") { //this:State
					action { //it:State
						request("engage", "engage($MyName,330)" ,"basicrobot" )  
						CommUtils.outblue("$name in ${currentState.stateName} | $currentMsg | ${Thread.currentThread().getName()} n=${Thread.activeCount()}")
						 	   
						CommUtils.outblack("opRobot manda request")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t06",targetState="home",cond=whenReply("engagedone"))
					transition(edgeName="t07",targetState="error",cond=whenReply("engagerefused"))
				}	 
				state("home") { //this:State
					action { //it:State
						CommUtils.outblue("$name in ${currentState.stateName} | $currentMsg | ${Thread.currentThread().getName()} n=${Thread.activeCount()}")
						 	   
						CommUtils.outred("opRobot in HOME...")
						 Position = "HOME"  
						delay(5000) 
						 State = "idle"  
						updateResourceRep( "info($State, $Position)"  
						)
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t18",targetState="handleRP",cond=whenDispatch("startRobot"))
				}	 
				state("handleRP") { //this:State
					action { //it:State
						CommUtils.outblue("opRobot to RP...")
						 State = "work"  
						updateResourceRep( "info($State, $Position)"  
						)
						request("moverobot", "moverobot(0,4)" ,"basicrobot" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t29",targetState="handleBurnIn",cond=whenReply("moverobotdone"))
					transition(edgeName="t210",targetState="error",cond=whenReply("moverobotfailed"))
				}	 
				state("handleBurnIn") { //this:State
					action { //it:State
						CommUtils.outyellow("opRobot in wateIn going to burnIn...")
						 Position = "WASTEIN"  
						delay(2000) 
						updateResourceRep( "info($State, $Position)"  
						)
						request("moverobot", "moverobot(3,1)" ,"basicrobot" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t311",targetState="handleHome2",cond=whenReply("moverobotdone"))
					transition(edgeName="t312",targetState="error",cond=whenReply("moverobotfailed"))
				}	 
				state("handleHome2") { //this:State
					action { //it:State
						CommUtils.outyellow("opRobot in burIn going to HOME...")
						 Position = "BURNIN"  
						delay(2000) 
						updateResourceRep( "info($State, $Position)"  
						)
						request("moverobot", "moverobot(0,0)" ,"basicrobot" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t413",targetState="home2",cond=whenReply("moverobotdone"))
					transition(edgeName="t414",targetState="error",cond=whenReply("moverobotfailed"))
				}	 
				state("home2") { //this:State
					action { //it:State
						CommUtils.outyellow("opRobot HOME waiting for the incinerator to stop...")
						 Position = "HOME"  
						updateResourceRep( "info($State, $Position)"  
						)
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t515",targetState="handleBurnOut",cond=whenEvent("burnEnd"))
				}	 
				state("handleBurnOut") { //this:State
					action { //it:State
						CommUtils.outyellow("opRobot in HOME going to gathering ashes...")
						request("moverobot", "moverobot(5,3)" ,"basicrobot" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t616",targetState="handleBurnOutGo",cond=whenReply("moverobotdone"))
					transition(edgeName="t617",targetState="error",cond=whenReply("moverobotfailed"))
				}	 
				state("handleBurnOutGo") { //this:State
					action { //it:State
						CommUtils.outyellow("opRobot in BurnOUT going to AshOUT...")
						 Position = "BURNOUT"  
						delay(2000) 
						updateResourceRep( "info($State, $Position)"  
						)
						request("moverobot", "moverobot(6,4)" ,"basicrobot" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t718",targetState="handleAshOut",cond=whenReply("moverobotdone"))
					transition(edgeName="t719",targetState="error",cond=whenReply("moverobotfailed"))
				}	 
				state("handleAshOut") { //this:State
					action { //it:State
						CommUtils.outyellow("opRobot in AshOUT going to HOME...")
						CommUtils.outred("RESTARTING THE ROUTINE...")
						 Position = "ASHOUT"  
						delay(2000) 
						updateResourceRep( "info($State, $Position)"  
						)
						request("moverobot", "moverobot(0,0)" ,"basicrobot" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t720",targetState="home",cond=whenReply("moverobotdone"))
					transition(edgeName="t721",targetState="error",cond=whenReply("moverobotfailed"))
				}	 
				state("error") { //this:State
					action { //it:State
						CommUtils.outred("ERROR...")
						System.exit(-1) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
				}	 
			}
		}
} 
