package softuni.livedemo.services;

import softuni.livedemo.models.serviceModel.ExerciseServiceModel;

public interface ExerciseService {
    ExerciseServiceModel addEx(ExerciseServiceModel map);

    ExerciseServiceModel findByName(String name);


}
