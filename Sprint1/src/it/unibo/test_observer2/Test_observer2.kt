/* Generated by AN DISI Unibo */ 
package it.unibo.test_observer2

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

class Test_observer2 ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

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
						CommUtils.outblue("$name ready...")
						delay(1000) 
						observeResource("localhost","10010","ctxtestop","oprobot","info")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t016",targetState="controllo",cond=whenDispatch("info"))
				}	 
				state("controllo") { //this:State
					action { //it:State
						CommUtils.outgreen("$name in ${currentState.stateName} | $currentMsg | ${Thread.currentThread().getName()} n=${Thread.activeCount()}")
						 	   
						if( checkMsgContent( Term.createTerm("info(X,Y)"), Term.createTerm("info(X,Y)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								  State = payloadArg(0) 
								 Position = payloadArg(1) 
						}
						CommUtils.outblue("$name $State $Position")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t117",targetState="handleStart",cond=whenRequest("start_test"))
					transition(edgeName="t118",targetState="controllo",cond=whenDispatch("info"))
				}	 
				state("handleStart") { //this:State
					action { //it:State
						var RISPOSTA = "$State,$Position"  
						CommUtils.outgreen("$name in ${currentState.stateName} | $currentMsg | ${Thread.currentThread().getName()} n=${Thread.activeCount()}")
						 	   
						CommUtils.outgreen("START: $name $State $Position")
						forward("startRobot", "startRobot(start)" ,"oprobot" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="invio", cond=doswitch() )
				}	 
				state("invio") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("info(X,Y)"), Term.createTerm("info(X,Y)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								  State = payloadArg(0) 
								 Position = payloadArg(1) 
								 if(State.equals("working") && (Position.equals("ASHOUT"))){ 
								   			 var RISPOSTA  = "$State,$Position"  
								answer("start_test", "start_test_reply", "start_test_reply($RISPOSTA)"   )  
								 }else{}  
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t219",targetState="invio",cond=whenDispatch("info"))
				}	 
			}
		}
} 
