package tags;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public class EnlaceTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url;
	private String texto;
	private PageContext page;
	
	@Override
	public void setPageContext(PageContext page){
		this.page = page;
		
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public void setTexto(String texto){
		this.texto = texto;
	}
	
	public int doEndTag(){
		HttpServletResponse response = (HttpServletResponse) page.getResponse();
		String urlEncoded = response.encodeURL(url);
		try {
			page.getOut().append("<a href=\"" + urlEncoded + "\">" + texto +"</a>");
		} catch (IOException e) {
			e.printStackTrace();
			return SKIP_PAGE;
		}
	return EVAL_PAGE;
	}
 }
