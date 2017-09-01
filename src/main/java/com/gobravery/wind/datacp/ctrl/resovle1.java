package com.gobravery.wind.datacp.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gobravery.wind.common.XUser;

@Controller
@RequestMapping("/re")
public class resovle1 {
	@RequestMapping(value="/user/{userid}")
	public @ResponseBody XUser  queryUser(@PathVariable("userid") String userID)
	///re/user/asdfs.xml,/re/user/asdfs.json
	{
	      
	       XUser u = new XUser();
	       u.setUsername("zhaoyang");
	       u.setPassword(userID);
	       return u;

	}
}
