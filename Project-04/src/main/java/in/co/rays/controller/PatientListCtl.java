package in.co.rays.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

<<<<<<< HEAD
import org.apache.log4j.Logger;

=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
import in.co.rays.bean.BaseBean;
import in.co.rays.bean.PatientBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.PatientModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

<<<<<<< HEAD

/**
 * PatientListCtl Servlet Controller.
 * Handles list, search, delete and pagination operations for patients.
 *
 * author Amit
 * version 1.0
 */
@WebServlet(name = "PatientListCtl", urlPatterns = { "/ctl/PatientListCtl" })
public class PatientListCtl extends BaseCtl {

	Logger log = Logger.getLogger(UserCtl.class);

	
	 /**
     * Preloads patient data and sets disease map for dropdowns.
     */
	@Override
	protected void preload(HttpServletRequest request) {
		log.info("PatientListCtl preload Method Started");
=======
@WebServlet(name = "PatientListCtl", urlPatterns = { "/ctl/PatientListCtl" })
public class PatientListCtl extends BaseCtl {

	@Override
	protected void preload(HttpServletRequest request) {
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da

		PatientModel model = new PatientModel();
		try {
			List patientList = model.list();

			HashMap diseaseMap = new HashMap();

			Iterator it = patientList.iterator();

			while (it.hasNext()) {
				PatientBean bean = (PatientBean) it.next();

				diseaseMap.put(bean.getDisease(), bean.getDisease());

			}
			request.setAttribute("diseaseMap", diseaseMap);

		} catch (Exception e) {
			e.printStackTrace();
		}
<<<<<<< HEAD

		log.info("PatientListCtl preload Method Ended");
	}

	 /**
     * Populates PatientBean with search parameters.
     *
     * @param request HTTP request
     * @return populated PatientBean
     */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.info("PatientListCtl populateBean Method Started");

=======
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
		PatientBean bean = new PatientBean();

		bean.setDisease(DataUtility.getString(request.getParameter("disease")));
		bean.setName(DataUtility.getString(request.getParameter("name")));

		populateDTO(bean, request);
<<<<<<< HEAD

		log.info("PatientListCtl populateBean Method Ended");

		return bean;
	}
	
	 /**
     * Handles HTTP GET requests.
     * Displays first page of patient list with pagination.
     */
=======
		return bean;
	}
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
<<<<<<< HEAD
		log.info("PatientListCtl doGet Method Started");
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		PatientModel model = new PatientModel();

		PatientBean bean = (PatientBean) populateBean(request);
		try {
			List list = model.search(bean, pageNo, pageSize);
			List next = model.search(bean, pageNo + 1, pageSize);

			if (list == null || list.isEmpty()) {
				ServletUtility.setErrorMessage("No record found", request);
			}

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.setBean(bean, request);
			request.setAttribute("nextListSize", next.size());

			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
<<<<<<< HEAD

		log.info("PatientListCtl doGet Method Ended");
	}
	
	/**
     * Handles HTTP POST requests.
     * Performs search, next, previous, new, delete, reset and back operations.
     */
=======
	}
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

<<<<<<< HEAD
		log.info("PatientListCtl doPost Method Started");

=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
		List list = null;
		List next = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		PatientBean bean = (PatientBean) populateBean(request);
		PatientModel model = new PatientModel();

		String op = DataUtility.getString(request.getParameter("operation"));
		String[] ids = request.getParameterValues("ids");

		try {
			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {
				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}
			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.PATIENT_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					PatientBean deletebean = new PatientBean();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						model.delete(deletebean);
					}
					ServletUtility.setSuccessMessage("Data is deleted successfully", request);
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.PATIENT_LIST_CTL, request, response);
				return;
			} else if (OP_BACK.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.PATIENT_LIST_CTL, request, response);
				return;
			}

			list = model.search(bean, pageNo, pageSize);
			next = model.search(bean, pageNo + 1, pageSize);

			if (!OP_DELETE.equalsIgnoreCase(op)) {
				if (list == null || list.size() == 0) {
					ServletUtility.setErrorMessage("No record found ", request);
				}
			}

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.setBean(bean, request);
			request.setAttribute("nextListSize", next.size());
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
<<<<<<< HEAD

		log.info("PatientListCtl doPost Method Ended");
	}

	 /**
     * Returns the view page for patient.
     */
=======
	}

>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
	@Override
	protected String getView() {

		return ORSView.PATIENT_LIST_VIEW;
	}

}
