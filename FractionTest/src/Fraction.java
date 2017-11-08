
/**
 * Preliminary Interface for fraction class 
 * Write your tests to this interface
 * @author gosnat
 *
 */
public interface Fraction {
	//Any constructor for a concrete Fraction class should
	//throw an ArithmeticException in the event of a zero denominator
	
	/**
	 * @return the numerator of this fraction
	 */
	public int getNumerator();
	
	/**
	 * @return the denominator of this fraction
	 */
	public int getDenominator();
	
	/**
	 * @param other The fraction to add on to this one
	 * @return new fraction that is the result of adding this and other
	 * @throws ArithmeticException if the resulting sum overflows a 32-bit integer
	 */
	public Fraction add(Fraction other) throws ArithmeticException;

}
