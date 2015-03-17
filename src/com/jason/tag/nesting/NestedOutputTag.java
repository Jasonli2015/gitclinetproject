package com.jason.tag.nesting;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.beanutils.PropertyUtils;

public class NestedOutputTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	private String property = null;

	public void setProperty(String property) {
		this.property = property;
	}

	public int doEndTag() throws JspException {
		WithTag parent = (WithTag) getParent();
		if (parent == null)
			throw new JspException("Can not find parent Tag ");
		try {
			Object propertyValue = PropertyUtils.getProperty(parent.getValue(),property);
			parent.setOutput(propertyValue);
		} catch (Exception e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}
}