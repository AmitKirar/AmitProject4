package in.co.rays.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.PatientBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.PatientModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * PatientCtl Servlet Controller. Handles patient form requests for add, update,
 * delete and validation.
 * 
 * author Amit version 1.0
 */
@WebServlet(name = "PatientCtl", urlPatterns = { "/ctl/PatientCtl" })
public class PatientCtl extends BaseCtl {

	Logger log = Logger.getLogger(UserCtl.class);
	
	
	
	@Override
	protected void preload(HttpServletRequest request) throws Exception {
		
		
		 HashMap<String, String> diseaseMap = new HashMap<String, String>();
         diseaseMap.put("Diabetes", "Diabetes");
         diseaseMap.put("Hypertension", "Hypertension");
         diseaseMap.put("Asthma", "Asthma");
         diseaseMap.put("Tuberculosis", "Tuberculosis");
         diseaseMap.put("Malaria", "Malaria");
         diseaseMap.put("Dengue", "Dengue");
         diseaseMap.put("Cancer", "Cancer");
         diseaseMap.put("Arthritis", "Arthritis");
         diseaseMap.put("HIV/AIDS", "HIV/AIDS");
         diseaseMap.put("Heart Disease", "Heart Disease");

         request.setAttribute("diseaseMap", diseaseMap);
	}

	/**
	 * Validates patient form input fields.
	 * 
	 * @param request HTTP request
	 * @return true if valid, false otherwise
	 */

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.info("PatientCtl validate Method Started");

		boolean isValid = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "name"));
			isValid = false;
		} else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", "Invalid Name");
			isValid = false;
		}

		if (DataValidator.isNull(request.getParameter("disease"))) {
			request.setAttribute("disease", PropertyReader.getValue("error.require", "desease"));
			isValid = false;
		}

		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "mobileNo"));
			isValid = false;
		} else if (!DataValidator.isPhoneLength(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "Mobile No must have 10 digits");
			isValid = false;
		} else if (!DataValidator.isPhoneNo(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "Invalid Mobile No");
			isValid = false;
		}

		if (DataValidator.isNull(request.getParameter("dateofvisit"))) {
			request.setAttribute("dateOfVisit", PropertyReader.getValue("error.require", "dateofvisit"));
			isValid = false;
		} else if (!DataValidator.isDate(request.getParameter("dateofvisit"))) {
			request.setAttribute("dateofvisit", PropertyReader.getValue("error.date", "dateofvisit"));
			isValid = false;
		}

		log.info("PatientCtl validate Method Ended");
		return isValid;
	}

	/**
	 * Populates PatientBean with request parameters.
	 * 
	 * @param request HTTP request
	 * @return populated bean
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.info("PatientCtl populateBean Method Started");

		PatientBean bean = new PatientBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDisease(DataUtility.getString(request.getParameter("disease")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setDateofvisit(DataUtility.getDate(request.getParameter("dateofvisit")));

		populateDTO(bean, request);

		log.info("PatientCtl populateBean Method Ended");
		return bean;
	}

	/**
	 * Handles HTTP GET requests. Loads patient data for given id and forwards to
	 * view.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("PatientCtl doGet Method Started");

		PatientModel model = new PatientModel();
		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {
			try {
				PatientBean bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				return;
			}
		}

		log.info("PatientCtl doGet Method Ended");
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Handles HTTP POST requests. Performs save, update, reset, and cancel
	 * operations.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("PatientCtl doPost Method Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		PatientModel model = new PatientModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {
			PatientBean bean = (PatientBean) populateBean(request);

			try {
				model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data Saved Successfully", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				return;
			} catch (Exception e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Login Id already exists", request);
			}

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.PATIENT_CTL, request, response);
			return;
		}

		else if (OP_UPDATE.equalsIgnoreCase(op)) {
			PatientBean bean = (PatientBean) populateBean(request);

			try {
				model.update(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data updated Successfully", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				return;
			} catch (Exception e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Login Id already exists", request);
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.PATIENT_LIST_CTL, request, response);
			return;

		}

		log.info("PatientCtl doPost Method Ended");
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Returns the view page for patient.
	 */

	@Override
	protected String getView() {
		return ORSView.PATIENT_VIEW;
	}
}
