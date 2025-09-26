package in.co.rays.bean;

import java.util.Date;

<<<<<<< HEAD
/**
 * PatientBean is a JavaBean class that represents patient details. It contains
 * attributes like name, date of visit, mobile number, and disease.
 * <p>
 * This bean extends {@link BaseBean}.
 * </p>
 * 
 * @author Amit
 * @version 1.0
 *
 */
public class PatientBean extends BaseBean {

	/** Name of the patient */
	private String name;

	/** Date of visit of the patient */
	private Date dateofvisit;

	/** Mobile number of the patient */
	private String mobileNo;

	/** Disease of the patient */
	private String disease;

	/**
	 * Gets the name of the patient.
	 * 
	 * @return name of the patient
	 */
=======
public class PatientBean extends BaseBean {

	private String name;
	private Date dateofvisit;
	private String mobileNo;
	private String disease;

>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
	public String getName() {
		return name;
	}

<<<<<<< HEAD
	/**
	 * Sets the name of the patient.
	 * 
	 * @param name name of the patient
	 */
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
	public void setName(String name) {
		this.name = name;
	}

<<<<<<< HEAD
	/**
	 * Gets the date of visit.
	 * 
	 * @return date of visit
	 */
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
	public Date getDateofvisit() {
		return dateofvisit;
	}

<<<<<<< HEAD
	/**
	 * Sets the date of visit.
	 * 
	 * @param dateofvisit date of visit
	 */
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
	public void setDateofvisit(Date dateofvisit) {
		this.dateofvisit = dateofvisit;
	}

<<<<<<< HEAD
	/**
	 * Gets the mobile number of the patient.
	 * 
	 * @return mobile number
	 */
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
	public String getMobileNo() {
		return mobileNo;
	}

<<<<<<< HEAD
	/**
	 * Sets the mobile number of the patient.
	 * 
	 * @param mobileNo mobile number
	 */
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

<<<<<<< HEAD
	/**
	 * Gets the disease of the patient.
	 * 
	 * @return disease
	 */
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
	public String getDisease() {
		return disease;
	}

<<<<<<< HEAD
	/**
	 * Sets the disease of the patient.
	 * 
	 * @param disease disease
	 */
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
	public void setDisease(String disease) {
		this.disease = disease;
	}

<<<<<<< HEAD
	/**
	 * Gets the key for this bean.
	 * 
	 * @return disease as key
	 */
	@Override
	public String getkey() {
		return disease;
	}

	/**
	 * Gets the value for this bean.
	 * 
	 * @return disease as value
	 */
	@Override
	public String getValue() {
		return disease;
	}

}
=======
	@Override
	public String getkey() {

		return disease;
	}

	@Override
	public String getValue() {

		return disease;
	}

}
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
