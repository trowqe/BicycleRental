package by.bokshic.bicycle.utils;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

import by.bokshic.bicycle.entity.Rent;
import by.bokshic.bicycle.entity.Tariff;

public class TariffUtilsTest {
	@Test 
	 public void shouldReturnAmountOfRent() {
		BigDecimal expected = new BigDecimal(3); 
		
		Rent rent = new Rent();
		Tariff tariff = new Tariff();
		tariff.setRentalTime(1);
		tariff.setPrice(new BigDecimal(3));
		rent.setTariff(tariff);
		rent.setCreateDateTime(2018, 1, 1, 11, 0);
		
		BigDecimal actual = TariffUtils.getAmountOfRent(rent);
		Assert.assertEquals(expected, actual);
	 }
}
