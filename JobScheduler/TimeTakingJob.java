package JobScheduler;

public class TimeTakingJob implements IJob {
    int id;
    long scheduledTime;
    int priority;
    long recurringTime;
    int retries;
    boolean isRecurring=false;
    long expiry=Long.MAX_VALUE;

    public TimeTakingJob(int id,long scheduledTime,int priority){
        this.id=id;
        this.scheduledTime=scheduledTime;
        this.priority=priority;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




