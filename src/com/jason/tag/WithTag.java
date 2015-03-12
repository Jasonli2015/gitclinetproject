package com.jason.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

public class WithTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	private Object value = null;
	private Object output = null;

	public void setOutput(Object output) {
		this.output = output;
	}

	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) throws JspException {
		/*
		 * ExpressionEvaluatorManager.evaluate 有五个参数。
		 * 第一个表示tag的名字，在取el表达式出错时使用。一般和属性名字相同。
		 * 第二个要求字符串，通常简单调用输入对象的toString方法。
		 * 第三个是类，通常用Object.class。
		 * 第四个用this即可。
		 * 第五个是pageContext变量。
		 */
		this.value = ExpressionEvaluatorManager.evaluate("value",
				value.toString(), Object.class, this, pageContext);
	}

	public int doStartTag() {
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().print(output);
		} catch (IOException e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}
}