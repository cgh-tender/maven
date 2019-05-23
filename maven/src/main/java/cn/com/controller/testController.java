package cn.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @see : testController
 * @desc : TODO
 * @author : cgh
 * @date : 2019-04-26 13:02
 */
@Controller
@RequestMapping("test")
public class testController {

    @RequestMapping("/sys")
    public void sysData(){
        System.out.println("Hello World!");
    }
    
}
