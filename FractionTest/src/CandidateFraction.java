/**
 * A candidate implementation for the Fraction interface.  Sadly this
 * contains errors.  
 * @author gosnat
 * @author your name here
 *
 */
public class CandidateFraction implements Fraction {
	/** Numerator of the fraction */
	private int numerator;
	
	/** Denominator of the fraction */
	private int denominator;
	
	/**
	 * Creates and reduces a fraction
	 * @param numerator starting numerator
	 * @param denominator starting denominator
	 * @throws ArithmeticException if denominator is zero
	 */
	public CandidateFraction(int numerator, int denominator) throws ArithmeticException {
		if(denominator == 0) throw new ArithmeticException("Denominator can't be zero");
		this.denominator = denominator;
		this.numerator = numerator;
		this.reduce();
	}


	@Override
	public int getNumerator() {
		return numerator;
	}

	@Override
	public int getDenominator() {
		return denominator;
	}

	@Override
	public Fraction add(Fraction other) throws ArithmeticException{
		//this method will overflow with large values
		//unit tests should catch this issue
		long tempNum1 = this.numerator;
		long tempNum2 = other.getNumerator();
		long tempDen = this.denominator;
		tempNum1 *= other.getDenominator();
		tempNum2 *= this.denominator;
		tempDen *= other.getDenominator();
		if(tempDen > Integer.MAX_VALUE) {
			throw new ArithmeticException("Denominator Overflow");
		}
		if(tempNum1 + tempNum2 > Integer.MAX_VALUE) {
			throw new ArithmeticException("Numerator Overflow");
		}
		// check for negative overflow
		if(tempDen < Integer.MIN_VALUE) {
			throw new ArithmeticException("Negative Denominator Overflow");
		}
		if(tempNum1 + tempNum2 < Integer.MIN_VALUE) {
			throw new ArithmeticException("Negative Numerator Overflow");
		}
				
		CandidateFraction answer = new CandidateFraction((int)(tempNum1+tempNum2), (int) tempDen);
		return answer;
	}
	
	/**
	 * Convert fraction to reduced form
	 */
	private void reduce() {
		//this will timeout with a zero numerator
		
		//find GCD
		int a = Math.abs(numerator);
		int b = Math.abs(denominator);
	    while (a != b) { 
	        if (a > b) {
	           a = a - b; 
	        }else {
	           b = b - a; 
	        }
	    }
	    
	    //a is now the GCD
	    numerator /= a;
	    denominator /= a;
	    
	    // make negatives are reduced properly
	    if(denominator < 0) {
	    	denominator = denominator * -1;
	    	numerator = numerator * -1;
	    }
	    
	}

}
