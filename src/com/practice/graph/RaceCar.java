package com.practice.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

//https://leetcode.com/problems/race-car/
public class RaceCar {
    class QItem {
        int position;
        int speed;
        int dist;
        QItem(int position, int speed, int dist) {
            this.position = position;
            this.speed = speed;
            this.dist = dist;
        }
    }
    public int racecar(int target) {
        Set<String> visited = new HashSet<>();
        int minNumberOfSteps = 0;
        Queue<QItem> queue = new LinkedList<>();
        queue.add(new QItem(0, 1, 0));
        while(!queue.isEmpty()) {
            QItem current = queue.poll();
            int currentPos = current.position;
            int currentSpeed = current.speed;
            visited.add(String.valueOf(currentPos) + "," + String.valueOf(currentSpeed));
            if (current.position == target) {
                return current.dist;
            }
            if (!visited.contains(String.valueOf(currentPos + currentSpeed)+ "," + String.valueOf(currentSpeed * 2))
                    && Math.abs(currentPos + currentSpeed - target) < target) {
                //add step for A
                queue.add(new QItem(currentPos + currentSpeed, currentSpeed * 2, current.dist + 1));

            }
            if (!visited.contains(String.valueOf(currentPos) + "," + String.valueOf(currentSpeed >= 0 ? -1 : 1))) {
                //add step for R
                queue.add(new QItem(currentPos, currentSpeed >= 0 ? -1 : 1, current.dist + 1));
            }
        }
        return minNumberOfSteps;
    }

    public static void main(String args[]) {
        RaceCar raceCar = new RaceCar();
        System.out.print(raceCar.racecar(6));
    }
}
