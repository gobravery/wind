package cp.csscis.ctrl;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import cp.csscis.common.TUser;
import cp.csscis.common.Utils;
import cp.csscis.xml.JaxbReadXml;

@Controller
@RequestMapping("/ix")
//@SessionAttributes("tu")
public class test1 {
	@Autowired
	public Utils u;
	@RequestMapping(value="/index") //��������ǰ̨��login����  
    private String index(){
		System.out.println("ix/index");
        return "index";  
          
    }
	@RequestMapping("/login") //��������ǰ̨��login����  
	public  @ResponseBody String hello(
            @RequestParam(value = "username", required = false)String username,  
            @RequestParam(value = "password", required = false)String d ,
            String out
            ){

		String content=u.out();
		System.out.println("my out:"+out);
        return "Hello "+username+",Your password is: "+d+";content:"+content;  
          
    }
	@RequestMapping(value = "/test", method = RequestMethod.POST, consumes = "application/json") //��������ǰ̨��login����  @RequestBody
	public  @ResponseBody String test(@RequestBody TUser t,HttpServletResponse re){

		String content=t.getUsername();
		System.out.println(content);
		return content;  
    }
	@RequestMapping("/say/{name}") //��������ǰ̨��login����  
	public  @ResponseBody String say(
			@PathVariable String name
            ){
		System.out.println("say:"+name);
		
        return name;
          
    }
	@RequestMapping("/to") //��������ǰ̨��login����  
	public  @ResponseBody String to() throws Exception{
		JaxbReadXml.to();
        return "ok!";
          
    }
	@RequestMapping(value = "/out")
	public void out(OutputStream os) throws IOException{
	       os.write("my is out".getBytes());
	       os.flush();
	}
	//@ModelAttribute("tu")
	@RequestMapping("/info") //��������ǰ̨��login����  
	public  @ResponseBody TUser info(){
		TUser tu=new TUser();
		tu.setPassword("info");
		tu.setUsername("csscis");
		//sa(tu);
        return tu;
          
    }
	@RequestMapping("/infohead") //��������ǰ̨��login����  
	public  ResponseEntity<TUser>  infohead(@ModelAttribute("tu") TUser tu){
//		TUser tu=new TUser();
//		tu.setPassword("asdfasfdas");
//		tu.setUsername("csscis");
		HttpHeaders headers = new HttpHeaders();  
        headers.setContentType(MediaType.TEXT_PLAIN);  
        return new ResponseEntity<TUser>(tu,  
                headers, HttpStatus.ACCEPTED); 
          
    }
	@ModelAttribute("tu")
	public TUser sa(TUser tu){
		if(tu==null || tu.getPassword()==null){
			
			tu=new TUser();
			tu.setPassword("sa");
			tu.setUsername("csscis");
		}
		System.out.println("sa:"+tu.getPassword());
		return tu;
    }
	@RequestMapping("/gb") //��������ǰ̨��login����  
	public  @ResponseBody TUser gb(TUser sd,@ModelAttribute("tu") TUser tu){
        return tu;
    }
	@RequestMapping("/gb2") //��������ǰ̨��login����  
	public  @ResponseBody TUser gb2(ModelMap mm,SessionStatus  s){
		TUser tu = (TUser)mm.get("tu");
		if(tu!=null){
			tu.setPassword("555555");
			//s.setComplete();
		}
        return tu;
          
    }
	@RequestMapping("/getname") //��������ǰ̨��login����  
	public  ModelAndView getName(TUser sd,@ModelAttribute("tu") TUser tu){
		ModelAndView mv=new ModelAndView("test");
		
		mv.addObject("username",tu.getUsername());
        return mv;
    }
	@RequestMapping("/getname3") //��������ǰ̨��login����  
	public  void getName(HttpServletResponse response,HttpServletRequest request) throws Exception{
			String name="asdf";
			request.setAttribute("username",name);
			request.getRequestDispatcher("/jsp/test.jsp").forward(request, response);
    }
}
