package myssm.controller;

import myssm.bean.User;
import myssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class MyController {


    @Autowired
    UserService userService;

    @RequestMapping("/register")
    public String register(User user , MultipartFile header, Model model) throws IOException {

        String classpath = this.getClass().getResource("/").getPath().replaceFirst("/", "");
        String webroot = classpath.replaceAll("WEB-INF/classes", "");
        String path = webroot + "/WEB-INF/upload";
        File directory = new File(path);
        if (!directory.exists()){
            directory.mkdir();
        }

        String name = header.getOriginalFilename();
        File file = new File(directory, name);
        header.transferTo(file);
        user.setHeaderimg(name);

        int i = userService.addUser(user);
        if (i>0){
            model.addAttribute("user1",user);
            return "WEB-INF/view/userdetail.jsp";
        }
        else {
            return null;
        }


    }


    @RequestMapping("/login")
    public String login(String username ,String password, HttpServletRequest request) throws IOException {
        User user1 = userService.login(username,password);
        if (user1 == null){
            request.setAttribute("msg","用户名或密码错误");
            return "login.jsp";
        }else {
            request.getSession().setAttribute("user1",user1);
            return "index.jsp";
        }
    }

    @RequestMapping("/userdetail")
    public String userdetail(Model model,String uid){
        User user1 = userService.findUserByUid(uid);
        model.addAttribute(user1);
        return "WEB-INF/view/userdetail.jsp";
    }


}
