package cl.subtel.model.common.security;

public class Usuarios implements java.io.Serializable {

	private static final long serialVersionUID = -3513557064077249861L;
	private int usuarioId;
	private Roles roles;
	private String usuario;
	private String clave;
	private String email;
	private Boolean bloqueado;
	private Integer intentos;
	private String rut;
	private String telefono;
	private String organismo;
	private Integer maxIntentos;
	private String nombre;
	private String apellido;

	public Usuarios() {
	}

	public Usuarios(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Usuarios(int usuarioId, Roles roles, String usuario, String clave, String email, Boolean bloqueado,
			Integer intentos, String rut, String telefono, String organismo, Integer maxIntentos, String nombre,
			String apellido) {
		this.usuarioId = usuarioId;
		this.roles = roles;
		this.usuario = usuario;
		this.clave = clave;
		this.email = email;
		this.bloqueado = bloqueado;
		this.intentos = intentos;
		this.rut = rut;
		this.telefono = telefono;
		this.organismo = organismo;
		this.maxIntentos = maxIntentos;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public int getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Roles getRoles() {
		return this.roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getBloqueado() {
		return this.bloqueado;
	}

	public void setBloqueado(Boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public Integer getIntentos() {
		return this.intentos;
	}

	public void setIntentos(Integer intentos) {
		this.intentos = intentos;
	}

	public String getRut() {
		return this.rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getOrganismo() {
		return this.organismo;
	}

	public void setOrganismo(String organismo) {
		this.organismo = organismo;
	}

	public Integer getMaxIntentos() {
		return this.maxIntentos;
	}

	public void setMaxIntentos(Integer maxIntentos) {
		this.maxIntentos = maxIntentos;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

}
