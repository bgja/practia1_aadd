package tags;

import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.tagext.BodyTagSupport;

import model.Usuario;

public class ListarUsuariosTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	
	private List<Usuario> listaUsuarios;
	private Iterator<Usuario> ite;
	
	public void setUsuarios(List<Usuario> usuarios){
		this.listaUsuarios = usuarios;
	}
	
	public int doStartTag(){
		if(listaUsuarios.isEmpty()) return SKIP_BODY;
		ite = listaUsuarios.iterator();
		if(ite.hasNext()){
			Usuario usuario = ite.next();
			pageContext.setAttribute("usu", usuario.getUsuario());
			pageContext.setAttribute("pwd", usuario.getContrasena());
		}
		return EVAL_BODY_INCLUDE;
	}
	
	
	public int doAfterBody(){
		if(ite.hasNext()){
			Usuario usuario = ite.next();
			pageContext.setAttribute("usu", usuario.getUsuario());
			pageContext.setAttribute("pwd", usuario.getContrasena());
			return EVAL_BODY_AGAIN;
		}
		return SKIP_BODY;
	}
	

}
