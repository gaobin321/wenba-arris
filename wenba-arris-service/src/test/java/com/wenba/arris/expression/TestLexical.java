package com.wenba.arris.expression;

import com.alibaba.fastjson.TypeReference;
import com.wenba.arris.common.utils.http.*;
import com.wenba.arris.common.utils.json.FastJsonUtil;
import com.wenba.arris.expression.lexical.LexicalAnalyzer;
import com.wenba.arris.expression.lexical.LexicalException;
import com.wenba.arris.expression.tokens.DataType;
import com.wenba.arris.expression.tokens.TerminalToken;
import com.wenba.arris.expression.tokens.Valuable;
import com.wenba.arris.expression.utils.ValueUtil;
import junit.framework.TestCase;

import java.math.BigDecimal;
import java.util.*;




public class TestLexical extends TestCase {
	private ExpressionFactory factory = ExpressionFactory.getInstance();

	public void testDemo() {
		//创建表达式
		/**
		*
		* 	lexical : 词法
		* 	syntax : 语法
		* 	tokens : 符号
		*
		* 	类Expression的实例表示一个表达式,
		* 	可通过Expression的构造函数创建或者使用ExpressionFactory创建
		*
		* 	表达式由语句组成,每个语句必须由分号结尾.
		*
		* 	定义表达式时可包含一个语句或多个语句,解析结果是被执行的最后一个语句的结果
		*
		* 	Expression有三个构造函数,可分别接受参数 String, InputStream, Reader
		*
		* 	1. 使用构造函数创建
		* 		Expression exp = new Expression("a=1; a+a*100;");
		*
		* 	2.使用ExpressionFactory创建
		*		ExpressionFactory factory = ExpressionFactory.getInstance();
		*		Expression exp = factory.getExpression("a=1; a+a*100;");
		*
		*	3.执行表达式
		*		通过调用Expression的evaluate()方法执行表达式
		*		ExpressionFactory factory = ExpressionFactory.getInstance();
		*		Expression exp = factory.getExpression("a=1; a+a*100;");
		*		//词法解析
		*		exp.lexicalAnalysis();
		*		//执行
		*		Valuable result = exp.evaluate();
		*
		*		注意:执行evaluate()的前提是已经执行词法分析,否则抛出异常
		*			或者直接调用Expression的reParseAndEvaluate()方法
		*			reParseAndEvaluate()方法每次都重新执行词法分析
		*
		*		表达式的执行结果以接口Valuable表示,Valuable提供如下方法:
		*			public DataType getDataType();
		*			public int getIndex();
		*			public BigDecimal getNumberValue();
		*			public String getStringValue();
		*			public Character getCharValue();
		*			public Calender getDateValue();
		*			public Boolean getBooleanValue();
		*			public Object getValue();
		*/

		Expression exp = new Expression("a=1; a+a*100;");

		exp.lexicalAnalysis();
		/*List<TerminalToken> terminalTokens = exp.lexicalAnalysis();
		if(terminalTokens != null && terminalTokens.size() > 0) {
			for (TerminalToken tt : terminalTokens) {
				System.out.println(tt.toString());
			}
		}*/
		List<Valuable> resList = exp.evaluate();
		if(null != resList && resList.size() > 0) {
			for(Valuable result : resList) {
				Object value = result.getValue();
				System.out.println(value);
			}
		}

	}

	public void testDemo1() {
		ExpressionFactory factory = ExpressionFactory.getInstance();
		Expression exp = factory.getExpression("a=b=1; a+b*100;");

		exp.lexicalAnalysis();

		List<Valuable> resList = exp.evaluate();
		if(null != resList && resList.size() > 0) {
			for(Valuable result : resList) {

				BigDecimal numberValue = ValueUtil.getNumberValue(result);

				System.out.println(numberValue);

				DataType dataType = result.getDataType();
				System.out.println(dataType);
				Object value = result.getValue();
				System.out.println(value);
			}
		}

	}

