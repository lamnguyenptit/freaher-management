package com.vmo.freshermanagement.service;

import com.vmo.freshermanagement.model.Fresher;

import java.util.List;

public interface FresherService {
    Fresher create(Fresher fresher);

    Fresher update(Fresher param);

    Fresher delete(int id);

    List<Fresher> findByName(String param);

    Fresher findByEmail(String email);

    List<Fresher> findByProgrammingLanguage(int id);

    List<Fresher> findAllFresher();

    int countFresher();

    int countFresherByCenter(int centerId);

    Fresher findFresherById(int id);

    Fresher takeFresherToCenter(int fresherId, int centerId);
}
