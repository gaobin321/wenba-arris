package com.wenba.arris.expression.syntax.operator;

import com.wenba.arris.expression.syntax.ArgumentsMismatchException;
import com.wenba.arris.expression.tokens.DataType;
import com.wenba.arris.expression.tokens.Valuable;

import java.math.BigDecimal;


public class ModOperator extends BinaryOperator {

	public ModOperator() {
		super("MOD");
	}

	@Override
	public Object operate(Valuable[] arguments)
			throws ArgumentsMismatchException {
		Object result = null;
		Valuable a1 = arguments[0];
		Valuable a2 = arguments[1];
		if (a1.getDataType() == DataType.NUMBER
				&& a2.getDataType() == DataType.NUMBER) {
			if (a2.getNumberValue().compareTo(new BigDecimal("0")) == 0)
				throw new ArithmeticException("Divided by zero.");
			result = a1.getNumberValue().divideAndRemainder(a2.getNumberValue())[1];
		} else {
			throw new ArgumentsMismatchException(arguments, "%");
		}
		return result;
	}
}
