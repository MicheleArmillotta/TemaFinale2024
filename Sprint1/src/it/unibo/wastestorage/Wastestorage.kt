/* Generated by AN DISI Unibo */ 
package it.unibo.wastestorage

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

class Wastestorage ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		
		        var WasteLimit = 5;   // Numero per test RANDOM
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outgreen("WasteStorage Mock ready...")
						delay(1000) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t029",targetState="incrementa",cond=whenDispatch("add"))
				}	 
				state("incrementa") { //this:State
					action { //it:State
						CommUtils.outgreen("WasteStorage Mock aggiungo al contenitore...")
						if( checkMsgContent( Term.createTerm("add(N)"), Term.createTerm("add(N)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 WasteLimit = WasteLimit + payloadArg(0).toInt()   
								CommUtils.outmagenta("WasteStorage aumentato fino a $WasteLimit")
								forward("update", "update($WasteLimit)" ,"wis" ) 
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
