package com.jason.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

/**
 * Tag支持El表达式
 * */
public class ELTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private Object name = null;

	public void setName(Object name) throws JspException {
		/*
		 * ExpressionEvaluatorManager.evaluate 有五个参数。
		 * 第一个表示tag的名字，在取el表达式出错时使用。一般和属性名字相同。
		 * 第二个要求字符串，通常简单调用输入对象的toString方法。
		 * 第三个是类，通常用Object.class。
		 * 第四个用this即可。
		 * 第五个是pageContext变量。
		 */
		this.name = ExpressionEvaluatorManager.evaluate("name",
				name.toString(), Object.class, this, pageContext);
	}

	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			out.print("Hello! " + name);
		} catch (Exception e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;

	}
}
