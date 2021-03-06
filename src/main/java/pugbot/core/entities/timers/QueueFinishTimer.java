package pugbot.core.entities.timers;

import java.util.concurrent.TimeUnit;

import pugbot.core.entities.QueueManager;

public class QueueFinishTimer extends Timer {

	private QueueManager manager;
	private int timerDuration;
	private int timeElapsed = 0;
	
	public QueueFinishTimer(QueueManager manager) {
		super(1, TimeUnit.SECONDS);
		this.manager = manager;
		timerDuration = manager.getServer().getSettingsManager().getQueueFinishTimer();
	}

	@Override
	protected void cycleCompleted() {
		timeElapsed++;
		
		if(timeElapsed >= timerDuration){
			condition = false;
			
			manager.queueFinishTimerEnd(this);
		}
	}
	
	public int getTimeRemaining(){
		return timerDuration - timeElapsed;
	}

}
