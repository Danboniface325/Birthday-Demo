package com.example.birthdaydemo.services;

import com.example.birthdaydemo.models.BirthdayResponse;
import com.example.birthdaydemo.repos.BirthdayDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BirthdayService {

    @Qualifier("birthdayDao")
    private final BirthdayDao birthdayDao;

    public BirthdayService(@Qualifier("birthdayDao") BirthdayDao birthdayDao) {
        this.birthdayDao = birthdayDao;
    }

    public BirthdayResponse calculateBirthdayDetails(int month, int day) {
        return this.birthdayDao.calculateBirthdayDetails(month, day);
    }

    public BirthdayResponse calculateBirthdayDetailsSync(int month, int day) {
        return this.birthdayDao.calculateBirthdayDetailsSync(month, day);
    }
}