package softuni.livedemo.web;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.livedemo.models.binding.RoleAddBindingModel;
import softuni.livedemo.services.RoleService;
import softuni.livedemo.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/roles")
public class RolesController {

    private final RoleService roleService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public RolesController(RoleService roleService, UserService userService, ModelMapper modelMapper) {
        this.roleService = roleService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public ModelAndView add(ModelAndView modelAndView){

        modelAndView.addObject("usernames",this.userService.findAllUsernames());
        modelAndView.setViewName("role-add");
        return modelAndView;
    }

    @PostMapping("/add")
    public String addConfirm(@ModelAttribute("roleAddBindingModel")
                             RoleAddBindingModel roleAddBindingModel){

        //TODO validate

        this.userService
                .addRoleToUser(roleAddBindingModel.getUsername(),
                                roleAddBindingModel.getRole());
        return "redirect:/";
    }
}
