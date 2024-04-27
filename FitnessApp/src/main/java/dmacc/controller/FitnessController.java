package dmacc.controller;

import dmacc.beans.Client;
import dmacc.beans.Exercise;
import dmacc.beans.Nutrition;
import dmacc.beans.WeightBMI;
import dmacc.repository.ExerciseRepository;
import dmacc.repository.NutritionRepository;
import dmacc.repository.WeightBMIRepository;
import dmacc.repository.ClientRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FitnessController {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private NutritionRepository nutritionRepository;
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired 
    private WeightBMIRepository weightBMIRepository;

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
        exercise.setType(exerciseDetails.getType());
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
    
    @GetMapping("/addClient")
    public String showAddClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "addClient"; 
    }

    @PostMapping("/addClient")
    public String addClient(@ModelAttribute Client client) {
        clientRepository.save(client);
        return "redirect:/clientList";
    }

    @GetMapping("/editClient/{id}")
    public String showEditClientForm(@PathVariable Long id, Model model) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + id));
        model.addAttribute("client", client);
        return "editClient";
    }

    @PostMapping("/editClient/{id}")
    public String editClient(@PathVariable Long id, @ModelAttribute Client clientDetails) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + id));
        client.setName(clientDetails.getName());
        client.setEmail(clientDetails.getEmail());
        client.setPhoneNumber(clientDetails.getPhoneNumber());
        clientRepository.save(client);
        return "redirect:/clientList";
    }

    @GetMapping("/clientList")
    public String showClientList(Model model) {
        model.addAttribute("clients", clientRepository.findAll());
        return "clientList";
    }

    @PostMapping("/deleteClient/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
        return "redirect:/clientList";
    }
    
    @GetMapping("/addClientExercise")
    public String showAddClientExerciseForm(Model model) {
        // Retrieve all clients from the repository
        List<Client> clients = clientRepository.findAll();
        
        // Pass the list of clients and a new Exercise object to the model
        model.addAttribute("clients", clients);
        model.addAttribute("exercise", new Exercise());
        
        return "addClientExercise";
    }

    @PostMapping("/addClientExercise")
    public String addClientExercise(@RequestParam Long clientId, @ModelAttribute Exercise exercise) {
        // Retrieve the client by id from the repository
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + clientId));
        // Set the client for the exercise
        exercise.setClient(client);
        // Save the exercise to the database
        exerciseRepository.save(exercise);
        // Redirect to the index page
        return "redirect:/";
    }


    @GetMapping("/clientExerciseList/{clientId}")
    public String showClientExerciseList(@PathVariable Long clientId, Model model) {
        // Retrieve the client by id from the repository
        Client client = clientRepository.findById(clientId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + clientId));
        // Pass the client object to the model
        model.addAttribute("client", client);
        return "clientExerciseList";
    }
    
    @PostMapping("/deleteClientExercise/{id}")
    public String deleteClientExercise(@PathVariable Long id) {
        // Get the client ID of the exercise before deleting it
        Long clientId = exerciseRepository.findById(id)
                                           .map(exercise -> exercise.getClient().getId())
                                           .orElse(null);
        
        exerciseRepository.deleteById(id);
        
        // Redirect back to the client's exercise list
        if (clientId != null) {
            return "redirect:/clientExerciseList/" + clientId;
        } else {
            // Handle error case where client ID is null
            return "redirect:/clientExerciseList";
        }
    }

    @GetMapping("/addClientNutrition")
    public String showAddClientNutritionForm(Model model) {
        // Retrieve all clients from the repository
        List<Client> clients = clientRepository.findAll();
        
        // Pass the list of clients and a new Nutrition object to the model
        model.addAttribute("clients", clients);
        model.addAttribute("nutrition", new Nutrition());
        
        return "addClientNutrition";
    }

    @PostMapping("/addClientNutrition")
    public String addClientNutrition(@RequestParam Long clientId, @ModelAttribute Nutrition nutrition) {
        // Retrieve the client by id from the repository
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + clientId));
        // Set the client for the nutrition
        nutrition.setClient(client);
        // Save the nutrition to the database
        nutritionRepository.save(nutrition);
        // Redirect to the index page
        return "redirect:/";
    }

    @GetMapping("/clientNutritionList/{clientId}")
    public String showClientNutritionList(@PathVariable Long clientId, Model model) {
        // Retrieve the client by id from the repository
        Client client = clientRepository.findById(clientId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + clientId));
        // Pass the client object to the model
        model.addAttribute("client", client);
        return "clientNutritionList";
    }

    @PostMapping("/deleteClientNutrition/{id}")
    public String deleteClientNutrition(@PathVariable Long id) {
        // Get the client ID of the nutrition before deleting it
        Long clientId = nutritionRepository.findById(id)
                                           .map(nutrition -> nutrition.getClient().getId())
                                           .orElse(null);
        
        nutritionRepository.deleteById(id);
        
        // Redirect back to the client's nutrition list
        if (clientId != null) {
            return "redirect:/clientNutritionList/" + clientId;
        } else {
            // Handle error case where client ID is null
            return "redirect:/clientNutritionList";
        }
    }
    
    @GetMapping("/addWeightHeight")
    public String showWeightHeightForm(Model model) {
        model.addAttribute("weightBMI", new WeightBMI());
        return "addWeightHeight";
    }

    @PostMapping("/addWeightHeight")
    public String addWeightHeight(WeightBMI weightBMI) {
        weightBMIRepository.save(weightBMI);
        return "redirect:/";
    }
    
    @GetMapping("/weightHeightList")
    public String showWeightHeightList(Model model) {
        model.addAttribute("weightBMI", weightBMIRepository.findAll());
        return "weightHeightList";
    }
    
    @GetMapping("/addClientWeightHeight")
    public String showAddClientWeightHeightForm(Model model) {
        // Retrieve all clients from the repository
        List<Client> clients = clientRepository.findAll();
        
        // Pass the list of clients and a new WeightBMI object to the model
        model.addAttribute("clients", clients);
        model.addAttribute("weightBMI", new WeightBMI());
        
        return "addClientWeightHeight";
    }
    
    @PostMapping("/addClientWeightHeight")
    public String addClientWeightHeight(@RequestParam Long clientId, @ModelAttribute WeightBMI weightBMI) {
        // Retrieve the client by id from the repository
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + clientId));
        // Set the client for the weight and height entry
        weightBMI.setClient(client);
        // Save the weight and height entry to the database
        weightBMIRepository.save(weightBMI);
        // Redirect to the index page
        return "redirect:/";
    }

    @GetMapping("/clientWeightHeightList/{clientId}")
    public String showClientWeightHeightList(@PathVariable Long clientId, Model model) {
        // Retrieve the client by id from the repository
        Client client = clientRepository.findById(clientId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + clientId));
        // Pass the client object to the model
        model.addAttribute("client", client);
        return "clientWeightHeightList";
    }
    
    @PostMapping("/deleteClientWeightHeight/{id}")
    public String deleteClientWeightHeight(@PathVariable Long id) {
        // Get the client ID of the weightBMI before deleting it
        Long clientId = weightBMIRepository.findById(id)
                                           .map(weightBMI -> weightBMI.getClient().getId())
                                           .orElse(null);
        
        weightBMIRepository.deleteById(id);
        
        // Redirect back to the client's weightBMI list
        if (clientId != null) {
            return "redirect:/clientWeightHeightList/" + clientId;
        } else {
            // Handle error case where client ID is null
            return "redirect:/clientWeightHeightList";
        }
    }
    
    @PostMapping("/deleteWeightHeight/{id}")
    public String deleteWeightHeight(@PathVariable Long id) {
        weightBMIRepository.deleteById(id);
        return "redirect:/weightHeightList";
    }
    
    @GetMapping("/workouts")
    public String showWorkoutsPage(Model model) {
        return "workouts";
    }

    @GetMapping("/progress")
    public String showProgressPage(Model model) {
        List<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        return "progress";
    }
    
    @GetMapping("api/clientWeightHeightList/{clientId}")
    public ResponseEntity<List<WeightBMI>> weightHeightApiController(@PathVariable Long clientId) {
        // Retrieve the client by id from the repository
        Client client = clientRepository.findById(clientId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + clientId));
        // Retrieve the list of WeightBMI objects for the specific client
        List<WeightBMI> weightBMIs = weightBMIRepository.findByClient(client);
        // Return the list of WeightBMI objects in JSON format
        return ResponseEntity.ok(weightBMIs);
    }

    
//    @GetMapping("/weightHeightList/{clientId}")
//    public ResponseEntity<List<WeightBMI>> showWeightHeightList(@PathVariable Long clientId) {
//        Client client = clientRepository.findById(clientId)
//            .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));
//        List<WeightBMI> weightBMIs = weightBMIRepository.findById(client);
//        return ResponseEntity.ok(weightBMIs);
//    }

  
}