package by.epam.bicycle.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.bicycle.entity.Rent;
import by.epam.bicycle.entity.Tariff;

public class TariffUtils {
	private static Logger logger = LogManager.getLogger(TariffUtils.class);
	
	public static BigDecimal getAmountOfRent(Rent rent) {
		Tariff tariff = rent.getTariff();
		BigDecimal amount = tariff.getPrice();
		logger.debug("amount = " + amount);
		Calendar now = Calendar.getInstance();
		Calendar shouldReturn = Calendar.getInstance(); 
		
		shouldReturn.setTime(rent.getPlanFinishDateTime());
		
		if (now.after(shouldReturn)) {
			long duration  = now.getTimeInMillis() - shouldReturn.getTimeInMillis();
			logger.debug("duration = " + duration);
			BigDecimal diffsInMinutes = new BigDecimal(TimeUnit.MILLISECONDS.toMinutes(duration));
			logger.debug("diffsInMinutes = " + diffsInMinutes);
			BigDecimal rentalTime = new BigDecimal(tariff.getRentalTime() * 60);
			logger.debug("rentalTime = " + rentalTime);
			BigDecimal costOneMinutes = amount.divide(rentalTime, 2, RoundingMode.HALF_UP);
			logger.debug("costOneHours = " + costOneMinutes);
			BigDecimal addAmount = costOneMinutes.multiply(diffsInMinutes);
			logger.debug("addAmount = " + addAmount);
			amount = amount.add(addAmount);
		}
		
		return amount;
	}
}
