package in.co.rays.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

<<<<<<< HEAD
import org.apache.log4j.Logger;

=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
import in.co.rays.bean.BaseBean;
import in.co.rays.bean.UserBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.RecordNotFoundException;
import in.co.rays.model.UserModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * ForgetPasswordCtl handles the logic for sending the user's password to their
 * registered email if they forget it. It validates the email and interacts with
 * UserModel to perform the operation.
 * 
 * @author Amit
 * @version 1.0
 */

@WebServlet(name = "ForgetPasswordCtl", urlPatterns = { "/ForgetPasswordCtl" })
public class ForgetPasswordCtl extends BaseCtl {
<<<<<<< HEAD
	
	Logger log = Logger.getLogger(ForgetPasswordCtl.class);
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da

	/**
	 * Validates the input from the forget password form.
	 * 
	 * @param request HttpServletRequest object
	 * @return true if input is valid, false otherwise
	 */

	@Override
	protected boolean validate(HttpServletRequest request) {
<<<<<<< HEAD
		log.info("ForgetPasswordCtl validate Method Started");
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", PropertyReader.getValue("error.require", "Email Id"));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login", PropertyReader.getValue("error.email", "Login "));
			pass = false;
		}
<<<<<<< HEAD
		
		log.info("ForgetPasswordCtl validate Method Ended");
=======

>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
		return pass;
	}

	/**
	 * Populates a UserBean object with data from the request.
	 * 
	 * @param request HttpServletRequest object
	 * @return populated UserBean
	 */

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
<<<<<<< HEAD
		log.info("ForgetPasswordCtl populateBean Method Started");
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da

		UserBean bean = new UserBean();

		bean.setLogin(DataUtility.getString(request.getParameter("login")));
<<<<<<< HEAD
		
		   log.info("ForgetPasswordCtl populateBean Method Ended");
=======

>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
		return bean;
	}

	/**
	 * Displays the forget password view.
	 * 
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException
	 * @throws IOException
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
<<<<<<< HEAD
		log.info("ForgetPasswordCtl doGet Method Started");
		
		ServletUtility.forward(getView(), request, response);
		
	 	log.info("ForgetPasswordCtl doGet Method Ended");
=======
		ServletUtility.forward(getView(), request, response);
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
	}

	/**
	 * Handles the forget password request when the form is submitted. Sends
	 * password to user's registered email if found.
	 * 
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException
	 * @throws IOException
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
<<<<<<< HEAD
		log.info("ForgetPasswordCtl doPost Method Started");
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da

		String op = DataUtility.getString(request.getParameter("operation"));

		UserBean bean = (UserBean) populateBean(request);

		UserModel model = new UserModel();

		if (OP_GO.equalsIgnoreCase(op)) {
			try {
				boolean flag = model.forgetPassword(bean.getLogin());
				if (flag) {
					ServletUtility.setSuccessMessage("Password has been sent to your email id", request);
				}
			} catch (RecordNotFoundException e) {
				ServletUtility.setErrorMessage(e.getMessage(), request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
				ServletUtility.setErrorMessage("Please check your internet connection..!!", request);
			}
<<<<<<< HEAD
			
			 log.info("ForgetPasswordCtl doPost Method Ended");
=======
>>>>>>> c0449d83a871c9402a2357c7baaa3afecc4081da
			ServletUtility.forward(getView(), request, response);
		}
	}

	/**
	 * Returns the view path for the forget password page.
	 * 
	 * @return String view path
	 */

	@Override
	protected String getView() {

		return ORSView.FORGET_PASSWORD_VIEW;
	}

}