	public void testChinaExp1() {
		//Expression exp = factory.getExpression("if(0 > 1) 0; if(4 > 3) 4; else 3; endif else 1; endif");
		//Expression exp = factory.getExpression("如果(0 > 1) 那么 2; 如果(4 > 3) 那么 4; 否则 3; endif 否则 1; endif");
		Expression exp = factory.getExpression("如果(0 > 1) 那么 2; 如果(4 > 3) 那么 4; 否则 3; endif 否则 1; 如果(6 > 5) 那么 6; 否则 5; endif endif");

		//Expression exp = factory.getExpression("if(0 > 1) 2; else 1; if(4 > 3) 4; else 3; endif endif");
		//Expression exp = factory.getExpression("如果(2 > 1) 那么 2; 如果(4 > 3) 那么 4; 否则 5; endif 否则 1; endif");
		//Expression exp = factory.getExpression("如果(0 > 1) 那么 2; 如果(4 > 3) 那么 4; 否则 3; endif 否则 1; endif");
		//Expression exp = factory.getExpression("如果(0 > 1) 那么 2; 否则 1; 如果(4 > 3) 那么 4; 否则 3; endif endif");
		exp.lexicalAnalysis();
		List<Valuable> resList = exp.evaluate();
		if(null != resList && resList.size() > 0) {
			for(Valuable result : resList) {
				Object value = result.getValue();
				System.out.println("结果:" + value);
			}
		}
	}

	public void testChinaExp2() {
		//Expression exp = factory.getExpression("如果(2 > 1) 那么 2; endif");
		Expression exp = factory.getExpression("如果(\"#一级渠道\" == \"DSP\") 那么 (\"S,低\"); endif");
		//Expression exp = factory.getExpression("ifthen((\"#一级渠道\" == \"DSP\"),\"S,低\");");
		//Expression exp = factory.getExpression("如果(2 > 1) 那么 2; 否则(1); endif");
		exp.lexicalAnalysis();
		exp.initVariable("\"#一级渠道\"","DSP");
		List<Valuable> resList = exp.evaluate();
		if(null != resList && resList.size() > 0) {
			for(Valuable result : resList) {
				Object value = result.getValue();
				System.out.println("结果:" + value);
			}
		}
	}

	public void testFor() {
		//表达式
		Expression exp = factory.getExpression("for(3);a=1;a+3;");
		//参数赋值
		//exp.initVariable();
		//词法分析
		exp.lexicalAnalysis();
		//执行表达式

		List<Valuable> resList = exp.evaluate();
		if(null != resList && resList.size() > 0) {
			for(Valuable result : resList) {
				//获取执行表达式结果
				Object value = result.getValue();
				System.out.println("结果: " + value.toString());
			}
		}
	}

	public void testIfthen() {
		//表达式
		Expression exp = factory.getExpression("ifthen(2 > 2,2)");
		//参数赋值
		//exp.initVariable();
		//词法分析
		exp.lexicalAnalysis();
		//执行表达式

		List<Valuable> resList = exp.evaluate();
		if(null != resList && resList.size() > 0) {
			for(Valuable result : resList) {
				//获取执行表达式结果
				Object value = result.getValue();
				System.out.println("结果: " + value.toString());
			}
		}
	}

