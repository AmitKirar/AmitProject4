package in.co.rays.controller;

import java.io.IOException;
import java.sql.SQLException;
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
import in.co.rays.bean.UserBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.RoleModel;
import in.co.rays.model.UserModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * Controller to handle operations for listing, searching, and deleting user
 * records.
 * <p>
 * This controller supports pagination, dynamic search by role, name, and login,
 * and provides options to add or delete users.
 * </p>
 * 
 * @author Amit
 * @version 1.0
 * 
 */

@WebServlet(name = "UserListCtl", urlPatterns = { "/ctl/UserListCtl" })
public class UserListCtl extends BaseCtl {
<<<<<<< HEAD
	Logger log = Logger.getLogger(UserListCtl.class);
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da

	/**
	 * Populates the UserBean from request parameters for search criteria.
	 *
	 * @param request the HTTP request
	 * @return populated UserBean
	 */

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
<<<<<<< HEAD

		log.info("UserListCtl populateBean Method Started");

=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
		UserBean bean = new UserBean();
		bean.setFirstname(DataUtility.getString(request.getParameter("firstName")));

		bean.setRoleId(DataUtility.getLong(request.getParameter("roleId")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
<<<<<<< HEAD

		log.info("UserListCtl populateBean Method Ended");
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
		return bean;
	}

	/**
	 * Loads role list and sets it in request scope for filter dropdown.
	 *
	 * @param request the HTTP request
	 */

	@Override
	protected void preload(HttpServletRequest request) {
<<<<<<< HEAD
		log.info("UserListCtl preload Method Started");

=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
		RoleModel roleModel = new RoleModel();
		try {
			List rolelist = roleModel.list();
			request.setAttribute("roleList", rolelist);
		} catch (Exception e) {

			e.printStackTrace();
<<<<<<< HEAD

			return;
		}
		log.info("UserListCtl preload Method Ended");
=======
		}
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da

	}

	/**
	 * Handles GET request to display initial user list with pagination.
	 *
	 * @param request  the HTTP request
	 * @param response the HTTP response
	 */

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
<<<<<<< HEAD

		log.info("UserListCtl doGet Method Started");

=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		UserBean bean = (UserBean) populateBean(request);

		UserModel model = new UserModel();

		try {
			List<UserBean> list = model.search(bean, pageNo, pageSize);
			List<UserBean> next = model.search(bean, pageNo + 1, pageSize);

			if (list == null || list.isEmpty()) {
				ServletUtility.setErrorMessage("No Record Found", request);

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
		} catch (Exception e) {

			e.printStackTrace();
		}

<<<<<<< HEAD
		log.info("UserListCtl doGet Method Ended");
		ServletUtility.forward(getView(), request, response);

=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
	}

	/**
	 * Handles POST request for searching, pagination, deletion, or navigation.
	 *
	 * @param request  the HTTP request
	 * @param response the HTTP response
	 */

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
<<<<<<< HEAD
		log.info("UserListCtl doPost Method Started");
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da

		List list = null;
		List next = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		UserBean bean = (UserBean) populateBean(request);
		UserModel model = new UserModel();

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
					UserBean deletebean = new UserBean();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						try {
							model.delete(deletebean);
						} catch (SQLException e) {

							e.printStackTrace();
						}
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

			try {
				list = model.search(bean, pageNo, pageSize);
			} catch (Exception e) {

				e.printStackTrace();
			}
			try {
				next = model.search(bean, pageNo + 1, pageSize);
			} catch (Exception e) {

				e.printStackTrace();
			}

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

		log.info("UserListCtl doPost Method Ended");
		ServletUtility.forward(getView(), request, response);
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
	}

	/**
	 * Returns the view path for User List JSP page.
	 *
	 * @return path of user list view
	 */

	@Override
	protected String getView() {

		return ORSView.USER_LIST_VIEW;
	}

}
