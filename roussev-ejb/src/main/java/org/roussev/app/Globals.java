package org.roussev.app;

/**
 * This class contains all project constants
 * <p>
 * 
 * @author Atanas Roussev
 */
public class Globals {

	/**
	 * The ear name of the J2EE application
	 */
	public static final String EAR_NAME = "ejb3-maven-1.0";

	/**
	 * The JTA persistent unit name used within ejb container
	 * <p>
	 * persistence.xml:
	 * <p>
	 * <persistence-unit name="RoussevPU" transaction-type="JTA">.
	 */
	public static final String JTA_PERSISTENT_UNIT = "RoussevPU";

	/** 
	 * The RESOURCE_LOCAL persistent unit name used out-of-container (unit testing)
	 * <p>
	 * persistence.xml:
	 * <persistence-unit name="RoussevTestPU" transaction-type="RESOURCE_LOCAL">.
	 */
	public static final String LOCAL_PERSISTENT_UNIT = "RoussevTestPU";

}
