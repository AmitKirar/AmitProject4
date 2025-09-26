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
import in.co.rays.bean.RoleBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.RoleModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * RoleListCtl is a controller class that handles the listing, searching,
 * pagination, and deletion of Role records.
 *
 * <p>
 * View: {@code ORSView.ROLE_LIST_VIEW}
 * </p>
 * <p>
 * URL: {@code /RoleListCtl}
 * </p>
 *
 * @author Amit
 * @version 1.0
 * @see in.co.rays.bean.RoleBean
 * @see in.co.rays.model.RoleModel
 */

@WebServlet(name = "RoleListCtl", urlPatterns = { "/ctl/RoleListCtl" })
public class RoleListCtl extends BaseCtl {

<<<<<<< HEAD
	Logger log = Logger.getLogger(RoleListCtl.class);

=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
	/**
	 * Preloads the role list data and sets it as a request attribute.
	 *
	 * @param request the HTTP request
	 */

	@Override
	protected void preload(HttpServletRequest request) {
<<<<<<< HEAD

		log.info("RoleListCtl preload Method Started");

=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
		RoleModel roleModel = new RoleModel();

		try {
			List roleList = roleModel.list();
			request.setAttribute("roleList", roleList);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return;
		}
<<<<<<< HEAD
		log.info("RoleListCtl preload Method Ended");
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
	}

	/**
	 * Populates the RoleBean using request parameters.
	 *
	 * @param request the HTTP request
	 * @return populated RoleBean
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
<<<<<<< HEAD
		log.info("RoleListCtl preload Method Started");
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da

		RoleBean bean = new RoleBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setId(DataUtility.getLong(request.getParameter("roleId")));

<<<<<<< HEAD
		log.info("RoleListCtl preload Method Ended");
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
		return bean;
	}

	/**
	 * Handles HTTP GET requests for displaying the initial list view with
	 * pagination.
	 *
	 * @param request  the HTTP request
	 * @param response the HTTP response
	 * @throws IOException
	 * @throws ServletException
	 */

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
<<<<<<< HEAD
		log.info("RoleListCtl doGet Method Started");
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		RoleBean bean = (RoleBean) populateBean(request);
		RoleModel model = new RoleModel();

		try {
			List<RoleBean> list = model.search(bean, pageNo, pageSize);
			List<RoleBean> next = model.search(bean, pageNo + 1, pageSize);

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
			return;
		}
<<<<<<< HEAD

		log.info("RoleListCtl doGet Method Ended");

		ServletUtility.forward(getView(), request, response);
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
	}

	/**
	 * Handles HTTP POST requests including search, next, previous, new, delete,
	 * reset, and back.
	 *
	 * @param request  the HTTP request
	 * @param response the HTTP response
	 * @throws ServletException
	 * @throws IOException
	 */

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
<<<<<<< HEAD
		log.info("RoleListCtl doPost Method Started");
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da

		List list = null;
		List next = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		RoleBean bean = (RoleBean) populateBean(request);
		RoleModel model = new RoleModel();

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
				ServletUtility.redirect(ORSView.USER_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					RoleBean deletebean = new RoleBean();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						model.delete(deletebean);
						ServletUtility.setSuccessMessage("Data is deleted successfully", request);
					}
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
				return;
			} else if (OP_BACK.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
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
		
		log.info("RoleListCtl doPost Method Ended");
		ServletUtility.forward(getView(), request, response);
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
	}

	/**
	 * Returns the path of the Role list view.
	 *
	 * @return view path
	 */

	@Override
	protected String getView() {

		return ORSView.ROLE_LIST_VIEW;
	}

}
