package kakao.bline2022;

//#3 kakao2022blind 주차 요금 계산 

import java.util.*;

public class ParkingFee {

	public static void main(String[] args) {
		int[] fees = { 180, 5000, 10, 600 };
		String[] records = { "05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN",
				"18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT" };

		System.out.println(Arrays.toString(solution(fees, records)));
	}

	static class FeePolicy {
		int basicTime;
		int basicFee;
		int unitTime;
		int unitFee;

		public FeePolicy(int basicTime, int basicFee, int unitTime, int unitFee) {
			this.basicTime = basicTime;
			this.basicFee = basicFee;
			this.unitTime = unitTime;
			this.unitFee = unitFee;
		}
		
		public int calcuateFee(int parkTime) {
			int fee = basicFee;
			if(parkTime > basicTime) {
				int feeTime = (int)Math.ceil((parkTime - basicTime)/(double)unitTime);
				fee += feeTime * unitFee;
			}
			return fee;
		}
	}

	static enum Type {
		IN, OUT;
	}

	static class ParkRecord {
		Type type;
		int time;

		public ParkRecord(Type type, String time) {
			this.type = type;
			this.time = toIntTime(time);
		}

		private int toIntTime(String time) {
			String[] t = time.split(":");
			int hourToMin = Integer.parseInt(t[0]) * 60;
			int min = Integer.parseInt(t[1]);
			return hourToMin + min;
		}

		public int getParkingDurationTime(String outTime) {
			return toIntTime(outTime) - this.time;
		}
	}

	public static int[] solution(int[] fees, String[] records) {
		Map<String, Integer> cars = new TreeMap<>();
		parkRecording(records, cars);
		
		FeePolicy feePolicy = new FeePolicy(fees[0], fees[1], fees[2], fees[3]);
		int[] answer = new int[cars.keySet().size()];
		chargeParkingFee(cars, feePolicy, answer);
		return answer;
	}

	private static void chargeParkingFee(Map<String, Integer> cars, FeePolicy feePolicy, int[] answer) {
		int idx = 0;
		for(String carNo : cars.keySet()) {
			answer[idx++] =  feePolicy.calcuateFee(cars.get(carNo));
		}
	}

	private static void parkRecording(String[] records, Map<String, Integer> cars) {
		Map<String, ParkRecord> parkRecs = new HashMap<>();
		for (int i = 0; i < records.length; i++) {
			String[] record = records[i].split(" ");
			String carNo = record[1];
			String recTime = record[0];
			if (parkRecs.containsKey(carNo)) {
				cars.put(carNo, 
						cars.getOrDefault(carNo, 0)
						+ parkRecs.get(carNo).getParkingDurationTime(recTime));
				parkRecs.remove(carNo);

			} else {
				parkRecs.put(carNo, new ParkRecord(Type.valueOf(record[2]), recTime));
			}
		}

		for (String restCarNo : parkRecs.keySet()) {
			cars.put(restCarNo, 
					cars.getOrDefault(restCarNo, 0) 
					+ parkRecs.get(restCarNo).getParkingDurationTime("23:59"));
		}
	}
}
