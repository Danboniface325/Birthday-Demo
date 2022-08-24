package com.example.birthdaydemo.models;

public class BirthdayResponse {

    private int daysUntil;

    private int hoursUntil;

    private int processTime;

    public int getDaysUntil() {
        return daysUntil;
    }

    public int getHoursUntil() {
        return hoursUntil;
    }

    public int getProcessTime() {
        return processTime;
    }

    public void setDaysUntil(int daysUntil) {
        this.daysUntil = daysUntil;
    }

    public void setHoursUntil(int hoursUntil) {
        this.hoursUntil = hoursUntil;
    }

    public void setProcessTime(int processTime) {
        this.processTime = processTime;
    }
}