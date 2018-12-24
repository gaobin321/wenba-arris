package com.wenba.arris.expression.syntax.function;

import com.wenba.arris.expression.tokens.DataType;
import com.wenba.arris.expression.tokens.Valuable;

public class EndsWith extends Function {

	@Override
	public int getArgumentNum() {
		return 2;
	}

	@Override
	protected Object executeFunction(Valuable[] arguments) {
		Object str = arguments[0].getValue();
		Object string = arguments[1].getValue();
		if(string.toString().endsWith(str.toString()))
			return true;
		return false;
	}

	@Override
	public String getName() {
		return "endsWith";
	}

	@Override
	public DataType[] getArgumentsDataType() {
		return new DataType[]{DataType.ANY,DataType.ANY};
	}
}
