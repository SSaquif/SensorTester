import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.ColorSensor;
import lejos.util.Datalogger;
import lejos.nxt.Sound;

public class SensorTester {
	private static ColorSensor cs1 = new ColorSensor(SensorPort.S1);	
	private static ColorSensor cs2 = new ColorSensor(SensorPort.S2);
	private static UltrasonicSensor us1 = new UltrasonicSensor(SensorPort.S4);
	private static UltrasonicSensor us2 = new UltrasonicSensor(SensorPort.S3);
	private static Datalogger dlog = new Datalogger();
	
	public static void main(String[] args)
	{	
		LCD.drawString("< LEFT | RIGHT >", 0, 0);
		LCD.drawString("       |        ", 0, 1);
		LCD.drawString("  US   | Light  ", 0, 2);
		LCD.drawString("Sensors|Sensors ", 0, 3);
		
		int buttonChoice = Button.waitForAnyPress();
		
		if(buttonChoice == Button.ID_LEFT)
		{		
			LCD.drawString("<ENTER | ESC   >", 0, 0);
			LCD.drawString("       |        ", 0, 1);
			LCD.drawString("  Log  | Exit   ", 0, 2);
			LCD.drawString(" Data  |    	", 0, 3);
			
			buttonChoice = Button.waitForAnyPress();
			
			while(buttonChoice != Button.ID_ESCAPE)
			{			
				if(buttonChoice == Button.ID_ENTER)
				{
					dlog.writeLog(us1.getDistance(),us2.getDistance());
					Sound.beep();
				}
				buttonChoice = Button.waitForAnyPress();
			}
		
			if(buttonChoice == Button.ID_ESCAPE)
			{
				dlog.transmit();
				Sound.twoBeeps();
				LCD.clear();
			}
		}
		
		else if(buttonChoice == Button.ID_RIGHT)
		{	
			cs1.setFloodlight(true);
			cs2.setFloodlight(true);
			LCD.drawString("<ENTER | ESC   >", 0, 0);
			LCD.drawString("       |        ", 0, 1);
			LCD.drawString("  Log  | Exit   ", 0, 2);
			LCD.drawString(" Data  |    	", 0, 3);
			
			buttonChoice = Button.waitForAnyPress();
			
			while(buttonChoice != Button.ID_ESCAPE)
			{			
				if(buttonChoice == Button.ID_ENTER)
				{
					dlog.writeLog(cs1.getNormalizedLightValue(),cs2.getNormalizedLightValue());
					Sound.beep();
				}
				buttonChoice = Button.waitForAnyPress();
			}
		
			if(buttonChoice == Button.ID_ESCAPE)
			{
				cs1.setFloodlight(false);
				cs2.setFloodlight(false);
				dlog.transmit();
				Sound.twoBeeps();
				LCD.clear();
			}
		}
		
	}
}
