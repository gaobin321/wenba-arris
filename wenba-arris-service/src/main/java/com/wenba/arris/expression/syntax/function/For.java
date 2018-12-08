package com.wenba.arris.expression.syntax.function;

import com.wenba.arris.expression.Expression;
import com.wenba.arris.expression.ExpressionFactory;
import com.wenba.arris.expression.tokens.DataType;
import com.wenba.arris.expression.tokens.Valuable;

import java.util.List;

public class For extends Function {

	@Override
	public int getArgumentNum() {
		return -1;
	}

	@Override
	protected Object executeFunction(Valuable[] arguments) {
		ExpressionFactory factory = ExpressionFactory.getInstance();
		int i = Integer.parseInt(arguments[0].getValue().toString());
		String  expression = arguments[1].getStringValue();
		Expression exp = factory.getExpression(expression);
		Object value = null;
		for(int j = 0; j < i; j++) {
			exp.lexicalAnalysis();
			List<Valuable> resList= exp.evaluate();
			if(null != resList && resList.size() > 0) {
				for(Valuable result : resList) {
					value = result.getValue();
				}
			}
			return value;
		}
		return null;
	}

	@Override
	public String getName() {
		return "for";
	}

	@Override
	public DataType[] getArgumentsDataType() {
		return new DataType[]{DataType.ANY};
	}
}
