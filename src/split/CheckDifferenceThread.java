package split;

import java.util.HashMap;

import javax.sound.midi.VoiceStatus;

public class CheckDifferenceThread extends Thread{
	int extID = 0;
	HashMap<Integer, Integer> map;
	public CheckDifferenceThread(int i,HashMap<Integer, Integer>mapping) {
		// TODO Auto-generated constructor stub
		this.extID = i;
		this.map = mapping;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(map.get(extID) == null){
			 //outString += tempStringE +"\n";
		 }
		 else{
			 //map.remove(idExtend);
		 }
	}

}
