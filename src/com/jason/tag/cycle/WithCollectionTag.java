package com.jason.tag.cycle;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * 该Tag负责取得Collection，并遍历执行子Tag
 * */
public class WithCollectionTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	
	private Object element = null;
	
	private Collection<Object> list = null;
	
	private Iterator<Object> iterator = null;
	
	public Object getElement(){
		return element;
	}
	
	@SuppressWarnings("unchecked")
	public void setProperty(String property) throws JspException {
		//取得父tag，并得到Collection
		WithObjectTag parent = (WithObjectTag) getParent();
		if (parent==null) {
			throw new JspException("parent tag is null");
		}
		try {
			Object propertyValue = PropertyUtils.getProperty(parent.getValue(), property);
			this.list = (Collection<Object>) propertyValue;
			if (list==null) {
				throw new JspException("Collection tag is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Override
	public int doStartTag() throws JspException {
		// 设置第一个子元素，然后执行子tag
		iterator = list.iterator();
		if (iterator.hasNext()) {
			element = iterator.next();
		}

		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doAfterBody() throws JspException {
		// 如果子元素还存在，设置子元素并执行tag，否则则不执行tag
		if (iterator.hasNext()) {
			element = iterator.next();
			return EVAL_BODY_AGAIN;
		} else {
			return EVAL_PAGE;
		}
	}
	
}
