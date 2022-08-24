package com.example.birthdaydemo.repos;

import com.example.birthdaydemo.models.BirthdayResponse;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;

@Repository("birthdayDao")
public class BirthdayDao implements IBirthdayDao {

    public int numberOfCalls = 0;

    @Override
    public BirthdayResponse calculateBirthdayDetails(int month, int day) {
        BirthdayResponse br = new BirthdayResponse();

        LocalDateTime today = LocalDateTime.now();
        int currentDay = today.getDayOfMonth();
        int currentMonth = today.getMonthValue();
        int currentYear = today.getYear();

        LocalDateTime nextBirthday;
        if(currentMonth > month || (currentMonth == month && currentDay > day)) {
            //Birthday is next year
            nextBirthday = LocalDateTime.of(currentYear + 1, (int) month, (int) day, 0, 0, 0);
        } else {
            //Birthday is this year
            nextBirthday = LocalDateTime.of(currentYear,(int) month, (int) day, 0, 0, 0);
        }

        br.setDaysUntil((int)(DAYS.between(today, nextBirthday)));
        br.setHoursUntil((int)(HOURS.between(today, nextBirthday)) % 24);

        calculated(month, day);

        return br;
    }

    private void calculated(int month, int day) {
        numberOfCalls++;
        System.out.println("Thread " + Thread.currentThread().getName() + ": Birthday month: " + month + " & day: " + day + " calculated");
    }

    @Override
    synchronized public BirthdayResponse calculateBirthdayDetailsSync(int month, int day) {
        BirthdayResponse br = new BirthdayResponse();

        LocalDateTime today = LocalDateTime.now();
        int currentDay = today.getDayOfMonth();
        int currentMonth = today.getMonthValue();
        int currentYear = today.getYear();

        LocalDateTime nextBirthday;
        if(currentMonth > month || (currentMonth == month && currentDay > day)) {
            //Birthday is next year
            nextBirthday = LocalDateTime.of(currentYear + 1, (int) month, (int) day, 0, 0, 0);
        } else {
            //Birthday is this year
            nextBirthday = LocalDateTime.of(currentYear,(int) month, (int) day, 0, 0, 0);
        }

        br.setDaysUntil((int)(DAYS.between(today, nextBirthday)));
        br.setHoursUntil((int)(HOURS.between(today, nextBirthday)) % 24);

        calculatedSync(month, day);

        return br;
    }

    synchronized private void calculatedSync(int month, int day) {
        numberOfCalls++;
        System.out.println("Thread " + Thread.currentThread().getName() + ": Birthday month: " + month + " & day: " + day + " calculated");
    }
}
