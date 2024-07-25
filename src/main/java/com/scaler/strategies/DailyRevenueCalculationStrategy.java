package com.scaler.strategies;

import com.scaler.models.AggregatedRevenue;
import com.scaler.models.DailyRevenue;
import com.scaler.repositories.DailyRevenueRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DailyRevenueCalculationStrategy {
    public static AggregatedRevenue currentFinancialYear(DailyRevenueRepository dailyRevenueRepository){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);

        // Set start date to January 1st of current year
        cal.set(currentYear, Calendar.JANUARY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date startDate = cal.getTime();

        // Set end date to December 31st of current year
        cal.set(currentYear, Calendar.DECEMBER, 31, 23, 59, 59);
        cal.set(Calendar.MILLISECOND, 999);
        Date endDate = cal.getTime();

        List<DailyRevenue> dailyRevenues = dailyRevenueRepository.getDailyRevenueBetweenDates(startDate,endDate);
        double revenueFromFoodSales=0;
        double totalGst=0;
        double totalServiceCharge=0;
        double totalRevenue=0;
        for(DailyRevenue revenue:dailyRevenues){
            revenueFromFoodSales += revenue.getRevenueFromFoodSales();
            totalGst += revenue.getTotalGst();
            totalServiceCharge += revenue.getTotalServiceCharge();
        }
        totalRevenue = revenueFromFoodSales
                + totalGst
                + totalServiceCharge;

        AggregatedRevenue aggregatedRevenue = new AggregatedRevenue();
        aggregatedRevenue.setTotalRevenue(totalRevenue);
        aggregatedRevenue.setRevenueFromFoodSales(revenueFromFoodSales);
        aggregatedRevenue.setTotalGst(totalGst);
        aggregatedRevenue.setTotalServiceCharge(totalServiceCharge);
        aggregatedRevenue.setFromDate(startDate);
        aggregatedRevenue.setToDate(endDate);
        return aggregatedRevenue;
    }
    public static AggregatedRevenue previousFinancialYear(DailyRevenueRepository dailyRevenueRepository){
        Calendar cal = Calendar.getInstance();
        int lastYear = cal.get(Calendar.YEAR) - 1;

        // Set start date to January 1st of last year
        cal.set(lastYear, Calendar.JANUARY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date startDate = cal.getTime();

        // Set end date to December 31st of last year
        cal.set(lastYear, Calendar.DECEMBER, 31, 23, 59, 59);
        cal.set(Calendar.MILLISECOND, 999);
        Date endDate = cal.getTime();

        List<DailyRevenue> dailyRevenues = dailyRevenueRepository.getDailyRevenueBetweenDates(startDate,endDate);
        double revenueFromFoodSales=0;
        double totalGst=0;
        double totalServiceCharge=0;
        double totalRevenue=0;
        for(DailyRevenue revenue:dailyRevenues){
            revenueFromFoodSales += revenue.getRevenueFromFoodSales();
            totalGst += revenue.getTotalGst();
            totalServiceCharge += revenue.getTotalServiceCharge();
        }
        totalRevenue = revenueFromFoodSales
                + totalGst
                + totalServiceCharge;

        AggregatedRevenue aggregatedRevenue = new AggregatedRevenue();
        aggregatedRevenue.setTotalRevenue(totalRevenue);
        aggregatedRevenue.setRevenueFromFoodSales(revenueFromFoodSales);
        aggregatedRevenue.setTotalGst(totalGst);
        aggregatedRevenue.setTotalServiceCharge(totalServiceCharge);
        aggregatedRevenue.setFromDate(startDate);
        aggregatedRevenue.setToDate(endDate);
        return aggregatedRevenue;
    }

    public static AggregatedRevenue currentMonth(DailyRevenueRepository dailyRevenueRepository){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH);

        // Set start date to the first day of the current month
        cal.set(currentYear, currentMonth, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date startDate = cal.getTime();

        // Set end date to the last day of the current month
        cal.set(currentYear, currentMonth, cal.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
        cal.set(Calendar.MILLISECOND, 999);
        Date endDate = cal.getTime();

        List<DailyRevenue> dailyRevenues = dailyRevenueRepository.getDailyRevenueBetweenDates(startDate,endDate);
        double revenueFromFoodSales=0;
        double totalGst=0;
        double totalServiceCharge=0;
        double totalRevenue=0;
        for(DailyRevenue revenue:dailyRevenues){
            revenueFromFoodSales += revenue.getRevenueFromFoodSales();
            totalGst += revenue.getTotalGst();
            totalServiceCharge += revenue.getTotalServiceCharge();
        }
        totalRevenue = revenueFromFoodSales
                + totalGst
                + totalServiceCharge;

        AggregatedRevenue aggregatedRevenue = new AggregatedRevenue();
        aggregatedRevenue.setTotalRevenue(totalRevenue);
        aggregatedRevenue.setRevenueFromFoodSales(revenueFromFoodSales);
        aggregatedRevenue.setTotalGst(totalGst);
        aggregatedRevenue.setTotalServiceCharge(totalServiceCharge);
        aggregatedRevenue.setFromDate(startDate);
        aggregatedRevenue.setToDate(endDate);
        return aggregatedRevenue;
    }

    public static AggregatedRevenue previousMonth(DailyRevenueRepository dailyRevenueRepository){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1); // Move to previous month

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);

        // Set start date to the first day of the previous month
        cal.set(year, month, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date startDate = cal.getTime();

        // Set end date to the last day of the previous month
        cal.set(year, month, cal.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
        cal.set(Calendar.MILLISECOND, 999);
        Date endDate = cal.getTime();

        List<DailyRevenue> dailyRevenues = dailyRevenueRepository.getDailyRevenueBetweenDates(startDate,endDate);
        double revenueFromFoodSales=0;
        double totalGst=0;
        double totalServiceCharge=0;
        double totalRevenue=0;
        for(DailyRevenue revenue:dailyRevenues){
            revenueFromFoodSales += revenue.getRevenueFromFoodSales();
            totalGst += revenue.getTotalGst();
            totalServiceCharge += revenue.getTotalServiceCharge();
        }
        totalRevenue = revenueFromFoodSales
                + totalGst
                + totalServiceCharge;

        AggregatedRevenue aggregatedRevenue = new AggregatedRevenue();
        aggregatedRevenue.setTotalRevenue(totalRevenue);
        aggregatedRevenue.setRevenueFromFoodSales(revenueFromFoodSales);
        aggregatedRevenue.setTotalGst(totalGst);
        aggregatedRevenue.setTotalServiceCharge(totalServiceCharge);
        aggregatedRevenue.setFromDate(startDate);
        aggregatedRevenue.setToDate(endDate);
        return aggregatedRevenue;
    }

}
