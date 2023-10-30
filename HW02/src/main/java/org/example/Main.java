package org.example;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

class HW02 {
    // Get the BMI of the height and the weight
    double getbmi(double height, double weight){
        return weight / ((height/100) * (height/100));
    }

    // Get the dayofweek of the specified month and date
    String daychecker(int month,int day){

        Calendar calendar = Calendar.getInstance();
        int curr_year = 2021;

        Calendar Date_selected = Calendar.getInstance();
        Date_selected.set(curr_year, month - 1, day);

        SimpleDateFormat dayformat = new SimpleDateFormat("EEEE");
        Date date = Date_selected.getTime();
        String dayofweek =dayformat.format(date);
        return dayofweek;
    }

    // Get the information of the next day
    String Checknextday(String daystring){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(daystring); // turn input date into Date object

            // Use Calendar class to modify the date
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            // Add 1 to the Day
            calendar.add(Calendar.DAY_OF_YEAR, 1);

            // Get modified day
            Date nextDay = calendar.getTime();

            // Change the format of the day and return the string
            return dateFormat.format(nextDay);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Currency class
    class Currency {
        int amount;
        String symbol;
        public Currency(int a, String s){
            this.amount = a;
            this.symbol = s;
        }
        public Currency add(Currency other){
            if(this.symbol.equals(other.symbol)){
                return new Currency(this.amount + other.amount, this.symbol);
            }
            else if(this.symbol.equals("NT")){
                return new Currency(this.amount + other.amount*30, this.symbol);
            }
            else{
                return new Currency(this.amount + other.amount/30,this.symbol);
            }
        }
    }
}

