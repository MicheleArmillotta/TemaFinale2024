/* Generated by AN DISI Unibo */ 
package it.unibo.test_observer

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

class Test_observer ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		 var W = 50 
			   var RP_w = 0
			   var RP_string = ""	
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outgreen("OBSERVER READY")
						delay(1000) 
						observeResource("localhost","8080","ctxtestscale","wastestorage","infotest")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t11",targetState="handleStart",cond=whenRequest("start_test"))
				}	 
				state("handleStart") { //this:State
					action { //it:State
						emitLocalStreamEvent("scaledata", "scaledata($W)" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t22",targetState="handle_info",cond=whenDispatch("infotest"))
				}	 
				state("handle_info") { //this:State
					action { //it:State
						CommUtils.outred("TEST ESEGUITO CON SUCCESSO RISPONDO")
						if( checkMsgContent( Term.createTerm("infotest(X)"), Term.createTerm("infotest(X)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 RP_string = payloadArg(0)  
												   
												   RP_w = RP_string.toInt()
													
						}
						
									if(RP_w > 0 ){
						answer("start_test", "start_test_reply", "start_test_reply(ok)"   )  
						
								}else{
						answer("start_test", "start_test_reply", "start_test_reply(notok)"   )  
						
								}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
				}	 
			}
		}
} 
