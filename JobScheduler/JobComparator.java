package JobScheduler;

import java.util.Comparator;

public class JobComparator implements Comparator<Job> {
    @Override
    public int compare(Job o1, Job o2) {
        if(o1.scheduledTime==o2.scheduledTime){
            return o2.priority-o1.priority;
        }
        return (int)(o1.scheduledTime-o2.scheduledTime);
    }
}
