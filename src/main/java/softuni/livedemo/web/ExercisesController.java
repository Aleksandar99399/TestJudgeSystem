package softuni.livedemo.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.livedemo.models.binding.ExerciseBindingAddModel;
import softuni.livedemo.models.serviceModel.ExerciseServiceModel;
import softuni.livedemo.services.ExerciseService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
@RequestMapping("/exercises")
public class ExercisesController {

    private final ExerciseService exerciseService;
    private final ModelMapper modelMapper;

    public ExercisesController(ExerciseService exerciseService, ModelMapper modelMapper) {
        this.exerciseService = exerciseService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add(@Valid @ModelAttribute("exerciseBindingAddModel")
                              ExerciseBindingAddModel exerciseBindingAddModel,
                      BindingResult bindingResult) {
        return "exercise-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("exerciseBindingAddModel")
                                     ExerciseBindingAddModel exerciseBindingAddModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("exerciseBindingAddModel", exerciseBindingAddModel);
            return "redirect:/exercises/add";
        } else {
            //TODO compare dates
            ExerciseServiceModel byName = this.exerciseService.findByName(exerciseBindingAddModel.getName());

            if (byName == null) {



                LocalDate before =
                        LocalDate.parse(
                                exerciseBindingAddModel.getAddedOn().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

                LocalDate after =
                        LocalDate.parse(
                                exerciseBindingAddModel.getDueDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

                LocalDate now = LocalDate.now();

                if (before.isBefore(now) && after.isAfter(now)) {

                    ExerciseServiceModel esm =
                            this.exerciseService.addEx(
                                    this.modelMapper.map(exerciseBindingAddModel, ExerciseServiceModel.class));

                    return "redirect:/";
                }
            }//else {
//                this.exerciseService.deleteExercise(byName.getName());
//            }
            redirectAttributes.addFlashAttribute("exerciseBindingAddModel", exerciseBindingAddModel);
            return "redirect:/exercises/add";

        }

    }
}

