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

    int score(int[] inningA, int[] inningB, int[] playerA, int[] playerB) throws Exception {
        int len_inningA = inningA.length;// 獲取inningA陣列長度
        int len_inningB = inningB.length;// 獲取inningB陣列長度
        int len_playerA = playerA.length;// 獲取playerA陣列長度
        int len_playerB = playerB.length;// 獲取playerB陣列長度
        boolean less_len = (len_inningA < 9 || len_inningB < 9);// 判斷局數是否小於九
        if (less_len) throw new Exception("局數小於九局");// 小於九則拋出錯誤

        int sum_inningA = get_nine_rounds_sum(inningA);// 計算A隊前九局得分
        int sum_inningB = get_nine_rounds_sum(inningB);// 計算B隊前九局得分

        int all_sum_inningA = get_all_rounds_sum(inningA, len_inningA);// 計算A隊全局總得分
        int all_sum_inningB = get_all_rounds_sum(inningB, len_inningB);// 計算B隊全局總得分

        int sum_playerA = get_player_sum(playerA, len_playerA);// 計算A隊球員總得分
        int sum_playerB = get_player_sum(playerB, len_playerB);// 計算B隊球員總得分

        // 判斷每隊得分是否一致
        boolean unmatched_scores = (all_sum_inningA != sum_playerA || all_sum_inningB != sum_playerB);
        if (unmatched_scores) throw new Exception("總分不一致");// 不一致則拋出錯誤
        boolean unmatched_player = (len_playerA != len_playerB);// 判斷球員人數是否一致
        if (unmatched_player) throw new Exception("球員不一致");// 不一致則拋出錯誤
        boolean unsame_round = (len_inningA != len_inningB);// 判斷局數是否一致
        if (unsame_round) throw new Exception("局數不一致");// 不一致則拋出錯誤

        int delta = sum_inningA - sum_inningB;// 前九局之差

        if (delta > 0) {// A隊領先
            if (inningB[8] == -1) {// 當後攻未領先並且還沒打九下
                throw new Exception("不合理的提前結束");// 拋出錯誤
            } else {// 後攻未領先並且已打九下
                if (len_inningA > 9) throw new Exception("沒有必要的延長局");// 已經領先故無需延長所以拋錯
                else return delta;// 回傳兩隊得分差值
            }
        }
        else if (delta < 0) {// B隊領先
            if(inningA[8] == -1) throw new Exception("不合理的先攻未打");// 九上也未打即拋錯
            if (inningB[8] == -1) {// 九下未打
                if(len_inningB > 9) throw new Exception("沒有必要的延長局");// 已經領先故無需延長所以拋錯
                else return delta;// 回傳兩隊得分差值
            } else {// 九下已打
                if (len_inningB > 9) throw new Exception("沒有必要的延長局");// 已經領先故無需延長所以拋錯
                else return delta;// 回傳兩隊得分差值
            }
        }
        else {// 當AB前九局都同分(delta = 0)
            if (len_inningA == 9 && len_inningB == 9) throw new Exception("不可以和局");// 剛好九局分數還是一樣故拋錯
            else {// 局數大於九局
                for (int i = 0; i < len_inningA - 9; i++) {// 分別去加上延長的局數的得分
                    if (inningA[i + 9] == -1 || inningB[i + 9] == -1) throw new Exception("提前結束只可能出現在九下");// 需拋出錯誤

                    sum_inningA += inningA[i + 9];
                    sum_inningB += inningB[i + 9];
                    delta = sum_inningA - sum_inningB;// 更新差值
                    if(delta != 0){// 分出勝負
                        if((i + 9)+1 != len_inningA) throw new Exception("沒有必要的延長局");// 先前延長賽已分出勝負故無需更多場
                        else return delta;// 回傳兩隊得分差值
                    }
                }
                throw new Exception("不可以和局");// 拋出錯誤
            }
        }
    }
}
