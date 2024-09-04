package main.java.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ApplMessage;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.ColorsOut;
import unibo.basicomm23.utils.CommUtils;
import unibo.basicomm23.utils.ConnectionFactory;


public class TestOpRobot {

	private static Interaction connSupport;

	private static final String ADDRESS = "localhost";
	private static final String PORT = "10010"; // 
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
		IApplMessage start = CommUtils.buildRequest("tester", "start_test", "start_test(X)", "test_observer");
		IApplMessage fine = CommUtils.buildRequest("tester", "fine_test", "fine_test(X)", "test_observer");

		try {
			CommUtils.outmagenta("test_observer ======================================= ");
			while(connSupport == null) {
				connSupport = ConnectionFactory.createClientSupport(PROTOCOL, ADDRESS, PORT);
				CommUtils.outcyan("testoprobot another connect attempt ");
				Thread.sleep(1000);
			}
			
			CommUtils.outcyan("CONNECTED to test_observer " + connSupport);
	
			//invio di start all' observer

			//start
			Thread.sleep(2000);
			IApplMessage start_test_reply = connSupport.request(start);
			CommUtils.outcyan("START: start reply=" + start_test_reply);
			String answer_start = start_test_reply.msgContent();
			String parameters = answer_start.substring(answer_start.indexOf('(') + 1, answer_start.lastIndexOf(')'));
			String[] s = parameters.split(",");
			CommUtils.outcyan("State payload: " + s[0] + ", Position payload: " + s[1]);


			assertEquals(s[0], "working");
			assertEquals(s[1], "ASHOUT");
			
			CommUtils.outcyan("Test ok");
			
			
		}catch(Exception e) {
			CommUtils.outred("test_observer ERROR " + e.getMessage());
			fail("testRequest " + e.getMessage());
		}
	}
	
}
