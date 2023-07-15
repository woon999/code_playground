package ch4.item16;

public final class Time {
	private static final int HOURS_PER_DAY = 24;
	private static final int MINUTES_PER_HOUR = 60;

	public final int hour;
	public final int minute;

	public Time(int hour, int minute) {
		if (hour < 0 || hour >= HOURS_PER_DAY) {
			throw new IllegalArgumentException("시간: " + hour);
		}
		if (minute < 0 || minute >= MINUTES_PER_HOUR) {
			throw new IllegalArgumentException("분: " + minute);
		}
		this.hour = hour;
		this.minute = minute;
	}

	@Override
	public String toString() {
		return "Time{" +
			"hour=" + hour +
			", minute=" + minute +
			'}';
	}
}
