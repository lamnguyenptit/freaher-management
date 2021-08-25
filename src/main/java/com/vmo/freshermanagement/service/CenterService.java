package com.vmo.freshermanagement.service;

import com.vmo.freshermanagement.model.Center;

import java.util.List;

public interface CenterService {
    Center create(Center param);

    Center update(Center param);

    Center delete(int id);

    List<Center> findAllCenter();

    Center findCenterById(int id);
}
