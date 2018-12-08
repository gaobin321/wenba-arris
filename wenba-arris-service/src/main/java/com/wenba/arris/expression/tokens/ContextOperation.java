package com.wenba.arris.expression.tokens;

/**
 * 上下文操作
 * @author shanxuecheng
 *
 */
public enum ContextOperation {

	/**
	 * if条件
	 */
	IF_CONDITION,
	如果_CONDITION,
	
	/**
	 * else条件
	 */
	ELSE_CONDITION,
	那么_CONDITION,

	/**
	 * 新建上下文
	 */
	NEW_CONTEXT,

	/**
	 * 上下文结束
	 */
	END_CONTEXT,
	
	/**
	 * if语句结束
	 */
	END_IF
}
