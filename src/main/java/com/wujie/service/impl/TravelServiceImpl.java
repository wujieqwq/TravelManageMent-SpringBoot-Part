package com.wujie.service.impl;

import com.wujie.mapper.TravellerMapper;
import com.wujie.pojo.Traveller;
import com.wujie.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelServiceImpl implements TravelService {

    @Autowired
    private TravellerMapper travellerMapper;
    @Override
    public void updateTraveller(List<Traveller> list) {
        for (Traveller traveller : list) {
            travellerMapper.updateTraveller(traveller);
        }
    }
}
