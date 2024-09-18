/* Generated by AN DISI Unibo */ 
package it.unibo.ashstorage

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

class Ashstorage ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		
		        var AshStorage = 0;   
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outgreen("AshStorage Mock ready...")
						delay(1000) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t031",targetState="incrementa",cond=whenDispatch("add2"))
				}	 
				state("incrementa") { //this:State
					action { //it:State
						CommUtils.outgreen("AshStorage Mock aggiungo al contenitore...")
						if( checkMsgContent( Term.createTerm("add2(N)"), Term.createTerm("add2(N)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 AshStorage = AshStorage + payloadArg(0).toInt()   
								CommUtils.outmagenta("AshStorage aumentato fino a $AshStorage")
								forward("update2", "update2($AshStorage)" ,"wis" ) 
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