	/**
	 * 计算额外积分金额 规则如下: 订单原价金额
	 * 100以下, 不加分
	 * 100-500 加100分
	 * 500-1000 加500分
	 * 1000 以上 加1000分
	 */
	public void testInvest() {

		/*Expression exp = factory.getExpression("(pChannel == \"客服\") ? \"S+,中\" : " +
				"(pChannel == \"SEM\" && pChannel1 == \"学好网\" && pChannel2 == \"百度\" && pAddress == \"北京\") ? \"S+,高\" : " +
				"(pChannel == \"SEM\" && pChannel1 == \"学好网\" && pAddress == \"北京\") ? \"S+,高\" : " +
				"(pChannel == \"DSP\" && pChannel1 == \"朋友圈\" && pChannel2 == \"账号1\" && pAddress == \"上海\") ? \"S,中\" : " +
				"(pChannel == \"DSP\" && pChannel1 == \"朋友圈\" && pChannel2 == \"账号2\" && pAddress == \"北京\") ? \"A+,中\" : " +
				"(pChannel == \"NW对外投放\" && pChannel1 == \"公众号\" && pAddress == \"天津\") ? \"S,中\" : " +
				"(pChannel == \"NW对外投放\" && pChannel1 == \"公众号\") ? \"A,中\" : " +
				"(pChannel == \"DSP\") ? \"S,低\" : \"ERROR\"");

		exp.lexicalAnalysis();
		Valuable result = exp.evaluate();
		Object value = result.getValue();
		System.out.println(value);*/

		//表达式
		List<String> expressions = new ArrayList<String>();


		expressions.add("ifthen((pChannel == \"客服\"), \"S+,中\");");
		expressions.add("ifthen((pChannel == \"SEM\" && pChannel1 == \"学好网\" && pChannel2 == \"百度\" && pAddress == \"北京\"), \"S+,高\");");
		expressions.add("ifthen((pChannel == \"SEM\" && pChannel1 == \"学好网\" && pAddress == \"北京\"), \"S+,高\");");
		expressions.add("ifthen((pChannel == \"DSP\" && pChannel1 == \"朋友圈\" && pChannel2 == \"账号1\" && pAddress == \"上海\"), \"S,中\");");
		expressions.add("ifthen((pChannel == \"DSP\" && pChannel1 == \"朋友圈\" && pChannel2 == \"账号2\" && pAddress == \"北京\"), \"A+,中\");");
		expressions.add("ifthen((pChannel == \"NW对外投放\" && pChannel1 == \"公众号\" && pAddress == \"天津\"), \"S,中\");");
		expressions.add("ifthen((pChannel == \"NW对外投放\" && pChannel1 == \"公众号\"), \"A,中\");");
		expressions.add("ifthen((pChannel == \"DSP\"), \"S,低\");");
		expressions.add("ifthen((\"#渠道\" == \"DSP\"), \"S,低\");");


		List<Map<String,Object>> list = new ArrayList<>();

		Map<String,Object> map1 = new HashMap<>();
		Map<String,Object> map2 = new HashMap<>();
		Map<String,Object> map3 = new HashMap<>();

		//map1.put("pChannel","DSP");
		map1.put("\"#渠道\"","DSP");
		list.add(map1);

		map2.put("pChannel","NW对外投放");
		map2.put("pChannel1","公众号");
		map2.put("pAddress","天津");
		list.add(map2);

		map3.put("pChannel","SEM");
		map3.put("pChannel1","学好网");
		map3.put("pAddress","北京");
		list.add(map3);

		Expression exp;
		for(int i = 0; i < list.size(); i++) {
			if(null != expressions && expressions.size() > 0) {
				for(String s : expressions) {
					exp = factory.getExpression(s);
					exp.lexicalAnalysis();


					for(String str : list.get(i).keySet()) {
						exp.initVariable(str,list.get(i).get(str));
					}
				/*exp.initVariable("pChannel","DSP");
				exp.initVariable("pChannel","DSP");*/

					List<Valuable> resList = exp.evaluate();
					if(null != resList && resList.size() > 0) {
						for(Valuable result : resList) {
							if(null == result || "".equals(result)) {
								continue;
							}else {
								Object value = result.getValue();
								System.out.println("结果:" + value);
							}
						}
					}
				}
			}
		}




		/*exp = factory.getExpression("ifthen(pChannel == \"客服\", \"S+,中\");");
		exp = factory.getExpression("ifthen(pChannel == \"SEM\", \"S+,高\");");*/
		//Expression exp = factory.getExpression("ifthen(pChannel == \"SEM\", \"S+,高\");");
		//Expression exp = factory.getExpression("(pChannel == \"ifthen客服\") ? \"S+,中\"; : \"ERROR\";");
		//Expression exp = factory.getExpression("ifthen(2 > 1, 2);");
		/*exp.initVariable("pChannel","客服");

		exp.lexicalAnalysis();
		Valuable result = exp.evaluate();
		Object value = result.getValue();
		System.out.println(value);*/
	}


