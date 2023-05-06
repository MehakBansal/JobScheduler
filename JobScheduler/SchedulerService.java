package JobScheduler;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.Condition;

public class SchedulerService implements IScheduler{

    //example of maximum 10 jobs to be scheduled

    final PriorityBlockingQueue<Job> jobQueue= new PriorityBlockingQueue<>(2, new JobComparator());



    @Override
    public void schedule(Job job) {

  //      if(this.jobQueue.size()<=2){

            synchronized (this.jobQueue){
                this.jobQueue.add(job);
                System.out.println("Thread-->"+Thread.currentThread().getName()+" notifying all");
                this.jobQueue.notifyAll();
            }

     //   }
//        else{
//            waitForJobAssignment(job);
//        }
    }
    public void start() throws InterruptedException {

        for(int i = 0; i<=0; --i) {
            if (!this.jobQueue.isEmpty()) {
                long currenTime = System.currentTimeMillis();

                assert this.jobQueue.peek() != null;
                if (this.jobQueue.peek().scheduledTime <= currenTime) {
                    Job jobToBeRun = this.jobQueue.remove();
//                    if (jobToBeRun.isTimeTaking) {
//                        Thread.sleep(1000);
//                    }
                    jobToBeRun.run();
                    if (jobToBeRun.isRecurring) {
                        long schedTime = System.currentTimeMillis() + jobToBeRun.recurringTime;
                        if (currenTime >= jobToBeRun.expiry) {
                            jobToBeRun.isRecurring = false; //  and do nothing as job has expired
                        } else {
                            this.jobQueue.add(new Job(jobToBeRun.id, schedTime, jobToBeRun.priority, jobToBeRun.retries, jobToBeRun.recurringTime, true, jobToBeRun.expiry));
                        }

                    }
                }
            }

            else{
                synchronized (this.jobQueue){
                    System.out.println("Current thread--> "+Thread.currentThread().getName()+" is waiting");
                    this.jobQueue.wait();
                }
            }



        }


    }
    private void waitForJobAssignment(Job job){
        while(true){
            if(this.jobQueue.size()<=2) {
                this.schedule(job);
                return;
            }
        }
    }
}

//1) method which is always running and polling the queue
//2) if there is any way to handle different types of jobs