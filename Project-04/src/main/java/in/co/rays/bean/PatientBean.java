package in.co.rays.bean;

import java.util.Date;

public class PatientBean extends BaseBean {

	private String name;
	private Date dateofvisit;
	private String mobileNo;
	private String disease;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateofvisit() {
		return dateofvisit;
	}

	public void setDateofvisit(Date dateofvisit) {
		this.dateofvisit = dateofvisit;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	@Override
	public String getkey() {

		return disease;
	}

	@Override
	public String getValue() {

		return disease;
	}

}