	public void testFZ() {

		//Expression exp = factory.getExpression("如果(2 > 1) 那么 2; 如果(4 > 3) 那么 4; 否则 5; endif 否则 1; endif");


		//Expression exp = factory.getExpression("如果(pChannel == \"客服\") 那么(\"S+,中\"); endif");

		/*Expression exp = factory.getExpression("(pChannel == \"客服\") ? \"S+,中\" : " +
				"(pChannel == \"SEM\" && pChannel1 == \"学好网\" && pChannel2 == \"百度\" && pAddress == \"北京\") ? \"S+,高\" : " +
				"(pChannel == \"SEM\" && pChannel1 == \"学好网\" && pAddress == \"北京\") ? \"S+,高\" : " +
				"(pChannel == \"DSP\" && pChannel1 == \"朋友圈\" && pChannel2 == \"账号1\" && pAddress == \"上海\") ? \"S,中\" : " +
				"(pChannel == \"DSP\" && pChannel1 == \"朋友圈\" && pChannel2 == \"账号2\" && pAddress == \"北京\") ? \"A+,中\" : " +
				"(pChannel == \"NW对外投放\" && pChannel1 == \"公众号\" && pAddress == \"天津\") ? \"S,中\" : " +
				"(pChannel == \"NW对外投放\" && pChannel1 == \"公众号\") ? \"A,中\" : " +
				"(pChannel == \"DSP\") ? \"S,低\" : \"ERROR\"");*/

		Expression exp = factory.getExpression("judge(pChannel == \"客服\",\"S+,中\"," +
				"judge((pChannel == \"SEM\" && pChannel1 == \"学好网\" && pChannel2 == \"百度\" && pAddress == \"北京\"),\"S+,高\"," +
				"judge((pChannel == \"SEM\" && pChannel1 == \"学好网\" && pAddress == \"北京\"),\"S+,高\"," +
				"judge((pChannel == \"DSP\" && pChannel1 == \"朋友圈\" && pChannel2 == \"账号1\" && pAddress == \"上海\"),\"S,中\"," +
				"judge((pChannel == \"DSP\" && pChannel1 == \"朋友圈\" && pChannel2 == \"账号2\" && pAddress == \"北京\"),\"A+,中\"," +
				"judge((pChannel == \"NW对外投放\" && pChannel1 == \"公众号\" && pAddress == \"天津\"),\"S,中\"," +
				"judge((pChannel == \"NW对外投放\" && pChannel1 == \"公众号\"),\"A,中\"," +
				"judge((pChannel == \"DSP\"),\"S,低\",\"Error\"))))))))");

		/*("ifthen((pChannel == \"客服\"), \"S+,中\");");
		("ifthen((pChannel == \"SEM\" && pChannel1 == \"学好网\" && pChannel2 == \"百度\" && pAddress == \"北京\"), \"S+,高\");");
		("ifthen((pChannel == \"SEM\" && pChannel1 == \"学好网\" && pAddress == \"北京\"), \"S+,高\");");
		("ifthen((pChannel == \"DSP\" && pChannel1 == \"朋友圈\" && pChannel2 == \"账号1\" && pAddress == \"上海\"), \"S,中\");");
		("ifthen((pChannel == \"DSP\" && pChannel1 == \"朋友圈\" && pChannel2 == \"账号2\" && pAddress == \"北京\"), \"A+,中\");");
		("ifthen((pChannel == \"NW对外投放\" && pChannel1 == \"公众号\" && pAddress == \"天津\"), \"S,中\");");
		("ifthen((pChannel == \"NW对外投放\" && pChannel1 == \"公众号\"), \"A,中\");");
		("ifthen((pChannel == \"DSP\"), \"S,低\");");
		("ifthen((\"#渠道\" == \"DSP\"), \"S,低\");");*/

		exp.initVariable("pChannel","NW对外投放");
		exp.initVariable("pChannel1","公众号");
		//exp.initVariable("pAddress","天津");
		exp.lexicalAnalysis();
		List<Valuable> resList = exp.evaluate();

		if(null != resList && resList.size() > 0) {
			for(Valuable result : resList) {
				Object value = result.getValue();
				System.out.println("结果: " + value.toString());
			}
		}
	}

