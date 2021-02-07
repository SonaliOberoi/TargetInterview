package com.practice.dp;

//https://leetcode.com/problems/stone-game/solution/
//https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/NPotGold.java
public class StoneGame {
    static class Pair{
        int first, second;
        int pick=0;
        public String toString(){
            return first + " " + second + " " + pick;
        }
    }

    public boolean stoneGame(int[] pots) {
        Pair[][] moves = new Pair[pots.length][pots.length];

        for(int i=0; i < moves.length; i++){
            for(int j=0; j < moves[i].length; j++){
                moves[i][j] = new Pair();
            }
        }

        for(int i=0; i < pots.length; i++){
            moves[i][i].first = pots[i];
            //to track the sequence of moves
            moves[i][i].pick = i;
        }

        for(int l = 2; l <= pots.length; l++){
            for(int i=0; i <= pots.length - l; i++){
                int j = i + l -1;
                if(pots[i] + moves[i+1][j].second > moves[i][j-1].second + pots[j]){
                    moves[i][j].first = pots[i] + moves[i+1][j].second;
                    moves[i][j].second = moves[i+1][j].first;
                    moves[i][j].pick = i;
                }else{
                    moves[i][j].first = pots[j] + moves[i][j-1].second;
                    moves[i][j].second = moves[i][j-1].first;
                    moves[i][j].pick =j;
                }
            }
        }

        return moves[pots.length - 1][pots.length - 1].first > moves[pots.length - 1][pots.length - 1].first ;
    }


}
