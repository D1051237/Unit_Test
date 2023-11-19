package org.example;
import java.text.SimpleDateFormat;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Calendar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class Texas {
    int[] inningA;
    int[] inningB;
    int[] playerA;
    int[] playerB;

    Texas(int[] inningA, int[] inningB, int[] playerA, int[] playerB){
        this.inningA = inningA;
        this.inningB = inningB;
        this.playerA = playerA;
        this.playerB = playerB;
    }

    int check_value(int value){
        if(value == -1){
            return 0;
        }
        else {
            return value;
        }
    }

    int get_nine_rounds_sum(int[] arr){
        int sum = 0;
        for(int i=0;i<9;i++){
            sum += check_value(arr[i]);
        }
        return sum;
    }

    int get_all_rounds_sum(int[] arr, int len){
        int sum = 0;
        for(int i=0;i<len;i++){
            sum += check_value(arr[i]);
        }
        return sum;
    }

    int get_player_sum(int[] arr, int len){
        int sum = 0;
        for(int i=0;i<len;i++){
            sum += arr[i];
        }
        return sum;
    }

    int checkwinner(int[] inningA, int[] inningB, int[] playerA, int[] playerB) throws Exception {
        int len_inningA = inningA.length;
        int len_inningB = inningB.length;
        int len_playerA = playerA.length;
        int len_playerB = playerB.length;
        boolean less_len = (len_inningA < 9 || len_inningB < 9);
        if (less_len) throw new Exception("局數小於九局");

        int sum_inningA = get_nine_rounds_sum(inningA);
        int sum_inningB = get_nine_rounds_sum(inningB);

        int all_sum_inningA = get_all_rounds_sum(inningA, len_inningA);
        int all_sum_inningB = get_all_rounds_sum(inningB, len_inningB);

        int sum_playerA = get_player_sum(playerA, len_playerA);
        int sum_playerB = get_player_sum(playerB, len_playerB);

        boolean unmatched_len = (all_sum_inningA != sum_playerA || all_sum_inningB != sum_playerB);
        if (unmatched_len) throw new Exception("總分不一致");
        boolean unmatched_player = (len_playerA != len_playerB);
        if (unmatched_player) throw new Exception("球員不一致");
        boolean unsame_round = (len_inningA != len_inningB);
        if (unsame_round) throw new Exception("局數不一致");


        if (sum_inningA > sum_inningB) {
            if (inningB[8] == -1) {
                throw new Exception("不合理的提前結束");
            } else {
                if (len_inningA > 9) throw new Exception("沒有必要的延長局");
                else return 1;
            }
        }
        else if (sum_inningA < sum_inningB) {
            if (inningA[8] == -1) {
                throw new Exception("不合理的先攻未打");
            } else {
                if (len_inningB > 9) throw new Exception("沒有必要的延長局");
                else return -1;
            }
        }
        else {
            if (len_inningA == 9 && len_inningB == 9) throw new Exception("不可以和局");
            else {
                for (int i = 0; i < len_inningA - 9; i++) {
                    if (inningA[i + 9] == -1 || inningB[i + 9] == -1) throw new Exception("提前結束只可能出現在九下");

                    sum_inningA += inningA[i + 9];
                    sum_inningB += inningB[i + 9];

                    if(sum_inningA != sum_inningB){
                        return (sum_inningA > sum_inningB) ? 1 : -1;
                    }
                }
                throw new Exception("不可以和局");
            }
        }
    }
}