	/**
	 *	if(Boolean) endif
	 *	或
	 *	if(Boolean) else endif
	 *
	*/
	public void testExpression() {
		//Expression exp = factory.getExpression("if(2 > 1) 2; endif");
		//Expression exp = factory.getExpression("if(2 > 1) 2; else 1; endif");


		//Expression exp = factory.getExpression("if(2 > 1) 2; else 1;");
		Expression exp = factory.getExpression("if(2 > 1) a=abs(-3); if(false) a=a+1; else a=a+2; endif a=a+1; else if(false) a=9; endif endif");
		/*Expression exp = factory.getExpression("if(2 > 1)" +
													"a=abs(-3);" +
													"if(false)" +
														"a=a+1;" +
													"else " +
														"a=a+2;" +
													"endif" +
													"a=a+1;" +
												"else" +
													"if(2 > 1)" +
														"a=9;" +
													"endif" +
												"endif");*/
		//Expression exp = factory.getExpression("if(2 > 1) 2; if(4 > 3) 4; else 5; endif else 1; endif");
		exp.lexicalAnalysis();
		List<Valuable> resList = exp.evaluate();
		if(null != resList && resList.size() > 0) {
			for(Valuable result : resList) {
				Object value = result.getValue();
				System.out.println("结果:" + value);
			}
		}
	}

	/*public void testStack() {
		Stack<String> stack = new Stack<>();
		stack.push("1");
		stack.push("2");
		stack.push("3");
		stack.push("4");
		stack.push("5");
		stack.
		//得到stack中的枚举对象
		Enumeration<String> items = stack.elements();
		while (items.hasMoreElements()) { //显示枚举（stack ） 中的所有元素
			System.out.println("栈中所有元素: " + items.nextElement() + " ");
		}
	}*/

	public void testNestExp() {
		//Expression exp = factory.getExpression("if(2 > 1) 2; endif");
		Expression exp = factory.getExpression("if(0 > 1) 2; else 1; endif");
		//Expression exp = factory.getExpression("if(0 > 1) 2; else 1;endif");
		exp.lexicalAnalysis();

		List<Valuable> resList = exp.evaluate();
		if(null != resList && resList.size() > 0) {
			for(Valuable result : resList) {
				Object value = result.getValue();
				System.out.println("结果: " + value.toString());
			}
		}

		/*Expression exp = factory.getExpression("b+2");
		exp.lexicalAnalysis();
		Valuable result = exp.evaluate();
		Object value = result.getValue();
		System.out.println("结果:" + value);*/
	}

	public void testNumber() {
		Expression expression = factory.getExpression("1 1.1 1.10 1e+2 1.1e-1");
		lexicalAnalysis(expression);
	}
	
	public void testDelimiter() {
		Expression expression = factory.getExpression("+-*/ >=<= ><,;&& ||!");
		lexicalAnalysis(expression);
	}
	
	public void testBoolean() {
		Expression expression = factory.getExpression("true false TRUE FALSE");
		lexicalAnalysis(expression);
	}
	
	public void testDate() {
		Expression expression = factory.getExpression("[2011-1-11] [2011-01-11] [2011-1-11 1:1:1] [2011-1-11 23:59:59]");
		lexicalAnalysis(expression);
	}
	
	public void testString() {
		Expression expression = factory.getExpression(" \"as\" ");
		lexicalAnalysis(expression);
	}
	
	public void testChar() {
		Expression expression = factory.getExpression(" 'a' ");
		lexicalAnalysis(expression);
	}
	
	public void testFunction() {
		Expression expression = factory.getExpression(" max abs ");
		lexicalAnalysis(expression);
	}
	
	public void testError() {
		Expression expression = factory.getExpression(" &2");
		lexicalAnalysis(expression);
	}

