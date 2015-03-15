package com.jason.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Tag支持El表达式
 * */
public class ELTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private Object name = null;

	public void setName(Object name) {
		this.name = name;
	}

	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			out.print("你好! " + name);
		} catch (Exception e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;

	}
}
