package com.wenba.arris.expression.syntax.function;

import com.wenba.arris.expression.tokens.DataType;
import com.wenba.arris.expression.tokens.Valuable;

import java.math.BigDecimal;

public class Substring extends Function {

	@Override
	public int getArgumentNum() {
		return -1;
	}

	@Override
	protected Object executeFunction(Valuable[] arguments) {
		int length = arguments.length;
		if(2 == length) {
			String str = arguments[0].getValue().toString();
			int l = str.length();
			int statrs = Integer.parseInt(arguments[1].getValue().toString());
			if(statrs < 0) {
				return str.substring(0);
			}
			if(statrs > l) {
				return str.substring(l);

			}
			return str.substring(statrs);
		}else if(3 == length) {
			String str = arguments[0].getValue().toString();
			int l = str.length();
			int statrs = Integer.parseInt(arguments[1].getValue().toString());
			int ends = Integer.parseInt(arguments[2].getValue().toString());

			if(statrs < 0) {
				if(ends > (l)) {
					return str.substring(0, (l));
				}
				return str.substring(0, ends);
			}
			if(ends > (l)) {
				return str.substring(statrs, (l));
			}
			return str.substring(statrs, ends);
		}
		return null;
	}

	@Override
	public String getName() {
		return "substring";
	}

	@Override
	public DataType[] getArgumentsDataType() {
		return new DataType[]{DataType.ANY};
	}
}
