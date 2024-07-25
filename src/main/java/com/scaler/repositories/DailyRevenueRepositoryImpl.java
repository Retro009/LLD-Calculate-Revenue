package com.scaler.repositories;

import com.scaler.models.DailyRevenue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DailyRevenueRepositoryImpl implements DailyRevenueRepository{
    List<DailyRevenue> dailyRevenues = new ArrayList<>();
    private static long idCounter = 0;
    @Override
    public DailyRevenue save(DailyRevenue dailyRevenue) {
        if(dailyRevenue.getId()==0)
            dailyRevenue.setId(++idCounter);
        dailyRevenues.add(dailyRevenue);
        return dailyRevenue;
    }

    @Override
    public List<DailyRevenue> getDailyRevenueBetweenDates(Date startDate, Date endDate) {
        return dailyRevenues
                .stream()
                .filter(dailyRevenue -> !(dailyRevenue.getDate().before(startDate) || dailyRevenue.getDate().after(endDate)))
                .collect(Collectors.toList());
    }
}