	//词法分析
	private void lexicalAnalysis(Expression expression) {
		LexicalAnalyzer la = new LexicalAnalyzer();
		try {
			List<TerminalToken> tokens = la.analysis(expression.getExpression(), expression.getFunctionDefinitions());
			PrintExpression.printTokens(tokens);
		} catch (LexicalException e) {
			e.printStackTrace();
		}
	}


	private static Integer num = 0;
	public void testThreadLocalDemo1() {

		Thread[] threads =  new Thread[5];

		for(int i = 0; i < 5; i++) {
			threads[i] = new Thread(() -> {
				num += 5;
				System.out.println(Thread.currentThread().getName() + " : " + num);
			},"Thread-" + i);
		}
		for(Thread thread : threads) {
			thread.start();
		}
	}


	private static final ThreadLocal<Integer> local = new ThreadLocal<Integer>(){
		protected Integer initialValue() {
			//通过initialValue方法设置默认值
			return 0;
		}
	};

	public void testThreadLocalDemo2() {

		Thread[] threads = new Thread[5];

		for(int i = 0; i < 5; i++) {
			threads[i] = new Thread(() -> {
				int num = local.get().intValue();
				num += 5;
				System.out.println(Thread.currentThread().getName() + " : " + num);
			},"Thread-" + i);
		}

		for(Thread thread : threads) {
			thread.start();
		}
	}


	public void testHttp() {
		Map<String,String> params = new HashMap<>();
		params.put("appKey","wenba-pgpt");
		params.put("appSecret","50b410184aa25ef7edcf1a7dd7cd951d");
		params.put("thingId","25");
		params.put("tagId","20");
		params.put("indexStatus","0"); //N
		params.put("name","Tina测试");	//N
		params.put("objectId","21");
		long startTime = System.currentTimeMillis();
		System.out.println("请求参数: " + params.toString());


		String url = "http://10.1.58.29:8087/api/tag/put/on";

		/*HttpParams httpParams = new HttpParams();
		httpParams.setFormByMap(params);
		HttpResult<String> http = HttpUtil.http(HttpMethod.POST, url, httpParams, 3000);
		System.out.println("返回结果: " + http.toString());*/

		String result = HttpUtils.doPostStr(url, params, 5000, 5000, "UTF-8");
		Map<String, Object> respMap = FastJsonUtil.parseObject(result,
				new TypeReference<Map<String, Object>>() {
				});
		System.out.println("返回结果: " + result);

	}

	public void testabs() {
		//Expression exp = factory.getExpression("abs(-3)");
		Expression exp = factory.getExpression("abs(0)");
		//Expression exp = factory.getExpression("abs(3)");
		exp.lexicalAnalysis();
		List<Valuable> resList = exp.evaluate();
		if(resList != null && resList.size() > 0) {
			for(Valuable result : resList) {
				Object value = result.getValue();
				System.out.println("结果: " + value.toString());
			}
		}

	}

	public void testmax() {
		//Expression exp = factory.getExpression("abs(-3)");
		Expression exp = factory.getExpression("max(0,1,1,3,-1,-2,1.1,4.1)");
		//Expression exp = factory.getExpression("abs(3)");
		exp.lexicalAnalysis();
		List<Valuable> resList = exp.evaluate();
		if(resList != null && resList.size() > 0) {
			for(Valuable result : resList) {
				Object value = result.getValue();
				System.out.println("结果: " + value.toString());
			}
		}

	}

	public void testmin() {
		//Expression exp = factory.getExpression("abs(-3)");
		Expression exp = factory.getExpression("min(0,1,1,3,-1,-2,1.1,4.1,-3.1,-3.1)");
		//Expression exp = factory.getExpression("abs(3)");
		exp.lexicalAnalysis();
		List<Valuable> resList = exp.evaluate();
		if(resList != null && resList.size() > 0) {
			for(Valuable result : resList) {
				Object value = result.getValue();
				System.out.println("结果: " + value.toString());
			}
		}

	}

