
/**
 * JUnit tests for Fraction
 * @author Robert Wroblewski
 */
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.rules.ExpectedException;

public class TestFraction 
{
	
	@Before
	public void setUp()
	{

	}
	
	
	@Test
	public void zeroDenominator()
	{
	    assertThrows(ArithmeticException.class, () -> new CandidateFraction(1, 0), "Zero is not a valid Denominator");
	}
	
	@Test
	public void zeroNumerator()
	{
		Duration d = Duration.ofSeconds(1);
		assertTimeoutPreemptively(d, () -> new CandidateFraction(0, 5), "Numerator of 0 should be valid. Check if denominator is 0 after reduction");
	}
	
	@Test
	public void validFraction()
	{
		Fraction f = new CandidateFraction(1, 2);
	}
	
	@Test
	public void reduction()
	{
		Fraction f = new CandidateFraction(2, 4);
		assertEquals(1, f.getNumerator(), "2/4 reduced should have numerator equal to 1");
		assertEquals(2, f.getDenominator(), "2/4 reduces should have denominator equal to 2");
	}
	
	@Test
	public void greaterThanSignedIntNumeratorAddition()
	{
		Fraction f1 = new CandidateFraction(((int)Math.pow(2, 31))-1, 4);
		Fraction f2 = new CandidateFraction(1,4);
		assertThrows(ArithmeticException.class, () -> f1.add(f2), "Numerator is too large to hold signed int");
	}
	
	@Test
	public void greaterThanSignedIntDenominatorAddition()
	{
		Fraction f1 = new CandidateFraction(4, (int)Math.pow(2, 30)-1);
		Fraction f2 = new CandidateFraction(1, (int)Math.pow(3, 19));
		assertThrows(ArithmeticException.class, () -> f1.add(f2), "Denominator is too large to hold signed int");
		
	}
	
	@Test
	public void improperFraction()
	{
		Fraction f = new CandidateFraction(6,4);
		assertTrue(f.getNumerator() > f.getDenominator());
		
	}
	
	@Test
	public void improperFractionReduction()
	{
		Fraction f = new CandidateFraction(6,4);
		assertEquals(f.getNumerator(), 3);
		assertEquals(f.getDenominator(), 2);
	}
	
	@Test
	public void negNumeratorWithReduction() 
	{
		Fraction f = new CandidateFraction(-2,4);
		assertEquals(f.getNumerator(), -1);
		assertEquals(f.getDenominator(),2);

	}
		
	@Test
	public void LessThanSignedIntNumeratorAddition()
	{
		Fraction f1 = new CandidateFraction(((int)Math.pow(2, 31))-1, 4);
		Fraction f2 = new CandidateFraction(1,4);
		assertThrows(ArithmeticException.class, () -> f1.add(f2), "Numerator is too small to hold signed int");
	}
	
	@Test
	public void LessThanSignedIntNegDenominatorAddition()
	{
		Fraction f1 = new CandidateFraction(4, -((int)Math.pow(2, 30)-1));
		Fraction f2 = new CandidateFraction(1, -((int)Math.pow(3, 19)));
		assertThrows(ArithmeticException.class, () -> f1.add(f2), "Denominator is too small to hold signed int");
		
	}
}
