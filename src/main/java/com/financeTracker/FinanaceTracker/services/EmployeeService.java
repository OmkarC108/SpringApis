package com.financeTracker.FinanaceTracker.services;

import com.financeTracker.FinanaceTracker.dto.EmployeeDto;
import com.financeTracker.FinanaceTracker.entities.EmployeeEntity;
import com.financeTracker.FinanaceTracker.repositories.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    final EmployeeRepo employeeRepo;

    final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepo employeeRepo, ModelMapper modelMapper) {
        this.employeeRepo = employeeRepo;
        this.modelMapper = modelMapper;
    }

    public EmployeeDto getEmployeeById(Long id){
        EmployeeEntity employeeEntity=employeeRepo.getReferenceById(id);
        //return new EmployeeDto(employeeEntity.getId(),employeeEntity.getName(),employeeEntity.getDateOfJoining(),employeeEntity.isActive());
        return modelMapper.map(employeeEntity,EmployeeDto.class);
    }

    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity=modelMapper.map(employeeDto,EmployeeEntity.class);
        return modelMapper.map(employeeRepo.save(employeeEntity), EmployeeDto.class);
    }

    public List<EmployeeDto> getAllEmployees() {
        return employeeRepo.findAll()
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public boolean deleteEmployeeById(Long id) {
        boolean isPresent=employeeRepo.existsById(id);
        if(isPresent) employeeRepo.deleteById(id);
        return isPresent;
    }
}
