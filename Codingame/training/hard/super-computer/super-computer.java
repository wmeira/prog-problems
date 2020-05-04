/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/hard/super-computer/
*/


import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

	private static List<Task> tasks = new ArrayList<Task>();

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            int J = in.nextInt();
            int D = in.nextInt();
            tasks.add(new Task(J, D));
        }
        Collections.sort(tasks);
        int tasksDone = 0;
        int time = 0;
        for(int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).startingDay >= time) {
                Task task = nextTask(i, time);
                if(task == null) break;
                time = task.startingDay + task.duration;
                tasksDone++;
            }

        }
        System.out.println(tasksDone);
    }

    private static Task nextTask(int t, int time) {
        int bestEnd = Integer.MAX_VALUE;
        Task bestTask = null;
        for(int i = t; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if(task.startingDay >= bestEnd) break;

            if(task.startingDay >= time && (task.startingDay + task.duration < bestEnd)) {
                bestTask = task;
                bestEnd = task.startingDay + task.duration;
            }
        }
        return bestTask;
    }
}

class Task implements Comparable<Task>{
    int startingDay;
    int duration;

    Task(int startingDay, int duration) {
        this.startingDay = startingDay;
        this.duration = duration;
    }

	@Override
	public int compareTo(Task task) {
		if(startingDay == task.startingDay) {
			return this.duration - task.duration;
		}
		return this.startingDay - task.startingDay;
	}

	public String toString() {
		return startingDay + " " + duration;
	}
}