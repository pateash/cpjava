package live.ashish.cpjava.systemdesign.meetingscheduler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/*
https://leetcode.com/discuss/interview-question/490962/Design-Meeting-Scheduler
Design Meeting Scheduler. Here there are n given meeting rooms. Book a meeting in any meeting room at given interval(starting time, end time). Also send notifications to all person who are invited for meeting.
You should use calender for tracking date and time. And also history of all the meetings which are booked and meeting room.
 */

@Data @AllArgsConstructor
class User{
    String name;
}
@Data @AllArgsConstructor
class Meeting{
    Date start;
    Date end;
    User byUser;

}

enum MeetingRoomStatus{
    EMPTY,
    OCCUPIED
}


@Data @AllArgsConstructor
class MeetingRoom {
    int id;
    MeetingRoomStatus status;
}

@Data
class MeetingScheduler{
    int capacity; // total available rooms
    List<MeetingRoom> meetingRoomList = new ArrayList<>();

    public MeetingScheduler(int total_capacity) {
        this.capacity=total_capacity;
        for(int i=0;i<total_capacity;i++)
            meetingRoomList.add(new MeetingRoom(i,MeetingRoomStatus.EMPTY));
    }

    public void book(int startTime, int endTime) {

    }
}
public class MeetingSchedulerRunner {
    static MeetingScheduler meetingScheduler;
    public static void main(String[] args) {
        int capacity=3;
        meetingScheduler=new MeetingScheduler(capacity);
        while (true) {
            System.out.println("Welcome to meeting scheduler!!!\n Choose your option" +
//                    "\n1-Check capacity(now)" +
                    "\n1-Book meeting(Please provide roomid, and start and end time)" +
                    "\n2-Meeting booking history(for a room)\n9-Exit");
            Scanner input = new Scanner(System.in);
            int option = input.nextInt();
            switch (option) {
                case 1:
                    int startTime=input.nextInt();
                    int endTime=input.nextInt();
                    meetingScheduler.book(startTime,endTime);
                default:

            }

        }
    }
}
