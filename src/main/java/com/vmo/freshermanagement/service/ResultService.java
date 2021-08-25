package com.vmo.freshermanagement.service;

import com.vmo.freshermanagement.model.Result;

public interface ResultService {
    double calculatePoint(int fresherId);

    Result createOrUpdateResult(Result param);

    int countFresherExcellent();

    int countFresherGood();

    int countFresherAverage();
}
