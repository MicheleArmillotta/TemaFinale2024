package main.java.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.ColorsOut;
import unibo.basicomm23.utils.CommUtils;
import unibo.basicomm23.utils.ConnectionFactory;

public class functionaltest {
	
	private static Interaction connSupport;

	private static final String ADDRESS = "localhost";
	private static final String PORT = "8080"; // 
	private static final ProtocolType PROTOCOL = ProtocolType.tcp; 
	
	
	public static void showOutput(Process proc, String color){
		new Thread(){
			public void run(){
				try {
				BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
				ColorsOut.outappl("Here is the standard output of the command:\n", color);
				while (true){
					String s = stdInput.readLine();
					if ( s != null ) ColorsOut.outappl( s, color );
				} 
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	
	public static void activateSystemUsingGradle() { 
		Thread th = new Thread(){
			public void run(){
				try {
					CommUtils.outmagenta("TestMDCtx activateSystemUsingGradle ");
					Process gradleProcess = Runtime.getRuntime().exec("./gradlew.bat run");
					showOutput(gradleProcess,ColorsOut.BLACK);
				} catch ( Exception e) {
					CommUtils.outred("TestMDCtx activate ERROR " + e.getMessage());
				}
			}
		};
		th.start();
	}  
	
	 public static void stopGradleProcess() {
		 try {
	            CommUtils.outmagenta("Stopping Gradle daemons...");
	            Process stopProcess = Runtime.getRuntime().exec("./gradlew.bat --stop");
	            showOutput(stopProcess, ColorsOut.BLACK);
	            int exitCode = stopProcess.waitFor();
	            if (exitCode == 0) {
	                CommUtils.outgreen("Gradle daemons stopped successfully.");
	            } else {
	                CommUtils.outred("Failed to stop Gradle daemons. Exit code: " + exitCode);
	            }
	        } catch (Exception e) {
	            CommUtils.outred("Error stopping Gradle daemons: " + e.getMessage());
	        }
	    }
	


	@Before
	public void setUp() throws Exception {
		CommUtils.outmagenta("TestMDCtx activate ");
		activateSystemUsingGradle();
	}

	@After
	public void tearDown() throws Exception {
		stopGradleProcess();
		CommUtils.outcyan("end of test ");	
	}

	@Test
	public void test() {
		//IApplMessage activation = CommUtils.buildRequest("tester", "activation_test", "act_test(N)", "incineratorobserver");
		IApplMessage start = CommUtils.buildRequest("tester", "start_test", "start_test(N)", "test_observer");
		
		try {
			CommUtils.outmagenta("test_observer ======================================= ");
			while (connSupport == null) {
				connSupport = ConnectionFactory.createClientSupport(PROTOCOL, ADDRESS, PORT);
				CommUtils.outcyan("testoprobot another connect attempt ");
				Thread.sleep(1000);
			}
			
			CommUtils.outcyan("CONNECTED to test_observer " + connSupport);

			//inviamo messaggio di start all' observer (aspettiamo sia in idle)
			
			
			
			//start
			Thread.sleep(1000);
			IApplMessage start_reply = connSupport.request(start);
			CommUtils.outcyan("START: start reply=" + start_reply);
			String answer_start = start_reply.msgContent();
			CommUtils.outcyan("START PAYLOAD: "+ answer_start);
			
			
			assertEquals(answer_start, "start_test_reply(OK)");
			
		} catch (Exception e) {
			CommUtils.outred("test_observer ERROR " + e.getMessage());
			fail("testRequest " + e.getMessage());
		}
	}

}
