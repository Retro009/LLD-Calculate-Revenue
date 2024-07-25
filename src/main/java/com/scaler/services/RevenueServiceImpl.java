package com.scaler.services;

import com.scaler.exceptions.UnAuthorizedAccess;
import com.scaler.exceptions.UserNotFoundException;
import com.scaler.models.AggregatedRevenue;
import com.scaler.models.RevenueQueryType;
import com.scaler.models.User;
import com.scaler.models.UserType;
import com.scaler.repositories.DailyRevenueRepository;
import com.scaler.repositories.UserRepository;
import com.scaler.strategies.DailyRevenueCalculationStrategy;

public class RevenueServiceImpl implements RevenueService{
    private DailyRevenueRepository dailyRevenueRepository;
    private UserRepository userRepository;

    public RevenueServiceImpl(DailyRevenueRepository dailyRevenueRepository, UserRepository userRepository){
        this.dailyRevenueRepository = dailyRevenueRepository;
        this.userRepository = userRepository;
    }
    @Override
    public AggregatedRevenue calculateRevenue(long userId, String queryType) throws UnAuthorizedAccess, UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User Not Found"));
        if(user.getUserType() != UserType.BILLING)
            throw new UnAuthorizedAccess("ACCESS DENIED");
        AggregatedRevenue aggregatedRevenue = new AggregatedRevenue();
        switch(RevenueQueryType.valueOf(queryType)){
            case CURRENT_FY -> {
                aggregatedRevenue = DailyRevenueCalculationStrategy.currentFinancialYear(dailyRevenueRepository);
            }
            case PREVIOUS_FY -> {
                aggregatedRevenue = DailyRevenueCalculationStrategy.previousFinancialYear(dailyRevenueRepository);
            }
            case CURRENT_MONTH -> {
                aggregatedRevenue = DailyRevenueCalculationStrategy.currentMonth(dailyRevenueRepository);
            }
            case PREVIOUS_MONTH -> {
                aggregatedRevenue = DailyRevenueCalculationStrategy.previousMonth(dailyRevenueRepository);
            }
        }
        return aggregatedRevenue;
    }
}
