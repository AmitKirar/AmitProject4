package in.co.rays.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

<<<<<<< HEAD
import org.apache.log4j.Logger;

=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
import in.co.rays.bean.BaseBean;
import in.co.rays.bean.TimetableBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.CourseModel;
import in.co.rays.model.SubjectModel;
import in.co.rays.model.TimetableModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;


/**
 * Controller class to handle operations related to Timetable List such as search,
 * delete, pagination, etc.
 * <br>
 * This controller interacts with {@code TimetableModel} and performs data retrieval
 * and deletion operations. It also supports filtering and pagination logic.
 * 
 * @author Amit 
 * @version 1.0
 */

@WebServlet(name = "TimetableListCtl", urlPatterns = { "/ctl/TimetableListCtl" })
public class TimetableListCtl extends BaseCtl {
	
<<<<<<< HEAD
	Logger log = Logger.getLogger(TimetableListCtl.class);
	
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
	/**
     * Preloads the subject and course lists for filter dropdowns in the timetable list view.
     *
     * @param request the HTTP request
     */

	
	@Override
	protected void preload(HttpServletRequest request) {
<<<<<<< HEAD
		log.info("TimetableListCtl preload Method Started");
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da

		SubjectModel subjectModel = new SubjectModel();
		CourseModel courseModel = new CourseModel();

		try {
			List subjectList = subjectModel.list();
			request.setAttribute("subjectList", subjectList);

			List courseList = courseModel.list();
			request.setAttribute("courseList", courseList);

		} catch (ApplicationException e) {
			e.printStackTrace();
			return;
		}
<<<<<<< HEAD
		log.info("TimetableListCtl preload Method Ended");
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
	}
	
	/**
     * Populates the {@code TimetableBean} from HTTP request parameters.
     *
     * @param request the HTTP request
     * @return populated TimetableBean
     */

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
<<<<<<< HEAD
		log.info("TimetableListCtl populateBean Method Started");
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da

		TimetableBean bean = new TimetableBean();

		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
		bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));
		bean.setExamDate(DataUtility.getDate(request.getParameter("examDate")));
		
<<<<<<< HEAD
		log.info("TimetableListCtl populateBean Method Ended");
=======

>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
		return bean;
	}
	
	/**
     * Handles GET requests to display the timetable list.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
<<<<<<< HEAD
		log.info("TimetableListCtl doGet Method Started");
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		TimetableBean bean = (TimetableBean) populateBean(request);
		TimetableModel model = new TimetableModel();

		try {
			List<TimetableBean> list = model.search(bean, pageNo, pageSize);
			List<TimetableBean> next = model.search(bean, pageNo + 1, pageSize);

			if (list == null || list.isEmpty()) {
				ServletUtility.setErrorMessage("No record found", request);
			}

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.setBean(bean, request);
			request.setAttribute("nextListSize", next.size());

<<<<<<< HEAD
			
=======
			ServletUtility.forward(getView(), request, response);
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da

		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
		}
<<<<<<< HEAD
		
		log.info("TimetableListCtl doGet Method Ended");
		ServletUtility.forward(getView(), request, response);
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
	}
	
	/**
     * Handles POST requests for search, delete, pagination, and navigation operations.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
<<<<<<< HEAD
		log.info("TimetableListCtl doPost Method Started");
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da

		List list = null;
		List next = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		TimetableBean bean = (TimetableBean) populateBean(request);
		TimetableModel model = new TimetableModel();

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
				ServletUtility.redirect(ORSView.TIMETABLE_CTL, request, response);
				return;

			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					TimetableBean deletebean = new TimetableBean();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						model.delete(deletebean);
						ServletUtility.setSuccessMessage("Data is deleted successfully", request);
					}
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}

			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
				return;

			} else if (OP_BACK.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
				return;
			}

			list = model.search(bean, pageNo, pageSize);
			next = model.search(bean, pageNo + 1, pageSize);

			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.setBean(bean, request);
			request.setAttribute("nextListSize", next.size());

<<<<<<< HEAD
		
=======
			ServletUtility.forward(getView(), request, response);
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
			return;
		}
<<<<<<< HEAD
		
		log.info("TimetableListCtl doPost Method Ended");
		ServletUtility.forward(getView(), request, response);
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
	}
	
	/**
     * Returns the view path of the timetable list page.
     *
     * @return path of the timetable list JSP
     */

	@Override
	protected String getView() {

		return ORSView.TIMETABLE_LIST_VIEW;
	}

}
