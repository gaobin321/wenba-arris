package com.wenba.arris.expression.syntax.operator;

import com.wenba.arris.expression.syntax.ArgumentsMismatchException;
import com.wenba.arris.expression.tokens.DataType;
import com.wenba.arris.expression.tokens.Valuable;

import java.math.BigDecimal;


public class NegativeOperator extends UnaryOperator {

	public NegativeOperator() {
		super("NEGATIVE");
	}

	@Override
	public Object operate(Valuable[] arguments)
			throws ArgumentsMismatchException {
		Object result = null;
		Valuable argument = arguments[0];
		if (argument.getDataType() == DataType.NUMBER) {
			result = new BigDecimal("0").subtract(argument.getNumberValue());
		} else {
			throw new ArgumentsMismatchException(arguments, "-");
		}
		return result;
	}

}
