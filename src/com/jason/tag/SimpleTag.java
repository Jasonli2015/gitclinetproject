/**
 * @author Jason
 *
 * @time 2015-3-12
 *
 * @version V1.0
 */
package com.jason.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author Jason
 *
 * description: 简单tag
 */
public class SimpleTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	
	//当jsp引擎碰到tag标签的开头时，doStartTag被调用， 因为简单的tag没有body，所以此方法将返回 SKIP_BODY。
	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().write("Hello world! ");
		} catch (IOException e) {
			throw new JspTagException("doStartTag of OutputTag" + e.getMessage()); 
		}
		return SKIP_BODY;
	}
	
	//当jsp引擎碰到tag标签的结尾时，doEndTag被调用，如果余下的页面还 要被计算，那它将返回EVAL_PAGE,否则将会返回SKIP_PAGE。
	@Override
	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().write("Hello Jason!");
		} catch (IOException e) {
			throw new JspTagException("doEndTag of OutputTag" + e.getMessage()); 
		}
		return EVAL_PAGE;
	}	

}