	public void testjudge() {
		//表达式
		//Expression exp = factory.getExpression("judge(2 > 1,2,1)");
		Expression exp = factory.getExpression("judge(2 > 1,judge(3 > 2,3,2),1)");
		//参数赋值
		//exp.initVariable();
		//词法分析
		exp.lexicalAnalysis();
		//执行表达式

		List<Valuable> resList = exp.evaluate();
		if(null != resList && resList.size() > 0) {
			for(Valuable result : resList) {
				//获取执行表达式结果
				Object value = result.getValue();
				System.out.println("结果: " + value.toString());
			}
		}
	}

	public void testifthen() {

		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < 2; i++) {
			sb.append(i + ",");
		}

		sb.deleteCharAt(sb.length()-1);

		System.out.println("sb结果: " + sb.toString());

		//表达式
		//Expression exp = factory.getExpression("ifthen(1 > 1,2)");
		Expression exp = factory.getExpression("ifthen(2 > 1,ifthen(3 > 2,3))");
		//参数赋值
		//exp.initVariable();
		//词法分析
		exp.lexicalAnalysis();
		//执行表达式

		List<Valuable> resList = exp.evaluate();
		if(null != resList && resList.size() > 0) {
			for(Valuable result : resList) {
				//获取执行表达式结果
				if(null != result && !"".equals(result)) {
					Object value = result.getValue();
					System.out.println("结果: " + value.toString());
				}else {
					System.out.println("没有满足条件的结果!");
				}
			}
		}
	}


	public void testcontains() {
		//Expression exp = factory.getExpression("contains(1,\"你好吗?\")");
		//Expression exp = factory.getExpression("contains(\"我\",\"我,喜欢,你!\")");
		//Expression exp = factory.getExpression("contains(1,\"我,喜欢,你!\")");
		//Expression exp = factory.getExpression("contains(10,123)");
		//Expression exp = factory.getExpression("contains(1.0,\"1.0,2,3\")");
		Expression exp = factory.getExpression("[2012-03-31] > [2012-03-01]");

		exp.lexicalAnalysis();

		List<Valuable> resList = exp.evaluate();

		if(null != resList && resList.size() > 0) {
			for(Valuable result : resList) {
				Object value = result.getValue();
				System.out.println("结果: " + value.toString());
			}
		}
	}

	public void teststartEndsWith() {

		//Expression exp = factory.getExpression("startsWith(\"?\",\"你好吗?\")");
		//Expression exp = factory.getExpression("endsWith(\"?\",\"你好吗?\")");
		Expression exp = factory.getExpression("endsWith(1,\"我,喜欢,你!\")");

		exp.lexicalAnalysis();

		List<Valuable> resList = exp.evaluate();

		if(null != resList && resList.size() > 0) {
			for(Valuable result : resList) {
				Object value = result.getValue();
				System.out.println("结果: " + value.toString());
			}
		}
	}


	public void testsubstring() {

		//Expression exp = factory.getExpression("substring(\"你好吗?\",-1,100)");
		//Expression exp = factory.getExpression("substring(\"我喜欢你!\",1,3)");
		Expression exp = factory.getExpression("substring(\"你好吗?\",1)");

		exp.lexicalAnalysis();

		List<Valuable> resList = exp.evaluate();

		if(null != resList && resList.size() > 0) {
			for(Valuable result : resList) {
				Object value = result.getValue();
				System.out.println("结果: " + value.toString());
			}
		}
	}


	private static Object lock1 = new Object();
	private static Object lock2 = new Object();

	public void testdeadlock() {

		new Thread(){

			@Override
			public void run() {
				synchronized(lock1) {
					System.out.println("thread1 get lock1");
					try {
						Thread.sleep(100);
					}catch (Exception e) {
						e.printStackTrace();
					}
					synchronized (lock2){
						System.out.println("thread1 get lock2");
					}
				}
				System.out.println("thread1 end");
			}
		}.start();


		new Thread(){

			@Override
			public void run() {
				synchronized (lock2) {
					System.out.println("thread2 get lock2");
					try{
						Thread.sleep(100);
					}catch (Exception e){
						e.printStackTrace();
					}
					synchronized (lock1) {
						System.out.println("thread2 get lock1");
					}
				}
				System.out.println("thread2 end");
			}
		}.start();
	}

}
