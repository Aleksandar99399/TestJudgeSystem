package softuni.livedemo.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.livedemo.models.entities.Exercise;
import softuni.livedemo.models.serviceModel.ExerciseServiceModel;
import softuni.livedemo.repositories.ExerciseRepository;
import softuni.livedemo.services.ExerciseService;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ExerciseServiceModel addEx(ExerciseServiceModel map) {
        Exercise esm=this.modelMapper
                .map(map, Exercise.class);
        return this.modelMapper.map(this.exerciseRepository.saveAndFlush(esm),ExerciseServiceModel.class);
    }

    @Override
    public ExerciseServiceModel findByName(String name) {
        return this.exerciseRepository.findByName(name)
                .map(exName->this.modelMapper
                .map(exName, ExerciseServiceModel.class))
                .orElse(null);


    }

}
