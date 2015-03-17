package com.jason.tag.nesting;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

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
		this.value = value;
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