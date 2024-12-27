package in.laxman.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.laxman.Entity.Counsellor;
import in.laxman.binding.DashoardResponse;
import in.laxman.service.CounsellerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {

	@Autowired
	private CounsellerService counsellorsvc;

	// this is for Display login page
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("counsellor", new Counsellor());
		return "loginView";
	}
	// this is for log out the Page
	@GetMapping("/logout")
	public String logout(HttpServletRequest req,Model model) {
		HttpSession session = req.getSession(true); 
		session.invalidate();
		return "redirect:/";
	}

	// for handelLogin page
	@PostMapping("/login")
	public String handleLogin(Counsellor c, HttpServletRequest req, Model model) {
		Counsellor obj = counsellorsvc.loginCheck(c.getEmail(), c.getPwd());
		if (obj == null) {
			model.addAttribute("errMsg", "Invalid Credential");
			return "loginView";
		}
		HttpSession session = req.getSession(true);
		session.setAttribute("CID", obj.getCid());

		return "redirect:dashboard";

	}

	// this method Should return the Dashboard
	@GetMapping("/dashboard")
	public String buildDashboard(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		Object obj = session.getAttribute("CID");
		Integer cid = (Integer) obj;
		DashoardResponse dashboardInfo = counsellorsvc.getDashboardinfo(cid);
		model.addAttribute("dashboard", dashboardInfo);
		return "dashboardView";
	}

	// this is for Registration page
	@GetMapping("/register")
	public String regPage(Model model) {
		model.addAttribute("counsellor", new Counsellor());
		return "registerView";
	}

	@PostMapping("/register")
	public String handleRegissteration(Counsellor c, Model model) {
		String msg = counsellorsvc.saveCounsellor(c);
		model.addAttribute("msg", msg);
		return "registerView";
	}

	// for forgot password page
	@GetMapping("/forgot-Pwd")
	public String recoverPwdPage(Model model) {
		return "forgotPwdView";
	}

	// for password recovery

	@GetMapping("/recover-pwd")
	public String recoverPwd(@RequestParam String email, Model model) {
		boolean status = counsellorsvc.recoverPwd(email);
		if (status) {
			model.addAttribute("smg", "PWd send to your email");
		} else {
			model.addAttribute("errmsg", "Invalid Email");
		}
		return "ForgotPwdView";
	}

}
