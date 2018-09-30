package mainFrame;
/**
 * 
 * @author Christian
 *This Class represents an alarm .
 */
public class Alarms {
	String name;
	String startDate;
	String endDate;
	String startTime;
	String endTime;
	int interval;
	String snooze;
	String sound;
	String[]groupDates;
/**
 * Creates an instance of Alarms with Default values
 */
	public Alarms(){
		//Default values
		name="Empty";
		startDate="Empty";
		endDate="Empty";
		startTime="Empty";
		endTime="Empty";
		interval=00;
		snooze="";
		sound="";
	}

/**
 * Gets the current name of the Alarm
 * @return name The String that represent the alarms name
 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the Starting date of the Alarm
	 * @return startDate The String that represent the alarms Starting Date
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * Gets the ending Date of the alarm
	 * @return endDate The String that represent the alarms ending Date
	 */

	public String getEndDate() {
		return endDate;
	}
/**
 * Gets the time when the alarm will start
 * @return startTime String that contains the starting Time
 */

	public String getStartTime() {
		return startTime;
	}
	/**
	 * Gets the time when the alarms are supposed to stop
	 * @return endTime String that contains the ending time
	 */
	public String getEndTime() {
		return endTime;
	}
/**
 * Gets the interval in which the alarm will be repeated
 * @return interval integer with the value in minutes of the repeat interval
 */
	public int getInterval() {
		return interval;
	}

/**
 * Change the name of the alarm
 * @param name The name that will replace the previous one
 */
	public void setName(String name) {
		this.name = name;
	}
/**
 * Change the starting date of the alarm
 * @param startDate The new starting Date
 */

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

/**
 * Change the ending date of the alarm
 * @param endDate The new ending Date
 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

/**
 * Change the starting time of the alarm
 * @param startTime The new starting time
 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * Changes the ending time  of the alarm
	 * @param endTime New ending time
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
/**
 * Changes the interval to be repeated
 * @param interval The new repeated interval 
 */
	public void setInterval(int interval) {
		this.interval = interval;
	}
/**
 * Gets the snooze password 
 * @return snooze String that contains the selected password to disable alarms
 */

	public String getSnooze() {
		return snooze;
	}

/**
 * Changes the snooze password
 * @param snooze The new snooze password
 */
	public void setSnooze(String snooze) {
		this.snooze = snooze;
	}

/**
 * Gets the current sound of the alarm
 * @return sound String that contains the name of the sound
 */
	public String getSound() {
		return sound;
	}

/**
 * Changes the sound of the alarm
 * @param sound The new Sound of the alarm
 */
	public void setSound(String sound) {
		this.sound = sound;
	}




}
