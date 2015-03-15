package com.jason.tag.cycle;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.beanutils.PropertyUtils;

public class ElementOutputTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	
	private Object propertyValue = null;

	public Object getPropertyValue() {
		return propertyValue;
	}

	public void setProperty(String property) throws JspException {
		
		WithCollectionTag parent = (WithCollectionTag) getParent();
		if (parent==null) {
			throw new JspException("parent tag is null");
		}
		// 判断上层tag中是否存在该属性名称，如果存在，取得属性值，否则报错
		try {
			propertyValue = PropertyUtils.getProperty(parent.getElement(), property);
		} catch (Exception e) {
			throw new JspException("property is null");
		}
	}
	
	// 把值打印到jsp页面
	@Override
	public int doEndTag() throws JspException {

		try {
			pageContext.getOut().print(propertyValue);
		} catch (IOException e) {
			throw new JspException(e);
		}
		
		return EVAL_PAGE;
	}	
	
}
