package dmacc.controller;

import dmacc.beans.Exercise;
import dmacc.beans.Nutrition;
import dmacc.repository.ExerciseRepository;
import dmacc.repository.NutritionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FitnessController {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private NutritionRepository nutritionRepository;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/addExercise")
    public String showExerciseForm(Model model) {
        model.addAttribute("exercise", new Exercise());
        return "addExercise";
    }

    @PostMapping("/addExercise")
    public String addExercise(@ModelAttribute Exercise exercise) {
        exerciseRepository.save(exercise);
        return "redirect:/exerciseList";
    }

    @GetMapping("/editExercise/{id}")
    public String showEditExerciseForm(@PathVariable Long id, Model model) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid exercise Id:" + id));
        model.addAttribute("exercise", exercise);
        return "editExercise";
    }

    @PostMapping("/editExercise/{id}")
    public String editExercise(@PathVariable Long id, @ModelAttribute Exercise exerciseDetails) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid exercise Id:" + id));
        exercise.setName(exerciseDetails.getName());
        exercise.setDescription(exerciseDetails.getDescription());
        exercise.setReps(exerciseDetails.getReps());
        exercise.setSets(exerciseDetails.getSets());
        exerciseRepository.save(exercise);
        return "redirect:/exerciseList";
    }

    @GetMapping("/exerciseList")
    public String showExerciseList(Model model) {
        model.addAttribute("exercises", exerciseRepository.findAll());
        return "exerciseList";
    }

    @GetMapping("/addNutrition")
    public String showNutritionForm(Model model) {
        model.addAttribute("nutrition", new Nutrition());
        return "addNutrition";
    }

    @PostMapping("/addNutrition")
    public String addNutrition(@ModelAttribute Nutrition nutrition) {
        nutritionRepository.save(nutrition);
        return "redirect:/nutritionList";
    }

    @GetMapping("/editNutrition/{id}")
    public String showEditNutritionForm(@PathVariable Long id, Model model) {
        Nutrition nutrition = nutritionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid nutrition Id:" + id));
        model.addAttribute("nutrition", nutrition);
        return "editNutrition";
    }

    @PostMapping("/editNutrition/{id}")
    public String editNutrition(@PathVariable Long id, @ModelAttribute Nutrition nutritionDetails) {
        Nutrition nutrition = nutritionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid nutrition Id:" + id));
        nutrition.setName(nutritionDetails.getName());
        nutrition.setDescription(nutritionDetails.getDescription());
        nutrition.setCalories(nutritionDetails.getCalories());
        nutrition.setProteinGrams(nutritionDetails.getProteinGrams());
        nutrition.setCarbsGrams(nutritionDetails.getCarbsGrams());
        nutrition.setFatGrams(nutritionDetails.getFatGrams());
        nutritionRepository.save(nutrition);
        return "redirect:/nutritionList";
    }

    @GetMapping("/nutritionList")
    public String showNutritionList(Model model) {
        model.addAttribute("nutritions", nutritionRepository.findAll());
        return "nutritionList";
    }
    
    @PostMapping("/deleteNutrition/{id}")
    public String deleteNutrition(@PathVariable Long id) {
        nutritionRepository.deleteById(id);
        return "redirect:/nutritionList";
    }
    
    @PostMapping("/deleteExercise/{id}")
    public String deleteExercise(@PathVariable Long id) {
        exerciseRepository.deleteById(id);
        return "redirect:/exerciseList";
    }
    
    @GetMapping("/workouts")
    public String showWorkoutsPage(Model model) {
        return "workouts";
    }
}