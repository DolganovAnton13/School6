package com.yugorsk.school6;

import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class CalendarDate {

    com.yugorsk.school6.data.Date date;
    List<String> listDate;
    ArrayList<Integer> listDays;
    private String firstQuarterTo = "До конца 1-ой четверти -\n";
    private String secondQuarterTo = "До конца 2-ой четверти -\n";
    private String thirdQuarterTo = "До конца 3-ой четверти -\n";
    private String fourthQuarterTo = "До конца 4-ой четверти -\n";
    private String holiday = "Ура, каникулы!!!\n До начала занятий -\n";
    private String school = "Школа №6\n Югорск\n";


    public CalendarDate(com.yugorsk.school6.data.Date date) {
       // this.listDate = listDate;
        listDays = new ArrayList<>();
        this.date = date;
    }

    public CalendarDate(List<String> date) {
         this.listDate = date;
    }


    public String CurrentDate() {
        try {
            Calendar calendar = new GregorianCalendar();
            Date myDay = calendar.getTime();

            listDays.clear();

            listDays.add(DifferenceDate(myDay, date.getQuarterOneFrom()));
            listDays.add(DifferenceDate(myDay, date.getQuarterOneTo()));
            listDays.add(DifferenceDate(myDay, date.getQuarterTwoFrom()));
            listDays.add(DifferenceDate(myDay, date.getQuarterTwoTo()));
            listDays.add(DifferenceDate(myDay, date.getQuarterThreeFrom()));
            listDays.add(DifferenceDate(myDay, date.getQuarterThreeTo()));
            listDays.add(DifferenceDate(myDay, date.getQuarterFourFrom()));
            listDays.add(DifferenceDate(myDay, date.getQuarterFourTo()));


            if (listDays.size() == 0) {
                return school;
            }
            if (listDays.get(1) > 0 && listDays.get(6) > 0) {
                return (holiday + listDays.get(1) + PrintDay(listDays.get(1)));
            }

            if (listDays.get(0) > 0 && listDays.get(1) <= 0) {
                return (firstQuarterTo + listDays.get(0) + PrintDay(listDays.get(0)));
            }

            if (listDays.get(3) > 0 && listDays.get(0) <= 0) {
                return (holiday + listDays.get(3) + PrintDay(listDays.get(3)));
            }

            if (listDays.get(2) > 0 && listDays.get(3) <= 0) {
                return (secondQuarterTo + listDays.get(2) + PrintDay(listDays.get(2)));
            }

            if (listDays.get(5) > 0 && listDays.get(2) <= 0) {
                return (holiday + listDays.get(5) + PrintDay(listDays.get(5)));
            }

            if (listDays.get(4) > 0 && listDays.get(5) <= 0) {
                return (thirdQuarterTo + listDays.get(4) + PrintDay(listDays.get(4)));
            }

            if (listDays.get(7) > 0 && listDays.get(4) <= 0) {
                return (holiday + listDays.get(7) + PrintDay(listDays.get(7)));
            }

            if (listDays.get(6) > 0 && listDays.get(7) <= 0) {
                return (fourthQuarterTo + listDays.get(6) + PrintDay(listDays.get(6)));
            }
        } catch (Exception ex) {
            return "ошибка";
        }
        return school;
    }

    private int DifferenceDate(Date dateOne, String date2) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date dateTwo = null;

        try {
            dateTwo = format.parse(date2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long difference = dateTwo.getTime() - dateOne.getTime();
        int days = (int) (difference / (24 * 60 * 60 * 1000));
        if (difference < 0) return days;

        return ++days;
    }

    private String PrintDay(int day) {
        int buf = day % 10;
        if (buf == 1 && day != 11) return " день";
        if ((buf == 2 || buf == 3 || buf == 4) && (day != 12 && day != 13 && day != 14))
            return " дня";

        return " дней";
    }

    public List<com.yugorsk.school6.data.Date> toDate()
    {
        List<com.yugorsk.school6.data.Date> dates = new ArrayList<>();
        dates.add(new com.yugorsk.school6.data.Date(listDate.get(0),listDate.get(1),listDate.get(2),listDate.get(3),listDate.get(4),listDate.get(5),listDate.get(6),listDate.get(7)));
        return dates;

    }
}
