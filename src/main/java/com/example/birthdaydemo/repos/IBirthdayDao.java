package com.example.birthdaydemo.repos;

import com.example.birthdaydemo.models.BirthdayResponse;

public interface IBirthdayDao {

    public BirthdayResponse calculateBirthdayDetails(int month, int day);

    public BirthdayResponse calculateBirthdayDetailsSync(int month, int day);
}
