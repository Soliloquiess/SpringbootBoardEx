package aa.bb.cc;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	Dao dao;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView check(HttpServletRequest request, HttpServletResponse resp) throws Exception {
				
		List<DTO> list = dao.boardlistmain();
		
		for(int i=0; i<list.size(); i++){
			
			
			System.out.println(list.get(i).getEname());
			
		}
		
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("list", list);
		mv.setViewName("home");
		return mv;
	}
	
	@RequestMapping(value = "/autocomplete", method = RequestMethod.POST)
	// url
	public void testLhy(Locale locale, Model model, HttpServletRequest request,
			HttpServletResponse resp, DTO dto) throws IOException {
		
		String result = request.getParameter("term");
		
		List<DTO> list = dao.listAll2(result); //result값이 포함되어 있는 emp테이블의 네임을 리턴

		JSONArray ja = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			ja.add(list.get(i).getEname());
		}

		PrintWriter out = resp.getWriter();

		out.print(ja.toString());

	}
	
}