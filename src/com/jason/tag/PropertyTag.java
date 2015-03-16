/**
 * @author Jason
 *
 * @time 2015-3-11
 *
 * @version V1.0
 */
package com.jason.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author Jason
 *
 * description: 带属性的tag
 */
public class PropertyTag extends TagSupport {
	
	private static final long serialVersionUID = 1L;
	private String value;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int doStartTag() throws JspException {		
		
		try {
			//输出到Jsp页面
			JspWriter out = pageContext.getOut();
			out.print("Hello "+value+"! ");
		} catch (IOException e) {
			throw new JspTagException("doStartTag of OutputTag" + e.getMessage()); 
		}
		//EVAL_PAGE 表示tag已处理完毕，返回jsp页面
		return EVAL_PAGE;
	}
	
	@Override
	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().write("Hello world!");
		} catch (IOException e) {
			throw new JspTagException("doEndTag of OutputTag" + e.getMessage()); 
		}
		//EVAL_PAGE 表示tag已处理完毕，返回jsp页面
		return EVAL_PAGE;
	}	
	
}
