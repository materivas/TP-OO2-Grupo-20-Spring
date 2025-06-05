package com.oo2.grupo20.helpers;

public class ViewRouteHelper {
	/**** Views ****/
	//HOME
	public final static String INDEX = "home/index";
	public final static String HELLO = "home/hello";

	//EMPLEADO
	public final static String EMPLEADO_INDEX = "empleado/index";
	public final static String EMPLEADO_INDEX2 = "empleado/index2";
	public final static String EMPLEADO_FORM = "empleado/form";
	public final static String EMPLEADO_NEW = "empleado/new";
	public final static String EMPLEADO_UPDATE = "empleado/update";
	public static final String EMPLEADO_DETAIL = "empleado/detail";

	//ESTABLECIMIENTO
	public final static String ESTABLECIMIENTO_INDEX = "establecimiento/index";
	public final static String ESTABLECIMIENTO_NEW = "establecimiento/new";
	public final static String ESTABLECIMIENTO_UPDATE = "establecimiento/update";
	public final static String ESTABLECIMIENTO_PARTIAL_VIEW = "establecimiento/partialEstablecimientoView";
	public static final String ESTABLECIMIENTO_DETAIL = "establecimiento/detail";
	
	//ESPECIALIDAD
	
	public final static String ESPECIALIDAD_INDEX = "especialidad/index";
    public final static String ESPECIALIDAD_INDEX2 = "especialidad/index2";
    public final static String ESPECIALIDAD_NEW = "especialidad/new";
    public final static String ESPECIALIDAD_UPDATE = "especialidad/update";
    public final static String ESPECIALIDAD_ROOT = "/especialidad/index";
    public static final String ESPECIALIDAD_DETAIL = "especialidad/detail";
	
	//LOGIN
	public final static String USER_LOGIN = "user/login";
	public final static String USER_LOGOUT = "user/logout";

	/**** Redirects ****/
	public final static String ROUTE = "/index";
	public final static String DEGREE_ROOT = "/degrees/";
	public final static String PERSON_ROOT = "/person";
	public final static String EMPLEADO_ROOT = "/empleado/index";
	public final static String ESTABLECIMIENTO_ROOT = "/establecimiento/index";
	
}
