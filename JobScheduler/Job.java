package JobScheduler;

public class Job implements IJob{

    int id;
    long scheduledTime;
    int priority;
    long recurringTime;
    int retries;
    boolean isRecurring=false;
    long expiry=Long.MAX_VALUE;
    boolean isTimeTaking=false;

    public Job(int id,long scheduledTime,int priority,int retries){
        this.id=id;
        this.scheduledTime=scheduledTime;
        this.priority=priority;
        this.retries=retries;
    }
    public Job(int id,long scheduledTime,int priority,int retries,boolean isTimeTaking){
        this.id=id;
        this.scheduledTime=scheduledTime;
        this.priority=priority;
        this.retries=retries;
        this.isTimeTaking=isTimeTaking;
    }
    public Job(int id,long scheduledTime,int priority,int retries,long recurringTime,boolean isRecurring,long expiry){
        this.id=id;
        this.scheduledTime=scheduledTime;
        this.priority=priority;
        this.retries=retries;
        this.recurringTime=recurringTime;
        this.isRecurring=isRecurring;
        this.expiry=expiry;
    }

    @Override
    public void run() {
        System.out.println("job--> "+this.id +" with scheduled time--> "+ this.scheduledTime+" is running at time -->"+ System.currentTimeMillis());
    }

}
